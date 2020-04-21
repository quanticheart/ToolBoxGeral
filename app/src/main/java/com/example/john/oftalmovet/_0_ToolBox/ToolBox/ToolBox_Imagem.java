package com.example.john.oftalmovet._0_ToolBox.ToolBox;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by John on 14/02/2018.
 */

public class ToolBox_Imagem {


    /**
     * Função para verificar se o tamanho da imagem é menor que a permitida
     * <p>
     * <p>
     * exemplo de uso
     * <p>
     * final Bitmap selectedImage = ToolBox_Imagem.Funcao_decodeUri(activity , imageUri);
     */
    public static Boolean Funcao_Verifica_Tamanho_IMG(Context context, Uri uri) throws FileNotFoundException {

        int Height_px_permitido = 150;
        int Width_px_permitido = 150;

        InputStream inputStream = context.getContentResolver().openInputStream(uri);
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, null, options);
        int imageHeight = options.outHeight;
        int imageWidth = options.outWidth;

        Log.w("Tamanhos da imagem", "Heigth = " + imageHeight + " : Width = " + imageWidth);
        if (imageHeight <= Height_px_permitido || imageWidth <= Width_px_permitido) {
            ToolBox_MSG.Funcao_Toast(context, "Imagem Muito Pequena");
            return false;
        } else {
            return true;
        }

    }


    /**
     * Função para Decodificar Uri capturada da camera ou capturada do paintwiew
     * <p>
     * <p>
     * exemplo de uso
     * <p>
     * final Bitmap selectedImage = ToolBox_Imagem.Funcao_decodeUri(activity , imageUri);
     */

    public static final int requiredSize = 200;

    public static Bitmap Funcao_decodeUri(Activity activity, Uri uri)
            throws FileNotFoundException {
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(activity.getContentResolver().openInputStream(uri), null, o);

        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;

        while (true) {
            if (width_tmp / 2 < requiredSize || height_tmp / 2 < requiredSize)
                break;
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeStream(activity.getContentResolver().openInputStream(uri), null, o2);
    }

    /**
     * Função para Codificar a imagem para String
     * <p>
     * <p>
     * exemplo de uso
     * <p>
     * String base64 = ClassGlobal.encodeToBase64(imageBitmap, Bitmap.CompressFormat.JPEG);
     */

    public static String Funcao_Imagem_encodeToBase64(Bitmap image, Bitmap.CompressFormat compressFormat) {
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
//        image.compress(compressFormat, 20, byteArrayOS);
        return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT);
    }

    /**
     * Função para Decodificar a imagem de String para Imagem Novamente
     */
    public static Bitmap Funcao_Imagem_decodeBase64(String input) {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }


    private static final String LOGTAG = "BitmapHelper";


    /**
     * Função não testadas
     */


    public static File scaleBitmapFile(File f, int WIDTH) throws IOException {
        Log.d(LOGTAG, "scaleBitmapFile to WIDTH: " + WIDTH);

        Bitmap b2 = decodeFileToScaledBitmap(f, WIDTH);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        Log.d(LOGTAG, "scaleBitmapFile compress bitmap to jpg ");
        b2.compress(Bitmap.CompressFormat.JPEG, 70, outStream);

        File scaledBitmapFile = new File(Environment.getExternalStorageDirectory()
                + File.separator + "temp.jpg");
        Log.d(LOGTAG, "scaleBitmapFile file: temp.jpg");
        scaledBitmapFile.createNewFile();
        Log.d(LOGTAG, "scaleBitmapFile file CREATED");
        //write the bytes in file
        FileOutputStream fo = new FileOutputStream(scaledBitmapFile);
        fo.write(outStream.toByteArray());
        Log.d(LOGTAG, "scaleBitmapFile file WRITTEN");
        // remember close de FileOutput
        fo.close();
        Log.d(LOGTAG, "scaleBitmapFile file CLOSED");

        return scaledBitmapFile;
    }

    public static Bitmap decodeFileToScaledBitmap(File f, int WIDTH) {
        Log.d(LOGTAG, "decodeFileToScaledBitmap to WIDTH: " + WIDTH);
        try {
            //Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, o);

            //The new size we want to scale to
            final int REQUIRED_WIDTH = WIDTH;

            final int ORIG_WIDTH = o.outWidth;
            final int ORIG_HEIGHT = o.outHeight;
            Log.d(LOGTAG, "decodeFileToScaledBitmap original width: [" + ORIG_WIDTH + "] original height: [" + ORIG_HEIGHT + "]");
            final int REQUIRED_HEIGHT = ORIG_HEIGHT / (ORIG_WIDTH / REQUIRED_WIDTH);

            //Decode with scaled height
            Log.d(LOGTAG, "decodeFileToScaledBitmap returning scaled bitmap ");
            return Bitmap.createScaledBitmap(
                    BitmapFactory.decodeFile(f.getAbsolutePath()),
                    REQUIRED_WIDTH,
                    REQUIRED_HEIGHT,
                    false);
        } catch (FileNotFoundException e) {
            Log.e(LOGTAG, "decodeFileToScaledBitmap FileNotFoundException: " + e.getMessage());
        }
        return null;
    }

    public static Bitmap scaleBitmap(Bitmap b, int WIDTH) {
        final int REQUIRED_WIDTH = WIDTH;

        final int ORIG_WIDTH = b.getWidth();
        final int ORIG_HEIGHT = b.getHeight();
        Log.d(LOGTAG, "scaleBitmap original width: [" + ORIG_WIDTH + "] original height: [" + ORIG_HEIGHT + "]");
        final int REQUIRED_HEIGHT = ORIG_HEIGHT / (ORIG_WIDTH / REQUIRED_WIDTH);
        Log.d(LOGTAG, "scaleBitmap returning scaled bitmap ");
        return Bitmap.createScaledBitmap(
                b,
                REQUIRED_WIDTH,
                REQUIRED_HEIGHT,
                false);
    }
}

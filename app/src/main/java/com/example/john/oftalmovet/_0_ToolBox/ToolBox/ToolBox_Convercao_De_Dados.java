package com.example.john.oftalmovet._0_ToolBox.ToolBox;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;

/**
 * Created by John on 16/02/2018.
 */

public class ToolBox_Convercao_De_Dados {

    /**
     * Função para converter um arquivo Drawable para um Bitmap
     * <p>
     * Exemplo de uso
     * <p>
     * Drawable d = getResources().getDrawable(R.drawable.logo);
     * Bitmap bmp = ToolBox_Convercao_De_Dados.Converter_Drawable_To_Bitmap(d , context);
     */
    public static Bitmap Funcao_Converter_Drawable_To_Bitmap(Drawable drawable, Context context) {

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        int width = (int) Funcao_Converter_Pixels_To_DP(2200, context);
        int height = (int) Funcao_Converter_Pixels_To_DP(2200, context);

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }


    /**
     * Este método converte a unidade dp em pixels equivalentes, dependendo da densidade do dispositivo
     *
     * @param dp Um valor na unidade dp (pixels independentes de densidade). O que precisamos converter em pixels
     *           contexto @param Contexto para obter recursos e métricas de exibição específicas do dispositivo
     * @return Um valor flutuante para representar px equivalente a dp dependendo da densidade do dispositivo
     *      
     */
    public static float Funcao_Converter_DP_To_Pixels(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    /**
     * Este método converte pixels específicos do dispositivo em pixels independentes de densidade. "dp"
     *
     * @param px Um valor na unidade px (pixels). O que precisamos converter em db
     *           contexto @param Contexto para obter recursos e métricas de exibição específicas do dispositivo
     * @return Um valor flutuante para representar dp equivalente ao valor px
     *      
     */
    public static float Funcao_Converter_Pixels_To_DP(float px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }

    /**
     * Outras funçoes de converção de pixel para dp e vice versa
     *      
     */
    public static float Funcao_convertPixelsToDp(float px) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return Math.round(dp);
    }

    public static float Funcao_convertDpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return Math.round(px);
    }

    //http://stackoverflow.com/questions/4605527/converting-pixels-to-dp
    //The above method results accurate method compared to below methods
    //http://stackoverflow.com/questions/8309354/formula-px-to-dp-dp-to-px-android


    private int Funcao_convertDpToPx(int dp, Context context) {
        return Math.round(dp * (context.getResources().getDisplayMetrics().xdpi / DisplayMetrics.DENSITY_DEFAULT));

    }

    private int Funcao_convertPxToDp(int px) {
        return Math.round(px / (Resources.getSystem().getDisplayMetrics().xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }



    /**
     * Este método converte String para SHA1 - Utilizado para criar HASH de senhas para banco de dados
     * Só funciona com a função "Converter_Bites_To_HEX" a cima
     */
    public static String Funcao_Converter_String_To_SHA1(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(text.getBytes("iso-8859-1"), 0, text.length());
        byte[] sha1hash = md.digest();
        return Funcao_Converter_Bites_To_HEX(sha1hash);
    }

   ///////////// complemento para a primeira função
    //////////// Este método converte Bits para Hexadecimal
    ////////////      
   /////////////
    private static String Funcao_Converter_Bites_To_HEX(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (byte b : data) {
            int halfbyte = (b >>> 4) & 0x0F;
            int two_halfs = 0;

            do {
                buf.append((0 <= halfbyte) && (halfbyte <= 9) ? (char) ('0' + halfbyte) : (char) ('a' + (halfbyte - 10)));
                halfbyte = b & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }


}

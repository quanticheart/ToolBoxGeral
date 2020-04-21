package com.example.john.oftalmovet._03_Cam_e_Sd;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;

import com.example.john.oftalmovet.R;
import com.example.john.oftalmovet._0_ToolBox.ToolBox.ToolBox_Imagem;
import com.example.john.oftalmovet._0_ToolBox.ToolBox._ToolBox_Config_Inicial;
import com.isseiaoki.simplecropview.util.Logger;
import com.isseiaoki.simplecropview.util.Utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by John on 25/03/2018.
 */

public class _03_ToolBox {

    private static Dialog dialog;
    public static Uri Uri_Camera;

    private static ExecutorService mExecutor;
    public static Bitmap.CompressFormat mCompressFormat = Bitmap.CompressFormat.JPEG;
        /**
     * Configuraçoes iniciar para abrir a camera e a galeria
     *
     * OPEN_CROPVIEW serve para chamar o intent do cropview
     * se for true , abre o cropview com a imagem seleciona ou tirada
     */
    public static int REQUEST_LOAD_IMG = 10;
    public static int REQUEST_IMAGE_CAPTURE = 20;
    public static boolean OPEN_CROPVIEW = true;

    /**
     * Configuraçoes iniciar para abrir a camera e a galeria
     *
     * OPEN_CROPVIEW serve para chamar o intent do cropview
     * se for true , abre o cropview com a imagem seleciona ou tirada
     */
    public static void ChamaModal(final Activity activity ,Uri uri_para_Camera) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        final View dialogbox = activity.getLayoutInflater().inflate(R.layout._03_dialog_activity, null);

        ImageView camera = dialogbox.findViewById(R.id.btn_camera);
        ImageView galeria = dialogbox.findViewById(R.id.btn_galeria);

        ImageView btn_close = dialogbox.findViewById(R.id.btn_close);

        builder.setView(dialogbox);
        dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();


        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                final String nome_foto = "principal.jpg";
//                final String imagem = _ToolBox_Config_Inicial.Constante_Pasta_Projeto_Imagens + nome_foto;
//
//                File Nova_Foto = new File(imagem.toLowerCase());
//
//                String CAMERA_FP_AUTHORITY = "com.example.john.oftalmovet.fileprovider";
//
//                Uri fileUri = null;
//
//                fileUri = FileProvider.getUriForFile(
//                        context,
//                        CAMERA_FP_AUTHORITY,
//                        Nova_Foto);

                Uri uri_Camera = Novo_Uri_Imagem(activity, Bitmap.CompressFormat.JPEG);

                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                takePictureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri_Camera);
                if (takePictureIntent.resolveActivity(activity.getBaseContext().getPackageManager()) != null) {
                    activity.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                    Uri_Camera = uri_Camera;
                    dialog.dismiss();
                }

            }
        });

        galeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                activity.startActivityForResult(intent, REQUEST_LOAD_IMG);
                dialog.dismiss();
            }
        });

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }


//    // AS DUAS FUNÇOES A BAIXO CHAMAM A INTENT PARA O CROPVIEW
    public static void ChamaCropView(Activity activity, Uri imageUri) {
        activity.startActivity(createIntent(activity, imageUri));
    }

    public static Intent createIntent(Activity activity, Uri uri) {
        Intent intent = new Intent(activity, _03_CropView.class); // AQUI COFIGURO QUAL CROPVIEW EU CHAMO CASO TENHA MAIS DE UM
        intent.setData(uri);
        return intent;
    }

    //AS TRES FUNÇOES A BAIXO CRIAM UMA URI COM OS DADOS DA IMAGEM E ONDE ELA SERA ARMAZENADA
    //POR PADRAO, SERA CRIADO O NOME "crop_{yyyyMMdd_HHmmss}.{extenção}
    //A EXTENÇÃO DA IMAGEM E DETERMINADA PELO TIPO DE COMPRESSÃO UTILIZADA
    //SE UTILIZAR Bitmap.CompressFormat.JPEG = RETORNA UMA IMAGEM DO TIPO .jpg
    //SE UTILIZAR Bitmap.CompressFormat.PNG = RETORNA UMA IMAGEM DO TIPO .png

    public static Uri Novo_Uri_Imagem(Activity activity, Bitmap.CompressFormat format) {

        long currentTimeMillis = System.currentTimeMillis();
        Date today = new Date(currentTimeMillis);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String title = dateFormat.format(today);
        final String dirPath = _ToolBox_Config_Inicial.Constante_Pasta_Projeto_Imagens;
//        String dirPath = getDirPath();
        String fileName = "crop_" + title + "." + getMimeType(format);
        String path = dirPath + "/" + fileName;
        File file = new File(path);
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, title);
        values.put(MediaStore.Images.Media.DISPLAY_NAME, fileName);
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/" + getMimeType(format));
        values.put(MediaStore.Images.Media.DATA, path);
        long time = currentTimeMillis / 1000;
        values.put(MediaStore.MediaColumns.DATE_ADDED, time);
        values.put(MediaStore.MediaColumns.DATE_MODIFIED, time);

        if (file.exists()) {
            values.put(MediaStore.Images.Media.SIZE, file.length());
        }

        ContentResolver resolver = activity.getContentResolver();
        Uri uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Logger.i("SaveUri = " + uri);
        return uri;
    }

    public static String getMimeType(Bitmap.CompressFormat format) {
        switch (format) {
            case JPEG:
                return "jpeg";
            case PNG:
                return "png";
        }
        return "png";
    }

    //FUNCAO RESPONSAVEL POR CARREGAR A IMAGEM SELECIONA , TIRA OU CORTADA
    public static void Carrega_Imagem_Selecionada(Activity activity , Uri outputUri) {
        final Uri uri = outputUri;
        mExecutor = Executors.newSingleThreadExecutor();
        mExecutor.submit(new _03_Inicio.Redimencionar_Imagem(activity, uri, Calcular_Metricas_Imagem(activity)));
    }

    private static int Calcular_Metricas_Imagem(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        Display display = activity.getWindowManager().getDefaultDisplay();
        display.getMetrics(metrics);
        return Math.min(Math.max(metrics.widthPixels, metrics.heightPixels), 2048);
    }

    //FUNCAO RESPONSAVEL POR REDIMENCIONAR A IMAGEM ANTES DE ADICIONAR A TELA
    //APOS REDIMENCIONAR , ESTA FUNÇÃO ADICIONA A IMAGEM A TELA

//    public static class Redimencionar_Imagem implements Runnable {
//        private Handler mHandler = new Handler(Looper.getMainLooper());
//        Context context;
//        Uri uri;
//        ImageView imageView;
//        int width;
//
//        public Redimencionar_Imagem(Context context, Uri uri, ImageView imageView, int width) {
//            this.context = context;
//            this.uri = uri;
//            this.imageView = imageView;
//            this.width = width;
//        }
//
//        @Override
//        public void run() {
//            final int exifRotation = Utils.getExifOrientation(context, uri);
//            int maxSize = Utils.getMaxSize();
//            int requestSize = Math.min(width, maxSize);
//            try {
//                final Bitmap sampledBitmap = Utils.decodeSampledBitmapFromUri(context, uri, requestSize);
//
//                //AQUI CHAMO A FUNÇÃO PARA GUARDAR A IMAGEM EM UM CHAR64 OU DECODIFICAR ELA
//                //Encode_Decode_Imagem64(sampledBitmap);
//
//                mHandler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        imageView.setImageMatrix(Utils.getMatrixFromExifOrientation(exifRotation));
//                        imageView.setImageBitmap(sampledBitmap);
//
//
////                        Glide_.with(this).load(uri).into(imageView);
////                        Picasso.with(this).load(uri).into(imageView);
//
//                    }
//                });
//            } catch (OutOfMemoryError e) {
//                e.printStackTrace();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

//    private static void Encode_Decode_Imagem64(Bitmap bitmap) {
//
//        String nova_imagem64 = ToolBox_Imagem.Funcao_Imagem_encodeToBase64(bitmap, mCompressFormat);
//
//        if (nova_imagem64.length() > 0) {
//            Bitmap bitmap2 = ToolBox_Imagem.Funcao_Imagem_decodeBase64(nova_imagem64);
//            iv_foto.setImageBitmap(bitmap2);
//        }
//
//    }
}

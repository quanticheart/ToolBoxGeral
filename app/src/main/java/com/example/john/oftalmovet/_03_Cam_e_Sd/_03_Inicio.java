package com.example.john.oftalmovet._03_Cam_e_Sd;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.john.oftalmovet.R;
import com.example.john.oftalmovet._0_ToolBox.ToolBox.ToolBox_Chama_Activity;
import com.example.john.oftalmovet._0_ToolBox.ToolBox.ToolBox_Imagem;
import com.example.john.oftalmovet._0_ToolBox.ToolBox.ToolBox_MSG;
import com.example.john.oftalmovet._0_ToolBox.ToolBox._ToolBox_Config_Inicial;
import com.isseiaoki.simplecropview.util.Logger;
import com.isseiaoki.simplecropview.util.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class _03_Inicio extends AppCompatActivity {

    private Context context;
    private Context context_activity;
    private static Activity activity;
    private Window window;
    /////////////////////////////////////////////////////////////////////////////

    /*
     *  _03_Inicio de Variaveis
     */
    private Dialog dialog;
    private static ImageView iv_foto;

    public static boolean OPEN_CROPVIEW = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._03_inicio);

        initVars();
        initActions();

    }

    private void initVars() {

        //Var padroes
        context = getApplicationContext();
        context_activity = _03_Inicio.this;
        activity = _03_Inicio.this;
        window = getWindow();
        ////////////////////////////////////////////////////////////////////////
    }

    private void initActions() {
        /*
         *  _04_Inicio de Ações da Pagina
         */


        //////////////////////////////////////////////////////////////////////////////////////
        /*
         *  Pagina de Paint simples
         *
         */
        Switch abrecropview = findViewById(R.id.sw_abre_cropview);

        Funcao_ChamaCrop(abrecropview);


        Button crop = findViewById(R.id.crop);
        ToolBox_Chama_Activity.Funcao_Chama_Activity(crop, activity, _03_CropView.class, null, false, false, "");

        Button crop2 = findViewById(R.id.crop2);
        ToolBox_Chama_Activity.Funcao_Chama_Activity(crop2, activity, _03_CropView_2.class, null, false, false, "");

        Button btn_modal = findViewById(R.id.btn_modal);
        btn_modal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _03_ToolBox.ChamaModal(activity , _03_ToolBox.Uri_Camera);
            }
        });

        iv_foto = findViewById(R.id.foto);


    }

    private void Funcao_ChamaCrop(final Switch sw) {

        sw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sw.isChecked()){
                    OPEN_CROPVIEW = true;
                } else {
                    OPEN_CROPVIEW = false;
                }
            }
        });

    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        if (resultCode == RESULT_OK && reqCode == _03_ToolBox.REQUEST_LOAD_IMG) {
            Log.w("Entrei", "Galeria");
            try {
                Uri imageUri = data.getData();
//                Bitmap selectedImage = ToolBox_Imagem.Funcao_decodeUri(activity, imageUri);
                Boolean imagem_valida = ToolBox_Imagem.Funcao_Verifica_Tamanho_IMG(context, imageUri);
                if (imagem_valida) {

                    if (OPEN_CROPVIEW) {
                        _03_ToolBox.ChamaCropView(activity , imageUri);
                    } else {
                        _03_ToolBox.Carrega_Imagem_Selecionada(activity , imageUri);
                    }

                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                ToolBox_MSG.Funcao_Toast(context, "Você não selecionou uma foto.");
            }

        } else if (resultCode == RESULT_OK && reqCode == _03_ToolBox.REQUEST_IMAGE_CAPTURE) {
            Log.w("Entrei", "Camera");

//            Uri uri = data.getData();
//            Bundle extras = data.getExtras();
//            Bitmap imageBitmap = (Bitmap) extras.get("data");
//
//            final String nome_foto = "principal.jpg";
//            final String imagem = _ToolBox_Config_Inicial.Constante_Pasta_Projeto_Imagens + nome_foto;
//            Uri imageUri_Cam = Novo_Uri_Imagem(context, Bitmap.CompressFormat.JPEG);
//            Bitmap bitmap = BitmapFactory.decodeFile(imagem);

            if (OPEN_CROPVIEW) {
                _03_ToolBox.ChamaCropView(activity , _03_ToolBox.Uri_Camera);
            } else {
                _03_ToolBox.Carrega_Imagem_Selecionada(activity , _03_ToolBox.Uri_Camera);
            }

        } else {
            ToolBox_MSG.Funcao_Toast(context, "Foto não Capturada.");
        }
        System.gc();
    }

    //FUNCAO RESPONSAVEL POR REDIMENCIONAR A IMAGEM ANTES DE ADICIONAR A TELA
    //APOS REDIMENCIONAR , ESTA FUNÇÃO ADICIONA A IMAGEM A TELA

    public static class Redimencionar_Imagem implements Runnable {
        private Handler mHandler = new Handler(Looper.getMainLooper());
        Context context;
        Uri uri;
        ImageView imageView;
        int width;

        public Redimencionar_Imagem(Context context, Uri uri, int width) {
            this.context = context;
            this.uri = uri;
            this.width = width;
        }

        @Override
        public void run() {
            final int exifRotation = Utils.getExifOrientation(context, uri);
            int maxSize = Utils.getMaxSize();
            int requestSize = Math.min(width, maxSize);
            try {
                final Bitmap sampledBitmap = Utils.decodeSampledBitmapFromUri(context, uri, requestSize);

                //AQUI CHAMO A FUNÇÃO PARA GUARDAR A IMAGEM EM UM CHAR64 OU DECODIFICAR ELA
                //Encode_Decode_Imagem64(sampledBitmap);

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iv_foto.setImageMatrix(Utils.getMatrixFromExifOrientation(exifRotation));
                        iv_foto.setImageBitmap(sampledBitmap);

//                        Glide_.with(this).load(uri).into(imageView);
//                        Picasso.with(this).load(uri).into(imageView);

                    }
                });
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onBackPressed() {
        ToolBox_Chama_Activity.Funcao_Chama_TelaPrincipal(activity);
        super.onBackPressed();
    }

}

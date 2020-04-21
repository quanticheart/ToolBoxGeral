package com.example.john.oftalmovet._0_ToolBox.ToolBox;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.example.john.oftalmovet.R;

import java.io.File;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by John on 09/12/2017.
 */

public class _ToolBox_Config_Inicial {

    //////////////////////////////////////////////////////////////////////////////////////
        /*
         *  valores iniciais para criar o caminho das pastas do projeto
         *
         */
    private static String nome_projeto_local_das_pastas = "/storage/emulated/0/";
    private static String nome_projeto_pastas = "ToolBox";// nao adicionar ' / ' nem no começo nem no fim , somente o nome da pasta a ser criada

    public static final String Constante_Pasta_Projeto_Imagens = nome_projeto_local_das_pastas + nome_projeto_pastas + "/media/Images/";
    public static final String Constante_Pasta_Projeto_Documentos = nome_projeto_local_das_pastas + nome_projeto_pastas + "/media/Documents/";


    //////////////////////////////////////////////////////////////////////////////////////
        /*
         *  Verifica as Pastas a serem criadas para o projeto
         *  como pasta para imagem , documentos ou outros que podem ser compartilhados
         *
         */
    public static void Funcao_Verifica_Pastas() {

        // cria os diretoris nessesarios para o projeto
        File Diretorio_Imagens = new File(Constante_Pasta_Projeto_Imagens);
        File Diretorio_PDF = new File(Constante_Pasta_Projeto_Documentos);

        Diretorio_Imagens.mkdirs();
        Diretorio_PDF.mkdirs();

        Log.w("Pastas Criadas", "ok");
        // create a File object for the parent directory
//        File wallpaperDirectory = new File("/sdcard/Wallpaper2/");
//
//        File wallpaperDirectory2 = new File("/storage/emulated/0/Wallpaper2/");
//        // have the object build the directory structure, if needed.
//        wallpaperDirectory2.mkdirs();

    }


    //////////////////////////////////////////////////////////////////////////////////////
        /*
         *  Função para setar a cor do Status bar Manualmente
         *
         */
    public static void Funcao_Set_StatusBar_Color(Context context, Window window) {
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // finally change the color
        window.setStatusBarColor(context.getResources().getColor(R.color.ColorSupremeBlack));
    }


    //////////////////////////////////////////////////////////////////////////////////////
        /*
         *  Função para Chamar as Permiçoes que o app Nessecita
         *
         */
    public static void Funcao_Verifica_Permicoes_Do_APP(Activity activity) {

        SharedPreferences prefs2 = activity.getSharedPreferences("Perm", MODE_PRIVATE);
        Boolean perm = prefs2.getBoolean("perm", false);

        if (!perm) {
            Manda_Permicoes(activity);

            SharedPreferences prefs = activity.getSharedPreferences("Perm", MODE_PRIVATE);
            SharedPreferences.Editor editor2 = prefs.edit();
            editor2.putBoolean("perm", true);
            editor2.commit();
        }

    }

    //////
    ////// complmento da função a cima
    ////// Aqui é Colocado todas as permiçoes que o app nessecita
    //////
    private static void Manda_Permicoes(Activity activity) {
        ActivityCompat.requestPermissions(activity,
                new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_CALENDAR,
                        Manifest.permission.CAMERA
                },
                0);
    }


    //////////////////////////////////////////////////////////////////////////////////////
        /*
         *  Função para Checar a Conexão com Internet
         *
         */

    public static boolean Funcao_ChecaConexao(Context context) {
        boolean connect;
        ConnectivityManager connMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            connect = true;
        } else {
            connect = false;
        }
        return connect;
    }



}

package com.example.john.oftalmovet._0_ToolBox.ToolBox;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;

import com.example.john.oftalmovet._00_Inicio.Inicio;

/**
 * Created by John on 29/01/2018.
 */

public class ToolBox_Chama_Activity {

    /////
    /////  Tela a ser chama como principal do projeto
    /////
    public static void Funcao_Chama_TelaPrincipal(Activity activity) {
        Intent finalIntent = new Intent(activity, Inicio.class);
        activity.startActivity(finalIntent);
        activity.finish();
    }

//    public static void Checa_Dados_Usuario(Activity activity, Context context) {
//
//        SharedPreferences prefs = context.getSharedPreferences("Tmp_ID", MODE_PRIVATE);
//        String usuario_ = prefs.getString("User", null);
//        String senha_ = prefs.getString("Senha", null);
//
//        if (usuario_ == null && senha_ == null) {
//            Chama_Tela_Login(activity, context);
//        }
//
//    }

//    public static void Chama_Tela_Login(Activity activity, Context context) {
//        SharedPreferences prefs = context.getSharedPreferences("Login", MODE_PRIVATE);
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.clear();
//        editor.commit();
//
//        SharedPreferences prefs2 = context.getSharedPreferences("Tmp_ID", MODE_PRIVATE);
//        SharedPreferences.Editor editor2 = prefs2.edit();
//        editor2.clear();
//        editor2.commit();
//
//        SharedPreferences prefs3 = context.getSharedPreferences("Usuario", MODE_PRIVATE);
//        SharedPreferences.Editor editor3 = prefs3.edit();
//        editor3.clear();
//        editor3.commit();
//
//        Intent intent = new Intent(context, Inicio_Login.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        context.startActivity(intent);
////        activity.finish();
//        activity.finishAffinity();
//    }


    /*
    chama tela de localização de usuario
    */

//    public static void Chama_Tela_Localiza_Cliente(Activity activity, Context context) {
//        Intent pagina_usuario = new Intent(context, Localiza_Cliente.class);
//        pagina_usuario.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(pagina_usuario);
//        activity.finish();
//    }

    /*
    chama tela de dados do cliente
    */
    public static void Chama_Tela(Activity activity, Context context, Intent intent) {
        activity.startActivity(intent);
        activity.finish();
    }


//    public static void Chama_Tela_Usuario_new(Activity activity, Context context) {
//        Intent intent = new Intent(context, Pagina_Usuario.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(intent);
//        activity.finish();
//    }

//    public static void Chama_Tela_Usuario(Context context, int indice) {
//        Intent intent = new Intent(context, Pagina_Usuario.class);
//        intent.putExtra("indice", indice);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(intent);
//    }


    //////////////////////////////////////////////////////////////////////////////////////
    /*
     *  Função para procurar e iniciar uma activity com ou sem parametros
     *
     *  0° View a ser clicada para chamar esta função
     *  1° Activity
     *  2° Classe a ser iniciada ***( caso nao existam putExtras , é so passar a classe a ser chamada , Do contrario , passar null )
     *  3° Intent ***( caso existam putExtras, pode criar a Intent e dar o start aqui, do contrario , mandar null )
     *  4° finaliza a tela atual ?
     *  5° mostra alerta para iniciar a tela??
     *  6° se mostrar alerta, qual o texto ?
     *
     *  exemplo de uso:
     *  ToolBox_Chama_Activity.Chama_Activity(activity, null, Carga_Bateria.class, true, false, "");
     */
    public static void Funcao_Chama_Activity(View view, final Activity activity, final Class classe_tela, final Intent intent, final Boolean finish, final Boolean alerta, final String texto_para_alerta) {

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent finalIntent;

                if (intent == null) {
                    finalIntent = new Intent(activity, classe_tela);
                } else {
                    finalIntent = intent;
                }

                finalIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                if (alerta) {
                    final Intent finalIntent1 = finalIntent;
                    new AlertDialog.Builder(activity)
                            .setMessage(texto_para_alerta)
                            .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    chamaactivity(activity, finalIntent1, finish);

                                }
                            })
                            .setNegativeButton("Não", null)
                            .create()
                            .show();
                } else {

                    chamaactivity(activity, finalIntent, finish);

                }


            }
        });


    }

    /////
    /////  Esta Função faz a mesma coisa que a Primeira , porem nao recebe um view
    /////  ou seja , so é chamada em eventos especificos detrminados pelo usuario
    /////  não precisa ser especificamente um OnclickListenner!!!
    /////

    public static void Funcao_Chama_Activity(final Activity activity, final Class classe_tela, final Intent intent, final Boolean finish, final Boolean alerta, final String texto_para_alerta) {

        Intent finalIntent;

        if (intent == null) {
            finalIntent = new Intent(activity, classe_tela);
        } else {
            finalIntent = intent;
        }

        finalIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (alerta) {
            final Intent finalIntent1 = finalIntent;
            new AlertDialog.Builder(activity)
                    .setMessage(texto_para_alerta)
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            chamaactivity(activity, finalIntent1, finish);

                        }
                    })
                    .setNegativeButton("Não", null)
                    .create()
                    .show();
        } else {

            chamaactivity(activity, finalIntent, finish);

        }

    }

    /////
    /////  complemento para a Primeira e Segunda funções a cima
    /////
    private static void chamaactivity(Activity activity, Intent intent, Boolean finish) {
        activity.startActivity(intent);
        if (finish) {
            activity.finish();
        }
    }


}

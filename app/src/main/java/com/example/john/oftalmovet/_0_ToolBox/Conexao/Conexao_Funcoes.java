package com.example.john.oftalmovet._0_ToolBox.Conexao;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.john.oftalmovet._0_ToolBox.ToolBox.ToolBox_MSG;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by John on 24/01/2018.
 */

public class Conexao_Funcoes {

    private static String Oque_A_Pagina_Faz; // simplismente para facilitar a manutenção do codigo

    private static String FUNCAO_DA_PAGINA;
    private static String FUNCAO_DA_PAGINA_FINAL;
    private static String TIPO;
    private static Map<String, String> PARAMETROS;
    private static String CREDENCIAIS;
    private static int final_status = -1;

    public static int Dados_Ws(final int pagina, String Dados_Para_URL_GET, final Map<String, String> Dados_Para_Parametros, final Context context, final Activity activity) {


        SharedPreferences prefs = context.getSharedPreferences("Tmp_ID", MODE_PRIVATE);
        final String usuario_ = prefs.getString("User", "Sem ID");
        String senha_ = prefs.getString("Senha", "Sem Senha");

        /**
         * Switch para receber o Context da pagina e outras funçoes de cada pagina
         * especifica , aqui recebe o API do WS, funçoes e outras coisas
         */

        switch (pagina) {
            case 1:
                Oque_A_Pagina_Faz = "Recuperação de Senha";
                FUNCAO_DA_PAGINA = "esqueci_senha";
                TIPO = "GET";
                PARAMETROS = Dados_Para_Parametros;
                CREDENCIAIS = null + ":" + null;
                break;
            case 2:
                Oque_A_Pagina_Faz = "Login";
                FUNCAO_DA_PAGINA = "login_usuario";
                TIPO = "POST";
                PARAMETROS = Dados_Para_Parametros;
                CREDENCIAIS = usuario_ + ":" + senha_;
                break;
            case 3:
                Oque_A_Pagina_Faz = "Editar Perfil";
                FUNCAO_DA_PAGINA = "editar_usuario";
                TIPO = "POST";
                PARAMETROS = Dados_Para_Parametros;
                CREDENCIAIS = usuario_ + ":" + senha_;
                break;
            case 4:
                Oque_A_Pagina_Faz = "Criar novo Cliente";
                FUNCAO_DA_PAGINA = "criar_cliente";
                TIPO = "POST";
                PARAMETROS = Dados_Para_Parametros;
                CREDENCIAIS = usuario_ + ":" + senha_;
                break;
            case 5:
                Oque_A_Pagina_Faz = "Pesquisar Clientes";
                FUNCAO_DA_PAGINA = "listar_clientes";
                TIPO = "GET";
                PARAMETROS = Dados_Para_Parametros;
                CREDENCIAIS = usuario_ + ":" + senha_;
                break;
            case 6:
                Oque_A_Pagina_Faz = "Pre Editar Clientes";
                FUNCAO_DA_PAGINA = "pre_editar_cliente";
                TIPO = "GET";
                PARAMETROS = Dados_Para_Parametros;
                CREDENCIAIS = usuario_ + ":" + senha_;
                break;
            case 7:
                Oque_A_Pagina_Faz = "Editar Cliente";
                FUNCAO_DA_PAGINA = "editar_cliente";
                TIPO = "POST";
                PARAMETROS = Dados_Para_Parametros;
                CREDENCIAIS = usuario_ + ":" + senha_;
                break;
            case 8:
                Oque_A_Pagina_Faz = "Pre Cadastra animal";
                FUNCAO_DA_PAGINA = "pre_cadastro_animal";
                TIPO = "GET";
                PARAMETROS = Dados_Para_Parametros;
                CREDENCIAIS = usuario_ + ":" + senha_;
                break;
            case 9:
                Oque_A_Pagina_Faz = "Adciona animal";
                FUNCAO_DA_PAGINA = "cadastro_animal";
                TIPO = "POST";
                PARAMETROS = Dados_Para_Parametros;
                CREDENCIAIS = usuario_ + ":" + senha_;
                break;
            case 10:
                Oque_A_Pagina_Faz = "Pre Editar animal";
                FUNCAO_DA_PAGINA = "pre_edicao_animal";
                TIPO = "GET";
                PARAMETROS = Dados_Para_Parametros;
                CREDENCIAIS = usuario_ + ":" + senha_;
                break;
            case 11:
                Oque_A_Pagina_Faz = "Editar Animal";
                FUNCAO_DA_PAGINA = "editar_animal";
                TIPO = "POST";
                PARAMETROS = Dados_Para_Parametros;
                CREDENCIAIS = usuario_ + ":" + senha_;
                break;
            case 12:
                Oque_A_Pagina_Faz = "Criar Procedimento";
                FUNCAO_DA_PAGINA = "novo_procedimento";
                TIPO = "POST";
                PARAMETROS = Dados_Para_Parametros;
                CREDENCIAIS = usuario_ + ":" + senha_;
                break;
            case 13:
                Oque_A_Pagina_Faz = "Listar Procedimentos";
                FUNCAO_DA_PAGINA = "lista_procedimentos";
                TIPO = "GET";
                PARAMETROS = Dados_Para_Parametros;
                CREDENCIAIS = usuario_ + ":" + senha_;
                break;
            case 14:
                Oque_A_Pagina_Faz = "Editar Procedimento";
                FUNCAO_DA_PAGINA = "edita_procedimento";
                TIPO = "PUT";
                PARAMETROS = Dados_Para_Parametros;
                CREDENCIAIS = usuario_ + ":" + senha_;
                break;
            case 15:
                Oque_A_Pagina_Faz = "Pré consulta";
                FUNCAO_DA_PAGINA = "pre_consulta";
                TIPO = "GET";
                PARAMETROS = Dados_Para_Parametros;
                CREDENCIAIS = usuario_ + ":" + senha_;
                break;
            case 16:
                Oque_A_Pagina_Faz = "Criar Consulta";
                FUNCAO_DA_PAGINA = "nova_consulta";
                TIPO = "POST";
                PARAMETROS = Dados_Para_Parametros;
                CREDENCIAIS = usuario_ + ":" + senha_;
                break;
            case 17:
                Oque_A_Pagina_Faz = "Criar Retorno";
                FUNCAO_DA_PAGINA = "novo_retorno";
                TIPO = "POST";
                PARAMETROS = Dados_Para_Parametros;
                CREDENCIAIS = usuario_ + ":" + senha_;
                break;
            case 18:
                Oque_A_Pagina_Faz = "Criar Receita";
                FUNCAO_DA_PAGINA = "nova_receita";
                TIPO = "POST";
                PARAMETROS = Dados_Para_Parametros;
                CREDENCIAIS = usuario_ + ":" + senha_;
                break;
            case 19:
                Oque_A_Pagina_Faz = "Criar procedimento animal";
                FUNCAO_DA_PAGINA = "novo_procedimento_animal";
                TIPO = "POST";
                PARAMETROS = Dados_Para_Parametros;
                CREDENCIAIS = usuario_ + ":" + senha_;
                break;
            case 20:
                Oque_A_Pagina_Faz = "Listar Prontuários";
                FUNCAO_DA_PAGINA = "lista_prontuario";
                TIPO = "GET";
                PARAMETROS = Dados_Para_Parametros;
                CREDENCIAIS = usuario_ + ":" + senha_;
                break;
            case 21:
                Oque_A_Pagina_Faz = "Criar agendamento";
                FUNCAO_DA_PAGINA = "novo_agendamento";
                TIPO = "POST";
                PARAMETROS = Dados_Para_Parametros;
                CREDENCIAIS = usuario_ + ":" + senha_;
                break;
            case 22:
                Oque_A_Pagina_Faz = "Listar agendamento";
                FUNCAO_DA_PAGINA = "lista_agendamento";
                TIPO = "GET";
                PARAMETROS = Dados_Para_Parametros;
                CREDENCIAIS = usuario_ + ":" + senha_;
                break;
            case 23:
                Oque_A_Pagina_Faz = "Financeiro resumido";
                FUNCAO_DA_PAGINA = "lista_sintetico";
                TIPO = "GET";
                PARAMETROS = Dados_Para_Parametros;
                CREDENCIAIS = usuario_ + ":" + senha_;
                break;
            case 24:
                Oque_A_Pagina_Faz = "Financeiro detalhado";
                FUNCAO_DA_PAGINA = "lista_analitico";
                TIPO = "GET";
                PARAMETROS = Dados_Para_Parametros;
                CREDENCIAIS = usuario_ + ":" + senha_;
                break;
            case 25:
                Oque_A_Pagina_Faz = "Pré termo";
                FUNCAO_DA_PAGINA = "pre_termo";
                TIPO = "GET";
                PARAMETROS = Dados_Para_Parametros;
                CREDENCIAIS = usuario_ + ":" + senha_;
                break;
            case 26:
                Oque_A_Pagina_Faz = "Criar termo";
                FUNCAO_DA_PAGINA = "novo_termo";
                TIPO = "POST";
                PARAMETROS = Dados_Para_Parametros;
                CREDENCIAIS = usuario_ + ":" + senha_;
                break;
            case 27:
                Oque_A_Pagina_Faz = " Criar Usuário";
                FUNCAO_DA_PAGINA = "novo_usuario";
                TIPO = "POST";
                PARAMETROS = Dados_Para_Parametros;
                CREDENCIAIS = null + ":" + null;
                break;
            case 28:
                Oque_A_Pagina_Faz = "Upload logo";
                FUNCAO_DA_PAGINA = "upload_logo";
                TIPO = "POST";
                PARAMETROS = Dados_Para_Parametros;
                CREDENCIAIS = usuario_ + ":" + senha_;
                break;
            case 29:
                Oque_A_Pagina_Faz = "Pré editar usuário";
                FUNCAO_DA_PAGINA = "pre_editar_usuario";
                TIPO = "GET";
                PARAMETROS = Dados_Para_Parametros;
                CREDENCIAIS = usuario_ + ":" + senha_;
                break;
            case 30:
                Oque_A_Pagina_Faz = "Listar Planos";
                FUNCAO_DA_PAGINA = "listar_planos";
                TIPO = "GET";
                PARAMETROS = Dados_Para_Parametros;
                CREDENCIAIS = usuario_ + ":" + senha_;
                break;
        }

        if (TIPO == "GET") {
            FUNCAO_DA_PAGINA_FINAL = FUNCAO_DA_PAGINA + "?" + Dados_Para_URL_GET;
        } else {
            FUNCAO_DA_PAGINA_FINAL = FUNCAO_DA_PAGINA;
        }


        Conexao_ ws = new Conexao_(

                Conexao_Constantes.URL_WS_PADRAO,
                FUNCAO_DA_PAGINA_FINAL,
                TIPO,
                PARAMETROS,
                CREDENCIAIS,
                context
        );

        ws.getData(new Conexao_.RetornoAssincrono()

        {
            @SuppressLint("LongLogTag")
            @Override
            public JSONObject onSuccess(JSONObject objeto) {
                System.out.println("Sucesso!!! Os valores são: \n" + objeto);

                int status = 0;
                try {
                    status = objeto.getInt("status");
                    String resultado = objeto.getString("resultado");


                    System.out.println("Status = " + status);
                    System.out.println("Resultado = " + resultado);
                    int assinatura = -1;

                    if (objeto.has("status_assinatura")) {
                        assinatura = objeto.getInt("status_assinatura");
                        System.out.println("Status da Assinatura = " + assinatura);

                        switch (assinatura) {

                            case 0:

//                                SharedPreferences id_prefs = context.getSharedPreferences("Usuario", MODE_PRIVATE);
//                                final String id_usuario_ass = id_prefs.getString("id_usuario", "");

                                final int id_usuario_ass = objeto.getInt("id_usuario");

                                SharedPreferences prefs_ass = context.getSharedPreferences("Login", MODE_PRIVATE);
                                SharedPreferences.Editor editor_ass = prefs_ass.edit();
                                editor_ass.clear();
                                editor_ass.commit();

                                SharedPreferences prefs_ass2 = context.getSharedPreferences("Tmp_ID", MODE_PRIVATE);
                                SharedPreferences.Editor editor_ass2 = prefs_ass2.edit();
                                editor_ass2.clear();
                                editor_ass2.commit();

                                SharedPreferences prefs_ass3 = context.getSharedPreferences("Usuario", MODE_PRIVATE);
                                SharedPreferences.Editor editor_ass3 = prefs_ass3.edit();
                                editor_ass3.clear();
                                editor_ass3.commit();


//                                new android.app.AlertDialog.Builder(activity)
//                                        .setCancelable(false)
//                                        .setMessage("Infelizmente seu plano chegou ao fim! \n" +
//                                                "Assine um novo plano para continuar usando o APP.")
//                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialog, int which) {
//
//
//                                                Intent planos = new Intent(context, Planos.class);
//                                                planos.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                                planos.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                                planos.putExtra("ass", "0");
//                                                planos.putExtra("id_usuario", id_usuario_ass);
//                                                context.startActivity(planos);
//                                                activity.finishAffinity();
//
//                                            }
//                                        })
////                                        .setNegativeButton("Cancelar", null)
//                                        .create()
//                                        .show();

//                                ToolBox_MSG.ExibeMSG("Assine um plano para continuar a usar o APP!", context);

                                break;

                            case 1:
                                ChamaLogin(objeto, activity, usuario_);
                                break;
                            case 2:
                                ChamaLogin(objeto, activity, usuario_);
                                break;

                        }
                    } else {

                        if (status == 1) {

                            /**
                             * Switch para funçoes diferenciadas das paginas
                             */
                            switch (pagina) {
                                case 1:
                                    break;
                                case 2:

                                    JSONObject dados = objeto.getJSONObject("dados");
//                                    System.out.println("Dados = " + dados);


                                    break;

                            }

                            Log.w("Conexao com a Pagina " + Oque_A_Pagina_Faz, "OK!");
                        } else {
                            switch (pagina) {

                                case 1:
                                    break;
                                case 2:
                                    break;

                            }
                        }

                        ToolBox_MSG.Funcao_Toast(context , resultado);

                    }


                } catch (JSONException e) {
                    switch (pagina) {

                        case 1:
                            break;
                        case 2:
                            break;

                    }
                    System.out.println("Catch : " + e.getMessage());
                    Log.w("Conexao_Hugo com a Pagina " + Oque_A_Pagina_Faz, "FALHOU!");
                    //hideDialog();
                }

                return objeto;
            }
        });


        return final_status;
    }

    private static void ChamaLogin(JSONObject objeto, Activity context, String usuario_) throws JSONException {

        JSONObject dados = null;
        try {
            dados = objeto.getJSONObject("dados");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("Dados = " + dados);

        String id_usuario = dados.getString("id_usuario");
        String nome_usuario = dados.getString("nome_usuario");
        String email_usuario = dados.getString("email_usuario");
        String cpf_usuario = dados.getString("cpf_usuario");
        String rg_usuario = dados.getString("rg_usuario");
        String crmv_usuario = dados.getString("crmv");
        String logradouro_usuario = dados.getString("logradouro_usuario");
        String numero_end_usuario = dados.getString("numero_end_usuario");
        String complemento_usuario = dados.getString("complemento_usuario");
        String bairro_usuario = dados.getString("bairro_usuario");
        String cep_usuario = dados.getString("cep_usuario");
        String cidade_usuario = dados.getString("cidade_usuario");
        String estado_usuario = dados.getString("estado_usuario");
        String nome_clinica = dados.getString("nome_clinica");

        SharedPreferences prefs = context.getSharedPreferences("Usuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("id_usuario", id_usuario);
        editor.putString("login_usuario", usuario_);
        editor.putString("nome_usuario", nome_usuario);
        editor.putString("email_usuario", email_usuario);
        editor.putString("cpf_usuario", cpf_usuario);
        editor.putString("rg_usuario", rg_usuario);
        editor.putString("crmv_usuario", crmv_usuario);
        editor.putString("logradouro_usuario", logradouro_usuario);
        editor.putString("numero_end_usuario", numero_end_usuario);
        editor.putString("complemento_usuario", complemento_usuario);
        editor.putString("bairro_usuario", bairro_usuario);
        editor.putString("cep_usuario", cep_usuario);
        editor.putString("cidade_usuario", cidade_usuario);
        editor.putString("estado_usuario", estado_usuario);
        editor.putString("nome_clinica", nome_clinica);
        editor.putString("img", "");
        editor.putBoolean("img_load", true);


        if (objeto.has("assinaturas")) {
            JSONObject dados_assinatura = objeto.getJSONObject("assinaturas");
            System.out.println("Dados assinaturas= " + dados_assinatura);

            String nome_plano = dados_assinatura.getString("nome_plano");
            String detalhes_plano = dados_assinatura.getString("detalhes_plano");
            String status_assinatura = dados_assinatura.getString("status_assinatura");
            String data_assinatura = dados_assinatura.getString("data_assinatura");
            String data_status_assinatura = dados_assinatura.getString("data_status_assinatura");
            String valor_plano = dados_assinatura.getString("valor_plano");

            editor.putString("nome_plano", nome_plano);
            editor.putString("detalhes_plano", detalhes_plano);
            editor.putString("status_assinatura", status_assinatura);
            editor.putString("data_assinatura", data_assinatura);
            editor.putString("data_status_assinatura", data_status_assinatura);
            editor.putString("valor_plano", valor_plano);

        }

        editor.commit();


    }


}

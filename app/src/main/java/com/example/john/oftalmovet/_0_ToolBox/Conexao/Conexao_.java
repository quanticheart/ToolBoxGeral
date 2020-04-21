package com.example.john.oftalmovet._0_ToolBox.Conexao;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Base64;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.john.oftalmovet.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Criado por Eduardo dos santos em 19/11/2017.
 * Megamil.net
 */

public class Conexao_ {

    Context contexto;
    private Toast aviso;
    private RequestQueue requisicao;
    private JSONObject objeto = null;

    private String url;                     /*http://192.168.0.106/ws*/
    private String funcao;                  /*Caso seja GET enviar os parametros concatenados FUNÇÃO?CAMPO=VALOR&CAMPO=VALOR&...*/
    private String tipo;                    /*DELETE,GET,POST ou PUT*/
    private String credenciais;             /*USUARIO:SENHA*/
    private Map<String, String> parametros;

    ProgressDialog dialog;

    //Instancia com valores.
    public Conexao_(String url, String funcao, String tipo, Map<String, String> parametros, String credenciais, Context contexto) {

        this.credenciais = credenciais;
        this.parametros = parametros;
        this.url = url;
        this.funcao = funcao;
        this.tipo = tipo;
        this.contexto = contexto;
        this.requisicao = Volley.newRequestQueue(this.contexto);

        dialog = new ProgressDialog(contexto);
        dialog.setMessage("Carregando...");
        dialog.setIndeterminate(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();

        System.out.println("WS Instanciado com valores iniciais.");

    }

    //Instancia Em Branco.
    public Conexao_(Context contexto) {

        this.credenciais = null;
        this.parametros = null;
        this.url = "";
        this.funcao = "";
        this.tipo = "";
        this.contexto = contexto;
        this.requisicao = Volley.newRequestQueue(this.contexto);

        System.out.println("WS Instanciado sem valores iniciais.");

    }

    /*
    * Gets e Sets
    * */

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCredenciais() {
        return credenciais;
    }

    public void setCredenciais(String credenciais) {
        this.credenciais = credenciais;
    }

    public Map<String, String> getParametros() {
        return parametros;
    }

    public void setParametros(Map<String, String> parametros) {
        this.parametros = parametros;
    }

    /*
    *  Funções específicas do Conexao_.
    * */

    public interface RetornoAssincrono {
        JSONObject onSuccess(JSONObject objeto);
    }

    public void exibirParametros() {

        System.out.println("Campos preenchidos: " + "\n" +
                "   URL: '" + getUrl() + "'\n" +
                "   Função: '" + getFuncao() + "'\n" +
                "   Tipo de requisição: '" + getTipo() + "'\n" +
                "   Credenciais: '" + credenciais + "'\n" +
                "   Parametros: '" + parametros + "'");

    }

    public void getData(final RetornoAssincrono retorno) {

        int tipoRequisicao = 0;

        switch (tipo) {
            case "POST":
                tipoRequisicao = Request.Method.POST;
                break;
            case "GET":
                tipoRequisicao = Request.Method.GET;
                break;
            case "PUT":
                tipoRequisicao = Request.Method.PUT;
                break;
            case "DELETE":
                tipoRequisicao = Request.Method.DELETE;
                break;

            default:
                tipoRequisicao = Request.Method.POST;
                break;

        }

        StringRequest stringRequisicao = new StringRequest(tipoRequisicao, url + funcao, new Response.Listener<String>() {

            @Override
            public void onResponse(String respostaWs) {
                System.out.println("Entrei no response >> " + respostaWs);
                try {
                    dialog.dismiss();
                    JSONObject objeto = new JSONObject(respostaWs);
                    retorno.onSuccess(objeto);

                } catch (JSONException e) {
                    dialog.dismiss();
                    System.out.println("Catch : " + e.getMessage());

                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Erro >>>: " + error);
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {

                    aviso = Toast.makeText(contexto, R.string.msg_global_sem_conexao , Toast.LENGTH_LONG);
                    aviso.show();

//                    ToolBox_MSG.ExibeMSG(R.string.global_toast_sem_conexao , contexto);

                } else if (error instanceof AuthFailureError) {

                    aviso = Toast.makeText(contexto, "[WSF02] Falha na autenticação", Toast.LENGTH_LONG);
                    aviso.show();

                } else if (error instanceof ServerError) {

                    aviso = Toast.makeText(contexto, "[WSF03] Falha no Servidor", Toast.LENGTH_LONG);
                    aviso.show();

                } else if (error instanceof NetworkError) {

                    aviso = Toast.makeText(contexto, "Falha na Conexão", Toast.LENGTH_LONG);
                    aviso.show();

                } else if (error instanceof ParseError) {

                    aviso = Toast.makeText(contexto, "[WSF05] Falha Parse Error", Toast.LENGTH_LONG);
                    aviso.show();

                } else {

                    aviso = Toast.makeText(contexto, "[WSF06] Falha Não identificada", Toast.LENGTH_LONG);
                    aviso.show();

                }
                String erro = "[WSF] ";
                if (error.getMessage() != null) {
                    erro += error.getMessage().trim();
                }

                if (error.networkResponse != null) {
                    erro += " Status: " + error.networkResponse.statusCode;
                }

                System.out.println(error.networkResponse);
                dialog.dismiss();
                aviso = Toast.makeText(contexto, "Erro de conexão com o servidor", Toast.LENGTH_LONG);
                aviso.show();

            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                String auth = "Basic ";

                if (getCredenciais() != null) {
                    auth += Base64.encodeToString(getCredenciais().getBytes(), Base64.NO_WRAP);
                }

                headers.put("Accept", "application/json");
                headers.put("Authorization", auth);
                headers.put("User-Agent", "Megamil");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return getParametros();
            }


        };

        exibirParametros();
        requisicao.add(stringRequisicao);

    }

}

package com.example.john.oftalmovet._0_ToolBox.Conexao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class Conexao_Hugo {

    /**
     * @param urlEnd Constants http.....
     * @param params Json
     * @return resposta em texto JSON servidor
     */
    public static String comunicacao(String urlEnd, String params) {
        StringBuilder sb = new StringBuilder();

        URL url;
        HttpURLConnection conn = null;

        try {

            url = new URL(urlEnd);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");



            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);

            //
            //conn.setConnectTimeout(60000);
            //conn.setReadTimeout(60000);
            //


            StringBuilder parametrosFormatados = new StringBuilder();
            parametrosFormatados.append(URLEncoder.encode("json", "UTF-8"));
            parametrosFormatados.append("=");
            parametrosFormatados.append(URLEncoder.encode(params, "UTF-8"));
            //
            // Envio de Parametros
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8")
            );
            writer.write(parametrosFormatados.toString());
            writer.flush();
            writer.close();
            //os.close();
            //
            // Ler as informacoes enviados pelo Servidor
            sb.append(readStreamHugo(conn.getInputStream()));
        } catch (Exception e) {
            sb.append(e.toString());
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        return sb.toString();
    }

    private static String readStreamHugo(InputStream inputStream) {
        Reader reader = null;
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];

        try {
            reader = new BufferedReader(
                    new InputStreamReader(inputStream, "UTF-8")
            );

            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (Exception e) {
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //
        return writer.toString();
    }

}

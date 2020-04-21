package com.example.john.oftalmovet._0_ToolBox.ToolBox;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ToolBox_Verivifacao {

    /**
     * Função para verificar se a variavel esta vazia , neste caso, todos que chamem esta função são obrigatorios , devem ser preeenchidos
     */
    public static boolean Funcao_ValidarSeEstaVazio(Context context, EditText editText_para_ser_selecionado, String valor) {

        if (valor.isEmpty() || valor.trim().isEmpty()) {
            editText_para_ser_selecionado.requestFocus();
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(editText_para_ser_selecionado, InputMethodManager.SHOW_IMPLICIT);
            return false;
        }

        return true;
    }


    /**
     * Função para verificar se a variavel esta vazia , neste caso, todos que chamem esta função são obrigatorios , devem ser preeenchidos
     *
     * exeemplo de uso
     * ToolBox_Verivifacao.Campo_Obrigatorio(context, et_cidade, et_cidade.getText().toString().trim());
     */
    public static boolean Funcao_Campo_Obrigatorio(Context context, EditText editText_para_ser_selecionado, String valor) {

        String MsgDeERROR = "Este Campo Precisa ser Preenchido";
        Log.w("O valor é = ", valor);
        if (valor.isEmpty() || valor.trim().isEmpty() || valor.equals("")) {
            editText_para_ser_selecionado.requestFocus();
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(editText_para_ser_selecionado, InputMethodManager.SHOW_IMPLICIT);
//            ToolBox_MSG.ExibeMSG(MsgDeERROR, context);
            return false;
        }

        return true;
    }


    /**
     * Função para verificar se a variavel numerica é numero , e se ela tem o valor minimo para ser guardada no banco
     *
     * exemplo de uso
     *  ToolBox_Verivifacao.Campo_Obrigatorio_Numero(context, et_cpf, et_cpf.getText().toString().trim() , 11);
     */
    public static boolean Funcao_Campo_Obrigatorio_Numero(Context context, EditText editText_para_ser_selecionado, String valor_original, int numero_de_caracteres_minimos) {

        String valor_limpo = Funcao_Retirar_Mascara(valor_original);
        String valor_limpo_2 = valor_limpo.replaceAll("[^\\d.]", "");


        if (valor_limpo_2.isEmpty() || valor_limpo_2.trim().isEmpty() || valor_limpo_2.equals("")) {
            Log.w("Validação de numero", "Numero vazio ou invalido = "+valor_limpo_2);
            editText_para_ser_selecionado.requestFocus();
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(editText_para_ser_selecionado, InputMethodManager.SHOW_IMPLICIT);
            return false;
        } else if (valor_limpo_2.length() < numero_de_caracteres_minimos) {
            Log.w("Validação de numero", "a String "+valor_limpo_2+" é menor que "+numero_de_caracteres_minimos);
            editText_para_ser_selecionado.requestFocus();
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(editText_para_ser_selecionado, InputMethodManager.SHOW_IMPLICIT);
            return false;
        } else {
            return true;
        }

    }


    /**
     * Função para verificar se a variavel e um email valido
     *
     * exemplo de uso
       ToolBox_Verivifacao.Campo_Obrigatorio_Email(context, et_email, et_email.getText().toString().trim());
     */
    public final static boolean Funcao_Campo_Obrigatorio_Email(Context context, EditText editText_para_ser_selecionado, String txtEmail) {



        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = txtEmail;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            return true;
        } else {

            editText_para_ser_selecionado.requestFocus();
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(editText_para_ser_selecionado, InputMethodManager.SHOW_IMPLICIT);

            return false;
        }


    }

    /**
     * Função para verificar se um array com a função Campo_Obrigatorio retornaram todos ok
     * <p>
     * Exemplo funcional
     * <p>
     * boolean[] Array_De_Obrigatorios = new boolean[2];
     * // no Array_De_Obrigatorios tem que ter o numero de campos a serem validados, o 0 (ZERO) não conta
     * // mas nos Array_De_Obrigatorios[0] e contado como uma possição , abaixo sempre tem que iniciar do 0 (ZERO)
     * Array_De_Obrigatorios[0] = ToolBox_Verivifacao.Campo_Obrigatorio(context, Senha, Senha.getText().toString().trim());
     * Array_De_Obrigatorios[1] = ToolBox_Verivifacao.Campo_Obrigatorio(context, Usuario, usuario);
     */
    public static boolean Funcao_Verifica_Se_Todos_Foram_Preenchidos(boolean[] array) {
        Log.w("Array de Obrigatorios", Arrays.toString(array));
        for (boolean b : array) if (!b) return false;

        return true;
    }



    /**
     * Função para validação de Email digitado
     */

    public final static boolean Funcao_ValidarEmail(String txtEmail) {

        String MsgDeERROR = "E-mail Invalido";
        Log.w("Validando o Email = ", txtEmail + "");
        if (TextUtils.isEmpty(txtEmail) || txtEmail.trim().isEmpty()) {
            Log.w("Email Invalido = ", txtEmail + "");
            return false;

        } else {
            Log.w("Email valido = ", txtEmail + "");
            Log.w("Email valido = ", android.util.Patterns.EMAIL_ADDRESS.matcher(txtEmail).matches() + "");
            return android.util.Patterns.EMAIL_ADDRESS.matcher(txtEmail).matches();
        }
    }

    /**
     * Função para validação de CPF
     */
    public static boolean Funcao_ValidarCPF(Context context, String CPF) {

        String MsgDeERROR = "CPF Invalido";

        CPF = Funcao_Retirar_Mascara(CPF);
        if (CPF.isEmpty() || CPF.equals("00000000000") || CPF.equals("11111111111")
                || CPF.equals("22222222222") || CPF.equals("33333333333")
                || CPF.equals("44444444444") || CPF.equals("55555555555")
                || CPF.equals("66666666666") || CPF.equals("77777777777")
                || CPF.equals("88888888888") || CPF.equals("99999999999")) {

            return false;
        }
        char dig10, dig11;
        int sm, i, r, num, peso;
        try {
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else
                dig10 = (char) (r + 48);
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else
                dig11 = (char) (r + 48);
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {
                return (true);
            } else {
                return (false);
            }
        } catch (Exception erro) {
            return (false);
        }
    }



    /**
     * Função para etirar qualquer caractere e deixar string limpa para teste
     */
    public static String Funcao_Retirar_Mascara(String s) {
        return s.replaceAll("[.]", "").replaceAll("[-]", "")
                .replaceAll("[/]", "").replaceAll("[(]", "")
                .replaceAll("[)]", "");
    }

}

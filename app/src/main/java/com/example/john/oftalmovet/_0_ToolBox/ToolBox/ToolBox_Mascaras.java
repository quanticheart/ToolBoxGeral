package com.example.john.oftalmovet._0_ToolBox.ToolBox;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by John on 09/12/2017.
 */

public class ToolBox_Mascaras {

    /**
     * Este método converte String para Moeda
     * String chega como 300 , e é convertida para 300.00
     * String chega como 1000000 , e é convertida para 1,000,000.00
     */
    public static String Funcao_Conveter_Para_Moeda(String valor_inteiro){
        DecimalFormat formatter = new DecimalFormat("###,###,##0.00");
        String numero = formatter.format(Double.parseDouble(valor_inteiro));
        return numero;
    }

    public static String Funcao_Mascara_Moeda_Primitivo(String numero) {

        String str = numero; // argumento da sua funcao

        if (str.length() == 1) {
            str = "00" + str;
        } else if (str.length() == 2) {
            str = "0" + str;
        }

        int index = str.length() - 2;
        return "R$" + str.substring(0, index) + "." + str.substring(index);

    }

    public static String Funcao_Mascara_Moeda_NumberFormat(String numero) {

        String money = NumberFormat.getCurrencyInstance().format(numero);
        return money;

    }

    public static String Funcao_Primeira_letra_Maiuscula(String valor) {

        String strOrigem = valor.replaceAll("\\s+"," ");
        String result = valor.substring(0,1).toUpperCase() + valor.substring(1).toLowerCase();

        return result;

    }


    /**
     * Função para adcionar mascaras aos campos de CPF
     * recebe um valor bruto de 11 caracteres (11122233344) e retorna (111.222.333-44)
     */
    public static String Funcao_InsereMascara_CPF(String cpf) {
        cpf = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
        return cpf;
    }

    /**
     * Função para adcionar mascaras aos campos de telefone (** esta função é somente para telefone **)
     */

    private static final String Constante_Mascara_Telefone_11 = "(##)#####-####";
    private static final String Constante_Mascara_Telefone_10 = "(##)####-####";
    private static final String Constante_Mascara_Telefone_9 = "#####-####";
    private static final String Constante_Mascara_Telefone_8 = "####-####";

    public static TextWatcher Funcao_MascaraParaTelefone(final EditText editText) {
        return new TextWatcher() {
            boolean isUpdating;
            String old = "";

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = Funcao_Retirar_Mascara(s.toString());
                String mask;
                String defaultMask = getDefaultMask(str);
                switch (str.length()) {
                    case 11:
                        mask = Constante_Mascara_Telefone_11;
                        break;
                    case 10:
                        mask = Constante_Mascara_Telefone_10;
                        break;
                    case 9:
                        mask = Constante_Mascara_Telefone_9;
                        break;
                    default:
                        mask = defaultMask;
                        break;
                }

                String mascara = "";
                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }
                int i = 0;
                for (char m : mask.toCharArray()) {
                    if ((m != '#' && str.length() > old.length()) || (m != '#' && str.length() < old.length() && str.length() != i)) {
                        mascara += m;
                        continue;
                    }

                    try {
                        mascara += str.charAt(i);
                    } catch (Exception e) {
                        break;
                    }
                    i++;
                }
                isUpdating = true;
                editText.setText(mascara);
                editText.setSelection(mascara.length());
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            public void afterTextChanged(Editable s) {
            }
        };
    }

    private static String getDefaultMask(String str) {
        String defaultMask = Constante_Mascara_Telefone_8;
        if (str.length() > 11) {
            defaultMask = Constante_Mascara_Telefone_11;
        }
        return defaultMask;
    }

    /**
     * Função para adcionar mascaras aos campos
     */
    //para chamar a função InsereMascara , vc passa o EditText que recebera a mascara como primeiro parametro
    //e no segundo paramentro recebera a mascara a ser inserida no EditText
    //et_telefone.addTextChangedListener(ToolBox_Texto.InsereMascara(et_telefone, "(##)#####-####"));
    public static final String CONSTANTE_MASCARA_FORMAT_CPF = "###.###.###-##";
    public static final String CONSTANTE_MASCARA_FORMAT_FONE_9DIGITOS = "(##)#####-####";
    public static final String CONSTANTE_MASCARA_FORMAT_FONE_ = "(##)####-####";
    public static final String CONSTANTE_MASCARA_FORMAT_CEP = "#####-###";
    public static final String CONSTANTE_MASCARA_FORMAT_DATE = "##/##/####";
    public static final String CONSTANTE_MASCARA_FORMAT_HOUR = "##:##";

    public static TextWatcher Funcao_InsereMascara_Universao(final EditText ediTxt, final String mask) {
        return new TextWatcher() {
            boolean isUpdating;
            String old = "";

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = Funcao_Retirar_Mascara(s.toString());

                String mascara = "";
                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }
                int i = 0;
                for (char m : mask.toCharArray()) {
                    if (m != '#' && str.length() > old.length()) {
                        mascara += m;
                        continue;
                    }
                    try {
                        mascara += str.charAt(i);
                    } catch (Exception e) {
                        break;
                    }
                    i++;
                }
                isUpdating = true;
                ediTxt.setText(mascara);
                ediTxt.setSelection(mascara.length());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }
        };
    }

    public static TextWatcher Funcao_Dois_Teclados_Com_Mascaras(final String mask, final EditText ediTxt, final int index, final int InputTypeBefore, final int InputTypeAfter) {
        return new TextWatcher() {
            boolean isUpdating;
            String old = "";

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = Funcao_Retirar_Mascara(s.toString());
                String mascara = "";
                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }

                int index = 0;
                for (int i = 0; i < mask.length(); i++) {
                    char m = mask.charAt(i);
                    if (m != '#') {
                        if (index == str.length() && str.length() < old.length()) {
                            continue;
                        }
                        mascara += m;
                        continue;
                    }

                    try {
                        mascara += str.charAt(index);
                    } catch (Exception e) {
                        break;
                    }

                    index++;
                }

                if (mascara.length() > index) {
                    char last_char = mascara.charAt(mascara.length() - 1);
                    boolean hadSign = false;
                    while (isASign(last_char) && str.length() == old.length()) {
                        mascara = mascara.substring(0, mascara.length() - 1);
                        last_char = mascara.charAt(mascara.length() - 1);
                        hadSign = true;
                    }

                    if (mascara.length() > 0 && hadSign) {
                        mascara = mascara.substring(0, mascara.length() - 1);
                    }
                }

                isUpdating = true;
                ediTxt.setText(mascara);
                ediTxt.setSelection(mascara.length());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
                if (ediTxt.length() < index) {
                    ediTxt.setInputType(InputTypeBefore);
                } else {
                    ediTxt.setInputType(InputTypeAfter);
                }
            }
        };
    }

    public static boolean isASign(char c) {
        if (c == '.' || c == '-' || c == '/' || c == '(' || c == ')' || c == ',' || c == ' ') {
            return true;
        } else {
            return false;
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

package com.example.john.oftalmovet._0_ToolBox.ToolBox;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * Created by John on 09/12/2017.
 */

public class _ToolBox_Gerador_De_Dados {


    /**
     * Funçoes Adicionar dados ao spinner
     */

    public static void Add_Numeros_Ao_Spinner(Context context, Spinner nome_variavel_spiner, int numero_minimo, int numero_maximo) {

        String[] sp_dados = new String[numero_maximo];
        for (int i = numero_minimo; i < numero_maximo; i++) {
            sp_dados[i] = String.valueOf(i);
        }
        //Creating the ArrayAdapter instance having the bank name list
        ArrayAdapter sp_rpl_esquerdo_dados = new ArrayAdapter(context, android.R.layout.simple_spinner_item, sp_dados);
        sp_rpl_esquerdo_dados.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nome_variavel_spiner.setAdapter(sp_rpl_esquerdo_dados);

    }


    public static void Spinner_Anos(Context context, Spinner spinner, Boolean inverter_data) {


        ArrayList<Integer> dados_array_ano = new ArrayList<>();
        dados_array_ano.clear();

        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        Date date = new Date();
        int data_atual = Integer.parseInt(dateFormat.format(date));

//        static final String[] Months = new String[] { "Januairy", "Februairy", "Mairch", "April", "May", "June", "July", "August", "September", "October", "November", "December" };

        for (int i = 1950; i <= data_atual; i++) {
            dados_array_ano.add(i);
        }

        if (inverter_data == true) {
            Collections.sort(dados_array_ano, Collections.reverseOrder());
        }


        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_expandable_list_item_1, dados_array_ano);
        spinner.setAdapter(arrayAdapter);


    }

    public static void Spinner_Mes(Context context, Spinner spinner) {

        String[] dados = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_expandable_list_item_1, dados);
        spinner.setAdapter(arrayAdapter);


    }

    public static void Spinner_Estados_do_Brasil(Context context, Spinner spinner) {

        String[] dados = {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"};

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_expandable_list_item_1, dados);
        spinner.setAdapter(arrayAdapter);


    }

    public static void Add_Numeros_Ao_Spinner_Valores_Fixos(Context context, Spinner nome_variavel_spiner) {

        String[] sp_dados = {"Selecione", "Positivo", "Negativo", "Diminuído"};

        //Creating the ArrayAdapter instance having the bank name list
        ArrayAdapter sp_rpl_esquerdo_dados = new ArrayAdapter(context, android.R.layout.simple_spinner_item, sp_dados);
        sp_rpl_esquerdo_dados.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nome_variavel_spiner.setAdapter(sp_rpl_esquerdo_dados);

    }

}

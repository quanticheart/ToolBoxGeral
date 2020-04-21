package com.example.john.oftalmovet._0_ToolBox.ToolBox;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by John on 09/12/2017.
 */

public class ToolBox_Data_e_Hora {

    private static DatePickerDialog datePickerDialog;
    private static TimePickerDialog mTimePicker;

    //////////////////////////////////////////////////////////////////////////////////////
        /*
         *  Função para abrir TimePicker a adicionar o horatio em um TextView
         *
         *  1° Activity a receber o Dialog
         *  2° TextView que sera clicado e recebera o valor apos ser selecionado
         *  3° se for True , o valor retorna 11:00 PM ,  ser for false ,  retorna 23:00
         *
         *  exemplo de uso:
         *
         *
         */
    public static void Funcao_AbrirTimerPicker(final Activity activity, final TextView recebe_hora, final Boolean data_24hora) {
        recebe_hora.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                recebe_hora.setEnabled(false);
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                int seconds = mcurrentTime.get(Calendar.SECOND);


                mTimePicker = new TimePickerDialog(activity, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                        String am = "AM";
                        String pm = "PM";
                        String var = "";

                        if (selectedHour >= 12) {
                            var = pm;
                        } else {
                            var = am;
                        }

//                        txtTime1.setText(String.format("%02d:%02d %s", hour, minute, hourOfDay < 12 ? "am" : "pm"));
                        recebe_hora.setEnabled(true);
                        recebe_hora.setText(String.format("%02d:%02d " + var, selectedHour, selectedMinute));

                    }
                }, hour, minute, data_24hora);//Yes 24 hour time
                mTimePicker.setTitle("       Selecione o Horário");

                mTimePicker.setButton(DatePickerDialog.BUTTON_NEGATIVE, "Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        recebe_hora.setEnabled(true);
                    }
                });

                mTimePicker.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        mTimePicker.cancel();
                        recebe_hora.setEnabled(true);
                    }
                });


                mTimePicker.show();

            }
        });
    }

    //////////////////////////////////////////////////////////////////////////////////////
        /*
         *  Função para abrir DataPicker a adicionar a Data em um TextView
         *
         *  1° Activity a receber o Dialog
         *  2° TextView que sera clicado e recebera o valor apos ser selecionado
         *  3° se for True , o valor retorna 31/12/2018 ,  ser for false ,  retorna 2018/12/31
         *  4° ser for true , a data minima é setada para hoje , ser false , a data e liberada
         *
         *  exemplo de uso:
         *
         *
         */
    public static void Funcao_AbrirDataPicker(final Context context, final TextView recebe_data, final Boolean data_brasil, final Boolean set_data_minima) {

        recebe_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                recebe_data.setEnabled(false);

                final Calendar c = Calendar.getInstance();

                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(context,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                // set day of month , month and year value in the edit text
                                String data_final = "";
                                if (data_brasil == true) {
                                    data_final = String.format("%02d/%02d/" + year, dayOfMonth, (monthOfYear + 1));
                                } else {
                                    data_final = String.format(year + "%02d/%02d/", (monthOfYear + 1), dayOfMonth);
                                }

                                recebe_data.setEnabled(true);
                                recebe_data.setText(data_final);

                            }

                        }, mYear, mMonth, mDay);

                datePickerDialog.setButton(DatePickerDialog.BUTTON_NEGATIVE, "Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        recebe_data.setEnabled(true);
                    }
                });

                datePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        datePickerDialog.cancel();
                        recebe_data.setEnabled(true);
                    }
                });

                if (set_data_minima == true) {
                    datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                }

                datePickerDialog.show();


            }
        });

        //                datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());


    }


    //////////////////////////////////////////////////////////////////////////////////////
        /*
         *  Função para converter TimeStamp para data Legivel
         *
         *  1° recebe 2018/12/31 13:50:50
         *  2° true: converte 13:50 para 01:50 PM
         *  3° true: mostra segundos
         *
         *  exemplo de uso:
         *
         *
         */
    public static String Funcao_Converter_hora_TimeStamp(String timestamp, Boolean retorna_am_pm, Boolean mostra_segundos) {

        String data_raw = timestamp.replaceAll("/", ":").replaceAll(" ", ":");
        String[] s = data_raw.split(":");

        String novaData = s[0] + "/" + s[1] + "/" + s[2];
        String novahora = s[3] + ":" + s[4];
        String Segundos = s[5];
        String Data_Final = "";
        String novo_ampm = "";

        if (retorna_am_pm == true) {

            int ampm = Integer.parseInt(s[3]);
            if (ampm > 12) {
                novo_ampm = "PM";
            } else {
                novo_ampm = "AM";
            }

            switch (Integer.parseInt(s[3])) {
                case 00:
                    novahora = "12:" + s[4];
                    break;
                case 12:
                    novahora = "12:" + s[4];
                    break;
                case 13:
                    novahora = "01:" + s[4];
                    break;
                case 14:
                    novahora = "02:" + s[4];
                    break;
                case 15:
                    novahora = "03:" + s[4];
                    break;
                case 16:
                    novahora = "04:" + s[4];
                    break;
                case 17:
                    novahora = "05:" + s[4];
                    break;
                case 18:
                    novahora = "06:" + s[4];
                    break;
                case 19:
                    novahora = "07:" + s[4];
                    break;
                case 20:
                    novahora = "08:" + s[4];
                    break;
                case 21:
                    novahora = "09:" + s[4];
                    break;
                case 22:
                    novahora = "10:" + s[4];
                    break;
                case 23:
                    novahora = "11:" + s[4];
                    break;
            }
        }

        if (mostra_segundos == true && retorna_am_pm == true) {
            Data_Final = novaData + " as " + novahora + ":" + Segundos + " " + novo_ampm;
        } else if (mostra_segundos == true && retorna_am_pm == false) {
            Data_Final = novaData + " as " + novahora + ":" + Segundos;
        } else if (mostra_segundos == false && retorna_am_pm == true) {
            Data_Final = novaData + " as " + novahora + " " + novo_ampm;
        } else {
            Data_Final = novaData + " as " + novahora;
        }

        Log.w("Data Formatada", Data_Final);
        return Data_Final;
    }


    //////////////////////////////////////////////////////////////////////////////////////
        /*
         *  Função para Retornar uma data para assinaturas
         *  A data e pega do horatio atual
         *
         *  1° true: o mes retora 01 de Janeiro de 2018 False: 01/01/2018
         *  2° true: retona a hora atual
         *  3° true: converte a hora para am pm
         *  4° true: mostra segundos
         *
         *  exemplo de uso:
         *
         *
         */
    public static String Funcao_Data_Para_Assinatura(Boolean mes_palavra, Boolean mostra_hora, Boolean retorna_am_pm, Boolean mostra_segundos) {

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String timestamp = dateFormat.format(date);

        String data_raw = timestamp.replaceAll("/", ":").replaceAll(" ", ":");
        String[] s = data_raw.split(":");

        String novaData = "";

        if (mes_palavra) {

            DateFormat dateFormat2 = new SimpleDateFormat("MMMM");
            Date date2 = new Date();
            String mes = dateFormat2.format(date2);

            novaData = s[0] + " de " + mes + " de " + s[2];

        } else {
            novaData = s[0] + "/" + s[1] + "/" + s[2];
        }

        String novahora = s[3] + ":" + s[4];
        String Segundos = s[5];
        String Data_Final = "";
        String novo_ampm = "";

        if (retorna_am_pm == true) {

            int ampm = Integer.parseInt(s[3]);
            if (ampm > 12) {
                novo_ampm = "PM";
            } else {
                novo_ampm = "AM";
            }

            switch (Integer.parseInt(s[3])) {
                case 00:
                    novahora = "12:" + s[4];
                    break;
                case 12:
                    novahora = "12:" + s[4];
                    break;
                case 13:
                    novahora = "01:" + s[4];
                    break;
                case 14:
                    novahora = "02:" + s[4];
                    break;
                case 15:
                    novahora = "03:" + s[4];
                    break;
                case 16:
                    novahora = "04:" + s[4];
                    break;
                case 17:
                    novahora = "05:" + s[4];
                    break;
                case 18:
                    novahora = "06:" + s[4];
                    break;
                case 19:
                    novahora = "07:" + s[4];
                    break;
                case 20:
                    novahora = "08:" + s[4];
                    break;
                case 21:
                    novahora = "09:" + s[4];
                    break;
                case 22:
                    novahora = "10:" + s[4];
                    break;
                case 23:
                    novahora = "11:" + s[4];
                    break;
            }
        }

        if (mostra_hora) {
            if (mostra_segundos == true && retorna_am_pm == true) {
                Data_Final = novaData + " as " + novahora + ":" + Segundos + " " + novo_ampm;
            } else if (mostra_segundos == true && retorna_am_pm == false) {
                Data_Final = novaData + " as " + novahora + ":" + Segundos;
            } else if (mostra_segundos == false && retorna_am_pm == true) {
                Data_Final = novaData + " as " + novahora + " " + novo_ampm;
            } else {
                Data_Final = novaData + " as " + novahora;
            }
        } else {
            Data_Final = novaData;
        }

        Log.w("Data Formatada", Data_Final);
        return Data_Final;
    }

    //////////////////////////////////////////////////////////////////////////////////////
        /*
         *  Função para verificar se a data é menor que hoje
         *
         *  1° Recebe um timestamp no formato yyyy-MM-dd HH:mm:ss ou seja 2018/12/31 13:50:50
         *
         *  exemplo de uso:
         *
         *
         */
    public static Boolean Funcao_Verifica_Data_Menor_que_Hoje(String data) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date data_atual_nova = null;
        try {
            data_atual_nova = df.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(data_atual_nova);
        cal.add(Calendar.MINUTE, 5);

        String new_data_raw = df.format(cal.getTime());

        Boolean data_valida;
        Log.w("Data Atual", String.valueOf(data_atual_nova.getTime()));
        Log.w("Data Selecionada", String.valueOf(System.currentTimeMillis()));

        if (data_atual_nova.getTime() <= System.currentTimeMillis()) {
            return false;
        } else {
            return true;
        }
    }


    //////////////////////////////////////////////////////////////////////////////////////
        /*
         *  Funçoes padroes que retornam datas e horarios do android
         *
         *  exemplo de uso:
         *
         *
         */


    public static String Funcao_DataCompleta_TimeStamp() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static String Funcao_PegarDia() {
        DateFormat dateFormat = new SimpleDateFormat("dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

    //
    // 1° true retorna 12 , false retorna Dezembro
    // 2° true abrevia o nome do mes como Dezembro para Dez
    //
    public static String Funcao_PegarMes(Boolean retorna_escrito, Boolean abrevia_mes) {
        String tipo_de_retorno = "";
        if (retorna_escrito) {
            if (abrevia_mes) {
                tipo_de_retorno = "MMM";  //esse retorna o mes abreviado
            } else {
                tipo_de_retorno = "MMMM";// esse retorna o nome do mes completo
            }
        } else {
            tipo_de_retorno = "mm";
        }

        DateFormat dateFormat = new SimpleDateFormat(tipo_de_retorno);
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String Funcao_PegarAno() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    //
    // 1° true retorna 11:00 PM , false retorna 23:00
    // 2° true retorna 11:00:00 PM , false retorna 23:00:00
    //
    public static String Funcao_Hora(Boolean retorna_am_pm, Boolean retorna_segundos) {

        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        String timestamp = dateFormat.format(date);
        String[] s = timestamp.split(":");

        int hora = Integer.parseInt(s[0]);
        String Minutos = s[1];
        String Segundos = s[2];
        //
        String novahora = "";
        String var = "";

        if (retorna_am_pm) {

            String am = "AM";
            String pm = "PM";

            if (hora >= 12) {
                var = pm;
            } else {
                var = am;
            }

            switch (hora) {
                case 00:
                    novahora = "12" + s[1];
                    break;
                case 12:
                    novahora = "12:" + s[1];
                    break;
                case 13:
                    novahora = "01:" + s[1];
                    break;
                case 14:
                    novahora = "02:" + s[1];
                    break;
                case 15:
                    novahora = "03:" + s[1];
                    break;
                case 16:
                    novahora = "04:" + s[1];
                    break;
                case 17:
                    novahora = "05:" + s[1];
                    break;
                case 18:
                    novahora = "06:" + s[1];
                    break;
                case 19:
                    novahora = "07:" + s[1];
                    break;
                case 20:
                    novahora = "08:" + s[1];
                    break;
                case 21:
                    novahora = "09:" + s[1];
                    break;
                case 22:
                    novahora = "10:" + s[1];
                    break;
                case 23:
                    novahora = "11:" + s[1];
                    break;
            }


        } else {
            novahora = s[0] + ":" + s[1];
        }

        String HoraFinal = "";

        if (retorna_am_pm && retorna_segundos) {
            HoraFinal = novahora + ":" + s[2] + " " + var;
        } else if (retorna_am_pm) {
            HoraFinal = novahora + " " + var;
        } else {
            HoraFinal = s[0] + ":" + s[1];
        }

        return HoraFinal;

    }


    public static String Funcao_PegarData_USA() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String Funcao_PegarData_BR() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }





}

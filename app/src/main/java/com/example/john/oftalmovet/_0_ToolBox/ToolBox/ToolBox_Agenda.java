package com.example.john.oftalmovet._0_ToolBox.ToolBox;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.CalendarContract;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by John on 09/12/2017.
 */

public class ToolBox_Agenda {

    public static void Cadastra_No_Calendario_Android(Context context, Map mapa_de_dados) {

//
//
//                Intent intent = new Intent(Intent.ACTION_INSERT);
//        intent.setType("vnd.android.cursor.item/event");
//        intent.putExtra(CalendarContract.Events.TITLE, "Learn Android");
//        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Home suit home");
//        intent.putExtra(CalendarContract.Events.DESCRIPTION, "Download Examples");
//
//// Setting dates
//        GregorianCalendar calDate = new GregorianCalendar(2012, 10, 02);
//        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
//                calDate.getTimeInMillis());
//        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
//                calDate.getTimeInMillis());
//
//// make it a full day event
//        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
//
//// make it a recurring Event
//        intent.putExtra(CalendarContract.Events.RRULE, "FREQ=WEEKLY;COUNT=11;WKST=SU;BYDAY=TU,TH");
//
//// Making it private and shown as busy
//        intent.putExtra(CalendarContract.Events.ACCESS_LEVEL, CalendarContract.Events.ACCESS_PRIVATE);
//        intent.putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);
//
//        intent.setData(CalendarContract.Events.CONTENT_URI);
//        context.startActivity(intent);

//        long calID = 3;
//        long startMillis = 0;
//        long endMillis = 0;
//        Calendar beginTime = Calendar.getInstance();
//        beginTime.set(2018, 2, 28, 7, 30);// set(int year, int month, int day, int hourOfDay, int minute)
//        startMillis = beginTime.getTimeInMillis();
//        Calendar endTime = Calendar.getInstance();
//        endTime.set(2018, 2, 28, 8, 30);
//        endMillis = endTime.getTimeInMillis();
//
//        TimeZone tz = TimeZone.getDefault();
//
//        ContentResolver cr = context.getContentResolver();
//        ContentValues values = new ContentValues();
//        values.put(CalendarContract.Events.DTSTART, startMillis);
//        values.put(CalendarContract.Events.DTEND, endMillis);
//        values.put(CalendarContract.Events.TITLE, "Jazzercise");
//        values.put(CalendarContract.Events.DESCRIPTION, "Group workout");
//        values.put(CalendarContract.Events.CALENDAR_ID, calID);
//        values.put(CalendarContract.Events.EVENT_TIMEZONE, tz.getID());
//        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);
//
//        // get the event ID that is the last element in the Uri
//        long eventID = Long.parseLong(uri.getLastPathSegment());
//
//
//        Uri.Builder builder = CalendarContract.CONTENT_URI.buildUpon();
//        builder.appendPath("time");
//        ContentUris.appendId(builder, startMillis);
//        Intent intent = new Intent(Intent.ACTION_VIEW).setData(builder.build());
//        context.startActivity(intent);
//


//certo

        String nome_cliente = String.valueOf(mapa_de_dados.get("nome_cliente"));
        String nome = String.valueOf(mapa_de_dados.get("nome_animal"));
        String descricao = String.valueOf(mapa_de_dados.get("observacao"));
        String data_raw = String.valueOf(mapa_de_dados.get("data_hora"));
        String descricao_final = "";

        if (nome_cliente.equals("")) {
            descricao_final = "Nome do Paciente:" + nome + "\nObservação:" + descricao;
        } else if (nome.equals("")) {
            descricao_final = "Nome do cliente:" + nome_cliente + "\nObservação:" + descricao;
        } else if (descricao.equals("")) {
            descricao_final = "Nome do cliente:" + nome_cliente + "\nNome do Paciente:" + nome;
        } else if (nome_cliente.equals("") && descricao.equals("")) {
            descricao_final = "Nome do Paciente:" + nome;
        } else if (nome.equals("") && descricao.equals("")) {
            descricao_final = "Nome do cliente:" + nome_cliente;
        } else if (nome.equals("") && nome_cliente.equals("")) {
            descricao_final = "Observação:" + descricao;
        } else {
            descricao_final = "Nome do cliente:" + nome_cliente + "\nNome do Paciente:" + nome + "\nObservação:" + descricao;
        }

        String data = data_raw.replaceAll(":", "-").replaceAll(" ", "-");
        String[] s = data.split("-");

        int ano = Integer.parseInt(s[0]);
        int mes = Integer.parseInt(s[1]);
        int dia = Integer.parseInt(s[2]);
        int hora = Integer.parseInt(s[3]);
        int minutos = Integer.parseInt(s[4]);

        Log.w("Array data = ", Arrays.toString(s));


        //need to set the real times
        Calendar beginTime = Calendar.getInstance();

        beginTime.set(ano, mes - 1, dia, hora, minutos);
        //need to set the real end time
        Calendar endTime = Calendar.getInstance();
        endTime.set(ano, mes - 1, dia, hora, minutos);

        //create content that will go into the calendar
        ContentValues calEvent = new ContentValues();
        //create ability to insert into the calendar
        ContentResolver cr = context.getContentResolver();
        //where/when/id_for_insert/start_time/end_time/time_zone
        //need address/description
        calEvent.put(CalendarContract.Events.CALENDAR_ID, 1); // XXX pick)


        calEvent.put(CalendarContract.Events.DTSTART, beginTime.getTimeInMillis());
        calEvent.put(CalendarContract.Events.DTEND, beginTime.getTimeInMillis());
        calEvent.put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().getID());
        calEvent.put(CalendarContract.Events.TITLE, nome);
        calEvent.put(CalendarContract.Events.EVENT_LOCATION, "");
        calEvent.put(CalendarContract.Events.DESCRIPTION, descricao_final);
        Uri eventsUri = Uri.parse("content://com.android.calendar/events");
        Uri EVENTS_URI = Uri.parse(CalendarContract.Events.CONTENT_URI.toString());
        Uri uri = cr.insert(EVENTS_URI, calEvent);
        //get id for reminders


//        ToolBox_MSG.ExibeMSG("Contato adicionado com sucesso!" , context);
        ToolBox_MSG.Funcao_Toast( context , nome + " Adicionado a agenda!");

//        Toast.makeText(context, uri + " was added to the Calendar", Toast.LENGTH_SHORT).show();
        //Toast.makeText(getActivity(), obj.getDescription() + " was added to the Calendar", Toast.LENGTH_SHORT).show();

    }


    public static void Abrir_Por_Data_Calendario_Android(Context context, String data_raw_) {


        String data_raw = data_raw_;

        String time = "2018/12/21 7:41:00"; // data teste

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // formatar de acordo com a data recebida
        long data_raw_to_milleseconds = 0;
        try {
            Date mDate = sdf.parse(data_raw);
            data_raw_to_milleseconds = mDate.getTime();
            System.out.println("Date in milli :: " + data_raw_to_milleseconds);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Log.w("Data para abrir = ", String.valueOf(data_raw_to_milleseconds));

        Uri.Builder builder = CalendarContract.CONTENT_URI.buildUpon();
        builder.appendPath("time");
        ContentUris.appendId(builder, data_raw_to_milleseconds);
        Intent intent = new Intent(Intent.ACTION_VIEW)
                .setData(builder.build());
        context.startActivity(intent);
    }


    public static String convertTimeInMillisToDateString(long timeInMillis, String DATE_TIME_FORMAT) {
        Date d = new Date(timeInMillis);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // formatar de acordo com a data recebida
        return sdf.format(d);
    }
}

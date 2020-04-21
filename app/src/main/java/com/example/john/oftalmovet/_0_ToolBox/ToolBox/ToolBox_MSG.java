package com.example.john.oftalmovet._0_ToolBox.ToolBox;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by John on 09/12/2017.
 */

public class ToolBox_MSG {


    //
    // 1° Context
    // 2° MSG para o Toast
    //

    public static void Funcao_Toast(Context context , String texto) {
        Toast.makeText(
                context,
                texto,
                Toast.LENGTH_LONG
        ).show();
    }


    //
    // 1° Activity a receber o Alert
    // 2° MSG para o Alert
    // 3° Evento de Click para receber no evento ok
    //
    public static void showMessageOKCancel(Activity activity, String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(activity)
                .setMessage(message)
                .setPositiveButton("Permitir", okListener)
                .setNegativeButton("Cancelar", null)
                .create()
                .show();
    }


}

package com.example.john.oftalmovet._0_ToolBox.ToolBox;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
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

public class ToolBox_Android_Intent {

    public void Função_Android_Intent_OPEN_DOCUMENT(Activity activity , int CODE_REQUEST_RESULT) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            activity.startActivityForResult(new Intent(Intent.ACTION_GET_CONTENT).setType("image/*"),
                    CODE_REQUEST_RESULT);
        } else {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            activity.startActivityForResult(intent, CODE_REQUEST_RESULT);
        }
    }

}

package com.example.john.oftalmovet._0_ToolBox.ToolBox;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import static android.Manifest.permission.READ_CALENDAR;
import static android.Manifest.permission.WRITE_CALENDAR;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_CONTACTS;
import static android.Manifest.permission.WRITE_CONTACTS;
import static android.Manifest.permission.GET_ACCOUNTS;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.READ_CALL_LOG;
import static android.Manifest.permission.WRITE_CALL_LOG;
import static android.Manifest.permission.ADD_VOICEMAIL;
import static android.Manifest.permission.USE_SIP;
import static android.Manifest.permission.PROCESS_OUTGOING_CALLS;
import static android.Manifest.permission.BODY_SENSORS;
import static android.Manifest.permission.SEND_SMS;
import static android.Manifest.permission.RECEIVE_SMS;
import static android.Manifest.permission.READ_SMS;
import static android.Manifest.permission.RECEIVE_WAP_PUSH;
import static android.Manifest.permission.RECEIVE_MMS;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;


/**
 * Created by John on 24/02/2018.
 */

public class ToolBox_Permissao {

//    public static final int VERIFICA_ACCESS_NETWORK_STATE = 1;
//    public static final int VERIFICA_ACCESS_NOTIFICATION_POLICY = 2;
//    public static final int VERIFICA_ACCESS_WIFI_STATE = 3;
//    public static final int VERIFICA_BLUETOOTH = 4;
//    public static final int VERIFICA_BLUETOOTH_ADMIN = 5;
//    public static final int VERIFICA_BROADCAST_STICKY = 6;
//    public static final int VERIFICA_CHANGE_NETWORK_STATE = 7;
//    public static final int VERIFICA_CHANGE_WIFI_MULTICAST_STATE = 8;
//    public static final int VERIFICA_CHANGE_WIFI_STATE = 9;
//    public static final int VERIFICA_DISABLE_KEYGUARD = 10;
//    public static final int VERIFICA_EXPAND_STATUS_BAR = 11;
//    public static final int VERIFICA_FLASHLIGHT = 12;
//    public static final int VERIFICA_GET_PACKAGE_SIZE = 13;
//    public static final int VERIFICA_INTERNET = 14;
//    public static final int VERIFICA_KILL_BACKGROUND_PROCESSES = 15;
//    public static final int VERIFICA_MODIFY_AUDIO_SETTINGS = 16;
//    public static final int VERIFICA_NFC = 17;
//    public static final int VERIFICA_READ_SYNC_SETTINGS = 18;
//    public static final int VERIFICA_READ_SYNC_STATS = 19;
//    public static final int VERIFICA_RECEIVE_BOOT_COMPLETED = 20;
//    public static final int VERIFICA_REORDER_TASKS = 21;
//    public static final int VERIFICA_REQUEST_INSTALL_PACKAGES = 22;
//    public static final int VERIFICA_SET_TIME_ZONE = 23;
//    public static final int VERIFICA_SET_WALLPAPER = 24;
//    public static final int VERIFICA_SET_WALLPAPER_HINTS = 25;
//    public static final int VERIFICA_TRANSMIT_IR = 26;
//    public static final int VERIFICA_USE_FINGERPRINT = 27;
//    public static final int VERIFICA_VIBRATE = 28;
//    public static final int VERIFICA_WAKE_LOCK = 29;
//    public static final int VERIFICA_WRITE_SYNC_SETTINGS = 30;
//    public static final int VERIFICA_SET_ALARM = 31;
//    public static final int VERIFICA_INSTALL_SHORTCUT = 32;
//    public static final int VERIFICA_UNINSTALL_SHORTCUT = 33;

    //    CALENDAR
    public static final int VERIFICA_READ_CALENDAR = 34;
    public static final int VERIFICA_WRITE_CALENDAR = 35;
    //    CAMERA
    public static final int VERIFICA_CAMERA = 36;
    //    CONTACTS
    public static final int VERIFICA_READ_CONTACTS = 37;
    public static final int VERIFICA_WRITE_CONTACTS = 38;
    public static final int VERIFICA_GET_ACCOUNTS = 39;
    //    LOCATION
    public static final int VERIFICA_ACCESS_FINE_LOCATION = 40;
    public static final int VERIFICA_ACCESS_COARSE_LOCATION = 41;
    //    MICROPHONE
    public static final int VERIFICA_RECORD_AUDIO = 42;
    //    PHONE
    public static final int VERIFICA_READ_PHONE_STATE = 43;
    public static final int VERIFICA_CALL_PHONE = 44;
    public static final int VERIFICA_READ_CALL_LOG = 45;
    public static final int VERIFICA_WRITE_CALL_LOG = 46;
    public static final int VERIFICA_ADD_VOICEMAIL = 47;
    public static final int VERIFICA_USE_SIP = 48;
    public static final int VERIFICA_PROCESS_OUTGOING_CALLS = 49;
    //    SENSORS
    public static final int VERIFICA_BODY_SENSORS = 50;
    //    SMS
    public static final int VERIFICA_SEND_SMS = 51;
    public static final int VERIFICA_RECEIVE_SMS = 52;
    public static final int VERIFICA_READ_SMS = 53;
    public static final int VERIFICA_RECEIVE_WAP_PUSH = 54;
    public static final int VERIFICA_RECEIVE_MMS = 55;
    //    STORAGE
    public static final int VERIFICA_READ_EXTERNAL_STORAGE = 56;
    public static final int VERIFICA_WRITE_EXTERNAL_STORAGE = 57;

    public static Boolean Funcao_Verifica_Permissao(final Activity activity, int codigo_da_permissao) {
        if (activity == null) {
            return false;
        }

        String codigo_permissao = "";
        String texto_permissao = "";

        switch (codigo_da_permissao) {

            case 34:
                codigo_permissao = READ_CALENDAR;
                texto_permissao = "";
                break;
            case 35:
                codigo_permissao = WRITE_CALENDAR;
                texto_permissao = "É necessária permissão para gravar os dados no seu calendário interno do celular, sem esta permissão, não é possível criar dados na agenda.";
                break;
            case 36:
                codigo_permissao = CAMERA;
                texto_permissao = "É necessário ter permissão para utilizar a câmera do aparelho.";
                break;
            case 37:
                codigo_permissao = READ_CONTACTS;
                texto_permissao = "";
                break;
            case 38:
                codigo_permissao = WRITE_CONTACTS;
                texto_permissao = "";
                break;
            case 39:
                codigo_permissao = GET_ACCOUNTS;
                texto_permissao = "";
                break;
            case 40:
                codigo_permissao = ACCESS_FINE_LOCATION;
                texto_permissao = "";
                break;
            case 41:
                codigo_permissao = ACCESS_COARSE_LOCATION;
                texto_permissao = "";
                break;
            case 42:
                codigo_permissao = RECORD_AUDIO;
                texto_permissao = "";
                break;
            case 43:
                codigo_permissao = READ_PHONE_STATE;
                texto_permissao = "";
                break;
            case 44:
                codigo_permissao = CALL_PHONE;
                texto_permissao = "";
                break;
            case 45:
                codigo_permissao = READ_CALL_LOG;
                texto_permissao = "";
                break;
            case 46:
                codigo_permissao = WRITE_CALL_LOG;
                texto_permissao = "";
                break;
            case 47:
                codigo_permissao = ADD_VOICEMAIL;
                texto_permissao = "";
                break;
            case 48:
                codigo_permissao = USE_SIP;
                texto_permissao = "";
                break;
            case 49:
                codigo_permissao = PROCESS_OUTGOING_CALLS;
                texto_permissao = "";
                break;
            case 50:
                codigo_permissao = BODY_SENSORS;
                texto_permissao = "";
                break;
            case 51:
                codigo_permissao = SEND_SMS;
                texto_permissao = "";
                break;
            case 52:
                codigo_permissao = RECEIVE_SMS;
                texto_permissao = "";
                break;
            case 53:
                codigo_permissao = READ_SMS;
                texto_permissao = "";
                break;
            case 54:
                codigo_permissao = RECEIVE_WAP_PUSH;
                texto_permissao = "";
                break;
            case 55:
                codigo_permissao = RECEIVE_MMS;
                texto_permissao = "";
                break;
            case 56:
                codigo_permissao = READ_EXTERNAL_STORAGE;
                texto_permissao = "";
                break;
            case 57:
                codigo_permissao = WRITE_EXTERNAL_STORAGE;
                texto_permissao = "É necessário ter permissão para guardar os arquivos no armazenamento local.";
                break;

        }

        Boolean permissao = ContextCompat.checkSelfPermission(activity, codigo_permissao) == PackageManager.PERMISSION_GRANTED;

        if (permissao == false) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, codigo_permissao)) {
                // Caso o usuário tenha negado a permissão anteriormente, e não tenha marcado o check "nunca mais mostre este alerta"
                // Podemos mostrar um alerta explicando para o usuário porque a permissão é importante.
                //ActivityCompat.requestPermissions(contexto,new String[]{WRITE_EXTERNAL_STORAGE},0);

                final String finalCodigo_permissao = codigo_permissao; // variavel TMP

                if (texto_permissao.equals("")) {
                    texto_permissao = "É necessário uma permissao de uso!";
                }

                ToolBox_MSG.showMessageOKCancel(
                        activity,
                        texto_permissao,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Pede_Permissao(activity, finalCodigo_permissao, false);
                            }
                        });


                return false;
            } else {
                Pede_Permissao(activity, codigo_permissao, true);
                return false;
            }

        } else {

//            ToolBox_MSG.ExibeMSG("permitido", activity);
            return true;
        }

    }

    private static void Pede_Permissao(Activity activity, String finalCodigo_permissao, Boolean primeira_vez) {

        ActivityCompat.requestPermissions(activity, new String[]{finalCodigo_permissao}, 0);
        System.out.println("SOLICITANDO PERMISSÃO PARA (" + finalCodigo_permissao + "), NÃO É A PRIMEIRA VEZ");

        if (primeira_vez == false) {

            final Intent i = new Intent();
            i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            i.addCategory(Intent.CATEGORY_DEFAULT);
            i.setData(Uri.parse("package:" + activity.getPackageName()));
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
            activity.startActivity(i);

        }

    }


}

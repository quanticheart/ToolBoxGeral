package com.example.john.oftalmovet._05_Bateria;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Chronometer;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.john.oftalmovet.R;
import com.example.john.oftalmovet._0_ToolBox.ToolBox.ToolBox_Chama_Activity;


public class Carga_Bateria extends AppCompatActivity {

    private Context context;
    private Context context_activity;
    private Activity activity;
    private Window window;
    /////////////////////////////////////////////////////////////////////////////

    /*
     *  _04_Inicio de Variaveis
     */
    private TextView mTextTelaLigada;
    private TextView mTextWifi;
    private TextView mTextWifiTipo;
    private TextView mTextTipo;
    private TextView mTextCarregando;
    private TextView mTextViewInfo;
    private TextView mTextViewPercentage;
    private TextView mUsuarioPresent;
    private ProgressBar mProgressBar;

    private Chronometer chronometer;

    private int mProgressStatus = 0;

    private static boolean TelaLigada = true;
    private static boolean UsuarioPresente = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._05_bateria);

        initVars();
        initActions();

    }

    private void initVars() {

        //Var padroes
        context = getApplicationContext();
        context_activity = Carga_Bateria.this;
        activity = Carga_Bateria.this;
        window = getWindow();
        ////////////////////////////////////////////////////////////////////////

        /*
         *  _04_Inicio de findViewById's
         */
        mTextTelaLigada = (TextView) findViewById(R.id.tv_tela_ligada);

        mTextWifi = (TextView) findViewById(R.id.tv_tela_wifi);
        mTextWifiTipo = (TextView) findViewById(R.id.tv_tela_wifi_tipo);

        mUsuarioPresent = (TextView) findViewById(R.id.tv_tela_usuario);

        chronometer = findViewById(R.id.time);


        mTextTipo = (TextView) findViewById(R.id.tv_tipo_carga);
        mTextViewInfo = (TextView) findViewById(R.id.tv_info);
        mTextCarregando = (TextView) findViewById(R.id.tv_carregando);
        mTextViewPercentage = (TextView) findViewById(R.id.tv_percentage);
        mProgressBar = (ProgressBar) findViewById(R.id.pb);

    }

    private void initActions() {
        /*
         *  _04_Inicio de Ações da Pagina
         */

        IntentFilter iFilter_Bateria = new IntentFilter();
        iFilter_Bateria.addAction(Intent.ACTION_BATTERY_CHANGED);  // pega a ação da bateria
        registerReceiver(mBroadcast_Bateria, iFilter_Bateria); // registra o BroadCast no Android

        IntentFilter iFilter_Status_Conexao = new IntentFilter();
        registerReceiver(mBroadcast_Status_Conexao, iFilter_Status_Conexao); // registra o BroadCast no Android

        IntentFilter iFilter_Status_Tela = new IntentFilter();
        iFilter_Status_Tela.addAction(Intent.ACTION_SCREEN_ON); // pega a ação de ligar a tela
        iFilter_Status_Tela.addAction(Intent.ACTION_SCREEN_OFF); // pega a ação de desligar a tela
        registerReceiver(mBroadcast_Status_Tela, iFilter_Status_Tela); // registra o BroadCast no Android

        IntentFilter iFilter_Usuario_Presente = new IntentFilter();
        iFilter_Usuario_Presente.addAction(Intent.ACTION_USER_PRESENT); // pega a ação de usuario presente no aplicativo
        registerReceiver(mBroadcast_Usuario_Presente, iFilter_Usuario_Presente);

    }


    private BroadcastReceiver mBroadcast_Bateria = new BroadcastReceiver() {


        @Override
        public void onReceive(Context context, Intent intent) {
            Log.w("B. Bateria", "Registrado");
            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);

            boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL;

            if (isCharging) {
                mTextCarregando.setText("Carregando");
            } else {
                mTextCarregando.setText("Não Carregando");
            }


            //////////////////////////////////////////////////////////////////////
            int chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
            boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
            boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

            if (usbCharge) {
                mTextTipo.setText("Carregando Via USB");
            } else if (acCharge) {
                mTextTipo.setText("Carregando Via Tomada");
            } else {
                mTextTipo.setText("Não Carregando");
            }


            ///////////////////////////////////////////////////////////////////////


            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

            mTextViewInfo.setText("Battery Scale : " + scale);

            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);

            mTextViewInfo.setText(mTextViewInfo.getText() + "\nBattery Level : " + level);

            float percentage = level / (float) scale;

            mProgressStatus = (int) ((percentage) * 100);

            mTextViewPercentage.setText("" + mProgressStatus + "%");

            mTextViewInfo.setText(mTextViewInfo.getText() + "\nPercentage : " + mProgressStatus + "%");

            mProgressBar.setProgress(mProgressStatus);

        }

    };


    private BroadcastReceiver mBroadcast_Status_Conexao = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.w("B. Conexão", "Registrado");

            boolean wifi = Status_Conexao(context);

            if (wifi) {
                mTextWifi.setText("Conexão ok");
            } else {
                mTextWifi.setText("Sem Conexão");
            }

            ////////////////////////////////////////////////////////////////////////////////

            ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

            //For 3G check
            boolean is3g = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
            //For WiFi Check
            boolean isWifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();

            if (isWifi) {
                mTextWifiTipo.setText("Via Wifi");
            } else if (is3g) {
                mTextWifiTipo.setText("Via 3G");
            } else {
                mTextWifiTipo.setText("Sem Conexão");
            }

        }

        private boolean Status_Conexao(Context context) {
            boolean connect;
            ConnectivityManager connMgr = (ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                connect = true;
            } else {
                connect = false;
            }
            return connect;
        }


    };


    private BroadcastReceiver mBroadcast_Status_Tela = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.w("B. Tela", "Registrado");

            if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                mTextTelaLigada.setText("Tela Desligada");
                chronometer.stop();
                chronometer.setText("00:00");
                TelaLigada = false;
            } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                mTextTelaLigada.setText("A tela esta em uso");
                chronometer.start();
                TelaLigada = true;
            }

        }
    };

    private BroadcastReceiver mBroadcast_Usuario_Presente = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.w("B. Usuario", "Registrado");

            if (intent.getAction().equals(Intent.ACTION_USER_PRESENT)) {
                UsuarioPresente = true;
                mUsuarioPresent.setText("Usuario Presente");
            } else
                mUsuarioPresent.setText("Não Presente");

        }

    };


    @Override
    protected void onPause() {
        // WHEN THE SCREEN IS ABOUT TO TURN OFF
        if (TelaLigada) {
            // THIS IS THE CASE WHEN ONPAUSE() IS CALLED BY THE SYSTEM DUE TO A SCREEN STATE CHANGE
            System.out.println("SCREEN TURNED OFF");
            System.out.println("User " + UsuarioPresente);
            mTextTelaLigada.setText("Tela Desligada");
            chronometer.stop();
            chronometer.setBase(SystemClock.elapsedRealtime());
        } else {
            // THIS IS WHEN ONPAUSE() IS CALLED WHEN THE SCREEN STATE HAS NOT CHANGED
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        // ONLY WHEN SCREEN TURNS ON
        if (TelaLigada) {
            mTextTelaLigada.setText("Tela Ligada");

            // THIS IS WHEN ONRESUME() IS CALLED DUE TO A SCREEN STATE CHANGE
            System.out.println("SCREEN TURNED ON");
            System.out.println("User " + UsuarioPresente);
        } else {
            // THIS IS WHEN ONRESUME() IS CALLED WHEN THE SCREEN STATE HAS NOT CHANGED
        }

        if (UsuarioPresente) {
            chronometer.start();
        } else {

        }
        super.onResume();
    }


//    @Override
//    protected void onDestroy() {
//        if (mBroadcast_Bateria != null) {
//            unregisterReceiver(mBroadcast_Bateria);
//            mBroadcast_Bateria = null;
//        }
//        super.onDestroy();
//    }

    @Override
    public void onBackPressed() {
        ToolBox_Chama_Activity.Funcao_Chama_TelaPrincipal(activity);
        super.onBackPressed();
    }

}

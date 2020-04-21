package com.example.john.oftalmovet._01_Debug;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.format.DateFormat;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.john.oftalmovet.R;
import com.example.john.oftalmovet._0_ToolBox.ToolBox.ToolBox_Chama_Activity;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Debug extends AppCompatActivity {

    private Context context;
    private Context context_activity;
    private Activity activity;
    private Window window;
    /////////////////////////////////////////////////////////////////////////////

    /*
     *  _04_Inicio de Variaveis
     */

    private TextView tv_infos;

    public static final int REQUEST_CODE_PHONE_STATE_READ = 100;
    private int checkedPermission = PackageManager.PERMISSION_DENIED;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._01_debug);

        initVars();
        initActions();

    }

    private void initVars() {

        //Var padroes
        context = getApplicationContext();
        context_activity = Debug.this;
        activity = Debug.this;
        window = getWindow();
        ////////////////////////////////////////////////////////////////////////

        /*
         *  _04_Inicio de findViewById's
         */


    }

    private void initActions() {
        /*
         *  _04_Inicio de Ações da Pagina
         */

        checkedPermission = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE);

        if (Build.VERSION.SDK_INT >= 23 && checkedPermission != PackageManager.PERMISSION_GRANTED) {
            showMessageOKCancel("É nescesssario ter Acesso a Informaçoes do Aparelho Para Obter Melhor Desempenho!",
                    new DialogInterface.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.M)
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE},
                                    REQUEST_CODE_PHONE_STATE_READ);
                        }
                    });
            return;
        } else
            checkedPermission = PackageManager.PERMISSION_GRANTED;


        String Language = Locale.getDefault().getDisplayLanguage().toString();
        TextView debug_liguagem = findViewById(R.id.debug_liguagem);
        debug_liguagem.setText(Language);

        String serviceName = activity.TELEPHONY_SERVICE;
        TelephonyManager m_telephonyManager = (TelephonyManager) activity.getSystemService(serviceName);
        String IMEI, IMSI;
        IMEI = m_telephonyManager.getDeviceId();
        IMSI = m_telephonyManager.getSubscriberId();

        TextView smartphone = findViewById(R.id.debug_smartphone);
        smartphone.setText(Build.MANUFACTURER + " " + Build.MODEL);

        TextView fabricante = findViewById(R.id.debug_fabricante);
        fabricante.setText(Build.BRAND);

        TextView imei = findViewById(R.id.debug_imei);
        imei.setText(IMEI);

        TextView imsi = findViewById(R.id.debug_imsi);
        imsi.setText(IMSI);

        ///////////////////////////////////////////////////////////////

        TextView sistema = findViewById(R.id.debug_sistema);
        sistema.setText("Android " + Build.VERSION.RELEASE);

        TextView vercao_atualizacao = findViewById(R.id.debug_vercao_atualizacao);
        vercao_atualizacao.setText(Build.DISPLAY);

        TextView v_incremental = findViewById(R.id.debug_v_incremental);
        v_incremental.setText(Build.VERSION.INCREMENTAL);

        TextView debug_sdk = findViewById(R.id.debug_sdk);
        debug_sdk.setText(String.valueOf(Build.VERSION.SDK_INT));

        //para pegar o nome da versao do android
        Field[] fields = Build.VERSION_CODES.class.getFields();
        String osName = fields[Build.VERSION.SDK_INT + 1].getName();
        TextView debug_nome_sistema = findViewById(R.id.debug_nome_sistema);
        debug_nome_sistema.setText(osName);

        ///////////////////////////////////////////////////////////////


        TextView debug_board = findViewById(R.id.debug_board);
        debug_board.setText(Build.BOARD);

        TextView debug_bootloard = findViewById(R.id.debug_bootloard);
        debug_bootloard.setText(Build.BOOTLOADER);

        TextView debug_cpuabi = findViewById(R.id.debug_cpuabi);
        debug_cpuabi.setText(Build.CPU_ABI);

        TextView debug_cpuabi2 = findViewById(R.id.debug_cpuabi2);
        debug_cpuabi2.setText(Build.CPU_ABI2);

        TextView debug_hardware = findViewById(R.id.debug_hardware);
        debug_hardware.setText(Build.HARDWARE);

        TextView debug_serial = findViewById(R.id.debug_serial);
        debug_serial.setText(Build.SERIAL);

        TextView debug_produto = findViewById(R.id.debug_produto);
        debug_produto.setText(Build.PRODUCT);

        TextView debug_user = findViewById(R.id.debug_user);
        debug_user.setText(Build.USER);

        //////////////////////////////////////////////////

        TextView debug_host = findViewById(R.id.debug_host);
        debug_host.setText(Build.HOST);

        TextView debug_id = findViewById(R.id.debug_id);
        debug_id.setText(Build.ID);

        TextView debug_tags = findViewById(R.id.debug_tags);
        debug_tags.setText(Build.TAGS);

        long millisecond = Long.parseLong(String.valueOf(Build.TIME));
        String dateString = DateFormat.format("dd/MM/yyyy", new Date(millisecond)).toString();
        TextView debug_time = findViewById(R.id.debug_time);
        debug_time.setText(String.valueOf(dateString));

        TextView debug_type = findViewById(R.id.debug_type);
        debug_type.setText(Build.TYPE);

        TextView debug_UNKNOWN = findViewById(R.id.debug_UNKNOWN);
        debug_UNKNOWN.setText(Build.UNKNOWN);

        TextView debug_FINGERPRINT = findViewById(R.id.debug_FINGERPRINT);
        debug_FINGERPRINT.setText(Build.FINGERPRINT);

        //////////////////////////////////////////////////////////////
        /*
        *  Como obter a informação da CPU do dispositivo Android de forma programática.
        *
        */

       TextView cpu = (TextView)findViewById(R.id.debug_cpu);

        byte[] byteArry = new byte[1024];
        String[] DATA = {"/system/bin/cat", "/proc/cpuinfo"};
        String Holder = "";

        try{
            ProcessBuilder processBuilder = new ProcessBuilder(DATA);

            Process process = processBuilder.start();

            InputStream inputStream = process.getInputStream();

            while(inputStream.read(byteArry) != -1){

                Holder = Holder + new String(byteArry);
            }

            inputStream.close();

        } catch(IOException ex){

            ex.printStackTrace();
        }

        cpu.setText(Holder);


        //////////////////////////////////////////////////////////////
        /*
        * Como obter toda a lista de sensores disponíveis no dispositivo Android, programaticamente.
        *
        */

        TextView listView ;
        SensorManager sensorManager ;
        List<Sensor> listsensor;
        List<String> liststring ;
        ArrayAdapter<String> adapter ;

        listView = (TextView)findViewById(R.id.debug_sensor);

        liststring = new ArrayList<String>();

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);

        listsensor = sensorManager.getSensorList(Sensor.TYPE_ALL);

        for(int i=0; i<listsensor.size(); i++){

            liststring.add(listsensor.get(i).getName()+"\n");
        }


        listView.setText(liststring.toString());

    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(activity)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancelar", null)
                .create()
                .show();
    }

    @Override
    public void onBackPressed() {
        ToolBox_Chama_Activity.Funcao_Chama_TelaPrincipal(activity);
        super.onBackPressed();
    }
}

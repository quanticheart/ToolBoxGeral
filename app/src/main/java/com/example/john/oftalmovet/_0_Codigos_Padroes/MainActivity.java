package com.example.john.oftalmovet._0_Codigos_Padroes;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.example.john.oftalmovet.R;
import com.example.john.oftalmovet._0_ToolBox.ToolBox.ToolBox_Chama_Activity;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private Context context_activity;
    private Activity activity;
    private Window window;
    /////////////////////////////////////////////////////////////////////////////

    /*
     *  _04_Inicio de Variaveis
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._01_splash);

        initVars();
        initActions();

    }

    private void initVars() {

        //Var padroes
        context = getApplicationContext();
        context_activity = MainActivity.this;
        activity = MainActivity.this;
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


    }

    @Override
    public void onBackPressed() {
        ToolBox_Chama_Activity.Funcao_Chama_TelaPrincipal(activity);
        super.onBackPressed();
    }
}

package com.example.john.oftalmovet._04_PaintView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;

import com.example.john.oftalmovet.R;
import com.example.john.oftalmovet._01_Debug.Debug;
import com.example.john.oftalmovet._0_ToolBox.ToolBox.ToolBox_Chama_Activity;

public class _04_Inicio extends AppCompatActivity {

    private Context context;
    private Context context_activity;
    private Activity activity;
    private Window window;
    /////////////////////////////////////////////////////////////////////////////

    /*
     *  _04_Inicio de Variaveis
     */
    public Toolbar ToolBarPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._04_inicio);

        initVars();
        initActions();

    }

    private void initVars() {

        //Var padroes
        context = getApplicationContext();
        context_activity = _04_Inicio.this;
        activity = _04_Inicio.this;
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


        //////////////////////////////////////////////////////////////////////////////////////
        /*
         *  Pagina de Paint simples
         *
         */
        Button btn_paintview = findViewById(R.id.btn_paintview1);
        ToolBox_Chama_Activity.Funcao_Chama_Activity(btn_paintview , activity, PaintActivity.class, null ,false, false, "");



        //////////////////////////////////////////////////////////////////////////////////////
        /*
         *  Pagina de Paint Prototipo
         *
         */
        Button btn_paintview2 = findViewById(R.id.btn_paintview2);
        ToolBox_Chama_Activity.Funcao_Chama_Activity(btn_paintview2 , activity, PaintActivity2.class, null ,false, false, "");

        //////////////////////////////////////////////////////////////////////////////////////
        /*
         *  Pagina de Paint Completa Full
         *
         */
        Button btn_paintview3 = findViewById(R.id.btn_paintview3);
        ToolBox_Chama_Activity.Funcao_Chama_Activity(btn_paintview3 , activity, PaintActivity3.class, null ,false, false, "");


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; Isso adiciona itens à barra de ação se estiver presente.
        getMenuInflater().inflate(R.menu.debug, menu); // aqui define que na pasta menu , ira procurar o arquivo xml debug.xmll e adicionar os item la inseridos ao ToolBar
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Acionar o item da Toolbar clica aqui. o Toolbar
        // manipula automaticamente os cliques no botão Home / Up, por tanto tempo
        // como você especifica uma atividade pai no AndroidManifest.xml.
        int id = item.getItemId(); // pega o id d item clicado no Toolbar

        switch (id) {
            case R.id.menu_debug:
                Intent debug = new Intent(context, Debug.class);
                debug.putExtra("debug" , 1);
                ToolBox_Chama_Activity.Funcao_Chama_Activity(activity, null, debug ,true, true, "Abrir Debug?");
                break;
        }

        return super.onOptionsItemSelected(item); // retorna a açao do menu clicado
    }


    @Override
    public void onBackPressed() {
        ToolBox_Chama_Activity.Funcao_Chama_TelaPrincipal(activity);
        super.onBackPressed();
    }

}

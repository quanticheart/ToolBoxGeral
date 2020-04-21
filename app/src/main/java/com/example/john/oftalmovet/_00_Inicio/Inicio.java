package com.example.john.oftalmovet._00_Inicio;

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
import com.example.john.oftalmovet._03_Cam_e_Sd._03_Inicio;
import com.example.john.oftalmovet._04_PaintView._04_Inicio;
import com.example.john.oftalmovet._05_Bateria.Carga_Bateria;
import com.example.john.oftalmovet._06_RecyclerView._06_Inicio;
import com.example.john.oftalmovet._07_Glide_Picasso._07_Inicio;
import com.example.john.oftalmovet._0_ToolBox.ToolBox.ToolBox_Chama_Activity;

public class Inicio extends AppCompatActivity {

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
        setContentView(R.layout._00_inicio);

        initVars();
        initActions();

    }

    private void initVars() {

        //Var padroes
        context = getApplicationContext();
        context_activity = Inicio.this;
        activity = Inicio.this;
        window = getWindow();
        ////////////////////////////////////////////////////////////////////////

        /*
         *  _04_Inicio de findViewById's
         */
        ToolBarPrincipal = findViewById(R.id.tb_inicio);



    }

    private void initActions() {
        /*
         *  _04_Inicio de Ações da Pagina
         */

        setSupportActionBar(ToolBarPrincipal); // adiciona o toolbar ao xml
        getSupportActionBar().setTitle(R.string.app_name); // muda o titulo do toolbar via java
//          aqui formato a string vinda do xml para mostrar de m jeito em ingles 'user's contacts' e de outro em portugues 'contatos de user'
//          String TextoContatosDoUsuario = String.format(getResources().getString(R.string.usuario_contatos_de), cAux_usuario.getNome());
        ToolBarPrincipal.setSubtitle("Funções Para o Uso"); // adciona um subtitulo ao toolbar
        ToolBarPrincipal.setSubtitleTextColor(0xffffffff);
        ToolBarPrincipal.setLogo(R.mipmap.ic_launcher); // adiciona um logo ao toolbat
//          ToolBarPrincipal.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.CorPadrao2))); // muda a con de fundo do toolbar via java

        //////////////////////////////////////////////////////////////////////////////////////
        /*
         *  Tirar Foto , Carregar foto da Galeria e Utilizar CropView ( Tela de Corte )
         *  _03
         */
        Button btn_cam = findViewById(R.id.btn_cam);
        ToolBox_Chama_Activity.Funcao_Chama_Activity(btn_cam , activity, _03_Inicio.class, null ,true, false, "");



        //////////////////////////////////////////////////////////////////////////////////////
        /*
         *  Pagina de Paint
         *  _04
         */
        Button btn_paintview = findViewById(R.id.btn_paintview);
        ToolBox_Chama_Activity.Funcao_Chama_Activity(btn_paintview , activity, _04_Inicio.class, null ,true, false, "");



              //////////////////////////////////////////////////////////////////////////////////////
        /*
         *  Pagina sobre informaçoes de bateria em tempo real
         *  _05
         */
        Button btn_carga_bateria = findViewById(R.id.btn_carga_bateria);
        ToolBox_Chama_Activity.Funcao_Chama_Activity(btn_carga_bateria , activity, Carga_Bateria.class, null ,true, false, "");

        //////////////////////////////////////////////////////////////////////////////////////
        /*
         *  RecyclerView
         *  _06
         */
        Button btn_recyclerview = findViewById(R.id.btn_recyclerview);
        ToolBox_Chama_Activity.Funcao_Chama_Activity(btn_recyclerview , activity, _06_Inicio.class, null ,true, false, "");

        //////////////////////////////////////////////////////////////////////////////////////
        /*
         *  Glide_ e Picasso
         *  _07
         */
        Button btn_imgs = findViewById(R.id.btn_imgs);
        ToolBox_Chama_Activity.Funcao_Chama_Activity(btn_imgs , activity, _07_Inicio.class, null ,true, false, "");



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
        super.onBackPressed();
    }

}

package com.example.john.oftalmovet._06_RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.john.oftalmovet.R;
import com.example.john.oftalmovet._01_Debug.Debug;
import com.example.john.oftalmovet._03_Cam_e_Sd._03_Inicio;
import com.example.john.oftalmovet._04_PaintView._04_Inicio;
import com.example.john.oftalmovet._05_Bateria.Carga_Bateria;
import com.example.john.oftalmovet._0_ToolBox.ToolBox.ToolBox_Chama_Activity;
import com.example.john.oftalmovet._0_ToolBox.ToolBox.ToolBox_MSG;

import java.util.ArrayList;
import java.util.List;

public class _06_Inicio extends AppCompatActivity {

    private Context context;
    private Context context_activity;
    private Activity activity;
    private Window window;
    /////////////////////////////////////////////////////////////////////////////

    /*
     *  _04_Inicio de Variaveis
     */
    public Toolbar ToolBarPrincipal;
    List<HMAux> livros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._06_inicio);

        initVars();
        initActions();

    }

    private void initVars() {

        //Var padroes
        context = getApplicationContext();
        context_activity = _06_Inicio.this;
        activity = _06_Inicio.this;
        window = getWindow();
        ////////////////////////////////////////////////////////////////////////

        /*
         *  _04_Inicio de findViewById's
         */
        ToolBarPrincipal = findViewById(R.id.tb_inicio);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        gerarNomes(200);

        recyclerView.setAdapter(new NossoAdapter(
                context,
                R.layout._06_celula,
                livros
        ));

        RecyclerView.LayoutManager layout = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layout);

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);


        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int pos = recyclerView.indexOfChild(v);

                ToolBox_MSG.Funcao_Toast(context, String.valueOf(pos));
            }
        });

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


    }

    private void gerarNomes(int quantidade) {
        livros = new ArrayList<>();
        //
        for (int i = 0; i <= quantidade; i++) {
            HMAux aux = new HMAux();
            aux.put(HMAux.ID, String.valueOf(i));
            aux.put(HMAux.NOME, "Nome - RecyclerView " + String.valueOf(i));
            aux.put(HMAux.PRECO, String.valueOf(i * 3));

            //
            livros.add(aux);
        }

        ToolBox_MSG.Funcao_Toast(context, String.valueOf(livros.size()));
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
                debug.putExtra("debug", 1);
                ToolBox_Chama_Activity.Funcao_Chama_Activity(activity, null, debug, true, true, "Abrir Debug?");
                break;
        }

        return super.onOptionsItemSelected(item); // retorna a açao do menu clicado
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}

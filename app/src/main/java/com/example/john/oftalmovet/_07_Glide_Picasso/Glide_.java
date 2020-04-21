package com.example.john.oftalmovet._07_Glide_Picasso;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ListView;

import com.example.john.oftalmovet.R;
import com.example.john.oftalmovet._0_ToolBox.ToolBox.ToolBox_Chama_Activity;
import com.example.john.oftalmovet._0_ToolBox.ToolBox.ToolBox_MSG;

import java.util.ArrayList;

public class Glide_ extends AppCompatActivity {

    private Context context;
    private Context context_activity;
    private Activity activity;
    private Window window;
    /////////////////////////////////////////////////////////////////////////////

    /*
     *  _04_Inicio de Variaveis
     */

    private ArrayList<HMAux> dados = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._07_glide);

        initVars();
        initActions();

    }

    private void initVars() {

        //Var padroes
        context = getApplicationContext();
        context_activity = Glide_.this;
        activity = Glide_.this;
        window = getWindow();
        ////////////////////////////////////////////////////////////////////////

        /*
         *  _04_Inicio de findViewById's
         */
        gerarDados(100);
        ListView lv_cell = findViewById(R.id.lv_cell);

        Adapter_Cell adapter_cell = new Adapter_Cell(
                context,
                R.layout._07_celula2,
                dados
        );
        lv_cell.setAdapter(adapter_cell);



    }

    private void initActions() {
        /*
         *  _04_Inicio de Ações da Pagina
         */


    }


    private void gerarDados(int quantidade) {
        dados = new ArrayList<>();
        //
        dados.clear();
        for (int i = 1; i <= quantidade; i++) {
            HMAux aux = new HMAux();
            aux.put(HMAux.ID, String.valueOf(i));
            aux.put(HMAux.ID_IMG, String.valueOf(i));
            aux.put(HMAux.NOME, "Produto " + String.valueOf(i));
            aux.put(HMAux.DESC, "É importante questionar o quanto o aumento do diálogo entre os diferentes setores produtivos oferece uma interessante oportunidade para verificação dos índices pretendidos.");

            //
            dados.add(aux);
        }

        ToolBox_MSG.Funcao_Toast(context, String.valueOf(dados.size()));
    }

    @Override
    public void onBackPressed() {
        ToolBox_Chama_Activity.Funcao_Chama_TelaPrincipal(activity);
        super.onBackPressed();
    }
}

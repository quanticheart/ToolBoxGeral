package com.example.john.oftalmovet.Usuario.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;



import java.util.ArrayList;

/**
 * Created by nalmir on 21/11/2017.
 */

public class Adapter_Historico extends BaseAdapter {

    private Context context;
    private int resource;
    private ArrayList<HMAux_Procedimentos> dados;

    private LayoutInflater mInflater;

    public interface IAdapterPosts {
        void onStatusChanged(String id, boolean status);
    }

    private IAdapterPosts delegate;

    public void setOnStatusChangedListener(IAdapterPosts delegate) {
        this.delegate = delegate;
    }

    public Adapter_Historico(Context context, int resource, ArrayList<HMAux_Procedimentos> dados) {
        this.context = context;
        this.resource = resource;
        this.dados = dados;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return dados.size();
    }

    @Override
    public Object getItem(int position) {
        return dados.get(position);
    }

    @Override
    public long getItemId(int position) {
        HMAux_Procedimentos hmAux = dados.get(position);

        return Long.parseLong(hmAux.get(HMAux_Procedimentos.ID));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = mInflater.inflate(resource, parent, false);
        }

        // Acessar os dados de uma posicao especirfica (position)
        final HMAux_Procedimentos hmAux = dados.get(position);

        // Acessar a Layout Celula

//        ImageView iv_animal = (ImageView)
//                convertView.findViewById(R.id.celula_genero_animal);

//        TextView tv_nome = (TextView)
//                convertView.findViewById(R.id.celula_historico_nome_valor);
//
//        TextView tv_valor = (TextView)
//                convertView.findViewById(R.id.celula_historico_valor_valor);
//
//        TextView tv_data = (TextView)
//                convertView.findViewById(R.id.celula_historico_data_valor);
//
//        RelativeLayout btn_cria_termo = (RelativeLayout)
//                convertView.findViewById(R.id.celula_historico_data_cria_termo);
//
//        ImageView mais = (ImageView)
//                convertView.findViewById(R.id.mais);
//
//        // Juntar Dados / Layout
//        tv_nome.setText(hmAux.get(HMAux_Procedimentos.NOME));
//        tv_valor.setText("R$"+ToolBox_Convercao_De_Dados.Conveter_Para_Moeda(hmAux.get(HMAux_Procedimentos.VALOR)));
//        tv_data.setText(ToolBox_MSG.Converter_hora_TimeStamp(hmAux.get(HMAux_Procedimentos.DATA), false, false));
//
//        if(hmAux.get(HMAux_Procedimentos.TERMO).equals("1")){
//            mais.setVisibility(View.GONE);
//        }else{
//            mais.setVisibility(View.VISIBLE);
//        }
//
//        btn_cria_termo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                ToolBox_MSG.ExibeMSG("teste 1 "+ hmAux.get(HMAux_Procedimentos.ID) + " " + hmAux.get(HMAux_Procedimentos.ID_CLIENTE), context);
//
//                if(hmAux.get(HMAux_Procedimentos.TERMO).equals("1")){
//
//                    String url = "http://devnaville-br2.16mb.com/oftalmovet/Controller_pdf/termo_pdf?id="+hmAux.get(HMAux_Procedimentos.PDF);
//                    Intent intent = new Intent(Intent.ACTION_VIEW);
//                    intent.setDataAndType(Uri.parse(url), "application/pdf");
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    context.startActivity(intent);
//
//
//                } else {
//
//                    Intent pagina_usuario = new Intent(context, Termo.class);
//                    pagina_usuario.putExtra("id_animal", hmAux.get(HMAux_Procedimentos.ID_ANIMAL));
//                    pagina_usuario.putExtra("id_procedimento", hmAux.get(HMAux_Procedimentos.ID));
//                    pagina_usuario.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    context.startActivity(pagina_usuario);
//                }
//
//
//            }
//        });


        return convertView;
    }


}

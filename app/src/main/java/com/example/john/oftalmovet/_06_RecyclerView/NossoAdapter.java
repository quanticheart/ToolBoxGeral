package com.example.john.oftalmovet._06_RecyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.john.oftalmovet.R;
import com.example.john.oftalmovet.Usuario.Adaptadores.HMAux_Procedimentos;
import com.example.john.oftalmovet._0_ToolBox.ToolBox.ToolBox_MSG;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 04/04/2018.
 */

public class NossoAdapter extends RecyclerView.Adapter<NossoAdapter.View_RecyclerView>{

    public static ClickRecyclerView_Interface clickRecyclerViewInterface;

    private Context context;
    private int resource;
    private List<HMAux> dados;
    private LayoutInflater mInflater;
    private View.OnClickListener mClickListener;
    private HMAux hmAux = new HMAux();
    private int POS = 0;

    public NossoAdapter(Context context, int resource, List<HMAux> dados) {
        this.context = context;
        this.resource = resource;
        this.dados = dados;
        this.mInflater = LayoutInflater.from(context);
    }



    public interface ClickRecyclerView_Interface {

        void onCustomClick(Object object);
    }

//    @Override
//    public long getItemId(int position) {
//        HMAux hmAux = dados.get(position);
//        return Long.parseLong(hmAux.get(HMAux_Procedimentos.ID));
//    }


    @Override
    public View_RecyclerView onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(resource, parent, false);
        return new View_RecyclerView(view);

    }

//    @Override
//    public long getItemId(int position) {
//        hmAux = dados.get(position);
//
//        return Long.parseLong(hmAux.get(HMAux.ID));
//    }

    @Override
    public void onBindViewHolder(View_RecyclerView viewHolder, int position) {
        hmAux = dados.get(position);
        viewHolder.botao.setText(hmAux.get(HMAux.ID));
        viewHolder.nome.setText(hmAux.get(HMAux.NOME));

        //        viewHolder.viewNome.setText(pessoa.getNome());
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

//    // Insert a new item to the RecyclerView on a predefined position
//    public void insert(int position, Data data) {
//        dados.add(position, data);
//        notifyItemInserted(position);
//    }
//
//    // Remove a RecyclerView item containing a specified Data object
//    public void remove(Data data) {
//        int position = dados.indexOf(data);
//        dados.remove(position);
//        notifyItemRemoved(position);
//    }

    protected class View_RecyclerView extends RecyclerView.ViewHolder {

        protected TextView nome;
        protected Button botao;

        public View_RecyclerView(final View itemView) {

            super(itemView);
            final int position = getAdapterPosition()+1;
            final HMAux hmAux_ = dados.get(POS);

            nome = itemView.findViewById(R.id._06_celula_nome);
            botao = itemView.findViewById(R.id.botao);

//            itemView.setOnClickListener(NossoAdapter.this);

            botao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToolBox_MSG.Funcao_Toast(context , hmAux_.get(HMAux.NOME));
                }
            });

        }
    }



}

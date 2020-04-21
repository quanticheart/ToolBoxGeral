package com.example.john.oftalmovet._07_Glide_Picasso;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.module.AppGlideModule;

import com.bumptech.glide.request.RequestOptions;
import com.example.john.oftalmovet.R;
import com.example.john.oftalmovet.Usuario.Adaptadores.HMAux_Procedimentos;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by nalmir on 21/11/2017.
 */

public class Adapter_Cell extends BaseAdapter {

    private Context context;
    private int resource;
    private ArrayList<HMAux> dados;

//    final int min = 20;
//    final int max = 80;
//    int random = Random.nextInt((max - min) + 1) + min;

    private LayoutInflater mInflater;

    public interface IAdapterPosts {
        void onStatusChanged(String id, boolean status);
    }

    private IAdapterPosts delegate;

    public void setOnStatusChangedListener(IAdapterPosts delegate) {
        this.delegate = delegate;
    }

    public Adapter_Cell(Context context, int resource, ArrayList<HMAux> dados) {
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
        HMAux hmAux = dados.get(position);

        return Long.parseLong(hmAux.get(HMAux.ID));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = mInflater.inflate(resource, parent, false);
        }

        // Acessar os dados de uma posicao especirfica (position)
        final HMAux hmAux = dados.get(position);

        // Acessar a Layout Celula

        ImageView img = (ImageView)
                convertView.findViewById(R.id._07_celula_img);

        TextView nome = (TextView)
                convertView.findViewById(R.id._07_celula_nome);

        TextView desc = (TextView)
                convertView.findViewById(R.id._07_celula_desc);

        CardView cardView = (CardView)
                convertView.findViewById(R.id.card_view);
        cardView.setPreventCornerOverlap(false); //it is very important

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
        nome.setText(hmAux.get(HMAux.NOME));
        desc.setText(hmAux.get(HMAux.DESC));

        int id_img = Integer.parseInt(hmAux.get(HMAux.ID_IMG));

        Random r = new Random();
//        int i1 = r.nextInt(2 - 5) + 5;

        final int min = 1;
        final int max = 5;
        int il = r.nextInt((max - min) + 1) + min;

        String url = "";
        switch (il) {
            case 1:
                url = "https://cdn.pixabay.com/photo/2015/05/07/22/51/mosaic-757323_960_720.jpg";
                break;
            case 2:
                url = "http://www.gamersdecide.com/sites/default/files/styles/news_images/public/content-images/news/2016/09/18/top-13-army-games/banner.jpg";
                break;
            case 3:
                url = "https://static.independent.co.uk/s3fs-public/styles/story_large/public/thumbnails/image/2017/10/30/11/lifestyle.jpg";
                break;
            case 4:
                url = "http://oyster.ignimgs.com/wordpress/stg.ign.com/2014/09/25-MODOK.jpg";
                break;
            case 5:
                url = "https://d1d3jrbl2unca7.cloudfront.net/large/gallery/exterior/59/859/harley-davidson-road-glide-special-slant-rear-view-(full-image).jpg";
                break;
        }




        Glide.with(context).load(R.drawable.material_design_11).apply(new RequestOptions().fitCenter()).into(img);


//        FUNCAO_Carrega_Imagem(context, img, url);



//        desc.setText("R$"+ToolBox_Convercao_De_Dados.Conveter_Para_Moeda(hmAux.get(HMAux_Procedimentos.VALOR)));
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

    private void FUNCAO_Carrega_Imagem(final Context context, ImageView img, String url) {
        Glide.with(context)
                .load(url)
//                .apply(new RequestOptions().override(300, 250).placeholder(R.drawable.bg_padrao).transform(new RoundedCornersTransformation(context, 27, 0 , RoundedCornersTransformation.CornerType.LEFT)))
                // para converter o pixel em dp para utilizar no xml : https://pixplicity.com/dp-px-converter
                .apply(new RequestOptions().placeholder(R.drawable.bg_padrao))
                .into(img);
    }




}

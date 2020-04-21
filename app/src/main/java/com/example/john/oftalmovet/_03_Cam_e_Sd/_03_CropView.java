package com.example.john.oftalmovet._03_Cam_e_Sd;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.example.john.oftalmovet.R;
import com.example.john.oftalmovet._0_ToolBox.ToolBox.ToolBox_MSG;
import com.example.john.oftalmovet._0_ToolBox.ToolBox._ToolBox_Config_Inicial;
import com.isseiaoki.simplecropview.CropImageView;
import com.isseiaoki.simplecropview.callback.CropCallback;
import com.isseiaoki.simplecropview.callback.LoadCallback;
import com.isseiaoki.simplecropview.callback.SaveCallback;
import com.isseiaoki.simplecropview.util.Logger;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class _03_CropView extends AppCompatActivity {

    private Context context;
    private Context context_activity;
    private Activity activity;
    private Window window;
    /////////////////////////////////////////////////////////////////////////////

    /*
     *  _03_Inicio de Variaveis
     */

    // LAYOUT_SIMPLES = true , então ira mostrar somente um botão de salvar imagem cortada
    // do contrario , um layout completo com outras funçoes ira ser abilitado
    private boolean LAYOUT_SIMPLES = true;
    private LinearLayout ll_completo;
    private LinearLayout ll_simples;

    private void Layout() {
        if (LAYOUT_SIMPLES) {
            ll_simples.setVisibility(View.VISIBLE);
            ll_completo.setVisibility(View.GONE);
        } else {
            ll_simples.setVisibility(View.GONE);
            ll_completo.setVisibility(View.VISIBLE);
        }
    }

    private CropImageView mCropView;
    private Uri mSourceUri = null;


    ///////////
    ///////////
    /////////// PARA UTILIZAR
    /////////// compile 'com.isseiaoki:simplecropview:1.1.7'
    /////////// https://github.com/IsseiAoki/SimpleCropView
    ///////////


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._03_cropview);

        initVars();
        initActions();
        Layout();
    }

    private void initVars() {

        //Var padroes
        context = getApplicationContext();
        context_activity = _03_CropView.this;
        activity = _03_CropView.this;
        window = getWindow();
        ////////////////////////////////////////////////////////////////////////

        /*
         *  findViewById's
         */


        mCropView = (CropImageView) findViewById(R.id.cropImageView);

        mCropView.setOutputMaxSize(300, 300);
//        mCropView.setOutputWidth(100); // Se o tamanho da imagem recortada for 400x200, o tamanho da saída será 100x50
//        mCropView.setOutputHeight(100); // Se o tamanho da imagem recortada for 400x200, o tamanho da saída será de 200x100
        mCropView.setCropMode(CropImageView.CropMode.CIRCLE_SQUARE);//adiciona um modo fixo de seleção ao abrir a intent
//        Modos de crop
//        FIT_IMAGE, RATIO_4_3, RATIO_3_4, SQUARE(default), RATIO_16_9, RATIO_9_16, FREE, CUSTOM, CIRCLE, CIRCLE_SQUARE
//        FREE: Modo livre, sem valores RATIO_X_Y ,
//        SQUARE: modo padrao relaciona ao tamanho da imagem
//        FIT_IMAGE: o modo de proporção fixa. O mesmo formato da foto original.
//        CIRCLE: Modo de taxa de proporção fixa. Cortar a imagem como círculo.
//        CIRCLE_SQUARE: Modo de taxa de proporção fixa. Mostrar círculo de guia, mas salve como quadrado ( getRectBitmap()é removido).
//        Se precisar de uma seleção personalizada, use setCustomRatio(int ratioX, int ratioY);

        mCropView.setMinFrameSizeInDp(100);
        // adiciona um minimo para seleção , Exemplo ,
        // em um modo SQUARE o minimo a selecionar é 100x100

        mCropView.setInitialFrameScale(0.5f);
        // inicia o modo seleção com um minimo ja selecionado
        // 1.0f max 0.1f min

//        mCropView.setBackgroundColor(0xFFFFFFFB); // background do cropview
//        mCropView.setOverlayColor(0xAA1C1C1C); // background da imagem nao selecionada
//        mCropView.setFrameColor(getResources().getColor(R.color.moldura)); // Cor da borda de seleção
//        mCropView.setHandleColor(getResources().getColor(R.color.lidar)); // Cor dos circulos de seleção
//        mCropView.setGuideColor(getResources().getColor(R.color.guia)); // Cor das linhas dentro da seleção

//        mCropView.setFrameStrokeWeightInDp(1); // tamanho da borda de seleção
//        mCropView.setGuideStrokeWeightInDp(1); // tamanho das linhas dentro da seleção
//        mCropView.setHandleSizeInDp(getResources().getDimension(R.dimen.handle_size)); // tamanho dos circulos de seleçãp
        mCropView.setHandleSizeInDp(0); // tamanho dos circulos de seleção

        mCropView.setTouchPaddingInDp(50);
        // seleciona o raio a ser tocado em volta do circulo ,
        // para ajudar a redimencionar a seleçao

//        mCropView.setHandleShowMode(CropImageView.ShowMode.SHOW_ALWAYS); // difine quando mostrar o circulo de seleção
//        mCropView.setGuideShowMode(CropImageView.ShowMode.SHOW_ON_TOUCH); // define quando mostrar as grades de seleção
//        SHOW_ALWAYS: sempre mostra
//        NOT_SHOW: nunca mostra
//        SHOW_ON_TOUCH: mostra somente ao tocar na tela

//        mCropView.setDebug(true); // mostra o debug do cropview

        ll_completo = findViewById(R.id.ll_completo);
        ll_simples = findViewById(R.id.ll_simples);

        findViewById(R.id.buttonDone).setOnClickListener(btnListener);
        findViewById(R.id.buttonDone2).setOnClickListener(btnListener);
        findViewById(R.id.buttonFitImage).setOnClickListener(btnListener);
        findViewById(R.id.button1_1).setOnClickListener(btnListener);
        findViewById(R.id.button3_4).setOnClickListener(btnListener);
        findViewById(R.id.button4_3).setOnClickListener(btnListener);
        findViewById(R.id.button9_16).setOnClickListener(btnListener);
        findViewById(R.id.button16_9).setOnClickListener(btnListener);
        findViewById(R.id.buttonFree).setOnClickListener(btnListener);
        findViewById(R.id.buttonPickImage).setOnClickListener(btnListener);
        findViewById(R.id.buttonRotateLeft).setOnClickListener(btnListener);
        findViewById(R.id.buttonRotateRight).setOnClickListener(btnListener);
        findViewById(R.id.buttonCustom).setOnClickListener(btnListener);
        findViewById(R.id.buttonCircle).setOnClickListener(btnListener);
        findViewById(R.id.buttonShowCircleButCropAsSquare).setOnClickListener(btnListener);

    }

    private void initActions() {
        /*
         *  _04_Inicio de Ações da Pagina
         */

        mSourceUri = getIntent().getData();
        Log.w("Uri da Imagem", String.valueOf(mSourceUri));
        if (mSourceUri == null) {
            // default data // CARREGA UMA IMAGEM PADRAO PARA O CROP VIEW
            // PARA MODIFICAR A IMAGEM PADRAO DO SISTEMA E SO PASSAR O DRAWABLE
            mSourceUri = getUriFromDrawableResId(activity, R.drawable.padrao);
        }

        // load image
        mCropView.load(mSourceUri)
                .useThumbnail(true)
                .execute(mLoadCallback);
    }

    private final View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.buttonDone:
                    Finaliza_Crop();
                    break;
                case R.id.buttonDone2:
                    Finaliza_Crop();
                    break;
                case R.id.buttonFitImage:
                    mCropView.setCropMode(CropImageView.CropMode.FIT_IMAGE);
                    break;
                case R.id.button1_1:
                    mCropView.setCropMode(CropImageView.CropMode.SQUARE);
                    break;
                case R.id.button3_4:
                    mCropView.setCropMode(CropImageView.CropMode.RATIO_3_4);
                    break;
                case R.id.button4_3:
                    mCropView.setCropMode(CropImageView.CropMode.RATIO_4_3);
                    break;
                case R.id.button9_16:
                    mCropView.setCropMode(CropImageView.CropMode.RATIO_9_16);
                    break;
                case R.id.button16_9:
                    mCropView.setCropMode(CropImageView.CropMode.RATIO_16_9);
                    break;
                case R.id.buttonCustom:
                    mCropView.setCustomRatio(7, 5);
                    break;
                case R.id.buttonFree:
                    mCropView.setCropMode(CropImageView.CropMode.FREE);
                    break;
                case R.id.buttonCircle:
                    mCropView.setCropMode(CropImageView.CropMode.CIRCLE);
                    break;
                case R.id.buttonShowCircleButCropAsSquare:
                    mCropView.setCropMode(CropImageView.CropMode.CIRCLE_SQUARE);
                    break;
                case R.id.buttonRotateLeft:
                    mCropView.rotateImage(CropImageView.RotateDegrees.ROTATE_M90D);
                    break;
                case R.id.buttonRotateRight:
                    mCropView.rotateImage(CropImageView.RotateDegrees.ROTATE_90D);
                    break;
                case R.id.buttonPickImage:
//                    pickImage();
                    break;
            }
        }


    };


    private void Finaliza_Crop() {
        mCropView.crop(mSourceUri)
                .execute(mCropCallback);
    }

    //ESTA FUNÇÃO CARREGA A IMAGEM PADRAO PARA O CROPVIEW
    public static Uri getUriFromDrawableResId(Context context, int drawableResId) {
        StringBuilder builder = new StringBuilder().append(ContentResolver.SCHEME_ANDROID_RESOURCE)
                .append("://")
                .append(context.getResources().getResourcePackageName(drawableResId))
                .append("/")
                .append(context.getResources().getResourceTypeName(drawableResId))
                .append("/")
                .append(context.getResources().getResourceEntryName(drawableResId));
        return Uri.parse(builder.toString());
    }

    // Callbacks ///////////////////////////////////////////////////////////////////////////////////
    // SÃO USADOS PARA ACHAMAR A FUNÇÃO DO CROPVIEW
    // FUNÇÃO LoadCallback = OQ FAZ APOS CARREGAR A IMAGEM SELECIONADA
    // FUNÇÃO CropCallback = OQ FAZ APOS SELECIONAR ONDE A IMAGEM SERA CORTADA
    // FUNÇÃO SaveCallback = OQ FAZ APOS SALVAR A IMAGEM SELECIONADA

    private final LoadCallback mLoadCallback = new LoadCallback() {
        @Override
        public void onSuccess() {
        }

        @Override
        public void onError(Throwable e) {
        }
    };

    private final CropCallback mCropCallback = new CropCallback() {
        @Override
        public void onSuccess(Bitmap cropped) {
            mCropView.save(cropped)
                    .compressFormat(_03_ToolBox.mCompressFormat)//define o formato para converssão
//                    .compressQuality(100)//define a qualidade 0 a 100
                    .execute(_03_ToolBox.Novo_Uri_Imagem(activity, _03_ToolBox.mCompressFormat), mSaveCallback);
        }

        @Override
        public void onError(Throwable e) {
        }
    };

    private final SaveCallback mSaveCallback = new SaveCallback() {
        @Override
        public void onSuccess(Uri outputUri) {
            ToolBox_MSG.Funcao_Toast(context, "Salvando");
            _03_ToolBox.Carrega_Imagem_Selecionada(activity, outputUri);
            finish();
        }

        @Override
        public void onError(Throwable e) {
            ToolBox_MSG.Funcao_Toast(context, String.valueOf(e));
        }
    };

//    @Override
//    public void onBackPressed() {
//        ToolBox_Chama_Activity.Funcao_Chama_TelaPrincipal(activity);
//        super.onBackPressed();
//    }
}

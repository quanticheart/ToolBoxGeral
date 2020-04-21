package com.example.john.oftalmovet._03_Cam_e_Sd;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.john.oftalmovet.R;
import com.example.john.oftalmovet._0_ToolBox.ToolBox.ToolBox_Chama_Activity;
import com.example.john.oftalmovet._0_ToolBox.ToolBox.ToolBox_MSG;
import com.steelkiwi.cropiwa.CropIwaView;
import com.steelkiwi.cropiwa.config.CropIwaSaveConfig;

import java.io.File;

public class _03_CropView_2 extends AppCompatActivity {

    private Context context;
    private Context context_activity;
    private Activity activity;
    private Window window;
    /////////////////////////////////////////////////////////////////////////////

    /*
     *  _04_Inicio de Variaveis
     */
    private Button button;
    private CropIwaView cropIwaView;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._03_cropview_2);

        initVars();
        initActions();

    }

    private void initVars() {

        //Var padroes
        context = getApplicationContext();
        context_activity = _03_CropView_2.this;
        activity = _03_CropView_2.this;
        window = getWindow();
        ////////////////////////////////////////////////////////////////////////

        /*
         *  _04_Inicio de findViewById's
         */
        cropIwaView = findViewById(R.id.crop_view);
        button = findViewById(R.id.save);

        Uri mSourceUri = getIntent().getData();
//        cropIwaView.setImage();
        cropIwaView.setImageUri(mSourceUri);
    }

    private void initActions() {
        /*
         *  _04_Inicio de Ações da Pagina
         */

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = "/sdcard/0/teste.jpg";
                Uri uri1 = Uri.fromFile(new File(s));
                ToolBox_MSG.Funcao_Toast(context,"Salvando");
                cropIwaView.crop(new CropIwaSaveConfig.Builder(uri1)
                        .setCompressFormat(Bitmap.CompressFormat.PNG)
                        .setSize(500, 500) //Optional. If not specified, SRC dimensions will be used
                        .setQuality(100) //Hint for lossy compression formats
                        .build());
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        ToolBox_Chama_Activity.Funcao_Chama_TelaPrincipal(activity);
        super.onBackPressed();
    }
}

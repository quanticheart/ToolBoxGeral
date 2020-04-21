package com.example.john.oftalmovet._04_PaintView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.john.oftalmovet.R;
import com.example.john.oftalmovet._04_PaintView.util.ImageUtil;
import com.example.john.oftalmovet._0_ToolBox.ToolBox._ToolBox_Config_Inicial;
import com.lht.paintview.PaintView;
import com.lht.paintview.pojo.DrawShape;

import java.io.File;
import java.util.ArrayList;

public class PaintActivity2 extends AppCompatActivity
        implements View.OnClickListener, PaintView.OnDrawListener {


    LinearLayout ll_colors, ll_brushs;
    ImageButton ib_red, ib_blue, ib_green, ib_yellow, ib_lapiz, ib_pincel, ib_rolo;

    final static int WIDTH_WRITE = 2, WIDTH_PAINT = 40, WIDTH_PAINT_ROLO = 80;
    private Context context;
    PaintView mPaintView;

    public Toolbar ToolBarPrincipal;
    ImageButton mBtnColor, mBtnStroke, mBtnUndo;
    boolean bRedOrBlue = true, bWriteOrPaint = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._04_paint_activity2);

        initVars();
        initActions();
    }


    private void initVars() {
        context = getBaseContext();
        mPaintView = (PaintView) findViewById(R.id.view_paint);
        mPaintView.setColorFromRes(R.color.paint_color_red);
        mPaintView.setTextColorFromRes(R.color.paint_color_red);
        mPaintView.setBgColor(Color.WHITE);
        mPaintView.setStrokeWidth(WIDTH_WRITE);
        mPaintView.setOnDrawListener(this);

        ll_colors = findViewById(R.id.ll_paintview_colors);
        ib_red = findViewById(R.id.ll_paintview_colors_red);
        ib_blue = findViewById(R.id.ll_paintview_colors_blue);
        ib_green = findViewById(R.id.ll_paintview_colors_green);
        ib_yellow = findViewById(R.id.ll_paintview_colors_yellow);


        ll_brushs = findViewById(R.id.ll_paintview_brush);
        ib_lapiz = findViewById(R.id.ll_paintview_brush_lapis);
        ib_pincel = findViewById(R.id.ll_paintview_brush_pincel);
        ib_rolo = findViewById(R.id.ll_paintview_brush_rolo);

//        Uri uri = getIntent().getParcelableExtra(BITMAP_URI);
//
//        Bitmap bitmap = ImageUtil.getBitmapByUri(this, uri);
//        if (bitmap != null) {
//            mPaintView.setBitmap(bitmap);
//        }


        mBtnColor = (ImageButton) findViewById(R.id.btn_color);
        mBtnColor.setOnClickListener(this);
        mBtnStroke = (ImageButton) findViewById(R.id.btn_stroke);
        mBtnStroke.setOnClickListener(this);
        mBtnUndo = (ImageButton) findViewById(R.id.btn_undo);
        mBtnUndo.setEnabled(false);
        mBtnUndo.setOnClickListener(this);

        ToolBarPrincipal = findViewById(R.id.tb_inicio);
        setSupportActionBar(ToolBarPrincipal); // adiciona o toolbar ao xml
    }

    private void initActions() {

        TrocaCorPincel(ib_red, R.color.paint_color_red, R.drawable.paintview_ic_circle_red);
        TrocaCorPincel(ib_blue, R.color.paint_color_blue, R.drawable.paintview_ic_circle_blue);
        TrocaCorPincel(ib_green, R.color.paint_color_green, R.drawable.paintview_ic_circle_green);
        TrocaCorPincel(ib_yellow, R.color.paint_color_yellow, R.drawable.paintview_ic_circle_yellow);

        TrocaPincel(ib_lapiz, WIDTH_WRITE, R.drawable.ic_edit);
        TrocaPincel(ib_pincel, WIDTH_PAINT, R.drawable.ic_brush);
        TrocaPincel(ib_rolo, WIDTH_PAINT_ROLO, R.drawable.ic_format_paint);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_color:
                colorChanged();
                break;
            case R.id.btn_stroke:
                strokeChanged();
                break;
            case R.id.btn_undo:
                mPaintView.undo();
                break;
        }
    }

    private void colorChanged() {

        ll_colors.setVisibility(View.VISIBLE);
        ll_brushs.setVisibility(View.GONE);

    }

    private void TrocaCorPincel(ImageButton imageView_Clicado, final int color, final int imagem) {

        imageView_Clicado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPaintView.setColorFromRes(color);
                mPaintView.setTextColorFromRes(color);
//                mBtnColor.setImageResource(color);

                mBtnColor.setImageResource(imagem);

                ll_colors.setVisibility(View.GONE);
            }
        });

    }

    private void strokeChanged() {
        ll_colors.setVisibility(View.GONE);
        ll_brushs.setVisibility(View.VISIBLE);

    }

    private void TrocaPincel(ImageButton imageView_Clicado, final int tamanho_brush, final int imagem) {

        imageView_Clicado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPaintView.setStrokeWidth(tamanho_brush);
                mBtnStroke.setImageResource(imagem);

                ll_brushs.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu._04__menu_paintactivity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                shareSingleImage(
                        ImageUtil.saveShareImage(this, mPaintView.getBitmap(true)));
                break;
            case R.id.action_save:
                Salvar_Imagem();
                break;
        }
        return true;
    }

    private void shareSingleImage(Uri imageUri) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
        shareIntent.setType("image/*");
        startActivity(
                Intent.createChooser(shareIntent, getResources().getString(R.string.msg_global_title_share)));

    }

    private void Salvar_Imagem() {

        _ToolBox_Config_Inicial.Funcao_Verifica_Pastas();

        String Nome_Imagem = "teste";
        String Extencao_Imagem = ".jpg";

        Intent intent = getIntent();
        Bitmap bitmap = mPaintView.getBitmap(true);
        String Nova_Imagem = Nome_Imagem + Extencao_Imagem;

        String LocalPath = _ToolBox_Config_Inicial.Constante_Pasta_Projeto_Imagens + Nova_Imagem;

        File file = new File(LocalPath);

        boolean exists = file.exists();

        if (exists == true) {
            file.delete();
        }

        ImageUtil.saveBitmap(bitmap, LocalPath, true);

        finish();
    }

    @Override
    public void afterPaintInit(int viewWidth, int viewHeight) {
//        mPaintView.setTextColor(Color.BLACK);
//        mPaintView.setTextSize(36);
//        mPaintView.addText("图表标题", -1, viewHeight - 50, PaintView.TextGravity.CENTER_HORIZONTAL);
    }

    @Override
    public void afterEachPaint(ArrayList<DrawShape> drawShapes) {
        setUndoEnable(drawShapes);
    }

    private void setUndoEnable(ArrayList<DrawShape> drawShapes) {
        if (drawShapes.size() == 0) {
            mBtnUndo.setEnabled(false);
        } else {
            mBtnUndo.setEnabled(true);
        }
    }
}

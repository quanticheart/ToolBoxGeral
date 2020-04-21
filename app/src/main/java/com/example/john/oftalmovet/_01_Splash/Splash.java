package com.example.john.oftalmovet._01_Splash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.john.oftalmovet.R;

/**
 * Created by nalmir on 01/12/2017.
 */

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._01_splash);

//        isUserLogged();//Pega do prefs se já está criado

    }


//    private boolean isUserLogged() {
//
//        SharedPreferences prefs = getSharedPreferences("Login", MODE_PRIVATE);
//        Boolean restoredText = prefs.getBoolean("Logado", false);
//        if (restoredText != false) {
//
//            Intent pagina_usuario = new Intent(this, Pagina_Usuario.class);
//            startActivity(pagina_usuario);
//            finish();
//
//        } else {
//
//            isSplash();
//
//        }
//
//
//        return restoredText;
//    }

//    private boolean isSplash() {
//
//        SharedPreferences prefs = getSharedPreferences("prefsSlash", MODE_PRIVATE);
//        Boolean restoredText = prefs.getBoolean("Splash", false);
//        if (restoredText != false) {
//
//            Intent pagina_usuario = new Intent(this, Inicio_Login.class);
//            startActivity(pagina_usuario);
//            finish();
//        } else {
//
//            new Handler().postDelayed(
//                    new Runnable() {
//                        @Override
//                        public void run() {
//
//                            SharedPreferences prefs = getApplicationContext().getSharedPreferences("prefsSlash", Context.MODE_PRIVATE);
//                            SharedPreferences.Editor editor = prefs.edit();
//                            editor.putBoolean("Splash", true);
//                            editor.commit();
//
//                            Intent mIntent = new Intent(getBaseContext(), Inicio_Login.class);
//                            startActivity(mIntent);
//                            //
//                            finish();
//                        }
//                    },
//                    3000
//            );
//
//        }
//
//        return restoredText;
//    }

}

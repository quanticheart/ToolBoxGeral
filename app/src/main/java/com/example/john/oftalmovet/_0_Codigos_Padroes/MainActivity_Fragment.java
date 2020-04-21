package com.example.john.oftalmovet._0_Codigos_Padroes;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.john.oftalmovet.R;

/**
 * Created by John on 09/12/2017.
 */

public class MainActivity_Fragment extends Fragment {

    private Context context;
    private Context context_activity;
    private Activity activity;
    private Window window;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout._01_debug, container, false);

        initVars(view);
        initActions();

        return view;
    }

    private void initVars(View view) {

        //Var padroes
        context = getActivity();
        context_activity = getActivity();
        activity = getActivity();
        window = getActivity().getWindow();

    }

    private void initActions() {

    }

}

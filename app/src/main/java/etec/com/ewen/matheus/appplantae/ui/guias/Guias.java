package etec.com.ewen.matheus.appplantae.ui.guias;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import etec.com.ewen.matheus.appplantae.R;
import etec.com.ewen.matheus.appplantae.activity.TelaGuias;


public class Guias extends Fragment {

    Activity context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = getActivity();

        View root = inflater.inflate(R.layout.fragment_guias, container, false);
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        Intent it = new Intent(context, TelaGuias.class);
        startActivity(it);

    }
}
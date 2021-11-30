package etec.com.ewen.matheus.appplantae.ui.help;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import etec.com.ewen.matheus.appplantae.R;
import etec.com.ewen.matheus.appplantae.activity.TelaHelp;

public class Help extends Fragment {

    Activity context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getActivity();

        View root = inflater.inflate(R.layout.fragment_guias, container, false);
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        Intent it = new Intent(context, TelaHelp.class);
        startActivity(it);
    }
}
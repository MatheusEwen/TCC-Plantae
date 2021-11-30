package etec.com.ewen.matheus.appplantae.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import etec.com.ewen.matheus.appplantae.R;

public class fragment1 extends Fragment {

    Activity context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = getActivity();

        View root = inflater.inflate(R.layout.fragment_fragment1, container, false);
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        Button btnYouTube, btnInst, btnTwi, btnPint;
        btnYouTube = (Button) context.findViewById(R.id.btnYoutube);
        btnInst = (Button) context.findViewById(R.id.btnInst);
        btnTwi = (Button) context.findViewById(R.id.btnTwi);
        btnPint = (Button) context.findViewById(R.id.btnPint);

        btnYouTube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri webpage = Uri.parse("https://www.youtube.com/channel/UCwBVhZ78Ju36XPdrlZFuzUg");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(webIntent);

            }
        });
        btnInst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri webpage = Uri.parse("https://www.instagram.com/plantaetcc/");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(webIntent);

            }
        });
        btnTwi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri webpage = Uri.parse("https://twitter.com/PlantaeTCC");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(webIntent);

            }
        });
        btnPint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri webpage = Uri.parse("https://www.android.com");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(webIntent);

            }
        });

    }
}
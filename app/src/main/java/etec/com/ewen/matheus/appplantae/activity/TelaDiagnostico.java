
package etec.com.ewen.matheus.appplantae.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import etec.com.ewen.matheus.appplantae.R;

public class TelaDiagnostico extends AppCompatActivity {

    TextView txtProblema, txtSolucao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_diagnostico);

        Intent dadosRecebidos = getIntent();
        Bundle valores = dadosRecebidos.getExtras();
        txtProblema = findViewById(R.id.txtProblema);
        txtSolucao = findViewById(R.id.txtSolucao);
        String p = valores.getString("problema");
        String s = valores.getString("solucao");

        txtProblema.setText(p);
        txtSolucao.setText(s);
    }
}
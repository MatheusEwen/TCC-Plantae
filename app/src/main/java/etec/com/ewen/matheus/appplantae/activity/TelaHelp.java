package etec.com.ewen.matheus.appplantae.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import etec.com.ewen.matheus.appplantae.MenusActivity;
import etec.com.ewen.matheus.appplantae.R;

public class TelaHelp extends AppCompatActivity {

    RadioGroup rdgLuzHelp;
    RadioButton rdbDireta, rdbDifusa;
    CheckBox amarela, murcha, pontaSeca, furada, pontoPreto, queda, caule, roxo, moleTrans, bordaSeca;
    Button btnDiagnostico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_help);

        rdgLuzHelp = findViewById(R.id.rdgLuzHelp);
        rdbDireta = findViewById(R.id.rdbDireta);
        rdbDifusa = findViewById(R.id.rdbDifusa);
        amarela = findViewById(R.id.amarelada);
        murcha = findViewById(R.id.murcha);
        pontaSeca = findViewById(R.id.pontasSecas);
        furada = findViewById(R.id.furada);
        pontoPreto = findViewById(R.id.pontospretos);
        queda = findViewById(R.id.queda);
        caule = findViewById(R.id.cauleFino);
        roxo = findViewById(R.id.roxo);
        moleTrans = findViewById(R.id.moleTrans);
        bordaSeca = findViewById(R.id.bordaSeca);
        btnDiagnostico = findViewById(R.id.btnDiagnostico);

        btnDiagnostico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fazerDiagnostico();
            }

        });
    }

    private void fazerDiagnostico() {
        int statusLuz;
        String problema = null;
        String solucao = null;
        Intent itDiagnostico = new Intent(TelaHelp.this, TelaDiagnostico.class);
        switch (rdgLuzHelp.getCheckedRadioButtonId()){
            case R.id.rdbDireta:
                statusLuz = 3;
                break;
            case R.id.rdbDifusa:
                statusLuz = 1;
                break;
            default:
                statusLuz = 1;
                break;
        }
        if(statusLuz == 3){

            if(amarela.isChecked() == true && murcha.isChecked() == true){
                problema = "Falta de água, Excesso de luz ou calor.";
                solucao = "Informe-se sobre as regas da espécie e coloque-a num lugar com mais sombra e ventilação. Se ela secou muito rápido, pode ter secado o miolo e, neste caso, é irreversível.";
            }
            if(amarela.isChecked() == true && pontaSeca.isChecked() == true){
                problema = "Excesso de luz ou calor";
                solucao= "Informe-se sobre a espécie e coloque-a em um lugar com mais sombra e ventilação.";
            }
            if(amarela.isChecked() == true && roxo.isChecked() == true){
                problema = "Falta de nitrogênio (N),Falta do nutriente fósforo (P).";
                solucao= "Adube com o chamado NPK, que supre os nutrientes. Também é possível usar o adubo completo que, além do NPK, possui micronutrientes.";
            }
            if(amarela.isChecked() == true && bordaSeca.isChecked() == true){
                problema = "Excesso de luz ou calor";
                solucao= "Coloque num lugar com mais sombra.";
            }
            if(amarela.isChecked() == true && furada.isChecked() == true){
                problema = "Infestação de insetos e pragas.";
                solucao= "Limpe as folhas para retirar os insetos (caso eles não sejam voadores) e aplique um inseticida próprio. Caso seja o primeiro ataque, vale usar repelentes com o óleo de neen e calda de fumo.";
            }
            if(amarela.isChecked() == true && pontoPreto.isChecked() == true){
                problema = "Infestação de fungos e bactérias";
                solucao= "Use um inseticida sistêmico próprio para plantas. Caso seja um tempero, dê um intervalor de 20 a 30 dias para usá-lo novamente.";
            }
            if(amarela.isChecked() == true && moleTrans.isChecked() == true){
                problema = "Excesso de água";
                solucao= "Informe-se sobre as regas e verifique se os furos do vaso não estão entupidos.";
            }
            if(amarela.isChecked() == true && queda.isChecked() == true){
                problema = "Pouca quantidade de regas e de bons nutrientes";
                solucao= "Manter o solo fértil, composto de nutrientes especiais para a espécie e aumentar a quantidade de água.";
            }
            if(amarela.isChecked() == true && caule.isChecked() == true){
                problema = "Muita exposição à luz solar";
                solucao= "Verifique quais são as necessidades adequadas da planta que apresenta o problema e mude-a de lugar.";
            }
            if(murcha.isChecked() == true && pontaSeca.isChecked() == true){
                problema = "Falta de água, Excesso de calor";
                solucao= "Informe-se sobre as regas da espécie e coloque-a num lugar com mais sombra e ventilação.";
            }
            if(murcha.isChecked() == true && roxo.isChecked() == true){
                problema = "Excesso de calor,Falta do nutriente fósforo (P)";
                solucao= "Mover a planta para um local com luminosidade diferente de onde está atualmente, adube com o chamado NPK, que supre os nutrientes.";
            }
            if(murcha.isChecked() == true && bordaSeca.isChecked() == true){
                problema = "Excesso de calor ou Falta de água.";
                solucao= "Mover para um lugar com mais sombra, Informe-se sobre as regas.";
            }
            if(murcha.isChecked() == true && furada.isChecked() == true){
                problema = "Infestação de insetos e pragas.";
                solucao= "Aplique um inseticida próprio.";
            }
            if(murcha.isChecked() == true && pontoPreto.isChecked() == true){
                problema = "Infestação de fungos e bactérias.";
                solucao= "Usar um inseticida sistêmico próprio para plantas.";
            }
            if(murcha.isChecked() == true && moleTrans.isChecked() == true){
                problema = "Execsso de água";
                solucao= "Diminuir o número de regas.";
            }
            if(murcha.isChecked() == true && queda.isChecked() == true){
                problema = "Calor excessivo e deficiência de rega";
                solucao= "Mover para um local menos iluminado e regar mais vezes.";
            }
            if(murcha.isChecked() == true && caule.isChecked() == true){
                problema = "Falta de água e excesso de calor.";
                solucao= "Regar a planta mais vezes e movê-la para um lugar menos iluminado.";
            }
            if(pontaSeca.isChecked() == true && roxo.isChecked() == true){
                problema = "Muito calor,Falta do nutriente fósforo (P)";
                solucao= "Adube com o chamado NPK e coloque em um lugar com pouca iluminação.";
            }
            if(pontaSeca.isChecked() == true && bordaSeca.isChecked() == true){
                problema = "Calor em excesso.";
                solucao= "Mover a planta para um local com luminosidade diferente de onde está atualmente. ";
            }
            if(pontaSeca.isChecked() == true && furada.isChecked() == true){
                problema = "Infestação de insetos e pragas.";
                solucao= "Aplicar um inseticida próprio.";
            }
            if(pontaSeca.isChecked() == true && pontoPreto.isChecked() == true){
                problema = "Infestação de fungos e bactérias.";
                solucao= "Aplicar um inseticida.";
            }
            if(pontaSeca.isChecked() == true && moleTrans.isChecked() == true){
                problema = "Muita luz ou excesso de água.";
                solucao= "Informe-se sobre a espécie e coloque-a em um lugar menos iluminado e informe-se sobre o número de regas.";
            }
            if(pontaSeca.isChecked() == true && queda.isChecked() == true){
                problema = "Erro nas regas e falta de nutrientes";
                solucao= "Informe-se sobre as regas da espécie e mantenha o solo fértil, composto de nutrientes especiais.";
            }
            if(pontaSeca.isChecked() == true && caule.isChecked() == true){
                problema = "Excesso de calor.";
                solucao= "Deixar a planta em um local com pouca iluminação.";
            }
            if(roxo.isChecked() == true && bordaSeca.isChecked() == true){
                problema = "Muito calor e Falta do nutriente fósforo (P)";
                solucao= "Mover para um local menos iluminado, usar o chamado NPK.";
            }
            if(roxo.isChecked() == true && furada.isChecked() == true){
                problema = "Falta do nutriente fósforo (P) e Infestação de insetos.";
                solucao= "Aplicar um inseticida próprio e adubar com o NPK.";
            }
            if(roxo.isChecked() == true && pontoPreto.isChecked() == true){
                problema = "Infestação de fungos e bactérias e Falta do nutriente fósforo (P)";
                solucao= "Usar o adubo NPK e usar um inseticida.";
            }
            if(roxo.isChecked() == true && moleTrans.isChecked() == true){
                problema = "Excesso de água e Falta do nutriente fósforo (P)";
                solucao= "Diminuir o número de regas e usar o adubo NPK.";
            }
            if(roxo.isChecked() == true && queda.isChecked() == true){
                problema = "Erro nas regas ou Falta do nutriente fósforo (P)";
                solucao= "informe-se sobre as regas da espécie e use o adubo NPK.";
            }
            if(roxo.isChecked() == true && caule.isChecked() == true){
                problema = "Excesso de calor e falta de fósforo (P) ";
                solucao= "Colocar a planta em um lugar com sombra e adubar com NPK.";
            }
            if(bordaSeca.isChecked() == true && furada.isChecked() == true){
                problema = "Excesso de iluminação e Infestação de insetos e pragas.";
                solucao= "Retire a planta do sol e aplique um inseticida próprio.";
            }
            if(bordaSeca.isChecked() == true && pontoPreto.isChecked() == true){
                problema = "Infestação de fungos e bactérias.";
                solucao= "Use um inseticida sistêmico próprio para plantas.";
            }
            if(bordaSeca.isChecked() == true && moleTrans.isChecked() == true){
                problema = "Muita água e muita luz.";
                solucao= "Diminuir a frequência das regas e mover a planta para um local com luminosidade diferente de onde está atualmente.";
            }
            if(bordaSeca.isChecked() == true && queda.isChecked() == true){
                problema = " Calor,deficiência de rega e de bons nutrientes";
                solucao= "Informe-se sobre as regas,Coloque a planta em um local com pouco calor e mantenha o solo fértil, composto de nutrientes especiais. ";
            }
            if(bordaSeca.isChecked() == true && caule.isChecked() == true){
                problema = "Excesso de luz.";
                solucao= "Deixar em um local com mais sombra e ventilação.";
            }
            if(furada.isChecked() == true && pontoPreto.isChecked() == true){
                problema = " Infestação de insetos e pragas,fungos e bactérias.";
                solucao= "Use um inseticida sistêmico próprio para plantas.  Caso seja um tempero, dê um intervalo de 20 a 30 dias para usá-lo novamente.";
            }
            if(furada.isChecked() == true && moleTrans.isChecked() == true){
                problema = "Infestação de insetos,pragas e Excesso de água.";
                solucao= "Use um inseticida sistêmico próprio para plantas e Diminua o número de regas";
            }
            if(furada.isChecked() == true && queda.isChecked() == true){
                problema = "Falta de água,Infestação de insetos e pragas";
                solucao= "Regar mais vezes e aplicar um inseticida próprio.";
            }
            if(furada.isChecked() == true && caule.isChecked() == true){
                problema = "Excesso de luz e Infestação de insetos,pragas";
                solucao= "Mover a planta para um lugar com pouca Iluminação e usar inseticida próprio. ";
            }
            if(pontoPreto.isChecked() == true && moleTrans.isChecked() == true){
                problema = "Infestação de fungos e bactérias e excessode água.";
                solucao= "Diminuir as regas e aplicar inseticida sistêmico próprio para plantas.";
            }
            if(pontoPreto.isChecked() == true && queda.isChecked() == true){
                problema = "Infestação de fungos e bactérias.";
                solucao= "Use um inseticida sistêmico próprio para plantas.";
            }
            if(pontoPreto.isChecked() == true && caule.isChecked() == true){
                problema = "Infestação de fungos,bactérias e excesso de luz.";
                solucao= "Aplique inseticida sistêmico próprio para plantas e mova sua planta para um local com pouca iluminação. ";
            }
            if(moleTrans.isChecked() == true && queda.isChecked() == true){
                problema = "Falta de água.";
                solucao= "Regar a sua planta mais vezes.";
            }
            if(moleTrans.isChecked() == true && caule.isChecked() == true){
                problema = "Falta de água e excesso de luz.";
                solucao= "Mover a planta para um lugar menos iluminado e regar mais vezes.";
            }
            if(queda.isChecked() == true && caule.isChecked() == true){
                problema = "Deficiência de rega e de bons nutrientes.";
                solucao= "Manter o solo fértil, composto de nutrientes especiais para a espécie em questão e regar mais vezes.";
            }

        }
        if(statusLuz == 1){

            if(amarela.isChecked() == true && murcha.isChecked() == true){
                problema = "Falta de água.";
                solucao = "Informe-se sobre as regas da espécie.";
            }
            if(amarela.isChecked() == true && pontaSeca.isChecked() == true){
                problema = "Falta  de água e calor.";
                solucao= "Aumente o número de regas e coloque-a em um lugar mais iluminado.";
            }
            if(amarela.isChecked() == true && roxo.isChecked() == true){
                problema = "Falta de nitrogênio (N),Falta do nutriente fósforo (P).";
                solucao= "Adube com o chamado NPK, que supre os nutrientes. Também é possível usar o adubo completo que, além do NPK, possui micronutrientes.";
            }
            if(amarela.isChecked() == true && bordaSeca.isChecked() == true){
                problema = "Falta de água";
                solucao= "Aumentar as regas.";
            }
            if(amarela.isChecked() == true && furada.isChecked() == true){
                problema = "Infestação de insetos e pragas";
                solucao= "limpe as folhas para retirar os insetos (caso eles não sejam voadores) e aplique um inseticida próprio. Caso seja o primeiro ataque, vale usar repelentes com o óleo de neen e calda de fumo.";
            }
            if(amarela.isChecked() == true && pontoPreto.isChecked() == true){
                problema = "Fungos e bactérias";
                solucao= "Use um inseticida sistêmico próprio para plantas. Caso seja um tempero, dê um intervalor de 20 a 30 dias para usá-lo novamente.";
            }
            if(amarela.isChecked() == true && moleTrans.isChecked() == true){
                problema = "Excesso de água";
                solucao= "Informe-se sobre as regas e verifique se os furos do vaso não estão entupidos.";
            }
            if(amarela.isChecked() == true && queda.isChecked() == true){
                problema = "Deficiência de rega e de bons nutrientes";
                solucao= "Manter o solo fértil, composto de nutrientes especiais para a espécie em questão e informar-se sobre as regas.";
            }
            if(amarela.isChecked() == true && caule.isChecked() == true){
                problema = "Falta de luz";
                solucao= "Informe-se sobre a espécie e coloque-a em um lugar mais iluminado.";
            }
            if(murcha.isChecked() == true && pontaSeca.isChecked() == true){
                problema = "Falta de calor";
                solucao= "Mover para um local iluminado.";
            }
            if(murcha.isChecked() == true && roxo.isChecked() == true){
                problema = "falta de sol,água e nutriente fósforo (P)";
                solucao= "Adube com o chamado NPK,coloque em um lugar com mais iluminação e aumente o número de regas.";
            }
            if(murcha.isChecked() == true && bordaSeca.isChecked() == true){
                problema = "Pouca luz,erro nas regas";
                solucao= "Colocar em local com maior iluminação e informar-se sobre as regas.";
            }
            if(murcha.isChecked() == true && furada.isChecked() == true){
                problema = "Erro nas regas e infestação de insetos e pragas.";
                solucao= "Informar-se sobre as regas,colocar em local iluminado e aplique um inseticida próprio.";
            }
            if(murcha.isChecked() == true && pontoPreto.isChecked() == true){
                problema = "Fungos e bactérias,falta de água";
                solucao= "Usar um inseticida,informar-se sobre as regas.";
            }
            if(murcha.isChecked() == true && moleTrans.isChecked() == true){
                problema = "Falta de água, ou excesso de luz";
                solucao= "Aumente o número de regas e coloque-a num lugar com mais sombra e ventilação.";
            }
            if(murcha.isChecked() == true && queda.isChecked() == true){
                problema = "Deficiência de rega e nutrientes";
                solucao= "Regar a planta mais vezes e manter o solo fértil, composto de nutrientes.";
            }
            if(murcha.isChecked() == true && caule.isChecked() == true){
                problema = "Falta de água e luz";
                solucao= "Mover para um local iluminado e Aumentar o número de regas.";
            }
            if(pontaSeca.isChecked() == true && roxo.isChecked() == true){
                problema = "Falta de luz e Falta do nutriente fósforo (P)";
                solucao= "Adube com o chamado NPK e mova a planta para um local com mais iluminação.";
            }
            if(pontaSeca.isChecked() == true && bordaSeca.isChecked() == true){
                problema = "Falta de luz";
                solucao= "Informe-se sobre a espécie e coloque-a em um lugar mais iluminado.";
            }
            if(pontaSeca.isChecked() == true && furada.isChecked() == true){
                problema = "Falta de calor e Infestação de insetos e pragas.";
                solucao= "Mover a planta para um local com luminosidade diferente de onde está atualmente e aplique um inseticida próprio.";
            }
            if(pontaSeca.isChecked() == true && pontoPreto.isChecked() == true){
                problema = "Falta de calor e Infestação de fungos e bactérias.";
                solucao= "Use um inseticida sistêmico próprio para plantas e mova para um local mais iluminado.";
            }
            if(pontaSeca.isChecked() == true && moleTrans.isChecked() == true){
                problema = "Excesso de água e falta de luz";
                solucao= "Diminua as regas e mova para um local iluminado.";
            }
            if(pontaSeca.isChecked() == true && queda.isChecked() == true){
                problema = "Deficiência de rega";
                solucao= "Regar a planta mais vezes.";
            }
            if(pontaSeca.isChecked() == true && caule.isChecked() == true){
                problema = "Falta de iluminação";
                solucao= "Coloque em um lugar mais iluminado.";
            }
            if(roxo.isChecked() == true && bordaSeca.isChecked() == true){
                problema = "Falta do nutriente fósforo (P),calor e água";
                solucao= "Adube com NPK,mova para um local com mais sombra e aumente o número de regas.";
            }
            if(roxo.isChecked() == true && furada.isChecked() == true){
                problema = "Falta de fósforo (P), Infestação de nsetos e pragas.";
                solucao= "Use um inseticida sistêmico próprio para plantas e Adube com NPK.";
            }
            if(roxo.isChecked() == true && pontoPreto.isChecked() == true){
                problema = "Falta de fósforo (P), Infestação de Fungos e bactérias";
                solucao= "Adube com NPK e aplique um inseticida próprio.";
            }
            if(roxo.isChecked() == true && moleTrans.isChecked() == true){
                problema = "Excesso de água e Falta do nutriente fósforo (P)";
                solucao= "Informe-se sobre as regas e adube com NPK.";
            }
            if(roxo.isChecked() == true && queda.isChecked() == true){
                problema = "Falta de rega e de nutrientes";
                solucao= "Mantenha o solo fértil e aumente a quantidade de regas.";
            }
            if(roxo.isChecked() == true && caule.isChecked() == true){
                problema = "Falta de luz e nutriente";
                solucao= "Mova para um local com mais iluminação e adube com NPK.";
            }
            if(bordaSeca.isChecked() == true && furada.isChecked() == true){
                problema = "Infestação de insetos e pragas e falta de luz";
                solucao= "Aplique um inseticida próprio e mova para um local com mais iluminação.";
            }
            if(bordaSeca.isChecked() == true && pontoPreto.isChecked() == true){
                problema = "Infestação de fungos e bactérias e falta de luz";
                solucao= "Use um inseticida sistêmico próprio para plantas coloque sua planta em um local com mais luz.";
            }
            if(bordaSeca.isChecked() == true && moleTrans.isChecked() == true){
                problema = "Falta de luz e excesso de água";
                solucao= "Informe-se sobre as regas mova a planta para um local com mais iluminação.";
            }
            if(bordaSeca.isChecked() == true && queda.isChecked() == true){
                problema = "falta de luz e falta de água";
                solucao= "Mover para um local com mais luz e regar mais vezes.";
            }
            if(bordaSeca.isChecked() == true && caule.isChecked() == true){
                problema = "Falta de luz";
                solucao= "Mover para um lugar com mais iluminação.";
            }
            if(furada.isChecked() == true && pontoPreto.isChecked() == true){
                problema = "Infestação de insetos e pragas,fungos e bactérias.";
                solucao= "Limpe as folhas para retirar os insetos (caso eles não sejam voadores) e use um inseticida sistêmico próprio para plantas. Caso seja um tempero, dê um intervalo de 20 a 30 dias para usá-lo novamente.";
            }
            if(furada.isChecked() == true && moleTrans.isChecked() == true){
                problema = "Infestação de insetos e pragas e excesso de água";
                solucao= "Use um inseticida sistêmico próprio para plantas e informe-se sobre as regas.";
            }
            if(furada.isChecked() == true && queda.isChecked() == true){
                problema = "Infestação de insetos e pragas,deficiência de rega e de bons nutrientes";
                solucao= "Usar um inseticida,aumentar o número de regas e manter o solo fértil, composto de nutrientes especiais.";
            }
            if(furada.isChecked() == true && caule.isChecked() == true){
                problema = "Infestação de insetos e pragas,falta de luz";
                solucao= "Use um inseticida sistêmico próprio para plantas e coloque em um lugar com mais iluminação. ";
            }
            if(pontoPreto.isChecked() == true && moleTrans.isChecked() == true){
                problema = "Infestação de fungos e bactérias e excesso de água";
                solucao= "Use um inseticida para plantas e diminua a quantidade de água.";
            }
            if(pontoPreto.isChecked() == true && queda.isChecked() == true){
                problema = "Infestação de fungos e bactérias,deficiência de rega e de bons nutrientes";
                solucao= "Aplique um inseticida próprio, aumente o número de regas e mantenha o solo fértil composto de nutrientes especiais.";
            }
            if(pontoPreto.isChecked() == true && caule.isChecked() == true){
                problema = "Infestação de fungos e bactérias e falta de iluminação";
                solucao= "Aplique um inseticida e mova para um local com mais luz.";
            }
            if(moleTrans.isChecked() == true && queda.isChecked() == true){
                problema = "Falta de nutrientes e rega";
                solucao= "Informe-se sobre as regas e mantenha o solo fértil, composto de nutrientes especiais para a espécie em questão.";
            }
            if(moleTrans.isChecked() == true && caule.isChecked() == true){
                problema = "Excesso de água e falta de luz";
                solucao= "Diminua a número de regas e mova para um local com mais iluminação.";
            }
            if(queda.isChecked() == true && caule.isChecked() == true){
                problema = "Deficiência de rega e falta de iluminação";
                solucao= "Aumente a número de regas e coloque em um local com mais iluminação.";
            }
        }
        itDiagnostico.putExtra("problema",problema);
        itDiagnostico.putExtra("solucao",solucao);
        startActivity(itDiagnostico);



    }


    @Override
    public void onBackPressed() {
        Intent it = new Intent(TelaHelp.this, MenusActivity.class);
        startActivity(it);
    }
}
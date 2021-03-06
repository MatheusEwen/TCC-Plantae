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
                problema = "Falta de ??gua, Excesso de luz ou calor.";
                solucao = "Informe-se sobre as regas da esp??cie e coloque-a num lugar com mais sombra e ventila????o. Se ela secou muito r??pido, pode ter secado o miolo e, neste caso, ?? irrevers??vel.";
            }
            if(amarela.isChecked() == true && pontaSeca.isChecked() == true){
                problema = "Excesso de luz ou calor";
                solucao= "Informe-se sobre a esp??cie e coloque-a em um lugar com mais sombra e ventila????o.";
            }
            if(amarela.isChecked() == true && roxo.isChecked() == true){
                problema = "Falta de nitrog??nio (N),Falta do nutriente f??sforo (P).";
                solucao= "Adube com o chamado NPK, que supre os nutrientes. Tamb??m ?? poss??vel usar o adubo completo que, al??m do NPK, possui micronutrientes.";
            }
            if(amarela.isChecked() == true && bordaSeca.isChecked() == true){
                problema = "Excesso de luz ou calor";
                solucao= "Coloque num lugar com mais sombra.";
            }
            if(amarela.isChecked() == true && furada.isChecked() == true){
                problema = "Infesta????o de insetos e pragas.";
                solucao= "Limpe as folhas para retirar os insetos (caso eles n??o sejam voadores) e aplique um inseticida pr??prio. Caso seja o primeiro ataque, vale usar repelentes com o ??leo de neen e calda de fumo.";
            }
            if(amarela.isChecked() == true && pontoPreto.isChecked() == true){
                problema = "Infesta????o de fungos e bact??rias";
                solucao= "Use um inseticida sist??mico pr??prio para plantas. Caso seja um tempero, d?? um intervalor de 20 a 30 dias para us??-lo novamente.";
            }
            if(amarela.isChecked() == true && moleTrans.isChecked() == true){
                problema = "Excesso de ??gua";
                solucao= "Informe-se sobre as regas e verifique se os furos do vaso n??o est??o entupidos.";
            }
            if(amarela.isChecked() == true && queda.isChecked() == true){
                problema = "Pouca quantidade de regas e de bons nutrientes";
                solucao= "Manter o solo f??rtil, composto de nutrientes especiais para a esp??cie e aumentar a quantidade de ??gua.";
            }
            if(amarela.isChecked() == true && caule.isChecked() == true){
                problema = "Muita exposi????o ?? luz solar";
                solucao= "Verifique quais s??o as necessidades adequadas da planta que apresenta o problema e mude-a de lugar.";
            }
            if(murcha.isChecked() == true && pontaSeca.isChecked() == true){
                problema = "Falta de ??gua, Excesso de calor";
                solucao= "Informe-se sobre as regas da esp??cie e coloque-a num lugar com mais sombra e ventila????o.";
            }
            if(murcha.isChecked() == true && roxo.isChecked() == true){
                problema = "Excesso de calor,Falta do nutriente f??sforo (P)";
                solucao= "Mover a planta para um local com luminosidade diferente de onde est?? atualmente, adube com o chamado NPK, que supre os nutrientes.";
            }
            if(murcha.isChecked() == true && bordaSeca.isChecked() == true){
                problema = "Excesso de calor ou Falta de ??gua.";
                solucao= "Mover para um lugar com mais sombra, Informe-se sobre as regas.";
            }
            if(murcha.isChecked() == true && furada.isChecked() == true){
                problema = "Infesta????o de insetos e pragas.";
                solucao= "Aplique um inseticida pr??prio.";
            }
            if(murcha.isChecked() == true && pontoPreto.isChecked() == true){
                problema = "Infesta????o de fungos e bact??rias.";
                solucao= "Usar um inseticida sist??mico pr??prio para plantas.";
            }
            if(murcha.isChecked() == true && moleTrans.isChecked() == true){
                problema = "Execsso de ??gua";
                solucao= "Diminuir o n??mero de regas.";
            }
            if(murcha.isChecked() == true && queda.isChecked() == true){
                problema = "Calor excessivo e defici??ncia de rega";
                solucao= "Mover para um local menos iluminado e regar mais vezes.";
            }
            if(murcha.isChecked() == true && caule.isChecked() == true){
                problema = "Falta de ??gua e excesso de calor.";
                solucao= "Regar a planta mais vezes e mov??-la para um lugar menos iluminado.";
            }
            if(pontaSeca.isChecked() == true && roxo.isChecked() == true){
                problema = "Muito calor,Falta do nutriente f??sforo (P)";
                solucao= "Adube com o chamado NPK e coloque em um lugar com pouca ilumina????o.";
            }
            if(pontaSeca.isChecked() == true && bordaSeca.isChecked() == true){
                problema = "Calor em excesso.";
                solucao= "Mover a planta para um local com luminosidade diferente de onde est?? atualmente. ";
            }
            if(pontaSeca.isChecked() == true && furada.isChecked() == true){
                problema = "Infesta????o de insetos e pragas.";
                solucao= "Aplicar um inseticida pr??prio.";
            }
            if(pontaSeca.isChecked() == true && pontoPreto.isChecked() == true){
                problema = "Infesta????o de fungos e bact??rias.";
                solucao= "Aplicar um inseticida.";
            }
            if(pontaSeca.isChecked() == true && moleTrans.isChecked() == true){
                problema = "Muita luz ou excesso de ??gua.";
                solucao= "Informe-se sobre a esp??cie e coloque-a em um lugar menos iluminado e informe-se sobre o n??mero de regas.";
            }
            if(pontaSeca.isChecked() == true && queda.isChecked() == true){
                problema = "Erro nas regas e falta de nutrientes";
                solucao= "Informe-se sobre as regas da esp??cie e mantenha o solo f??rtil, composto de nutrientes especiais.";
            }
            if(pontaSeca.isChecked() == true && caule.isChecked() == true){
                problema = "Excesso de calor.";
                solucao= "Deixar a planta em um local com pouca ilumina????o.";
            }
            if(roxo.isChecked() == true && bordaSeca.isChecked() == true){
                problema = "Muito calor e Falta do nutriente f??sforo (P)";
                solucao= "Mover para um local menos iluminado, usar o chamado NPK.";
            }
            if(roxo.isChecked() == true && furada.isChecked() == true){
                problema = "Falta do nutriente f??sforo (P) e Infesta????o de insetos.";
                solucao= "Aplicar um inseticida pr??prio e adubar com o NPK.";
            }
            if(roxo.isChecked() == true && pontoPreto.isChecked() == true){
                problema = "Infesta????o de fungos e bact??rias e Falta do nutriente f??sforo (P)";
                solucao= "Usar o adubo NPK e usar um inseticida.";
            }
            if(roxo.isChecked() == true && moleTrans.isChecked() == true){
                problema = "Excesso de ??gua e Falta do nutriente f??sforo (P)";
                solucao= "Diminuir o n??mero de regas e usar o adubo NPK.";
            }
            if(roxo.isChecked() == true && queda.isChecked() == true){
                problema = "Erro nas regas ou Falta do nutriente f??sforo (P)";
                solucao= "informe-se sobre as regas da esp??cie e use o adubo NPK.";
            }
            if(roxo.isChecked() == true && caule.isChecked() == true){
                problema = "Excesso de calor e falta de f??sforo (P) ";
                solucao= "Colocar a planta em um lugar com sombra e adubar com NPK.";
            }
            if(bordaSeca.isChecked() == true && furada.isChecked() == true){
                problema = "Excesso de ilumina????o e Infesta????o de insetos e pragas.";
                solucao= "Retire a planta do sol e aplique um inseticida pr??prio.";
            }
            if(bordaSeca.isChecked() == true && pontoPreto.isChecked() == true){
                problema = "Infesta????o de fungos e bact??rias.";
                solucao= "Use um inseticida sist??mico pr??prio para plantas.";
            }
            if(bordaSeca.isChecked() == true && moleTrans.isChecked() == true){
                problema = "Muita ??gua e muita luz.";
                solucao= "Diminuir a frequ??ncia das regas e mover a planta para um local com luminosidade diferente de onde est?? atualmente.";
            }
            if(bordaSeca.isChecked() == true && queda.isChecked() == true){
                problema = " Calor,defici??ncia de rega e de bons nutrientes";
                solucao= "Informe-se sobre as regas,Coloque a planta em um local com pouco calor e mantenha o solo f??rtil, composto de nutrientes especiais. ";
            }
            if(bordaSeca.isChecked() == true && caule.isChecked() == true){
                problema = "Excesso de luz.";
                solucao= "Deixar em um local com mais sombra e ventila????o.";
            }
            if(furada.isChecked() == true && pontoPreto.isChecked() == true){
                problema = " Infesta????o de insetos e pragas,fungos e bact??rias.";
                solucao= "Use um inseticida sist??mico pr??prio para plantas.  Caso seja um tempero, d?? um intervalo de 20 a 30 dias para us??-lo novamente.";
            }
            if(furada.isChecked() == true && moleTrans.isChecked() == true){
                problema = "Infesta????o de insetos,pragas e Excesso de ??gua.";
                solucao= "Use um inseticida sist??mico pr??prio para plantas e Diminua o n??mero de regas";
            }
            if(furada.isChecked() == true && queda.isChecked() == true){
                problema = "Falta de ??gua,Infesta????o de insetos e pragas";
                solucao= "Regar mais vezes e aplicar um inseticida pr??prio.";
            }
            if(furada.isChecked() == true && caule.isChecked() == true){
                problema = "Excesso de luz e Infesta????o de insetos,pragas";
                solucao= "Mover a planta para um lugar com pouca Ilumina????o e usar inseticida pr??prio. ";
            }
            if(pontoPreto.isChecked() == true && moleTrans.isChecked() == true){
                problema = "Infesta????o de fungos e bact??rias e excessode ??gua.";
                solucao= "Diminuir as regas e aplicar inseticida sist??mico pr??prio para plantas.";
            }
            if(pontoPreto.isChecked() == true && queda.isChecked() == true){
                problema = "Infesta????o de fungos e bact??rias.";
                solucao= "Use um inseticida sist??mico pr??prio para plantas.";
            }
            if(pontoPreto.isChecked() == true && caule.isChecked() == true){
                problema = "Infesta????o de fungos,bact??rias e excesso de luz.";
                solucao= "Aplique inseticida sist??mico pr??prio para plantas e mova sua planta para um local com pouca ilumina????o. ";
            }
            if(moleTrans.isChecked() == true && queda.isChecked() == true){
                problema = "Falta de ??gua.";
                solucao= "Regar a sua planta mais vezes.";
            }
            if(moleTrans.isChecked() == true && caule.isChecked() == true){
                problema = "Falta de ??gua e excesso de luz.";
                solucao= "Mover a planta para um lugar menos iluminado e regar mais vezes.";
            }
            if(queda.isChecked() == true && caule.isChecked() == true){
                problema = "Defici??ncia de rega e de bons nutrientes.";
                solucao= "Manter o solo f??rtil, composto de nutrientes especiais para a esp??cie em quest??o e regar mais vezes.";
            }

        }
        if(statusLuz == 1){

            if(amarela.isChecked() == true && murcha.isChecked() == true){
                problema = "Falta de ??gua.";
                solucao = "Informe-se sobre as regas da esp??cie.";
            }
            if(amarela.isChecked() == true && pontaSeca.isChecked() == true){
                problema = "Falta  de ??gua e calor.";
                solucao= "Aumente o n??mero de regas e coloque-a em um lugar mais iluminado.";
            }
            if(amarela.isChecked() == true && roxo.isChecked() == true){
                problema = "Falta de nitrog??nio (N),Falta do nutriente f??sforo (P).";
                solucao= "Adube com o chamado NPK, que supre os nutrientes. Tamb??m ?? poss??vel usar o adubo completo que, al??m do NPK, possui micronutrientes.";
            }
            if(amarela.isChecked() == true && bordaSeca.isChecked() == true){
                problema = "Falta de ??gua";
                solucao= "Aumentar as regas.";
            }
            if(amarela.isChecked() == true && furada.isChecked() == true){
                problema = "Infesta????o de insetos e pragas";
                solucao= "limpe as folhas para retirar os insetos (caso eles n??o sejam voadores) e aplique um inseticida pr??prio. Caso seja o primeiro ataque, vale usar repelentes com o ??leo de neen e calda de fumo.";
            }
            if(amarela.isChecked() == true && pontoPreto.isChecked() == true){
                problema = "Fungos e bact??rias";
                solucao= "Use um inseticida sist??mico pr??prio para plantas. Caso seja um tempero, d?? um intervalor de 20 a 30 dias para us??-lo novamente.";
            }
            if(amarela.isChecked() == true && moleTrans.isChecked() == true){
                problema = "Excesso de ??gua";
                solucao= "Informe-se sobre as regas e verifique se os furos do vaso n??o est??o entupidos.";
            }
            if(amarela.isChecked() == true && queda.isChecked() == true){
                problema = "Defici??ncia de rega e de bons nutrientes";
                solucao= "Manter o solo f??rtil, composto de nutrientes especiais para a esp??cie em quest??o e informar-se sobre as regas.";
            }
            if(amarela.isChecked() == true && caule.isChecked() == true){
                problema = "Falta de luz";
                solucao= "Informe-se sobre a esp??cie e coloque-a em um lugar mais iluminado.";
            }
            if(murcha.isChecked() == true && pontaSeca.isChecked() == true){
                problema = "Falta de calor";
                solucao= "Mover para um local iluminado.";
            }
            if(murcha.isChecked() == true && roxo.isChecked() == true){
                problema = "falta de sol,??gua e nutriente f??sforo (P)";
                solucao= "Adube com o chamado NPK,coloque em um lugar com mais ilumina????o e aumente o n??mero de regas.";
            }
            if(murcha.isChecked() == true && bordaSeca.isChecked() == true){
                problema = "Pouca luz,erro nas regas";
                solucao= "Colocar em local com maior ilumina????o e informar-se sobre as regas.";
            }
            if(murcha.isChecked() == true && furada.isChecked() == true){
                problema = "Erro nas regas e infesta????o de insetos e pragas.";
                solucao= "Informar-se sobre as regas,colocar em local iluminado e aplique um inseticida pr??prio.";
            }
            if(murcha.isChecked() == true && pontoPreto.isChecked() == true){
                problema = "Fungos e bact??rias,falta de ??gua";
                solucao= "Usar um inseticida,informar-se sobre as regas.";
            }
            if(murcha.isChecked() == true && moleTrans.isChecked() == true){
                problema = "Falta de ??gua, ou excesso de luz";
                solucao= "Aumente o n??mero de regas e coloque-a num lugar com mais sombra e ventila????o.";
            }
            if(murcha.isChecked() == true && queda.isChecked() == true){
                problema = "Defici??ncia de rega e nutrientes";
                solucao= "Regar a planta mais vezes e manter o solo f??rtil, composto de nutrientes.";
            }
            if(murcha.isChecked() == true && caule.isChecked() == true){
                problema = "Falta de ??gua e luz";
                solucao= "Mover para um local iluminado e Aumentar o n??mero de regas.";
            }
            if(pontaSeca.isChecked() == true && roxo.isChecked() == true){
                problema = "Falta de luz e Falta do nutriente f??sforo (P)";
                solucao= "Adube com o chamado NPK e mova a planta para um local com mais ilumina????o.";
            }
            if(pontaSeca.isChecked() == true && bordaSeca.isChecked() == true){
                problema = "Falta de luz";
                solucao= "Informe-se sobre a esp??cie e coloque-a em um lugar mais iluminado.";
            }
            if(pontaSeca.isChecked() == true && furada.isChecked() == true){
                problema = "Falta de calor e Infesta????o de insetos e pragas.";
                solucao= "Mover a planta para um local com luminosidade diferente de onde est?? atualmente e aplique um inseticida pr??prio.";
            }
            if(pontaSeca.isChecked() == true && pontoPreto.isChecked() == true){
                problema = "Falta de calor e Infesta????o de fungos e bact??rias.";
                solucao= "Use um inseticida sist??mico pr??prio para plantas e mova para um local mais iluminado.";
            }
            if(pontaSeca.isChecked() == true && moleTrans.isChecked() == true){
                problema = "Excesso de ??gua e falta de luz";
                solucao= "Diminua as regas e mova para um local iluminado.";
            }
            if(pontaSeca.isChecked() == true && queda.isChecked() == true){
                problema = "Defici??ncia de rega";
                solucao= "Regar a planta mais vezes.";
            }
            if(pontaSeca.isChecked() == true && caule.isChecked() == true){
                problema = "Falta de ilumina????o";
                solucao= "Coloque em um lugar mais iluminado.";
            }
            if(roxo.isChecked() == true && bordaSeca.isChecked() == true){
                problema = "Falta do nutriente f??sforo (P),calor e ??gua";
                solucao= "Adube com NPK,mova para um local com mais sombra e aumente o n??mero de regas.";
            }
            if(roxo.isChecked() == true && furada.isChecked() == true){
                problema = "Falta de f??sforo (P), Infesta????o de nsetos e pragas.";
                solucao= "Use um inseticida sist??mico pr??prio para plantas e Adube com NPK.";
            }
            if(roxo.isChecked() == true && pontoPreto.isChecked() == true){
                problema = "Falta de f??sforo (P), Infesta????o de Fungos e bact??rias";
                solucao= "Adube com NPK e aplique um inseticida pr??prio.";
            }
            if(roxo.isChecked() == true && moleTrans.isChecked() == true){
                problema = "Excesso de ??gua e Falta do nutriente f??sforo (P)";
                solucao= "Informe-se sobre as regas e adube com NPK.";
            }
            if(roxo.isChecked() == true && queda.isChecked() == true){
                problema = "Falta de rega e de nutrientes";
                solucao= "Mantenha o solo f??rtil e aumente a quantidade de regas.";
            }
            if(roxo.isChecked() == true && caule.isChecked() == true){
                problema = "Falta de luz e nutriente";
                solucao= "Mova para um local com mais ilumina????o e adube com NPK.";
            }
            if(bordaSeca.isChecked() == true && furada.isChecked() == true){
                problema = "Infesta????o de insetos e pragas e falta de luz";
                solucao= "Aplique um inseticida pr??prio e mova para um local com mais ilumina????o.";
            }
            if(bordaSeca.isChecked() == true && pontoPreto.isChecked() == true){
                problema = "Infesta????o de fungos e bact??rias e falta de luz";
                solucao= "Use um inseticida sist??mico pr??prio para plantas coloque sua planta em um local com mais luz.";
            }
            if(bordaSeca.isChecked() == true && moleTrans.isChecked() == true){
                problema = "Falta de luz e excesso de ??gua";
                solucao= "Informe-se sobre as regas mova a planta para um local com mais ilumina????o.";
            }
            if(bordaSeca.isChecked() == true && queda.isChecked() == true){
                problema = "falta de luz e falta de ??gua";
                solucao= "Mover para um local com mais luz e regar mais vezes.";
            }
            if(bordaSeca.isChecked() == true && caule.isChecked() == true){
                problema = "Falta de luz";
                solucao= "Mover para um lugar com mais ilumina????o.";
            }
            if(furada.isChecked() == true && pontoPreto.isChecked() == true){
                problema = "Infesta????o de insetos e pragas,fungos e bact??rias.";
                solucao= "Limpe as folhas para retirar os insetos (caso eles n??o sejam voadores) e use um inseticida sist??mico pr??prio para plantas. Caso seja um tempero, d?? um intervalo de 20 a 30 dias para us??-lo novamente.";
            }
            if(furada.isChecked() == true && moleTrans.isChecked() == true){
                problema = "Infesta????o de insetos e pragas e excesso de ??gua";
                solucao= "Use um inseticida sist??mico pr??prio para plantas e informe-se sobre as regas.";
            }
            if(furada.isChecked() == true && queda.isChecked() == true){
                problema = "Infesta????o de insetos e pragas,defici??ncia de rega e de bons nutrientes";
                solucao= "Usar um inseticida,aumentar o n??mero de regas e manter o solo f??rtil, composto de nutrientes especiais.";
            }
            if(furada.isChecked() == true && caule.isChecked() == true){
                problema = "Infesta????o de insetos e pragas,falta de luz";
                solucao= "Use um inseticida sist??mico pr??prio para plantas e coloque em um lugar com mais ilumina????o. ";
            }
            if(pontoPreto.isChecked() == true && moleTrans.isChecked() == true){
                problema = "Infesta????o de fungos e bact??rias e excesso de ??gua";
                solucao= "Use um inseticida para plantas e diminua a quantidade de ??gua.";
            }
            if(pontoPreto.isChecked() == true && queda.isChecked() == true){
                problema = "Infesta????o de fungos e bact??rias,defici??ncia de rega e de bons nutrientes";
                solucao= "Aplique um inseticida pr??prio, aumente o n??mero de regas e mantenha o solo f??rtil composto de nutrientes especiais.";
            }
            if(pontoPreto.isChecked() == true && caule.isChecked() == true){
                problema = "Infesta????o de fungos e bact??rias e falta de ilumina????o";
                solucao= "Aplique um inseticida e mova para um local com mais luz.";
            }
            if(moleTrans.isChecked() == true && queda.isChecked() == true){
                problema = "Falta de nutrientes e rega";
                solucao= "Informe-se sobre as regas e mantenha o solo f??rtil, composto de nutrientes especiais para a esp??cie em quest??o.";
            }
            if(moleTrans.isChecked() == true && caule.isChecked() == true){
                problema = "Excesso de ??gua e falta de luz";
                solucao= "Diminua a n??mero de regas e mova para um local com mais ilumina????o.";
            }
            if(queda.isChecked() == true && caule.isChecked() == true){
                problema = "Defici??ncia de rega e falta de ilumina????o";
                solucao= "Aumente a n??mero de regas e coloque em um local com mais ilumina????o.";
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
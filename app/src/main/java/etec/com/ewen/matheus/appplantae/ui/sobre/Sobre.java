package etec.com.ewen.matheus.appplantae.ui.sobre;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import etec.com.ewen.matheus.appplantae.R;
import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;


public class Sobre extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Sobre() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static Sobre newInstance(String param1, String param2) {
        Sobre fragment = new Sobre();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String descricao = "Esse aplicativo foi criado pelos alunos da Etec Dr.Emilio Hernandez Aguilar, como projeto de TCC(Trabalho de Conclusão de Curso). Agradecimentos aos professores que nos ajudaram durante" +
                " esse percurso. Como também, agradecemos ao site icon8 por disponibilizar alguns ícones. O projeto Plantae foi desenvolvido pelos alunos:\n -Leticia \n -Leonardo \n -Matheus \n -Willians ";
        Element versao = new Element();
        versao.setTitle("Versão 1.0");
        return new AboutPage(getActivity())
                .setImage(R.drawable.logosobre)
                .setDescription(descricao)
                .addGroup("Entre em contado")
                .addEmail("plantaetcc@gmail.com", "E-mail")
                .addWebsite("https://www.google.com/", "Acesse nosso site")
                .addGroup("Redes Sociais")
                .addFacebook("100073755169759", "Facebook")
                .addInstagram("plantaetcc", "Instagram")
                .addYoutube("UCwBVhZ78Ju36XPdrlZFuzUg", "YouTube")
                .addGitHub("MatheusEwen/TCC-Plantae", "Github do projeto")
                .addItem(versao)
                .create();

        //return inflater.inflate(R.layout.fragment_sobre, container, false);
    }
}
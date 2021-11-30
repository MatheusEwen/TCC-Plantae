package etec.com.ewen.matheus.appplantae.ui.regar;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import config.ConfiguracaoFirebase;
import etec.com.ewen.matheus.appplantae.CustomAdapterRegar;
import etec.com.ewen.matheus.appplantae.MyDb;
import etec.com.ewen.matheus.appplantae.R;
import etec.com.ewen.matheus.appplantae.activity.TelaAlarme;
import helper.Base64Custom;
import model.RegarPlanta;


public class Regar extends Fragment {

    private RecyclerView recyclerViewRegar;
    private Spinner spinner;
    private CheckBox chbSegunda, chbTerca, chbQuarta, chbQuinta, chbSexta, chbSabado, chbDomingo;
    private ArrayList<String> arrayList = new ArrayList<>();
    private Button btnAlarme;
    private ImageButton btnAddLista;
    MyDb myDB;
    ArrayList<RegarPlanta>regarPlantas;
    CustomAdapterRegar customAdapterRegar;
    RegarPlanta plantasRegar = new RegarPlanta();

    Activity context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        context = getActivity();

        View root = inflater.inflate(R.layout.fragment_regar, container, false);
        return root;

    }

    @Override
    public void onStart() {
        super.onStart();

        btnAlarme = (Button) context.findViewById(R.id.btnAlarme);
        btnAddLista = (ImageButton) context.findViewById(R.id.btnAddLista);
        recyclerViewRegar = (RecyclerView) context.findViewById(R.id.recyclerViewRegar);
        spinner = (Spinner) context.findViewById(R.id.spinner);
        chbSegunda = (CheckBox) context.findViewById(R.id.chbSegunda);
        chbTerca = (CheckBox) context.findViewById(R.id.chbTerca);
        chbQuarta = (CheckBox) context.findViewById(R.id.chbQuarta);
        chbQuinta = (CheckBox) context.findViewById(R.id.chbQuinta);
        chbSexta = (CheckBox) context.findViewById(R.id.chbSexta);
        chbSabado = (CheckBox) context.findViewById(R.id.chbSabado);
        chbDomingo = (CheckBox) context.findViewById(R.id.chbDomingo);
        btnAddLista = (ImageButton) context.findViewById(R.id.btnAddLista);
        btnAddLista = (ImageButton) context.findViewById(R.id.btnAddLista);
        btnAddLista = (ImageButton) context.findViewById(R.id.btnAddLista);
        carregarDadosSpinner();
        myDB = new MyDb(context);
        regarPlantas = new ArrayList<>();
        regarPlantas = myDB.selectAllPlanta();
        customAdapterRegar = new CustomAdapterRegar(context, regarPlantas);
        recyclerViewRegar.setAdapter(customAdapterRegar);
        recyclerViewRegar.setLayoutManager(new LinearLayoutManager(context));
        swipe();

        btnAlarme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent telaAlarme = new Intent(context, TelaAlarme.class);
                startActivity(telaAlarme);
            }
        });

        btnAddLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (spinner.getSelectedItem() == null) {
                    Toast.makeText(context, "Cadastre uma planta para criar um guia de rega!", Toast.LENGTH_SHORT).show();
                } else {
                    MyDb myDb = new MyDb(context);
                    String n = spinner.getSelectedItem().toString();
                    String seg, ter, qua, qui, sex, sab, dom;
                    if (chbSegunda.isChecked()) {
                        seg = "sim";
                    } else {
                        seg = "nao";
                    }

                    if (chbTerca.isChecked()) {
                        ter = "sim";
                    } else {
                        ter = "nao";
                    }

                    if (chbQuarta.isChecked()) {
                        qua = "sim";
                    } else {
                        qua = "nao";
                    }

                    if (chbQuinta.isChecked()) {
                        qui = "sim";
                    } else {
                        qui = "nao";
                    }

                    if (chbSexta.isChecked()) {
                        sex = "sim";
                    } else {
                        sex = "nao";
                    }

                    if (chbSabado.isChecked()) {
                        sab = "sim";
                    } else {
                        sab = "nao";
                    }

                    if (chbDomingo.isChecked()) {
                        dom = "sim";
                    } else {
                        dom = "nao";
                    }
                    RegarPlanta regarPlanta = new RegarPlanta();
                    regarPlanta.setNome(n + "");
                    regarPlanta.setSeg(seg);
                    regarPlanta.setTer(ter);
                    regarPlanta.setQua(qua);
                    regarPlanta.setQui(qui);
                    regarPlanta.setSab(sab);
                    regarPlanta.setSex(sex);
                    regarPlanta.setDom(dom);

                    myDb.addPlanta(regarPlanta);
                    context.recreate();
                }
            }
        });

    }

    private void carregarDadosSpinner() {

        FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        String idUsuario = Base64Custom.codificarBase64(autenticacao.getCurrentUser().getEmail());
        DatabaseReference firebase = ConfiguracaoFirebase.getFirebaseDatabase();
        firebase.child("planta")
                .child(idUsuario)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        arrayList.clear();
                        for(DataSnapshot item : snapshot.getChildren()){
                            arrayList.add(item.child("nomePlanta").getValue(String.class));
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context, R.layout.style_spinner, arrayList);
                        spinner.setAdapter(arrayAdapter);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
    }

    public void swipe(){

        ItemTouchHelper.Callback itemTouch = new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                int dragFlags = ItemTouchHelper.ACTION_STATE_IDLE;
                int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
                return makeMovementFlags(dragFlags, swipeFlags);
            }
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                excluirRegarPlanta(viewHolder);

            }
        };
        new ItemTouchHelper(itemTouch).attachToRecyclerView(recyclerViewRegar);

    }
    public void excluirRegarPlanta(RecyclerView.ViewHolder viewHolder){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Ação");
        alertDialog.setMessage("Qual ação você quer fazer?");
        alertDialog.setCancelable(false);

        alertDialog.setPositiveButton("Apagar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int position = viewHolder.getAdapterPosition();
                plantasRegar = regarPlantas.get(position);
                myDB.excluirRegarPlanta(plantasRegar);
                atualizarRecycler(viewHolder);
                customAdapterRegar.notifyDataSetChanged();
                context.recreate();


            }
        });
        alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                customAdapterRegar.notifyDataSetChanged();
            }
        });
        AlertDialog alert = alertDialog.create();
        alert.show();
    }
    public void atualizarRecycler(RecyclerView.ViewHolder viewHolder){
        int position = viewHolder.getAdapterPosition();
        plantasRegar = regarPlantas.get(position);
        customAdapterRegar.notifyItemRemoved(position);

    }

}
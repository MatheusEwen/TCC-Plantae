package etec.com.ewen.matheus.appplantae.ui.visualizar;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

import adapter.AdapterPlanta;
import config.ConfiguracaoFirebase;
import etec.com.ewen.matheus.appplantae.R;
import etec.com.ewen.matheus.appplantae.TelaAlteracao;
import helper.Base64Custom;
import model.Planta;

public class Visualizar extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Planta> plantas;
    private FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
    private DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebaseDatabase();
    private Planta planta;

    private AdapterPlanta adapterPlanta;

    Activity context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getActivity();

        View root = inflater.inflate(R.layout.fragment_visualizar, container, false);
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        recyclerView = (RecyclerView) context.findViewById(R.id.recyclerPlantas);

        String emailUsuario = autenticacao.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64(emailUsuario);
        DatabaseReference plantaRef = firebaseRef.child("planta").child(idUsuario);
        swipe();

        //configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        plantas = new ArrayList<>();
        adapterPlanta = new AdapterPlanta(context, plantas);
        recyclerView.setAdapter(adapterPlanta);

        plantaRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Planta planta = dataSnapshot.getValue(Planta.class);
                    plantas.add(planta);
                }
                adapterPlanta.notifyDataSetChanged();
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
                excluirPlanta(viewHolder);

            }
        };
        new ItemTouchHelper(itemTouch).attachToRecyclerView(recyclerView);

    }

    public void excluirPlanta(RecyclerView.ViewHolder viewHolder){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Ação");
        alertDialog.setMessage("Qual ação você quer fazer?");
        alertDialog.setCancelable(false);

        alertDialog.setPositiveButton("Apagar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int position = viewHolder.getAdapterPosition();
                planta = plantas.get(position);

                FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
                String idUsuario = Base64Custom.codificarBase64(autenticacao.getCurrentUser().getEmail());

                DatabaseReference firebase = ConfiguracaoFirebase.getFirebaseDatabase();
                firebase.child("planta")
                        .child(idUsuario)
                        .child(planta.getChave()).removeValue();
                StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                StorageReference imgRef;
                imgRef = storageReference.child("Imagens").child(planta.getChave()+".jpeg");
                imgRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //Toast.makeText(VisualizarPlanta.this, "Planta apagada com sucesso", Toast.LENGTH_SHORT).show();
                        adapterPlanta.notifyDataSetChanged();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

                atualizarRecycler(viewHolder);
            }
        });
        alertDialog.setNegativeButton("Editar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int position = viewHolder.getAdapterPosition();
                Intent intent = new Intent(context, TelaAlteracao.class);
                intent.putExtra("planta", plantas.get(position));
                startActivity(intent);
                //finish();

            }
        });
        alertDialog.setNeutralButton("Cancelar" , new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(context, "Cancelado", Toast.LENGTH_SHORT).show();
                adapterPlanta.notifyDataSetChanged();
            }
        });
        AlertDialog alert = alertDialog.create();
        alert.show();
    }
    public void atualizarRecycler(RecyclerView.ViewHolder viewHolder){
        int position = viewHolder.getAdapterPosition();
        planta = plantas.get(position);
        adapterPlanta.notifyItemRemoved(position);
        context.recreate();
    }
}
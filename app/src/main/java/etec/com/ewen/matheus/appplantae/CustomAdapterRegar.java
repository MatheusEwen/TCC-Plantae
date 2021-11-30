package etec.com.ewen.matheus.appplantae;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import model.RegarPlanta;

public class CustomAdapterRegar extends RecyclerView.Adapter<CustomAdapterRegar.MyViewHolder> {

    Context context;
    ArrayList<RegarPlanta> mList;

    public CustomAdapterRegar(Context context, ArrayList<RegarPlanta> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.regar_planta_card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        RegarPlanta regarPlanta = (RegarPlanta) mList.get(position);
        holder.plantaRegarNome.setText(regarPlanta.getNome());
        if (regarPlanta.getSeg().toString().equals("sim")){
            holder.teste1.setBackgroundResource(R.drawable.folha_azul);
        }else{
            holder.teste1.setBackgroundResource(R.drawable.verde_claro);
        }
        if (regarPlanta.getTer().toString().equals("sim")){
            holder.teste2.setBackgroundResource(R.drawable.folha_azul);
        }else{
            holder.teste2.setBackgroundResource(R.drawable.verde_claro);
        }
        if (regarPlanta.getQua().toString().equals("sim")){
            holder.teste3.setBackgroundResource(R.drawable.folha_azul);
        }else{
            holder.teste3.setBackgroundResource(R.drawable.verde_claro);
        }
        if (regarPlanta.getQui().toString().equals("sim")){
            holder.teste4.setBackgroundResource(R.drawable.folha_azul);
        }else{
            holder.teste4.setBackgroundResource(R.drawable.verde_claro);
        }
        if (regarPlanta.getSex().toString().equals("sim")){
            holder.teste5.setBackgroundResource(R.drawable.folha_azul);
        }else{
            holder.teste5.setBackgroundResource(R.drawable.verde_claro);
        }
        if (regarPlanta.getSab().toString().equals("sim")){
            holder.teste6.setBackgroundResource(R.drawable.folha_azul);
        }else{
            holder.teste6.setBackgroundResource(R.drawable.verde_claro);
        }
        if (regarPlanta.getDom().toString().equals("sim")){
            holder.teste7.setBackgroundResource(R.drawable.folha_azul);
        }else{
            holder.teste7.setBackgroundResource(R.drawable.verde_claro);
        }

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView plantaRegarNome, teste1, teste2, teste3, teste4, teste5, teste6, teste7;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            plantaRegarNome = itemView.findViewById(R.id.plantaRegarNome);
            teste1 = itemView.findViewById(R.id.teste1);
            teste2 = itemView.findViewById(R.id.teste2);
            teste3 = itemView.findViewById(R.id.teste3);
            teste4 = itemView.findViewById(R.id.teste4);
            teste5 = itemView.findViewById(R.id.teste5);
            teste6 = itemView.findViewById(R.id.teste6);
            teste7 = itemView.findViewById(R.id.teste7);
        }
    }
}

package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import etec.com.ewen.matheus.appplantae.R;
import model.Planta;

public class AdapterPlanta extends RecyclerView.Adapter<AdapterPlanta.MyViewHolder> {

    private ArrayList<Planta> mList;
    private Context context;

    public AdapterPlanta(Context context, ArrayList<Planta> mList){
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(context)
                .inflate(R.layout.plantas_card, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Planta planta = mList.get(position);
        Glide.with(context).load(mList.get(position).getUrl()).into(holder.plantaImagemCard);
        holder.nomePlantaCard.setText(planta.getNomePlanta());
        holder.afCard.setText(planta.getAf());
        holder.copCard.setText(planta.getCop());
        holder.diaCard.setText(planta.getDias());
        holder.especieCard.setText(planta.getEspecie());
        holder.infCard.setText(planta.getInf());
        holder.luzCard.setText(planta.getLuz());


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder {

        private TextView nomePlantaCard;
        private TextView especieCard;
        private TextView luzCard;
        private TextView diaCard;
        private TextView copCard;
        private TextView afCard;
        private TextView infCard;
        private ImageView plantaImagemCard;

        public MyViewHolder(View itemView) {
            super(itemView);

            nomePlantaCard = itemView.findViewById(R.id.nomePlantaCard);
            especieCard = itemView.findViewById(R.id.especieCard);
            luzCard = itemView.findViewById(R.id.luzCard);
            diaCard = itemView.findViewById(R.id.diaCard);
            copCard = itemView.findViewById(R.id.copCard);
            afCard = itemView.findViewById(R.id.afCard);
            infCard = itemView.findViewById(R.id.infCard);
            plantaImagemCard = itemView.findViewById(R.id.plantaImagemCard);

        }
    }


}

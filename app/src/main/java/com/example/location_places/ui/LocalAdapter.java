package com.example.location_places.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.location_places.R;
import com.example.location_places.model.Local;
import com.example.location_places.util.ImageUtil;

import java.util.ArrayList;
import java.util.List;

public class LocalAdapter extends RecyclerView.Adapter<LocalAdapter.LocalHolder> {
    private List<Local> results = new ArrayList<>();
    private static ItemClickListener itemClickListener;
    @NonNull
    @Override
    public LocalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_local, parent, false);
        return new LocalHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull LocalHolder holder, int position) {
        Local local = results.get(position);
        holder.textViewData.setText(local.getData());
        holder.textViewDescricao.setText(local.getDescricao());
        holder.textViewLatitude.setText(local.getLatitude());
        holder.textViewLongitude.setText(local.getLongitude());
        holder.fotoLocalCard.setImageBitmap(ImageUtil.decode(local.getImagem()));

    }

    @Override
    public int getItemCount() {
        return results.size();
    }
    public void setResults(List<Local> results) {
        this.results = results;
        notifyDataSetChanged();
    }
    class LocalHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textViewData;
        private TextView textViewDescricao;
        private TextView textViewLatitude;
        private TextView textViewLongitude;
        private ImageView fotoLocalCard;



        public LocalHolder(@NonNull View itemView) {
            super(itemView);
            textViewData = itemView.findViewById(R.id.textViewDataLocal);
            textViewDescricao = itemView.findViewById(R.id.textViewDescricaoLocal);
            textViewLatitude = itemView.findViewById(R.id.textViewLatitudeLocal);
            textViewLongitude = itemView.findViewById(R.id.textViewLongitudeLocal);
            fotoLocalCard = itemView.findViewById(R.id.fotoLocalCard);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if(itemClickListener != null) {
                itemClickListener.onItemClick(getAdapterPosition(), results.get(getAdapterPosition()));
            }
        }
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
    public interface ItemClickListener {
        void onItemClick(int position, Local local);
    }
}
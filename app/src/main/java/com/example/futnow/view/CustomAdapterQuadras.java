package com.example.futnow.view;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.TextView;
import java.util.ArrayList;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import com.example.futnow.R;
import com.example.futnow.model.Quadra;

public class CustomAdapterQuadras extends RecyclerView.Adapter<CustomAdapterQuadras.ViewHolder> {

    private ArrayList<Quadra> localDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewTitle;
        private final TextView textViewDescricao;

        public ViewHolder(View view) {
            super(view);

            textViewTitle = view.findViewById(R.id.textViewTitleQuadra);
            textViewDescricao = view.findViewById(R.id.textViewDescricaoQuadra);

        }

    }

    public CustomAdapterQuadras( ArrayList<Quadra> dataSet) {
        localDataSet = dataSet;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.quadra_layout, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        Quadra quadra = localDataSet.get( position );

        viewHolder.textViewTitle.setText(quadra.getTitle());
        viewHolder.textViewDescricao.setText(quadra.getDescricao());

    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}


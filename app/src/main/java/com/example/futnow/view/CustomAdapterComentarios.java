package com.example.futnow.view;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.TextView;
import java.util.ArrayList;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import com.example.futnow.R;
import com.example.futnow.model.Comentario;

public class CustomAdapterComentarios extends RecyclerView.Adapter<CustomAdapterComentarios.ViewHolder> {

    private ArrayList<Comentario> localDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewTitle;
        private final TextView textViewDescricao;

        public ViewHolder(View view) {
            super(view);

            textViewTitle = view.findViewById(R.id.textViewTitleComentario);
            textViewDescricao = view.findViewById(R.id.textViewDescricaoComentario);

        }

    }

    public CustomAdapterComentarios( ArrayList<Comentario> dataSet) {
        localDataSet = dataSet;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.comentario_layout, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        Comentario comentario = localDataSet.get( position );

        viewHolder.textViewTitle.setText( comentario.toString() );
        viewHolder.textViewDescricao.setText( comentario.toString() );

    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
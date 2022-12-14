package com.example.futnow.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import com.example.futnow.R;
import com.example.futnow.model.Horario;

import java.util.List;

public class CustomAdapterHorarios extends RecyclerView.Adapter<CustomAdapterHorarios.ViewHolder> {

    private List<String> localDataSet;

    public CustomAdapterHorarios(List<String> dataSet) {
        this.localDataSet = dataSet;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private Button buttonText;

        public ViewHolder(View view) {
            super(view);

            buttonText = view.findViewById(R.id.ButtonHorarios);

        }

    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.horarios_layout, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomAdapterHorarios.ViewHolder viewHolder, final int position) {

        String hora = localDataSet.get( position );

        viewHolder.buttonText.setText(hora);

    }


    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}

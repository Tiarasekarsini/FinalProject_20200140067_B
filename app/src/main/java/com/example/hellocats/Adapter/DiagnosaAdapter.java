package com.example.hellocats.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hellocats.R;
import com.example.hellocats.model.Diagnosa;

import java.util.ArrayList;

public class DiagnosaAdapter extends RecyclerView.Adapter<DiagnosaAdapter.DiagnosaViewHolder> {

    private ArrayList<Diagnosa>listdata;

    public DiagnosaAdapter(ArrayList<Diagnosa> listdata){
        this.listdata = listdata;
    }

    @NonNull
    @Override
    public DiagnosaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_data_diagnosa,parent,false);
        return new DiagnosaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiagnosaViewHolder holder, int position) {
        String idkd, nkd, nama_penyakit;

        idkd = listdata.get(position).getIDKD();
        nkd = listdata.get(position).getNamaKD();
        nama_penyakit = listdata.get(position).getNama_penyakit();

        holder.IDKiagnosa_tv.setTextColor(Color.BLACK);
        holder.IDKiagnosa_tv.setTextSize(20);
        holder.IDKiagnosa_tv.setText(idkd);
        holder.Nkdiagnosa_tv.setTextColor(Color.BLACK);
        holder.Nkdiagnosa_tv.setTextSize(20);
        holder.Nkdiagnosa_tv.setText(nkd);
        holder.Namapenyakit_tv.setTextColor(Color.BLACK);
        holder.Namapenyakit_tv.setTextSize(20);
        holder.Namapenyakit_tv.setText(nama_penyakit);
    }

    @Override
    public int getItemCount() {
        return (listdata != null)?listdata.size(): 0;
    }

    public class DiagnosaViewHolder extends RecyclerView.ViewHolder{
        private TextView IDKiagnosa_tv,Nkdiagnosa_tv, Namapenyakit_tv;

        public DiagnosaViewHolder(View view){
            super(view);

            IDKiagnosa_tv = (TextView) view.findViewById(R.id.tvdiagnosa_IDK);
            Nkdiagnosa_tv = (TextView) view.findViewById(R.id.tvdiagnosa_NK);
            Namapenyakit_tv = (TextView) view.findViewById(R.id.tvnama_penyakit);
        }
    }
}

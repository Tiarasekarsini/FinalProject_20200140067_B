package com.example.hellocats.Adapter;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.hellocats.App.AppController;
import com.example.hellocats.MyCats_Utama;
import com.example.hellocats.R;
import com.example.hellocats.UpdateKucing;
import com.example.hellocats.model.DataKucing;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class KucingAdapter extends RecyclerView.Adapter<KucingAdapter.KucingViewHolder> {

    private ArrayList<DataKucing> listdata;

    public KucingAdapter(ArrayList<DataKucing> listdata){

        this.listdata = listdata;
    }

    @Override
    public KucingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_data_kucing, parent, false);
        return new KucingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KucingViewHolder holder, int position) {
        String id,nk,jk,tgl;

        id = listdata.get(position).getIdK();
        nk = listdata.get(position).getNamaK();
        jk = listdata.get(position).getJenisK();
        tgl = listdata.get(position).getTanggalL();

        holder.idktxt.setTextColor(Color.BLACK);
        holder.idktxt.setTextSize(20);
        holder.idktxt.setText(id);
        holder.namaktxt.setTextColor(Color.BLACK);
        holder.namaktxt.setTextSize(20);
        holder.namaktxt.setText(nk);
        holder.jenistxt.setTextColor(Color.BLACK);
        holder.jenistxt.setTextSize(15);
        holder.jenistxt.setText(jk);
        holder.tgltxt.setTextColor(Color.BLACK);
        holder.tgltxt.setTextSize(15);
        holder.tgltxt.setText(tgl);

        holder.btnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu pm = new PopupMenu(view.getContext(),view);
                pm.inflate(R.menu.menu);

                pm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId())
                        {
                            case R.id.Lihat_Data:

                                Bundle bundle = new Bundle();
                                bundle.putString("key1", id);
                                bundle.putString("key2",nk);
                                bundle.putString("key3",tgl);
                                bundle.putString("key4",jk);
                                
                                Intent intent = new Intent(view.getContext(), UpdateKucing.class);
                                intent.putExtras(bundle);
                                view.getContext().startActivity(intent);
                                break;
                        }
                        return true;
                    }
                });
                pm.show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return (listdata != null)?listdata.size(): 0;
    }

    public class KucingViewHolder extends RecyclerView.ViewHolder{
        private TextView idktxt,namaktxt,jenistxt,tgltxt;
        private ImageButton btnImg;
        public KucingViewHolder(View view) {
            super(view);

            idktxt = (TextView) view.findViewById(R.id.txtID);
            namaktxt = (TextView) view.findViewById(R.id.txtNamaK);
            jenistxt = (TextView) view.findViewById(R.id.txtJenisK);
            tgltxt = (TextView) view.findViewById(R.id.txtTgl);
            btnImg = (ImageButton) view.findViewById(R.id.imgbtn);
        }
    }
}

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
import com.example.hellocats.Jadwal_Utama;
import com.example.hellocats.MyCats_Utama;
import com.example.hellocats.R;
import com.example.hellocats.UpdateJadwal;
import com.example.hellocats.model.JadwalVaksin;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JadwalAdapter extends RecyclerView.Adapter<JadwalAdapter.JadwalViewHolder> {

    private ArrayList<JadwalVaksin> listdata;

    public JadwalAdapter(ArrayList<JadwalVaksin> listdata) {
        this.listdata = listdata;
    }

    @NonNull
    @Override
    public JadwalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.row_data_jadwal, parent, false);
        return new JadwalViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull JadwalViewHolder holder, int position) {
        String idj,idkj, nkj, tv;

        idj = listdata.get(position).getIDJ();
        idkj = listdata.get(position).getIdKJ();
        nkj = listdata.get(position).getNamaKJ();
        tv = listdata.get(position).getTanggal_vaksin();


        holder.idjtxt.setTextColor(Color.BLACK);
        holder.idjtxt.setTextSize(20);
        holder.idjtxt.setText(idj);
        holder.namakjtxt.setTextColor(Color.BLACK);
        holder.namakjtxt.setTextSize(20);
        holder.namakjtxt.setText(nkj);
        holder.Tanggalvtxt.setTextColor(Color.BLACK);
        holder.Tanggalvtxt.setTextSize(20);
        holder.Tanggalvtxt.setText(tv);

        holder.btnImgJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu pop = new PopupMenu(view.getContext(), view);
                pop.inflate(R.menu.menu);

                pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.update:
                                Bundle bundel = new Bundle();
                                bundel.putString("kunci1", idj);
                                bundel.putString("kunci2", idkj);
                                bundel.putString("kunci3",nkj);
                                bundel.putString("kunci4", tv);

                                Intent intent = new Intent(view.getContext(), UpdateJadwal.class);
                                intent.putExtras(bundel);
                                view.getContext().startActivity(intent);
                                break;

                            case R.id.delete:
                                AlertDialog.Builder alertdb = new AlertDialog.Builder(view.getContext());
                                alertdb.setTitle("Yakin Jadwal Vaksinasi " + nkj + " akan dihapus?");

                                alertdb.setMessage("Tekan Ya untuk menghapus");
                                alertdb.setCancelable(false);
                                alertdb.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        HapusData(idj);
                                        Toast.makeText(view.getContext(), "Jadwal Vaksinasi " + nkj + " telah dihapus", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(view.getContext(),Jadwal_Utama.class);
                                        view.getContext().startActivity(intent);
                                    }
                                });
                                alertdb.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                                AlertDialog adlg = alertdb.create();
                                adlg.show();
                                break;
                        }
                        return true;
                    }
                });
                pop.show();
            }
        });
    }
    private void HapusData(final String idj) {
        String url_update = "https://20200140067.20200140067.praktikumtiumy.com/hapusdata_jadwal.php";
        final String TAG = Jadwal_Utama.class.getSimpleName();
        final String TAG_SUCCES = "success";
        final int[] sukses = new int[1];

        StringRequest stringReq = new StringRequest(Request.Method.POST, url_update, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Respon: " + response.toString());

                try {
                    JSONObject jobj = new JSONObject(response);
                    sukses[0] = jobj.getInt(TAG_SUCCES);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error : " + error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("id_jadwal", idj);

                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringReq);
    }

    @Override
    public int getItemCount() {
        return (listdata != null) ? listdata.size() : 0;
    }

    public class JadwalViewHolder extends RecyclerView.ViewHolder {
        private TextView idjtxt, namakjtxt, Tanggalvtxt;
        private ImageButton btnImgJ;

        public JadwalViewHolder(View v) {
            super(v);

            idjtxt = (TextView) v.findViewById(R.id.txtIDJ);
            namakjtxt = (TextView) v.findViewById(R.id.txtNamaKJ);
            Tanggalvtxt = (TextView) v.findViewById(R.id.txtTglV);
            btnImgJ = (ImageButton) v.findViewById(R.id.imgbtnJ);
        }
    }
}
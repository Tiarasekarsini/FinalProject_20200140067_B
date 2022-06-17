package com.example.hellocats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.hellocats.Adapter.JadwalAdapter;
import com.example.hellocats.Adapter.KucingAdapter;
import com.example.hellocats.model.DataKucing;
import com.example.hellocats.model.JadwalVaksin;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Jadwal_Utama extends AppCompatActivity {
    private RecyclerView recyclerView;
    private JadwalAdapter adapter;
    private ArrayList<JadwalVaksin> JadwalArrayList = new ArrayList<>();
    private FloatingActionButton FAB;

    private static final String TAG = Jadwal_Utama.class.getSimpleName();
    private static String url_select = "https://20200140067.20200140067.praktikumtiumy.com/bacadata_jadwal.php";
    public static final String TAG_IDJ = "id_jadwal";
    public static final String TAG_IDKJ = "id_kucing";
    public static final String TAG_NAMAKJ = "nama_kucing";
    public static final String TAG_TanggalV = "tanggal_vaksin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_utama);

        recyclerView = findViewById(R.id.rvJadwal);
        FAB = findViewById(R.id.fabJ);
        bacadata();
        adapter = new JadwalAdapter(JadwalArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Jadwal_Utama.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Jadwal_Utama.this, TambahJadwal.class);
                startActivity(i);
            }
        });
    }
    public void bacadata(){
        JadwalArrayList.clear();

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jArr = new JsonArrayRequest(url_select, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString());

                //parsing json
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        JadwalVaksin item = new JadwalVaksin();

                        item.setIDJ(obj.getString(TAG_IDJ));
                        item.setIdKJ(obj.getString(TAG_IDKJ));
                        item.setNamaKJ(obj.getString(TAG_NAMAKJ));
                        item.setTanggal_vaksin(obj.getString(TAG_TanggalV));

                        //menambah item ke array
                        JadwalArrayList.add(item);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                error.printStackTrace();
                Toast.makeText(Jadwal_Utama.this, "gagal", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jArr);
    }
}
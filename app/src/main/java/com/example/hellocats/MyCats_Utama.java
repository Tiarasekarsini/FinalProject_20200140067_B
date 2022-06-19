package com.example.hellocats;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.hellocats.Adapter.KucingAdapter;
import com.example.hellocats.model.DataKucing;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class MyCats_Utama extends AppCompatActivity {

    private RecyclerView recyclerView;
    private KucingAdapter adapter;
    private ArrayList<DataKucing> KucingArrayList = new ArrayList<>();
    private FloatingActionButton fab;

    private static final String TAG = MyCats_Utama.class.getSimpleName();
    private static String url_select = "https://20200140067.20200140067.praktikumtiumy.com/bacadata_kucing.php";
    public static final String TAG_IDK = "id_kucing";
    public static final String TAG_NAMAK = "nama_kucing";
    public static final String TAG_JenisK = "jenis_kelamin";
    public static final String TAG_Tanggal = "tanggal_lahir";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cats_utama);

        recyclerView = findViewById(R.id.rvdatakucing);
        fab = findViewById(R.id.floatingbtn);
        bacadata();
        adapter = new KucingAdapter(KucingArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MyCats_Utama.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyCats_Utama.this, TambahKucing.class);
                startActivity(intent);
            }
        });
    }
    //method untuk membaca data dari database
    public void bacadata() {
        //mereset arraylist
        KucingArrayList.clear();

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jArr = new JsonArrayRequest(url_select, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString());

                //parsing json
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        DataKucing item = new DataKucing();

                        item.setIdK(obj.getString(TAG_IDK));
                        item.setNamaK(obj.getString(TAG_NAMAK));
                        item.setJenisK(obj.getString(TAG_JenisK));
                        item.setTanggalL(obj.getString(TAG_Tanggal));

                        //menambah item ke array
                        KucingArrayList.add(item);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                //mengupdate adapter ketika MyCats_utama dijalankan
               adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                error.printStackTrace();
                Toast.makeText(MyCats_Utama.this, "gagal", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jArr);
    }
}
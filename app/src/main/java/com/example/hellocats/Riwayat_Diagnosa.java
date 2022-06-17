package com.example.hellocats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.hellocats.Adapter.DiagnosaAdapter;
import com.example.hellocats.Adapter.KucingAdapter;
import com.example.hellocats.model.DataKucing;
import com.example.hellocats.model.Diagnosa;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Riwayat_Diagnosa extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DiagnosaAdapter adapter;
    private ArrayList<Diagnosa> DiagnosaArrayList = new ArrayList<>();

    private static final String TAG = Riwayat_Diagnosa.class.getSimpleName();
    private static String url_select = "https://20200140067.20200140067.praktikumtiumy.com/bacadata_diagnosa.php";
    public static final String TAG_IDJ = "id_diagnosa";
    public static final String TAG_IDKD = "id_kucing";
    public static final String TAG_NamaKD = "nama_kucing";
    public static final String TAG_NamaP = "nama_penyakit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_diagnosa);

        recyclerView = findViewById(R.id.rvDiagnosa);
        bacadata();
        adapter = new DiagnosaAdapter(DiagnosaArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Riwayat_Diagnosa.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
    public void bacadata(){
        DiagnosaArrayList.clear();

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jArr = new JsonArrayRequest(url_select, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString());

                //parsing json
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        Diagnosa item = new Diagnosa();

                        item.setId_diagnosa(obj.getString(TAG_IDJ));
                        item.setIDKD(obj.getString(TAG_IDKD));
                        item.setNamaKD(obj.getString(TAG_NamaKD));
                        item.setNama_penyakit(obj.getString(TAG_NamaP));

                        //menambah item ke array
                        DiagnosaArrayList.add(item);
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
                Toast.makeText(Riwayat_Diagnosa.this, "gagal", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jArr);
    }
}
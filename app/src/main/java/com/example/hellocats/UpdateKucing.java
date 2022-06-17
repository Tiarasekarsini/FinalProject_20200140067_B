package com.example.hellocats;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UpdateKucing extends AppCompatActivity {

    TextView TVID, TVJK;
    EditText Edtnama, Edttanggal;
    Button savebtn, cancelbtn;
    String id, nk, tgl, jk, namaEdt, tanggalEdt;
    int sukses;

    private String url_update = "https://20200140067.20200140067.praktikumtiumy.com/updatedata_kucing.php";
    private static final String TAG = UpdateKucing.class.getSimpleName();
    private static final String TAG_SUCCES="success";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_kucing);

        TVID = findViewById(R.id.tvID);
        TVJK = findViewById(R.id.tvJenisK);
        Edtnama = findViewById(R.id.editNama);
        Edttanggal = findViewById(R.id.editTanggalL);
        savebtn = findViewById(R.id.btnSave);
        cancelbtn = findViewById(R.id.btnCancel);

        Bundle bl = getIntent().getExtras();
        id = bl.getString("key1");
        nk = bl.getString("key2");
        tgl = bl.getString("key3");
        jk = bl.getString("key4");


        TVID.setText("Data Kucing Ke " + id);
        Edtnama.setText(nk);
        TVJK.setText(jk);
        Edttanggal.setText(tgl);
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    UpdateData();
            }
        });

        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CallHomeActivity();
                Toast.makeText(UpdateKucing.this,"Perubahan dibatalkan",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void UpdateData()
    {
        namaEdt = Edtnama.getText().toString();
        tanggalEdt = Edttanggal.getText().toString();

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringReq = new StringRequest(Request.Method.POST, url_update, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Respon: " + toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    sukses = jObj.getInt(TAG_SUCCES);
                    if (sukses == 1) {
                        Toast.makeText(UpdateKucing.this, "Sukses mengedit data", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(UpdateKucing.this, "gagal", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error : " + error.getMessage());
                Toast.makeText(UpdateKucing.this, "Gagal Edit data", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("id_kucing",id);
                params.put("nama_kucing", namaEdt);
                params.put("tanggal_lahir", tanggalEdt);

                return params;
            }
        };
        requestQueue.add(stringReq);
        CallHomeActivity();
    }
    public void CallHomeActivity(){
        Intent inten = new Intent(getApplicationContext(), MyCats_Utama.class);
        startActivity(inten);
        finish();
    }
}

package com.example.hellocats;

import androidx.appcompat.app.AppCompatActivity;

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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HasilDiagnosa extends AppCompatActivity {

    TextView tvdiagnosa_hasil;
    EditText IDKDEdit, NamaKDEdit;
    Button diagnosa_batalkan, diagnosa_simpan;
    String Idkd, Nkd;

    int success;

    private static String url_insert = "http://10.0.2.2/umyTI/tambahdiagnosa.php";
    private static final String TAG = HasilDiagnosa.class.getSimpleName();
    private static final String TAG_SUCCES = "success";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_diagnosa);

        IDKDEdit = findViewById(R.id.edit_IDKD);
        NamaKDEdit =findViewById(R.id.edit_NamaKD);
        tvdiagnosa_hasil = findViewById(R.id.tv_hasildiagnosa);
        diagnosa_batalkan = findViewById(R.id.batal_diagnosa);
        diagnosa_simpan = findViewById(R.id.simpan_diagnosa);

        String NamaPenyakit = getIntent().getExtras().getString("nama_penyakit");
        tvdiagnosa_hasil.setText(String.valueOf(NamaPenyakit));

        diagnosa_batalkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HasilDiagnosa.this, Home.class);
                startActivity(i);
                finish();
            }
        });
        diagnosa_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpanDiagnosa();
            }
        });
    }
    public void SimpanDiagnosa(){
        if (IDKDEdit.getText().equals("") || NamaKDEdit.getText().equals("")){
            Toast.makeText(HasilDiagnosa.this, "Silahkan Isi Id Kucing dan Nama Kucing terlebih dahulu", Toast.LENGTH_SHORT).show();
        }
        else{
            Idkd = IDKDEdit.getText().toString();
            Nkd = NamaKDEdit.getText().toString();

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

            StringRequest strReq = new StringRequest(Request.Method.POST, url_insert, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d(TAG, "Response : " + response.toString());
                    try {
                        JSONObject jObj = new JSONObject(response);
                        success = jObj.getInt(TAG_SUCCES);
                        if (success == 1) {
                            Toast.makeText(HasilDiagnosa.this, "Sukses simpan diagnosa", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(HasilDiagnosa.this, "gagal menyimpan diagnosa", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, "Error :" + error.getMessage());
                    Toast.makeText(HasilDiagnosa.this, "Gagal Menyimpan Diagnosa", Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Idkd = IDKDEdit.getText().toString();
                    Nkd = NamaKDEdit.getText().toString();
                    String NamaPenyakit= getIntent().getExtras().getString("nama_penyakit");

                    Map<String, String> params = new HashMap<>();
                    params.put("id_kucing", Idkd);
                    params.put("nama_kucing", Nkd);
                    params.put("nama_penyakit", NamaPenyakit);

                    callHome();
                    return params;
                }
            };
            requestQueue.add(strReq);
        }
    }
    public void callHome(){
        Intent intent = new Intent(HasilDiagnosa.this, Riwayat_Diagnosa.class);
        startActivity(intent);
        finish();
    }
}
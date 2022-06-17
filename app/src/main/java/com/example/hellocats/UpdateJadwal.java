package com.example.hellocats;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class UpdateJadwal extends AppCompatActivity {
    TextView TVIDJ, TVIDKJ, TVNamaKJ;
    EditText EdtTanggalV;
    Button savebtnj,cancelbtnj;
    String idj,idkj,nmkj,tgv, TanggalVEdt;
    int sukses;

    private String url_update = "https://20200140067.20200140067.praktikumtiumy.com/updatedata_jadwal.php";
    private static final String TAG = UpdateJadwal.class.getSimpleName();
    private static final String TAG_SUCCES="success";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_jadwal);

        TVIDJ = findViewById(R.id.tvIDJ);
        TVIDKJ = findViewById(R.id.tvIDKJ);
        TVNamaKJ = findViewById(R.id.tvNamaKJ);
        EdtTanggalV = findViewById(R.id.edtTglV);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        savebtnj = findViewById(R.id.btnSaveJ);
        cancelbtnj = findViewById(R.id.btnCancelJ);

        Bundle bd = getIntent().getExtras();
        idj = bd.getString("kunci1");
        idkj = bd.getString("kunci2");
        nmkj = bd.getString("kunci3");
        tgv = bd.getString("kunci4");

        TVIDJ.setText("Jadwal Vaksinasi ke "+ idj);
        TVIDKJ.setText(idkj);
        TVNamaKJ.setText(nmkj);
        EdtTanggalV.setText(tgv);

        EdtTanggalV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(UpdateJadwal.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        month = month+1;
                        String date = year+"-"+month+"-"+dayOfMonth;
                        EdtTanggalV.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
        savebtnj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateData();
            }
        });
        cancelbtnj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Callhome();
                Toast.makeText(UpdateJadwal.this, "Perubahan Jadwal dibatalkan", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void UpdateData()
    {
        TanggalVEdt = EdtTanggalV.getText().toString();

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringReq = new StringRequest(Request.Method.POST, url_update, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Respon: " + toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    sukses = jObj.getInt(TAG_SUCCES);
                    if (sukses == 1) {
                        Toast.makeText(UpdateJadwal.this, "Sukses mengedit data", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(UpdateJadwal.this, "gagal", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error : " + error.getMessage());
                Toast.makeText(UpdateJadwal.this, "Gagal Edit data", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("id_jadwal",idj);
                params.put("tanggal_vaksin", TanggalVEdt);

                return params;
            }
        };
        requestQueue.add(stringReq);
        Callhome();
    }
    public void Callhome(){
        Intent inten = new Intent(getApplicationContext(), Jadwal_Utama.class);
        startActivity(inten);
        finish();
    }
}
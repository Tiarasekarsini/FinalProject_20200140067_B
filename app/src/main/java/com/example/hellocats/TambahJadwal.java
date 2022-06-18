package com.example.hellocats;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.strictmode.CleartextNetworkViolation;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class TambahJadwal extends AppCompatActivity {
    private EditText tambahIDKJ, tambahNamaKJ, tambahTglV;
    private Button simpanBtn, batalkanBtn;
    String idkj,nkj,tglv;

    int success;

    private static String url_insert = "https://20200140067.20200140067.praktikumtiumy.com/tambahjadwal.php";
    private static final String TAG = TambahJadwal.class.getSimpleName();
    private static final String TAG_SUCCES = "success";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_jadwal);

        tambahIDKJ = (EditText) findViewById(R.id.editIDKJ);
        tambahNamaKJ = (EditText) findViewById(R.id.edNamaKJ);
        tambahTglV = (EditText) findViewById(R.id.edTanggalV);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        simpanBtn = (Button) findViewById(R.id.btnsimpanJ);
        batalkanBtn = (Button) findViewById(R.id.btnbatalkanJ);

        tambahTglV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(TambahJadwal.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                       month = month+1;
                       String date = year+"-"+month+"-"+dayOfMonth;
                       tambahTglV.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
        simpanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Savedata();
            }
        });
        batalkanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callHome();
                Toast.makeText(TambahJadwal.this,"Data tidak ditambahkan",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void Savedata() {
        if (tambahIDKJ.getText().toString().equals("") || tambahNamaKJ.getText().toString().equals("") || tambahTglV.getText().toString().equals("")) {
            Toast.makeText(TambahJadwal.this, "Semua harus diisi data", Toast.LENGTH_SHORT).show();
        } else {
            idkj = tambahIDKJ.getText().toString();
            nkj = tambahNamaKJ.getText().toString();
            tglv = tambahTglV.getText().toString();

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

            StringRequest strReq = new StringRequest(Request.Method.POST, url_insert, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d(TAG, "Response : " + response.toString());
                    try {
                        JSONObject jObj = new JSONObject(response);
                        success = jObj.getInt(TAG_SUCCES);
                        if (success == 1) {
                            Toast.makeText(TambahJadwal.this, "Sukses simpan data", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(TambahJadwal.this, "gagal", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, "Error :" + error.getMessage());
                    Toast.makeText(TambahJadwal.this, "Gagal simpan data", Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    idkj = tambahIDKJ.getText().toString();
                    nkj = tambahNamaKJ.getText().toString();
                    tglv = tambahTglV.getText().toString();

                    Map<String, String> params = new HashMap<>();
                    params.put("id_kucing", idkj);
                    params.put("nama_kucing", nkj);
                    params.put("tanggal_vaksin", tglv);

                    callHome();
                    return params;
                }
            };
            requestQueue.add(strReq);
        }
    }
    public void callHome(){
        Intent intent = new Intent(TambahJadwal.this, Jadwal_Utama.class);
        startActivity(intent);
        finish();
    }
}
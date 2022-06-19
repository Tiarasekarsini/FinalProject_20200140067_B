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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class TambahKucing extends AppCompatActivity {

    private EditText edNK, edTK, edJK;
    private Button simpanbtn, batalkanbtn;
    String nk, tk, jk;

    int success;

    private static String url_insert = "https://20200140067.20200140067.praktikumtiumy.com/tambahkucing.php";
    private static final String TAG = TambahKucing.class.getSimpleName();
    private static final String TAG_SUCCES = "success";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_kucing);

        edNK = (EditText) findViewById(R.id.editNamaK);
        edTK = (EditText) findViewById(R.id.editTanggalL);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        edJK = (EditText) findViewById(R.id.editJK);
        simpanbtn = (Button) findViewById(R.id.btnsimpan);
        batalkanbtn = (Button) findViewById(R.id.btnbatalkan);

        edTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(TambahKucing.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        month = month+1;
                        String date = year+"-"+month+"-"+dayOfMonth;
                        edTK.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
        simpanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Savedata();
            }
        });
        batalkanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callHome();
                Toast.makeText(TambahKucing.this,"Data tidak ditambahkan",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void Savedata() {
        if (edNK.getText().toString().equals("") || edTK.getText().toString().equals("") || edJK.getText().toString().equals("")) {
            Toast.makeText(TambahKucing.this, "Semua harus diisi data", Toast.LENGTH_SHORT).show();
        } else {
            nk = edNK.getText().toString();
            tk = edTK.getText().toString();
            jk = edJK.getText().toString();

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

            StringRequest strReq = new StringRequest(Request.Method.POST, url_insert, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d(TAG, "Response : " + response.toString());
                    try {
                        JSONObject jObj = new JSONObject(response);
                        success = jObj.getInt(TAG_SUCCES);
                        if (success == 1) {
                            Toast.makeText(TambahKucing.this, "Sukses simpan data", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(TambahKucing.this, "gagal", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, "Error :" + error.getMessage());
                    Toast.makeText(TambahKucing.this, "Gagal simpan data", Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    nk = edNK.getText().toString();
                    jk = edJK.getText().toString();
                    tk = edTK.getText().toString();

                    Map<String, String> params = new HashMap<>();
                    params.put("nama_kucing", nk);
                    params.put("jenis_kelamin", jk);
                    params.put("tanggal_lahir", tk);

                    callHome();
                    return params;
                }
            };
            requestQueue.add(strReq);
        }
    }
    public void callHome(){
        Intent intent = new Intent(TambahKucing.this, MyCats_Utama.class);
        startActivity(intent);
        finish();
    }
}
package com.example.hellocats;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
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
import com.example.hellocats.App.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class UpdateJadwal extends AppCompatActivity {
    TextView TVIDJ, TVIDKJ, TVNamaKJ;
    EditText EdtTanggalV;
    Button savebtnj,cancelbtnj,hapusbtn;
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

        hapusbtn = findViewById(R.id.btnHapus);
        savebtnj = findViewById(R.id.btnUpdate);
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
        hapusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HapusData(idj);
                AlertDialog.Builder alertdb = new AlertDialog.Builder(view.getContext());
                alertdb.setTitle("Yakin Jadwal Vaksinasi " + nmkj + " akan dihapus?");

                alertdb.setMessage("Tekan Ya untuk menghapus");
                alertdb.setCancelable(false);
                alertdb.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        HapusData(idj);
                        Toast.makeText(view.getContext(), "Jadwal Vaksinasi " + nmkj + " telah dihapus", Toast.LENGTH_LONG).show();
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
    public void HapusData(final String idj){
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
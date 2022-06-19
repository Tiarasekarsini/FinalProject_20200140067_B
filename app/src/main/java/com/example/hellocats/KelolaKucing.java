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

public class KelolaKucing extends AppCompatActivity {

    TextView TVID, TVJK;
    EditText Edtnama, Edttanggal;
    Button updatebtn, cancelbtn,hapusbtn;
    String id, nk, tgl, jk, namaEdt, tanggalEdt;
    int sukses;

    private String url_update = "https://20200140067.20200140067.praktikumtiumy.com/updatedata_kucing.php";
    private static final String TAG = KelolaKucing.class.getSimpleName();
    private static final String TAG_SUCCES="success";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelola_kucing);

        TVID = findViewById(R.id.tvID);
        TVJK = findViewById(R.id.tvJenisK);
        Edtnama = findViewById(R.id.editNama);
        Edttanggal = findViewById(R.id.editTanggalL);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        hapusbtn = findViewById(R.id.btnHapusK);
        updatebtn = findViewById(R.id.btnUpdate);
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

        Edttanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(KelolaKucing.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        month = month+1;
                        String date = year+"-"+month+"-"+dayOfMonth;
                        Edttanggal.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();

            }
        });
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    UpdateData();
            }
        });
        hapusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HapusData(id);
                AlertDialog.Builder alertdb = new AlertDialog.Builder(view.getContext());
                alertdb.setTitle("Yakin " +nk+" akan dihapus?");

                alertdb.setMessage("Tekan Ya untuk menghapus");
                alertdb.setCancelable(false);
                alertdb.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        HapusData(id);
                        Toast.makeText(view.getContext(), "Data " +id+" telah dihapus", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(view.getContext(), MyCats_Utama.class);
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
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CallHomeActivity();
                Toast.makeText(KelolaKucing.this,"Perubahan dibatalkan",Toast.LENGTH_SHORT).show();
            }
        });
    }
    //method yang berfungsi untuk menghapus data yang ada di database
    public void HapusData(final String id){
        String url_update = "https://20200140067.20200140067.praktikumtiumy.com/hapusdata_kucing.php";
        final String TAG = MyCats_Utama.class.getSimpleName();
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
                Log.e(TAG, "Error : "+error.getMessage());
            }
        })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("id_kucing", id);

                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringReq);

    }
    //method untuk mengupdate/mengedit data kedalam database
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
                        Toast.makeText(KelolaKucing.this, "Sukses mengedit data", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(KelolaKucing.this, "gagal", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error : " + error.getMessage());
                Toast.makeText(KelolaKucing.this, "Gagal Edit data", Toast.LENGTH_SHORT).show();
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

    //method untuk berpindah dari activity KelolaKucing ke MyCats_Utama
    public void CallHomeActivity(){
        Intent inten = new Intent(getApplicationContext(), MyCats_Utama.class);
        startActivity(inten);
        finish();
    }
}

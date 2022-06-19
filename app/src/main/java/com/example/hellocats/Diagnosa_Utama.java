package com.example.hellocats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class Diagnosa_Utama extends AppCompatActivity {

    CheckBox Bersincb, Batukcb, Demamcb, PMterbukacb, Depresicb, Dehidrasicb, Infeksikemihcb, PbuangKKcb,Minumberlebihcb, Benjolancb,
            BauMcb, PenurunanBBcb, Hseleramakancb, Diarecb,Muntahcb, Lidahcokelatcb, Sembelitcb, Bulurontokcb, infeksimatacb, kelesuancb,
            Diareberdarahcb, MEkorKakicb, Kulitelastiscb;
    Button diagnosabtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosa_utama);

        Bersincb = findViewById(R.id.cb_Bersin);
        Demamcb = findViewById(R.id.cb_Demam);
        Batukcb = findViewById(R.id.cb_Batuk);
        PMterbukacb = findViewById(R.id.cb_PMterbuka);
        Depresicb = findViewById(R.id.cb_Depresi);
        Dehidrasicb = findViewById(R.id.cb_Dehidrasi);
        Infeksikemihcb = findViewById(R.id.cb_Infeksikemih);
        PbuangKKcb = findViewById(R.id.cb_Pbuangak);
        Minumberlebihcb = findViewById(R.id.cb_Minumberlebih);
        Benjolancb = findViewById(R.id.cb_Benjolan);
        BauMcb = findViewById(R.id.cb_BauM);
        PenurunanBBcb = findViewById(R.id.cb_PenurunanBB);
        Hseleramakancb = findViewById(R.id.cb_Hseleramakan);
        Diarecb = findViewById(R.id.cb_Diare);
        Muntahcb = findViewById(R.id.cb_Muntah);
        Lidahcokelatcb = findViewById(R.id.cb_lidahcokelat);
        Sembelitcb = findViewById(R.id.cb_sembelit);
        Bulurontokcb = findViewById(R.id.cb_Bulurontok);
        infeksimatacb = findViewById(R.id.cb_infeksimata);
        kelesuancb = findViewById(R.id.cb_kelesuan);
        Diareberdarahcb = findViewById(R.id.cb_Diareberdarah);
        MEkorKakicb = findViewById(R.id.cb_MEkorKaki);
        Kulitelastiscb = findViewById(R.id.cb_kulitelastis);
        diagnosabtn = findViewById(R.id.btn_diagnosa);

        diagnosabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NamaPenyakit = "";
                String TidakAda ="Mohon Maaf kami tidak dapat mendiagnosa";

                //Untuk penyakit Infeksi Saluran Pernapasan Atas (ISPA)
                if(Bersincb.isChecked() && Batukcb.isChecked() && Demamcb.isChecked() && PMterbukacb.isChecked() && Depresicb.isChecked()){
                    NamaPenyakit += "Infeksi Saluran Pernapasan Atas (ISPA)";
                }
                //Untuk penyakit Diabetes
                if(Minumberlebihcb.isChecked() && Dehidrasicb.isChecked() && PbuangKKcb.isChecked() && Infeksikemihcb.isChecked() && kelesuancb.isChecked()){
                    NamaPenyakit += "Diabetes";
                }
                //Untuk penyakit Kanker
                if(Benjolancb.isChecked() && BauMcb.isChecked() && PenurunanBBcb.isChecked() && Hseleramakancb.isChecked() && Diarecb.isChecked() && Muntahcb.isChecked()){
                    NamaPenyakit += "Kanker";
                }
                //Untuk penyakit Ginjal Kronis (PGK)
                if(BauMcb.isChecked() && Lidahcokelatcb.isChecked() && Sembelitcb.isChecked() && Muntahcb.isChecked() && PbuangKKcb.isChecked() && Minumberlebihcb.isChecked()){
                    NamaPenyakit += "Penyakit Gagal Ginjal Kronis";
                }
                //Untuk penyakit Feline Immunodeficiency Virus (FIV)
                if(Demamcb.isChecked() && PenurunanBBcb.isChecked() && Bulurontokcb.isChecked() && infeksimatacb.isChecked()){
                    NamaPenyakit += "Feline Immunodeficiency Virus (FIV)";
                }
                //Untuk penyakit Feline Panleukopenia (FPLV)
                if(kelesuancb.isChecked() && Diareberdarahcb.isChecked() && MEkorKakicb.isChecked() && Kulitelastiscb.isChecked()){
                    NamaPenyakit += "Feline Panleukopenia (FPLV)";
                }
                //Jika gejala yang dipilih tidak sesuai, maka akan muncul pemberitahuan tersebut
                else{
                    TidakAda +="";
                }
                Intent in = new Intent(Diagnosa_Utama.this, HasilDiagnosa.class);
                in.putExtra("nama_penyakit", NamaPenyakit);
                in.putExtra("nama_penyakit",TidakAda);
                startActivity(in);
                finish();
            }
        });
    }
    public void onClickChecked(View view){
        boolean checked = ((CheckBox) view).isChecked();
        String str = "";

        switch(view.getId()){
            case R.id.cb_Bersin:
                str = checked ? "Gejala Bersin Diseleksi" : "Gejala Bersin Tidak Diseleksi";
                break;
            case R.id.cb_Demam:
                str = checked?"Gejala Demam Diseleksi" : "Gejala Demam Tidak Diseleksi";
                break;
            case R.id.cb_Batuk:
                str = checked ? "Gejala Batuk Diseleksi" : "Gejala Batuk Tidak Diseleksi";
                break;
            case R.id.cb_PMterbuka:
                str = checked ? "Gejala Pernapasan Mulut Terbuka Diseleksi" : "Gejala Pernapasan Mulut Terbuka Tidak Diseleksi";
                break;
            case R.id.cb_Depresi:
                str = checked?"Gejala Depresi Diseleksi" : "Gejala Depresi Tidak Diseleksi";
                break;
            case R.id.cb_Dehidrasi:
                str = checked ? "Gejala Dehidrasi Diseleksi" : "Gejala Dehidrasi Tidak Diseleksi";
                break;
            case R.id.cb_Infeksikemih:
                str = checked ? "Gejala Infeksi Saluran Kemih Diseleksi" : "Gejala Infeksi Saluran Kemih Tidak Diseleksi";
                break;
            case R.id.cb_Pbuangak:
                str = checked ? "Gejala Peningkatan Buang Air Kecil Diseleksi" : "Gejala Peningkatan Buang Air Kecil Tidak Diseleksi";
                break;
            case R.id.cb_Minumberlebih:
                str = checked ? "Gejala Minum Berlebih Diseleksi" : "Gejala Minum Berlebih Tidak Diseleksi";
                break;
            case R.id.cb_Benjolan:
                str = checked?"Gejala Benjolan Diseleksi" : "Gejala Benjolan Tidak Diseleksi";
                break;
            case R.id.cb_BauM:
                str = checked ? "Gejala Bau Mulut Diseleksi" : "Gejala Bau Mulut Tidak Diseleksi";
                break;
            case R.id.cb_PenurunanBB:
                str = checked ? "Gejala Penurunan Berat Badan Diseleksi" : "Gejala Penurunan Berat Badan Tidak Diseleksi";
                break;
            case R.id.cb_Hseleramakan:
                str = checked?"Gejala Hilang Selera Makan Diseleksi" : "Gejala Hilang Selera Makan Tidak Diseleksi";
                break;
            case R.id.cb_Diare:
                str = checked ? "Gejala Diare Diseleksi" : "Gejala Diare Tidak Diseleksi";
                break;
            case R.id.cb_Muntah:
                str = checked ? "Gejala Muntah Diseleksi" : "Gejala Muntah Tidak Diseleksi";
                break;
            case R.id.cb_lidahcokelat:
                str = checked?"Gejala Lidah Berwarna Cokelat Diseleksi" : "Gejala Lidah Berwarna Cokelat Tidak Diseleksi";
                break;
            case R.id.cb_sembelit:
                str = checked ? "Gejala Sembelit Diseleksi" : "Gejala Sembelit Tidak Diseleksi";
                break;
            case R.id.cb_Bulurontok:
                str = checked ? "Gejala Bulu Rontok Diseleksi" : "Gejala Bulu Rontok Tidak Diseleksi";
                break;
            case R.id.cb_infeksimata:
                str = checked?"Gejala Infeksi Mata Diseleksi" : "Gejala Infeksi Mata Tidak Diseleksi";
                break;
            case R.id.cb_kelesuan:
                str = checked ? "Gejala Kelesuan Diseleksi" : "Gejala Kelesuan Tidak Diseleksi";
                break;
            case R.id.cb_Diareberdarah:
                str = checked ? "Gejala Diare Berdarah Diseleksi" : "Gejala Diare Berdarah Tidak Diseleksi";
                break;
            case R.id.cb_MEkorKaki:
                str = checked?"Gejala Menggigit Ekor atau Kaki Diseleksi" : "Gejala Menggigit Ekor atau Kaki Diseleksi Tidak Diseleksi";
                break;
            case R.id.cb_kulitelastis:
                str = checked ? "Gejala Kulit Elastis(Kendor) Diseleksi" : "Gejala Kulit Elastis(Kendor) Tidak Diseleksi";
                break;
        }
        Toast.makeText(getApplicationContext(),str, Toast.LENGTH_SHORT).show();
    }
}
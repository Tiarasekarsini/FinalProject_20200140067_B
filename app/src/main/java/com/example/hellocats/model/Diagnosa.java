package com.example.hellocats.model;

public class Diagnosa {
    String id_diagnosa;
    String id_kucing;
    String nama_kucing;
    String nama_penyakit;

    public Diagnosa(){
    }

    public Diagnosa(String id_diagnosa, String id_kucing, String nama_kucing, String nama_penyakit){
        this.id_diagnosa = id_diagnosa;
        this.id_kucing = id_kucing;
        this.nama_kucing = nama_kucing;
        this.nama_penyakit = nama_penyakit;
    }
    public String getId_diagnosa() {
        return id_diagnosa;
    }
    public void setId_diagnosa(String id_diagnosa) {
        this.id_diagnosa = id_diagnosa;
    }

    public String getIDKD() {
        return id_kucing;
    }

    public void setIDKD(String id_kucing) {
        this.id_kucing = id_kucing;
    }

    public String getNamaKD() {
        return nama_kucing;
    }

    public void setNamaKD(String nama_kucing) {
        this.nama_kucing = nama_kucing;
    }

    public String getNama_penyakit() {
        return nama_penyakit;
    }

    public void setNama_penyakit(String nama_penyakit) {
        this.nama_penyakit = nama_penyakit;
    }
}

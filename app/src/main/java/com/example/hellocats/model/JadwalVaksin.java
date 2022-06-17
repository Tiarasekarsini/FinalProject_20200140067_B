package com.example.hellocats.model;

public class JadwalVaksin {
    String id_jadwal;
    String id_kucing;
    String nama_kucing;
    String tanggal_vaksin;

    public JadwalVaksin() {
    }

    public JadwalVaksin(String id_jadwal,String id_kucing, String nama_kucing,String tanggal_vaksin) {
        this.id_jadwal = id_jadwal;
        this.id_kucing = id_kucing;
        this.nama_kucing = nama_kucing;
        this.tanggal_vaksin = tanggal_vaksin;
    }

    public String getIDJ() {
        return id_jadwal;
    }
    public void setIDJ(String id_jadwal) {
        this.id_jadwal = id_jadwal;
    }

    public String getIdKJ() {
        return id_kucing;
    }

    public void setIdKJ(String id_kucing) {
        this.id_kucing = id_kucing;
    }

    public String getNamaKJ() {
        return nama_kucing;
    }

    public void setNamaKJ(String nama_kucing) {
        this.nama_kucing = nama_kucing;
    }

    public String getTanggal_vaksin() {
        return tanggal_vaksin;
    }

    public void setTanggal_vaksin(String tanggal_vaksin) {
        this.tanggal_vaksin = tanggal_vaksin;
    }
}

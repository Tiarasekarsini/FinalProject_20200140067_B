package com.example.hellocats.model;

public class DataKucing {
        String id_kucing;
        String nama_kucing;
        String jenis_kelamin;
        String tanggal_lahir;

        public DataKucing() {
        }

        public DataKucing(String id_kucing, String nama_kucing, String jenis_kelamin, String tanggal_lahir) {
            this.id_kucing = id_kucing;
            this.nama_kucing = nama_kucing;
            this.jenis_kelamin = jenis_kelamin;
            this.tanggal_lahir = tanggal_lahir;
        }

        public String getIdK() {
            return id_kucing;
        }

        public void setIdK(String id_kucing) {
            this.id_kucing = id_kucing;
        }

        public String getNamaK() {
            return nama_kucing;
        }

        public void setNamaK(String nama_kucing) {
            this.nama_kucing = nama_kucing;
        }

        public String getJenisK() {
            return jenis_kelamin;
        }

        public void setJenisK(String jenis_kelamin) {
            this.jenis_kelamin = jenis_kelamin;
        }

        public String getTanggalL() {
        return tanggal_lahir;
    }

        public void setTanggalL(String tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }
    }


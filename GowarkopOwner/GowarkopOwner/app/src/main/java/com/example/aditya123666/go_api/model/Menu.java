package com.example.aditya123666.go_api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Aditya123666 on 29/05/2018.
 */


public class Menu {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("harga")
    @Expose
    private String harga;
    @SerializedName("kategori")
    @Expose
    private String kategori;
    @SerializedName("gambar")
    @Expose
    private String gambar;
    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;

    /**
     * No args constructor for use in serialization
     *
     */
    public Menu() {
    }

    /**
     *
     * @param id
     * @param gambar
     * @param kategori
     * @param nama
     * @param harga
     */
    public Menu(String id, String nama, String harga, String kategori, String gambar, String deskripsi) {
        super();
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
        this.gambar = gambar;
        this.deskripsi = deskripsi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String deskripsi) {
        this.gambar = gambar;
    }
    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

}
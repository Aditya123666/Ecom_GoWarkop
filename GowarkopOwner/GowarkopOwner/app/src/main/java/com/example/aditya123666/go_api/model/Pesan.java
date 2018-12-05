package com.example.aditya123666.go_api.model;

/**
 * Created by Aditya123666 on 24/05/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pesan {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("no_hp")
    @Expose
    private String noHp;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("latit")
    @Expose
    private String latit;
    @SerializedName("longit")
    @Expose
    private String longit;
    @SerializedName("nama_menu")
    @Expose
    private String namaMenu;
    @SerializedName("gambar")
    @Expose
    private String gambar;
    @SerializedName("jumlah")
    @Expose
    private String jumlah;
    @SerializedName("total_harga")
    @Expose
    private String totalHarga;
    @SerializedName("status")
    @Expose
    private String status;

    /**
     * No args constructor for use in serialization
     *
     */
    public Pesan() {
    }

    /**
     *
     * @param id
     * @param gambar
     * @param status
     * @param email
     * @param namaMenu
     * @param noHp
     * @param totalHarga
     * @param latit
     * @param nama
     * @param longit
     * @param jumlah
     */
    public Pesan(String id, String nama, String noHp, String email, String latit, String longit, String namaMenu, String gambar, String jumlah, String totalHarga, String status) {
        super();
        this.id = id;
        this.nama = nama;
        this.noHp = noHp;
        this.email = email;
        this.latit = latit;
        this.longit = longit;
        this.namaMenu = namaMenu;
        this.gambar = gambar;
        this.jumlah = jumlah;
        this.totalHarga = totalHarga;
        this.status = status;
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

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLatit() {
        return latit;
    }

    public void setLatit(String latit) {
        this.latit = latit;
    }

    public String getLongit() {
        return longit;
    }

    public void setLongit(String longit) {
        this.longit = longit;
    }

    public String getNamaMenu() {
        return namaMenu;
    }

    public void setNamaMenu(String namaMenu) {
        this.namaMenu = namaMenu;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(String totalHarga) {
        this.totalHarga = totalHarga;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
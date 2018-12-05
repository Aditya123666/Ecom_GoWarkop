package com.example.aditya123666.go_api.model;

/**
 * Created by Aditya123666 on 24/05/2018.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponsePesan {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<Pesan> data = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public ResponsePesan() {
    }

    /**
     *
     * @param status
     * @param data
     */
    public ResponsePesan(String status, List<Pesan> data) {
        super();
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Pesan> getData() {
        return data;
    }

    public void setData(List<Pesan> data) {
        this.data = data;
    }

}
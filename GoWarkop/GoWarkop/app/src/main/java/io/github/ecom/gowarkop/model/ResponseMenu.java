package io.github.ecom.gowarkop.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseMenu {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<Menu> data = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public ResponseMenu() {
    }

    /**
     *
     * @param status
     * @param data
     */
    public ResponseMenu(String status, List<Menu> data) {
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

    public List<Menu> getData() {
        return data;
    }

    public void setData(List<Menu> data) {
        this.data = data;
    }

}

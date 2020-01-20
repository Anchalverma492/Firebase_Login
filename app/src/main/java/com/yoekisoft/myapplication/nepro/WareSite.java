

package com.yoekisoft.myapplication.nepro;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WareSite {

    @SerializedName("WareHouseListData")
    @Expose
    private List<SiteListDatum> wareHouseListData = null;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("Message")
    @Expose
    private String message;

    public List<SiteListDatum> getWareHouseListData() {
        return wareHouseListData;
    }

    public void setWareHouseListData(List<SiteListDatum> wareHouseListData) {
        this.wareHouseListData = wareHouseListData;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}




package com.yoekisoft.myapplication.nepro;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StickerNo {


    private String StickerNo;
    private String UserId;
    private String SiteId;

    public StickerNo(String stickerNo, String userId) {
        StickerNo = stickerNo;
        UserId = userId;
    }
    public StickerNo(String SiteId) {
        this.SiteId = SiteId;

    }

}

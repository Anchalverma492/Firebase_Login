package com.yoekisoft.myapplication.nepro;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelRetrofit {

        @SerializedName("SiteListData")
        @Expose
        private List<SiteListDatum> siteListData = null;
        @SerializedName("ReasonList")
        @Expose
        private List<ReasonList> reasonList = null;
        @SerializedName("StickerID")
        @Expose
        private String stickerID;
        @SerializedName("StandardQty")
        @Expose
        private Integer standardQty;
        @SerializedName("ItemId")
        @Expose
        private String itemId;
        @SerializedName("Description")
        @Expose
        private String description;
        @SerializedName("ItemStdQty")
        @Expose
        private Integer itemStdQty;
        @SerializedName("WareHouseName")
        @Expose
        private String wareHouseName;
        @SerializedName("Location")
        @Expose
        private String location;
        @SerializedName("IsTransfered")
        @Expose
        private Integer isTransfered;
        @SerializedName("WareHouseID")
        @Expose
        private String wareHouseID;
        @SerializedName("SiteID")
        @Expose
        private Object siteID;
        @SerializedName("Status")
        @Expose
        private String status;
        @SerializedName("Message")
        @Expose
        private String message;
        @SerializedName("TransferId")
        @Expose
        private String transferId;
        @SerializedName("IsShiped")
        @Expose
        private String isShiped;
        @SerializedName("STRP")
        @Expose
        private Integer sTRP;
        @SerializedName("isHold")
        @Expose
        private Integer isHold;
        @SerializedName("IsCounted")
        @Expose
        private Integer isCounted;
        @SerializedName("ToBeTransferWHID")
        @Expose
        private String toBeTransferWHID;
        @SerializedName("WoStatus")
        @Expose
        private String woStatus;
        @SerializedName("Prodstatus")
        @Expose
        private Integer prodstatus;
        @SerializedName("IsAssignTransfer")
        @Expose
        private Integer isAssignTransfer;

        public List<SiteListDatum> getSiteListData() {
            return siteListData;
        }

        public void setSiteListData(List<SiteListDatum> siteListData) {
            this.siteListData = siteListData;
        }

        public List<ReasonList> getReasonList() {
            return reasonList;
        }

        public void setReasonList(List<ReasonList> reasonList) {
            this.reasonList = reasonList;
        }

        public String getStickerID() {
            return stickerID;
        }

        public void setStickerID(String stickerID) {
            this.stickerID = stickerID;
        }

        public Integer getStandardQty() {
            return standardQty;
        }

        public void setStandardQty(Integer standardQty) {
            this.standardQty = standardQty;
        }

        public String getItemId() {
            return itemId;
        }

        public void setItemId(String itemId) {
            this.itemId = itemId;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Integer getItemStdQty() {
            return itemStdQty;
        }

        public void setItemStdQty(Integer itemStdQty) {
            this.itemStdQty = itemStdQty;
        }

        public String getWareHouseName() {
            return wareHouseName;
        }

        public void setWareHouseName(String wareHouseName) {
            this.wareHouseName = wareHouseName;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public Integer getIsTransfered() {
            return isTransfered;
        }

        public void setIsTransfered(Integer isTransfered) {
            this.isTransfered = isTransfered;
        }

        public String getWareHouseID() {
            return wareHouseID;
        }

        public void setWareHouseID(String wareHouseID) {
            this.wareHouseID = wareHouseID;
        }

        public Object getSiteID() {
            return siteID;
        }

        public void setSiteID(Object siteID) {
            this.siteID = siteID;
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

        public String getTransferId() {
            return transferId;
        }

        public void setTransferId(String transferId) {
            this.transferId = transferId;
        }

        public String getIsShiped() {
            return isShiped;
        }

        public void setIsShiped(String isShiped) {
            this.isShiped = isShiped;
        }

        public Integer getSTRP() {
            return sTRP;
        }

        public void setSTRP(Integer sTRP) {
            this.sTRP = sTRP;
        }

        public Integer getIsHold() {
            return isHold;
        }

        public void setIsHold(Integer isHold) {
            this.isHold = isHold;
        }

        public Integer getIsCounted() {
            return isCounted;
        }

        public void setIsCounted(Integer isCounted) {
            this.isCounted = isCounted;
        }

        public String getToBeTransferWHID() {
            return toBeTransferWHID;
        }

        public void setToBeTransferWHID(String toBeTransferWHID) {
            this.toBeTransferWHID = toBeTransferWHID;
        }

        public String getWoStatus() {
            return woStatus;
        }

        public void setWoStatus(String woStatus) {
            this.woStatus = woStatus;
        }

        public Integer getProdstatus() {
            return prodstatus;
        }

        public void setProdstatus(Integer prodstatus) {
            this.prodstatus = prodstatus;
        }

        public Integer getIsAssignTransfer() {
            return isAssignTransfer;
        }

        public void setIsAssignTransfer(Integer isAssignTransfer) {
            this.isAssignTransfer = isAssignTransfer;
        }

    }


package com.example.mammamia;

import android.media.Image;

public class Address {
    int addrNo;
    String addrName;
    String addrTel;
    String addrAddr;
    String addrDetail;
    String addrLike;
    String addrTag;
    Image addrProfile;


    public Address(int addrNo, String addrName, String addrTel, String addrAddr, String addrDetail, String addrLike, String addrTag) {
        this.addrNo = addrNo;
        this.addrName = addrName;
        this.addrTel = addrTel;
        this.addrAddr = addrAddr;
        this.addrDetail = addrDetail;
        this.addrLike = addrLike;
        this.addrTag = addrTag;
    }


    public int getAddrNo() {
        return addrNo;
    }

    public void setAddrNo(int addrNo) {
        this.addrNo = addrNo;
    }

    public String getAddrName() {
        return addrName;
    }

    public void setAddrName(String addrName) {
        this.addrName = addrName;
    }

    public String getAddrTel() {
        return addrTel;
    }

    public void setAddrTel(String addrTel) {
        this.addrTel = addrTel;
    }

    public String getAddrAddr() {
        return addrAddr;
    }

    public void setAddrAddr(String addrAddr) {
        this.addrAddr = addrAddr;
    }

    public String getAddrDetail() {
        return addrDetail;
    }

    public void setAddrDetail(String addrDetail) {
        this.addrDetail = addrDetail;
    }

    public String getAddrLike() {
        return addrLike;
    }

    public void setAddrLike(String addrLike) {
        this.addrLike = addrLike;
    }

    public String getAddrTag() {
        return addrTag;
    }

    public void setAddrTag(String addrTag) {
        this.addrTag = addrTag;
    }
}//------------------------------

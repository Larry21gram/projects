package com.larry.order.domain;

public class Orderitem {
    private String iid ;

    private int count;
    private double subtatal;
    private String oid;
    private String bid;

    public String getIid() {
        return iid;
    }

    public void setIid(String iid) {
        this.iid = iid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getSubtatal() {
        return subtatal;
    }

    public void setSubtatal(double subtatal) {
        this.subtatal = subtatal;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public Orderitem() {

    }
}

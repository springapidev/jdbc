package com.coderbd.domain;

import java.util.Date;

public class Sales {

    private int id;
    private String productName;
    private String productCode;
    private int qty;
    private double unitprice;
    private double totalPrice;
    private Date salesdate;
    //For Foreign key
    Purchase purchase;

    User user;

    public Sales() {
    }

    public Sales(String productName, String productCode, int qty, double unitprice, double totalPrice, Date salesdate, Purchase purchase, User user) {
        this.productName = productName;
        this.productCode = productCode;
        this.qty = qty;
        this.unitprice = unitprice;
        this.totalPrice = totalPrice;
        this.salesdate = salesdate;
        this.purchase = purchase;
        this.user = user;
    }

    public Sales(String productCode, int qty, double unitprice, double totalPrice, Date salesdate) {
        this.productCode = productCode;
        this.qty = qty;
        this.unitprice = unitprice;
        this.totalPrice = totalPrice;
        this.salesdate = salesdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(double unitprice) {
        this.unitprice = unitprice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getSalesdate() {
        return salesdate;
    }

    public void setSalesdate(Date salesdate) {
        this.salesdate = salesdate;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Sales{" + "id=" + id + ", productName=" + productName + ", productCode=" + productCode + ", qty=" + qty + ", unitprice=" + unitprice + ", totalPrice=" + totalPrice + ", salesdate=" + salesdate + ", purchase=" + purchase + ", user=" + user + '}';
    }

}

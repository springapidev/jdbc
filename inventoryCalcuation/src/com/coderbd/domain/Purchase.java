package com.coderbd.domain;

import java.util.Date;

public class Purchase {

    private int id;
    private String productName;
    private String productCode;
    private int qty;
    private double unitprice;
    private double totalPrice;
    private Date purchasedate;
    //For Foreign key
    ProductCategory productCategory;
    User user;

    public Purchase() {
    }

    public Purchase(String productName, String productCode, int qty, double unitprice, double totalPrice, Date purchasedate, ProductCategory productCategory,User user) {
        this.productName = productName;
        this.productCode = productCode;
        this.qty = qty;
        this.unitprice = unitprice;
        this.totalPrice = totalPrice;
        this.purchasedate = purchasedate;
        this.productCategory = productCategory;
        this.user=user;
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

    public Date getPurchasedate() {
        return purchasedate;
    }

    public void setPurchasedate(Date purchasedate) {
        this.purchasedate = purchasedate;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Purchase{" + "id=" + id + ", productName=" + productName + ", productCode=" + productCode + ", qty=" + qty + ", unitprice=" + unitprice + ", totalPrice=" + totalPrice + ", purchasedate=" + purchasedate + ", productCategory=" + productCategory + ", user=" + user + '}';
    }

}

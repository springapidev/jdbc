package com.coderbd.domain;

public class Summary {

    private int id;
    private Product product;
    private int totalQty;
    private int soldQty;
    private int availabeQty;

    public Summary() {
    }

    public Summary(Product product, int totalQty, int soldQty, int availabeQty) {
       
        this.product = product;
        this.totalQty = totalQty;
        this.soldQty = soldQty;
        this.availabeQty = availabeQty;
    }

    public Summary(int id, int totalQty, int soldQty, int availabeQty) {
        this.id = id;
        this.totalQty = totalQty;
        this.soldQty = soldQty;
        this.availabeQty = availabeQty;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(int totalQty) {
        this.totalQty = totalQty;
    }

    public int getSoldQty() {
        return soldQty;
    }

    public void setSoldQty(int soldQty) {
        this.soldQty = soldQty;
    }

    public int getAvailabeQty() {
        return availabeQty;
    }

    public void setAvailabeQty(int availabeQty) {
        this.availabeQty = availabeQty;
    }

}

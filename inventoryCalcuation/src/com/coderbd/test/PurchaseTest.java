/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coderbd.test;

import com.coderbd.domain.ProductCategory;
import com.coderbd.domain.Purchase;
import com.coderbd.service.PurchaseService;
import java.util.Date;
import java.util.List;

/**
 *
 * @author User
 */
public class PurchaseTest {

    public static void main(String[] args) {
        //  PurchaseService.createTable();
        /*    ProductCategory c = new ProductCategory();
        c.setId(2);

        Purchase purchase = new Purchase("Hp 6000 Laptop", "HP600", 10, 50000, 500000, new Date(), c);
        PurchaseService.insertMain(purchase);
         */
        List<Purchase> list = PurchaseService.getProductList();
        for(Purchase p : list)
        System.out.println(p);
    }
}

package com.coderbd.test;

import com.coderbd.domain.Category;
import com.coderbd.domain.Product;
import com.coderbd.service.ProductService;
import java.util.Date;

public class ProductTest {

    public static void main(String[] args) {
       // ProductService.createTable();
        Product product = new Product();
        product.setName("Lenevo 6200");
        product.setQty(30);
        product.setUnitPrice(50000);
        product.setTotalPrice(1500000);
        product.setPurchaseDate(new Date());
        Category category = new Category();
        category.setId(2);
        product.setCategory(category);
        ProductService.insert(product);
    }

}

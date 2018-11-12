package com.coderbd.test;

import com.coderbd.domain.Category;
import com.coderbd.domain.Product;
import com.coderbd.service.ProductService;
import java.util.Date;

public class ProductTest {
    
    public static void main(String[] args) {
        // ProductService.createTable();
        Product product = new Product();
        product.setId(8);
        product.setName("Hp i 5");
        product.setQty(20);
        product.setUnitPrice(50000);
        product.setTotalPrice(1500000);
        product.setPurchaseDate(new Date());
        Category category = new Category();
        category.setId(2);
        product.setCategory(category);
        ProductService.dataInsertOrUpdateIntoProductAndSummaryTable(product);
    }
    
}

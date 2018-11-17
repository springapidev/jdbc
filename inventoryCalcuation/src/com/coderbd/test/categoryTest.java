package com.coderbd.test;

import com.coderbd.domain.ProductCategory;
import com.coderbd.service.ProductCategoryService;
import java.util.Locale;


/**
 *
 * @author User
 */
public class categoryTest {

    public static void main(String[] args) {
       // ProductCategoryService.createTable();
        ProductCategory cat=new ProductCategory();
        cat.setName("Drone");
        
        ProductCategoryService.insert(cat);

    }
}

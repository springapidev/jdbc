package com.coderbd.test;

import com.coderbd.domain.Category;
import com.coderbd.service.CategoryService;

public class CategoryTest {

    public static void main(String[] args) {
        // CategoryService.createTable();
//        Category c = new Category();
//        c.setName("Laptop");
//        CategoryService.insert(c);
        CategoryService.getCatList();
    }

}

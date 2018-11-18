package com.coderbd.service;

import com.coderbd.connection.MySqlDbConnection;
import com.coderbd.domain.ProductCategory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductCategoryService {

    static Connection conn = MySqlDbConnection.getConnection();

    /*
       private String productName;
    private String productCode;
    private int qty;
    private double unitprice;
    private double totalPrice;
    private Date purchasedate;
     */
    public static void createTable() {
        String sql = "create table category(id int auto_increment primary key, name varchar(30) not null)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
            System.out.println("Table Created!");
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void insert(ProductCategory cat) {
        String sql = "insert into category(name) values(?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cat.getName());
            ps.executeUpdate();
            System.out.println("Data Inserted!");
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<ProductCategory> getCatList() {
        List<ProductCategory> list = new ArrayList<>();

        String sql = "select * from category";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductCategory pc = new ProductCategory();
                pc.setId(rs.getInt(1));
                pc.setName(rs.getString(2));
                list.add(pc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}

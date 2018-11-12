package com.coderbd.service;

import com.coderbd.connections.MySqlDbConnection;
import com.coderbd.domain.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoryService {

    static Connection conn = MySqlDbConnection.getConnection();

    public static void createTable() {
        String sql = "create table category(id int(11) auto_increment primary key, name varchar(50))";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            System.out.println("Table created successfully");
        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void insert(Category category) {
        String sql = "insert into category(name) value(?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, category.getName());
            ps.executeUpdate();
            System.out.println("Data Inserted successfully");
        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static List<Category> getCatList() {
        List<Category> list = new ArrayList();

        String sql = "select * from category";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt(1), rs.getString(100)));
            }

        } catch (SQLException ex) {
            System.out.println(" ex.getMessage(): " + ex.getMessage());
            System.out.println(" ex.getErrorCode(): " + ex.getErrorCode());
            System.out.println(" ex.getSQLState(): " + ex.getSQLState());
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}

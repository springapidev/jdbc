package com.coderbd.service;

import com.coderbd.connections.MySqlDbConnection;
import com.coderbd.domain.Product;
import com.coderbd.domain.Summary;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SummaryService {

    static Connection conn = MySqlDbConnection.getConnection();

    public static void createTable() {
        String sql = "create table summary(id int(11) auto_increment primary key, total_qty int, sold_qty int,available_qty int,product_id int(11), FOREIGN KEY (product_id) REFERENCES product(id))";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            System.out.println("Table created successfully");
        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void insert(Summary summary) {
        String sql = "insert into summary(total_qty, sold_qty,available_qty,product_id) value(?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, summary.getTotalQty());
            ps.setInt(2, summary.getSoldQty());
            ps.setInt(3, summary.getAvailabeQty());
            ps.setInt(4, summary.getProduct().getId());
            ps.executeUpdate();
            System.out.println("Data Inserted successfully");
        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void update(Summary summary) {
        String sql = "update summary set total_qty=?, sold_qty=?,available_qty=? where id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, summary.getTotalQty());
            ps.setInt(2, summary.getSoldQty());
            ps.setInt(3, summary.getAvailabeQty());
            ps.setInt(4, summary.getId());
            ps.executeUpdate();
            System.out.println("Data Updated successfully");
        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static Summary getSummaryById(int productId) {
        Summary summary = null;
        String sql = "select * from summary where product_id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               summary = new Summary(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return summary;
    }
}

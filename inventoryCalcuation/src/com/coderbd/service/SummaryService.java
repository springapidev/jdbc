package com.coderbd.service;

import com.coderbd.connection.MySqlDbConnection;
import com.coderbd.domain.ProductCategory;
import com.coderbd.domain.Purchase;
import com.coderbd.domain.Summary;
import com.coderbd.domain.User;
import static com.coderbd.service.UserService.conn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SummaryService {
    
    static Connection conn = MySqlDbConnection.getConnection();

    /*
       private String productName;
    private String productCode;
     */
    public static void createTable() {
        String sql = "create table summary(id int auto_increment primary key, productName varchar(50) not null,productCode varchar(30) not null, totalQty int(11) not null,soldQty int(11) not null,availableQty int(11) not null,lastUpdate Date, product_id int(11) not null, foreign key (product_id) references purchase(id))";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
            System.out.println("Table Created!");
        } catch (SQLException ex) {
            Logger.getLogger(SummaryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void insert(Summary summary) {
        String sql = "insert into summary(productName,productCode, totalQty,soldQty,availableQty,lastUpdate, product_id) values(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, summary.getProductName());
            ps.setString(2, summary.getProductCode());
            ps.setInt(3, summary.getTotalQty());
            ps.setInt(4, summary.getSoldQty());
            ps.setInt(5, summary.getAvailableQty());
            ps.setDate(6, new java.sql.Date(summary.getLastUpdate().getTime()));
            ps.setInt(7, summary.getPurchase().getId());
            ps.executeUpdate();
            System.out.println("Data Inserted! in Summary");
        } catch (SQLException ex) {
            Logger.getLogger(SummaryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void update(Summary summary) {
        String sql = "update summary set totalQty=?,soldQty=?,availableQty=?,lastUpdate=? where productCode=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, summary.getTotalQty());
            ps.setInt(2, summary.getSoldQty());
            ps.setInt(3, summary.getAvailableQty());
            ps.setDate(4, new java.sql.Date(summary.getLastUpdate().getTime()));
            ps.setString(5, summary.getProductCode());
            
            ps.executeUpdate();
            System.out.println("Data Updated in Summary!");
        } catch (SQLException ex) {
            Logger.getLogger(SummaryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Summary getSummaryByProductCode(String productCode) {
        Summary summary = new Summary();
        String sql = "select * from summary where productCode=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, productCode);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                summary.setId(rs.getInt(1));
                summary.setProductName(rs.getString(2));
                summary.setProductCode(rs.getString(3));
                summary.setTotalQty(rs.getInt(4));
                summary.setSoldQty(rs.getInt(5));
                summary.setAvailableQty(rs.getInt(6));
                summary.setLastUpdate(rs.getDate(7));
                Purchase p = new Purchase();
                p.setId(rs.getInt(8));
                summary.setPurchase(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return summary;
    }
    
    public static List<Summary> getSummaryList() {
        List<Summary> list = new ArrayList<>();
        
        String sql = "select s.productName, s.productCode, s.totalQty, s.soldQty, s.availableQty, c.name from summary s, purchase p, category c where s.product_id=p.id and p.cat_id=c.id";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Summary summary = new Summary();                
                summary.setProductName(rs.getString(1));
                summary.setProductCode(rs.getString(2));
                summary.setTotalQty(rs.getInt(3));
                summary.setSoldQty(rs.getInt(4));
                summary.setAvailableQty(rs.getInt(5));                
                ProductCategory pc = new ProductCategory();
                pc.setName(rs.getString("name"));
                Purchase p = new Purchase();
                p.setProductCategory(pc);
                summary.setPurchase(p);
                list.add(summary);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
}

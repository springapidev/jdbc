package com.coderbd.service;

import com.coderbd.connection.MySqlDbConnection;
import com.coderbd.domain.ProductCategory;
import com.coderbd.domain.Purchase;
import com.coderbd.domain.Summary;
import com.coderbd.domain.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PurchaseService {

    static Connection conn = MySqlDbConnection.getConnection();

    public static void createTable() {
        String sql = "create table purchase(id int auto_increment primary key, productName varchar(30) not null, productCode varchar(30) not null,qty int(11) not null,unitprice double not null,  totalPrice double not null, purchasedate Date not null, cat_id int(11) not null, foreign key (cat_id) references category(id),user_id int(11) not null, foreign key (user_id) references user(id))";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
            System.out.println("Table Created!");
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void insert(Purchase purchase) {
        String sql = "insert into purchase(productName, productCode,qty, unitprice, totalPrice, purchasedate, cat_id,user_id) values(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, purchase.getProductName());
            ps.setString(2, purchase.getProductCode());
            ps.setInt(3, purchase.getQty());
            ps.setDouble(4, purchase.getUnitprice());
            ps.setDouble(5, purchase.getTotalPrice());
            ps.setDate(6, new java.sql.Date(purchase.getPurchasedate().getTime()));
            ps.setInt(7, purchase.getProductCategory().getId());
            ps.setInt(8, purchase.getUser().getId());
            ps.executeUpdate();
            System.out.println("Data Inserted!");
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Purchase getPurchaseByProductCode(String productCode) {
        Purchase purchase = new Purchase();
        String sql = "select * from purchase where productCode=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, productCode);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                purchase.setId(rs.getInt(1));
                purchase.setProductName(rs.getString(2));
                purchase.setProductCode(rs.getString(3));
                purchase.setQty(rs.getInt(4));
                purchase.setUnitprice(rs.getDouble(5));
                purchase.setTotalPrice(rs.getDouble(6));
                purchase.setPurchasedate(rs.getDate(7));
                ProductCategory c = new ProductCategory();
                c.setId(rs.getInt(8));
                purchase.setProductCategory(c);
                User user = new User();
                user.setId(rs.getInt(9));
                purchase.setUser(user);

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return purchase;
    }

    public static void insertMain(Purchase purchase) {
        if (purchase != null) {
            insert(purchase);
            Purchase p = getPurchaseByProductCode(purchase.getProductCode());

            try {
                Summary summary = SummaryService.getSummaryByProductCode(purchase.getProductCode());
                if (purchase.getProductCode().equalsIgnoreCase(summary.getProductCode())) {
                    int totalQty = summary.getTotalQty() + purchase.getQty();
                    int avilQty = summary.getAvailableQty() + purchase.getQty();
                    summary.setTotalQty(totalQty);
                    summary.setAvailableQty(avilQty);
                    summary.setLastUpdate(new Date());
                }
                SummaryService.update(summary);

            } catch (NullPointerException e) {

                Summary summary3 = new Summary(purchase.getProductName(), purchase.getProductCode(), purchase.getQty(), 0, purchase.getQty(), new Date(), p);
                SummaryService.insert(summary3);

            }

        }
    }

    public static Purchase getProductDetails(String productCode) {
        Purchase purchase = new Purchase();
        String sql = " select * from purchase p, Category c where p.productCode=? and p.cat_id=c.id limit 1";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, productCode);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                purchase.setId(rs.getInt(1));
                purchase.setProductName(rs.getString(2));
                purchase.setProductCode(rs.getString(3));
                purchase.setQty(rs.getInt(4));
                purchase.setUnitprice(rs.getDouble(5));
                purchase.setTotalPrice(rs.getDouble(6));
                purchase.setPurchasedate(rs.getDate(7));
                ProductCategory pc = new ProductCategory();
                pc.setId(rs.getInt(8));
                pc.setName(rs.getString("name"));
                purchase.setProductCategory(pc);
                User user = new User();
                user.setId(rs.getInt(9));
                purchase.setUser(user);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PurchaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return purchase;
    }

    public static List<Purchase> getProductList() {
        List<Purchase> list = new ArrayList<>();

        String sql = "select * from purchase p, Category c where p.cat_id=c.id";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Purchase purchase = new Purchase();
                purchase.setId(rs.getInt(1));
                purchase.setProductName(rs.getString(2));
                purchase.setProductCode(rs.getString(3));
                purchase.setQty(rs.getInt(4));
                purchase.setUnitprice(rs.getDouble(5));
                purchase.setTotalPrice(rs.getDouble(6));
                purchase.setPurchasedate(rs.getDate(7));
                ProductCategory pc = new ProductCategory();
                pc.setId(rs.getInt(8));
                pc.setName(rs.getString("name"));
                purchase.setProductCategory(pc);
                User user = new User();
                user.setId(rs.getInt(9));
                purchase.setUser(user);
                list.add(purchase);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PurchaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}

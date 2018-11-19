package com.coderbd.service;

import com.coderbd.connection.MySqlDbConnection;
import com.coderbd.domain.ProductCategory;
import com.coderbd.domain.Purchase;
import com.coderbd.domain.Sales;
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

public class SalesService {

    static Connection conn = MySqlDbConnection.getConnection();

    public static void createTable() {
        String sql = "create table sales(id int auto_increment primary key, productName varchar(30) not null, productCode varchar(30) not null,qty int(11) not null,unitprice double not null,  totalPrice double not null, salesdate Date not null, product_id int(11) not null, foreign key (product_id) references purchase(id))";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
            System.out.println("Table Created!");
        } catch (SQLException ex) {
            Logger.getLogger(SalesService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void insert(Sales sales) {
        String sql = "insert into sales(productName, productCode,qty, unitprice, totalPrice, salesdate, product_id, user_id) values(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, sales.getProductName());
            ps.setString(2, sales.getProductCode());
            ps.setInt(3, sales.getQty());
            ps.setDouble(4, sales.getUnitprice());
            ps.setDouble(5, sales.getTotalPrice());
            ps.setDate(6, new java.sql.Date(sales.getSalesdate().getTime()));
            ps.setInt(7, sales.getPurchase().getId());
            ps.setInt(8, sales.getUser().getId());

            ps.executeUpdate();
            System.out.println("Data Inserted!");
        } catch (SQLException ex) {
            Logger.getLogger(SalesService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void insertForSales(Sales sales) {
        if (sales != null) {
            Summary summary = SummaryService.getSummaryByProductCode(sales.getProductCode());
            if (summary.getAvailableQty() >= sales.getQty()) {
                insert(sales);
                int soldQrt = summary.getSoldQty() + sales.getQty();
                int avilQty = summary.getAvailableQty() - sales.getQty();

                summary.setSoldQty(soldQrt);
                summary.setAvailableQty(avilQty);
                summary.setLastUpdate(new Date());

                SummaryService.update(summary);
            } else {
                System.out.println("You do not have sufficient Product");
            }
        }
    }

    public static List<Sales> getSalesList() {
        List<Sales> list = new ArrayList<>();

        String sql = "select s.productName,s.productCode, s.qty, s.unitPrice, s.totalPrice, s.salesdate, c.name from sales s, purchase p, category c where s.product_id=p.id and p.cat_id=c.id";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Sales sales = new Sales();
                sales.setProductName(rs.getString(1));
                sales.setProductCode(rs.getString(2));
                sales.setQty(rs.getInt(3));
                sales.setUnitprice(rs.getDouble(4));
                sales.setTotalPrice(rs.getDouble(5));
                sales.setSalesdate(rs.getDate(6));
                ProductCategory pc = new ProductCategory();
                pc.setName(rs.getString(7));
                Purchase p = new Purchase();
                p.setProductCategory(pc);
                sales.setPurchase(p);
                User user = new User();
                user.setId(rs.getInt(9));
                sales.setUser(user);
                list.add(sales);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PurchaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}

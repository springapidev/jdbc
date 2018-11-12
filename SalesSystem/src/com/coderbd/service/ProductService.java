package com.coderbd.service;

import com.coderbd.connections.MySqlDbConnection;
import com.coderbd.domain.Product;
import com.coderbd.domain.Summary;
import static com.coderbd.service.SummaryService.conn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductService {

    static Connection conn = MySqlDbConnection.getConnection();

    public static void createTable() {
        String sql = "create table product(id int(11) auto_increment primary key, name varchar(50), qty int(11), unit_price double, total_price double, purchase_date Date,cat_id int(11), FOREIGN KEY (cat_id) REFERENCES category(id))";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            System.out.println("Table created successfully");
        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void insert(Product product) {
        String sql = "insert into product(name, qty, unit_price, total_price, purchase_date, cat_id) value(?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, product.getName());
            ps.setInt(2, product.getQty());
            ps.setDouble(3, product.getUnitPrice());
            ps.setDouble(4, product.getTotalPrice());
            ps.setDate(5, new java.sql.Date(product.getPurchaseDate().getTime()));
            ps.setInt(6, product.getCategory().getId());

            ps.executeUpdate();
            System.out.println("Data Inserted successfully");
        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void dataInsertOrUpdateIntoProductAndSummaryTable(Product p) {
        insert(p);

        Product product = getProductByName(p.getName());

        int totalQty = 0;
        int avilaleQty = 0;

        if (p.getId() != 0) {
            Summary sumFromDb = SummaryService.getSummaryById(p.getId());
            totalQty = sumFromDb.getTotalQty() + p.getQty();
            avilaleQty = totalQty - sumFromDb.getSoldQty();

            Summary summary1 = new Summary(sumFromDb.getId(), totalQty, 0, avilaleQty);
            SummaryService.update(summary1);
        } else {

            Summary summary3 = new Summary(product, p.getQty(), 0, p.getQty());
            SummaryService.insert(summary3);
        }
    }

    public static Product getProductByName(String productName) {
        Product product = new Product();
        String sql = "select * from product where name=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, productName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));

            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return product;
    }

}

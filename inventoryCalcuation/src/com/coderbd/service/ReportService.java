package com.coderbd.service;

import com.coderbd.connection.MySqlDbConnection;
import com.coderbd.domain.AdminDashBoard;
import com.coderbd.domain.ProductCategory;
import com.coderbd.domain.Purchase;
import com.coderbd.domain.Sales;
import com.coderbd.domain.User;
import com.coderbd.util.DateFormating;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReportService {

    static Connection conn = MySqlDbConnection.getConnection();

//////////////////////////user Report//////////////////////////
    public static List<User> getUserListByStatus(boolean status) {
        List<User> list = new ArrayList<>();

        String sql = "select * from user where status=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBoolean(1, status);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setUserType(rs.getString(4));
                user.setFirstName(rs.getString(5));
                user.setLastName(rs.getString(6));
                user.setEmail(rs.getString(7));
                user.setMobile(rs.getString(8));
                user.setRegiDate(rs.getDate(9));
                user.setStatus(rs.getBoolean(10));
                list.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static List<User> getUserListByUserType(String userType) {
        List<User> list = new ArrayList<>();

        String sql = "select * from user where userType=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userType);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setUserType(rs.getString(4));
                user.setFirstName(rs.getString(5));
                user.setLastName(rs.getString(6));
                user.setEmail(rs.getString(7));
                user.setMobile(rs.getString(8));
                user.setRegiDate(rs.getDate(9));
                user.setStatus(rs.getBoolean(10));
                list.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static List<User> getUserListByUserTypeAndStatus(String userType, boolean status) {
        List<User> list = new ArrayList<>();

        String sql = "select * from user where userType=? and status=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userType);
            ps.setBoolean(2, status);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setUserType(rs.getString(4));
                user.setFirstName(rs.getString(5));
                user.setLastName(rs.getString(6));
                user.setEmail(rs.getString(7));
                user.setMobile(rs.getString(8));
                user.setRegiDate(rs.getDate(9));
                user.setStatus(rs.getBoolean(10));
                list.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    ////////////////Purchase Report///////////////////////

    public static List<Purchase> getProductListBycategory(int categoryId) {
        List<Purchase> list = new ArrayList<>();

        String sql = "select * from purchase p, category c where p.cat_id=c.id and cat_id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, categoryId);
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

                list.add(purchase);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PurchaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static List<Purchase> getProductListByDateRange(Date startdate, Date endDate) {
        List<Purchase> list = new ArrayList<>();

        String sql = "select * from purchase p, category c where p.cat_id=c.id and purchasedate between ? and ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(startdate.getTime()));
            ps.setDate(2, new java.sql.Date(endDate.getTime()));
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

                list.add(purchase);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PurchaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static Double getPurchaseAmountByDateRange(Date startdate, Date endDate) {
        Double totalPurchaseAmount = 0.0;
        String sql = " select totalPrice from purchase where purchasedate between ? and ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(startdate.getTime()));
            ps.setDate(2, new java.sql.Date(endDate.getTime()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                totalPurchaseAmount += rs.getDouble(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PurchaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalPurchaseAmount;
    }

    public static Double getTotalPurchaseAmount() {
        Double totalPurchaseAmount = 0.0;
        String sql = " select totalPrice from purchase";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                totalPurchaseAmount += rs.getDouble(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PurchaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalPurchaseAmount;
    }

    public static int getTotalPurchaseItems() {
        int totalPurchaseItems = 0;
        String sql = " select qty from purchase";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                totalPurchaseItems += rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PurchaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalPurchaseItems;
    }

    public static int getTotalPurchaseItemsByToday(Date today) {
        int totalPurchaseItems = 0;
        String sql = " select qty from purchase where purchaseDate=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(today.getTime()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                totalPurchaseItems += rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PurchaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalPurchaseItems;
    }

    ////////////////////////Sales Report////////////////////////////////////
    public static List<Sales> getSalesByUser(int userID) {
        List<Sales> list = new ArrayList<>();

        String sql = "select s.productName,s.productCode, s.qty, s.unitPrice, s.totalPrice, s.salesdate, c.name from sales s, purchase p, category c where s.product_id=p.id and p.cat_id=c.id and s.user_id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userID);
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
                list.add(sales);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PurchaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static List<Sales> getSalesByUserAndDateRange(int userID, Date startdate, Date endDate) {
        List<Sales> list = new ArrayList<>();

        String sql = "select s.productName,s.productCode, s.qty, s.unitPrice, s.totalPrice, s.salesdate, c.name from sales s, purchase p, category c where s.product_id=p.id and p.cat_id=c.id and s.user_id=? and s.salesdate between ? and ? ";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userID);
            ps.setDate(2, new java.sql.Date(startdate.getTime()));
            ps.setDate(3, new java.sql.Date(endDate.getTime()));
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
                list.add(sales);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PurchaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static List<Sales> getSalesByDateRange(Date startdate, Date endDate) {
        List<Sales> list = new ArrayList<>();

        String sql = "select s.productName,s.productCode, s.qty, s.unitPrice, s.totalPrice, s.salesdate, c.name from sales s, purchase p, category c where s.product_id=p.id and p.cat_id=c.id and s.salesdate between ? and ? ";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(startdate.getTime()));
            ps.setDate(2, new java.sql.Date(endDate.getTime()));
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
                list.add(sales);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PurchaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static List<Sales> getSalesByToday(Date today) {
        List<Sales> list = new ArrayList<>();
        String sql = "select s.productName,s.productCode, s.qty, s.unitPrice, s.totalPrice, s.salesdate, c.name from sales s, purchase p, category c where s.product_id=p.id and p.cat_id=c.id and s.salesdate=? ";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(today.getTime()));

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
                list.add(sales);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PurchaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static Double getSalesAmountByToday(Date today) {
        Double amount = 0.0;
        String sql = "select sum(totalPrice) from sales where salesdate=? ";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(today.getTime()));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                amount = rs.getDouble(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PurchaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amount;
    }

    public static double getSalesTotalAmount() {
        double amount = 0.0;
        String sql = "select totalPrice from sales";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                amount += rs.getDouble(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PurchaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amount;
    }

    public static int getSalesTotalItems() {
        int amount = 0;
        String sql = "select qty from sales";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                amount += rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PurchaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amount;
    }

    public static int getSoldTodayItems(Date today) {
        int item = 0;
        String sql = "select sum(qty) from sales where salesdate=? ";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(today.getTime()));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                item = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PurchaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return item;
    }

    public static Double getSalesAmountByDateRange(Date sDate, Date eDate) {
        Double amount = 0.0;
        String sql = "select totalPrice from sales where salesdate between ? and ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(sDate.getTime()));
            ps.setDate(2, new java.sql.Date(eDate.getTime()));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                amount += rs.getDouble(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PurchaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amount;
    }

    public static AdminDashBoard getDashboardDetails() {
        AdminDashBoard adb = new AdminDashBoard();

        adb.setTodaysPurchaseAmount(getPurchaseAmountByDateRange(DateFormating.getDateFromString(DateFormating.getStringFromDate(new Date())), DateFormating.getDateFromString(DateFormating.getStringFromDate(new Date()))));

        adb.setEntirePurchaseAmount(getTotalPurchaseAmount());

        adb.setCurrentMonthPurchaseAmount(getPurchaseAmountByDateRange(DateFormating.getFirstDateOfCurrentMonth(), DateFormating.getDateFromString(DateFormating.getStringFromDate(new Date()))));
        adb.setTotalItems(getTotalPurchaseItems());
        adb.setTodaysPurchaseItem(getTotalPurchaseItemsByToday(DateFormating.getDateFromString(DateFormating.getStringFromDate(new Date()))));
        ///////////sales part

        adb.setTodaysSalesAmount(getSalesAmountByToday(DateFormating.getDateFromString(DateFormating.getStringFromDate(new Date()))));
        adb.setCurrentMonthSalesAmount(getSalesAmountByDateRange(DateFormating.getFirstDateOfCurrentMonth(), DateFormating.getDateFromString(DateFormating.getStringFromDate(new Date()))));

        adb.setEntireSalesAmount(getSalesTotalAmount());
        adb.setTotalSoldItems(getSalesTotalItems());
        adb.setTodaysSoldItems(getSoldTodayItems(DateFormating.getDateFromString(DateFormating.getStringFromDate(new Date()))));

        return adb;
    }

}

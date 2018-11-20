package com.coderbd.util;

import com.coderbd.view.DashBoard;
import com.coderbd.view.Login;
import com.coderbd.view.PurchaseReportView;
import com.coderbd.view.PurchaseView;
import com.coderbd.view.SalesReportView;
import com.coderbd.view.SalesView;
import com.coderbd.view.SignUpView;
import com.coderbd.view.UserReportView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuFormAdmin {

    public static JMenuBar commonMenuForAdmin(JFrame f) {
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem dashboard = new JMenuItem("Dashboard");
        dashboard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                new DashBoard().setVisible(true);
            }
        });
        JMenuItem pCategory = new JMenuItem("category");
        pCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                new DashBoard().setVisible(true);
            }
        });
        JMenuItem purchase = new JMenuItem("Purchase");
        purchase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                new PurchaseView().setVisible(true);
            }
        });
        JMenuItem sales = new JMenuItem("Sales");
        sales.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                new SalesView().setVisible(true);
            }
        });
        JMenuItem summary = new JMenuItem("Summary");
        summary.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                new DashBoard().setVisible(true);
            }
        });
        JMenuItem user = new JMenuItem("User");
        user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                new SignUpView().setVisible(true);
            }
        });
        JMenuItem signOut = new JMenuItem("Sign Out");
        signOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                new Login().setVisible(true);
            }
        });
        file.add(dashboard);
        file.addSeparator();
        file.add(pCategory);
        file.addSeparator();
        file.add(purchase);
        file.addSeparator();
        file.add(sales);
        file.addSeparator();
        file.add(summary);
        file.addSeparator();
        file.add(user);
        file.addSeparator();
        file.add(signOut);
        JMenu report = new JMenu("Report");
        JMenuItem purchaseReport = new JMenuItem("Purchase");
        purchaseReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                new PurchaseReportView().setVisible(true);
            }
        });
        JMenuItem saleReport = new JMenuItem("Sales");
        saleReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                new SalesReportView().setVisible(true);
            }
        });
        JMenuItem userReport = new JMenuItem("User");
        userReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                new UserReportView().setVisible(true);
            }
        });

        report.add(purchaseReport);
        report.add(saleReport);
        report.add(userReport);
        JMenuItem help = new JMenuItem("Help");

        menuBar.add(file);
        menuBar.add(report);
        menuBar.add(help);
        f.setJMenuBar(menuBar);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        return menuBar;
    }
}

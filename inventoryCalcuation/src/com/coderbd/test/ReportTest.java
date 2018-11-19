package com.coderbd.test;

import com.coderbd.domain.Purchase;
import com.coderbd.service.ReportService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReportTest {

    public static void main(String[] args) {
        try {
            Date sDate = new SimpleDateFormat("yyyy-MM-dd").parse("2018-11-17");
            Date eDate = new SimpleDateFormat("yyyy-MM-dd").parse("2018-11-17");
            ReportService.getProductListByDateRange(sDate, eDate);
            for (Purchase p : ReportService.getProductListByDateRange(sDate, eDate)) {
                System.out.println(p);
            }           
            
            double am=ReportService.getPurchaseAmountByDateRange(sDate, eDate);
            System.out.println("Amount: "+am);
        } catch (ParseException ex) {
            Logger.getLogger(ReportTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        

    }

}

package com.coderbd.util;

import static com.coderbd.service.ReportService.getTotalPurchaseItemsByToday;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class DateFormating {

    public static String getStringFromDate(Date date) {
        date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    public static Date getDateFromString(String strDate) {
        Date today = new Date();
        try {
            today = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
        } catch (ParseException ex) {
            Logger.getLogger(DateFormating.class.getName()).log(Level.SEVERE, null, ex);
        }
        return today;
    }

    public static Date getFirstDateOfCurrentMonth() {
        LocalDate now = LocalDate.now();
        Date date = getDateFromString(now.withDayOfMonth(1).toString());
        return date;
    }
    
     public static Date getToday() {
        LocalDate now = LocalDate.now();
        Date date = getDateFromString(now.toString());
        return date;
    }
    

    public static void main(String[] args) {
        LocalDate todaydate = LocalDate.now();
        System.out.println("Months first date in yyyy-mm-dd: " + todaydate.withDayOfMonth(1));

        Date d =getToday();
        System.out.println("d===== "+d);
    }
}

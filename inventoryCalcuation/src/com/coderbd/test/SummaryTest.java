package com.coderbd.test;

import com.coderbd.domain.Summary;
import com.coderbd.service.SummaryService;
import java.util.List;

public class SummaryTest {

    public static void main(String[] args) {
        //  SummaryService.createTable();
        /*    Purchase purchase = new Purchase();
        purchase.setId(1);
        Summary summary = new Summary("HP 5000", "HP5000", 50, 0, 50, new Date(), purchase);
        SummaryService.insert(summary);
         */
        List<Summary> list = SummaryService.getSummaryList();
        for(Summary s: list){
        System.out.println(s);
        }
    }
}

package com.coderbd.test;

import com.coderbd.domain.User;
import com.coderbd.service.UserService;
import java.util.Date;

/**
 *
 * @author User
 */
public class UserTest {

    public static void main(String[] args) {
        //UserService.createTable();
           User user = new User("arefin", "456", "user", "Mr.", "Arefin", "arefin@gmail.com", "01686239145", new Date(), true);
        UserService.insert(user);
         
       // User u = UserService.getUserbyUserName("admin", "123", true);
       // System.out.println(u);
    }
}

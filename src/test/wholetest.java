package test;

import com.scutcms.DAO.entity.User;
import com.scutcms.authentication.Service.TokenService;
import com.scutcms.authentication.Service.UserService;

import java.util.ArrayList;

/**
 * Created by loong on 16-7-3.
 */
public class wholetest {
    public static void main(String[] args) {
        UserService userService = new UserService();
        System.out.println(userService.login("root","root"));
        //new TokenService().createTokenforUser("root");


//        User user = new User();
//        user.setUsername("xiaoming");
//        user.setPassword("12345678");
//        user.setTelephone("12345678911");
//        user.setReal_name("小明");
//        userService.addUser(user);

//        User user = new User();
//        user.setUsername("xiaoming");
//        userService.deleteUser(user);


//        User user = new User();
//        user.setUsername("xiaoming");
//        user.setPassword("12345678");
//        user.setTelephone("12345678911");
//        user.setReal_name("小明");
//        userService.addUser(user);
//
//        user.setTelephone("99999999999");
//        userService.updateUser(user);

        ArrayList<User> users = userService.getAllUser();
        for (User each:users){
            System.out.println(each.getUsername());
        }

    }
}

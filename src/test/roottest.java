package test;

import com.scutcms.DAO.entity.User;
import com.scutcms.authentication.Service.UserService;
import com.scutcms.util.MD5Helper;
import com.scutcms.util.TokenHelper;

/**
 * Created by loong on 16-7-3.
 */
public class roottest {
    public static void main(String[] args) {
        User user = new User();
        user.setUsername("root");
        user.setPassword("root");
        user.setTelephone("12354678900");
        user.setReal_name("xiaoming");
        user.setSalt(TokenHelper.createRandomToken(32));
        user.setPassword_md5(MD5Helper.getMD5String(user.getPassword() + user.getSalt()));
        System.out.println(user.getSalt());
        System.out.println(user.getPassword_md5());
        String password = "root";
        System.out.println(MD5Helper.checkPassword(password + user.getSalt(), user.getPassword_md5()));
//insert user value('root','8164C3FC1C6F2D2D1D49DBCBBECE984E','N3DV39ohMbJEAdQXgNGnbOTJAIz6Fq0c','','chenyaofo','13076208199');

    }

}

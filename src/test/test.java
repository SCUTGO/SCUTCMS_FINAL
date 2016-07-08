package test;

import com.scutcms.DAO.entity.User;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONStringer;
import net.sf.json.util.JSONTokener;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by loong on 16-7-2.
 */
public class test {
    public static void main(String[] args) {

        User user  = new User();
        user.setUsername("root");
        user.setTelephone("12345678900");
        user.setReal_name("chenxudog");

        User user0  = new User();
        user0.setUsername("rodfdsaf");
        user0.setTelephone("12345678900");
        user0.setReal_name("chenxudog");

        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        users.add(user0);
        JSONStringer jsonStringer=new JSONStringer();
        try {
            jsonStringer.object();
            jsonStringer.key("state").value("SUCCESS");
            jsonStringer.key("users");
            jsonStringer.array();
            Iterator iterator = users.iterator();
            while (iterator.hasNext()) {
                user = (User) iterator.next();
                jsonStringer.object();
                jsonStringer.key("username").value(user.getUsername())
                        .key("real_name").value(user.getReal_name())
                        .key("telephone").value(user.getTelephone());
                jsonStringer.endObject();
            }
            jsonStringer.endArray();
            jsonStringer.endObject();
            System.out.println(jsonStringer.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

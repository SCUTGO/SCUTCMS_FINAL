package com.scutcms.web.Servlets;

import com.scutcms.DAO.entity.User;
import com.scutcms.authentication.Service.TokenService;
import com.scutcms.authentication.Service.UserService;
import com.scutcms.util.JsonHelper;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONStringer;
import net.sf.json.util.JSONTokener;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/**
 * Created by loong on 16-6-19.
 */

/**
 * Description: 用户(User)相关的所有操作<br>
 *
 */
@WebServlet(name = "UserServlet",urlPatterns = "/UserManagement")
public class UserServlet extends HttpServlet {
    /**
     * 添加用户<br>
     * @param request
     * request的json格式<br>
     *         {<br>
     *             "rootUser":username(管理员),<br>
     *             "token":token,<br>
     *             "username":username,<br>
     *             "password",password,<br>
     *             "real_name",real_name,<br>
     *             "telephone",telephone<br>
     *         }<br>
     * @param response
     * response的json格式<br>
     *         {<br>
     *             "state":state<br>
     *                 //SUCCESS表示添加成功，FAIL表示添加失败<br>
     *         }
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject jsonObject= JsonHelper.requestToJson(request);
        User user=new User();
        UserService userService=new UserService();
        TokenService tokenService=new TokenService();
        JSONStringer stringer=new JSONStringer();

        String token=jsonObject.getString("token");
        String rootUser=jsonObject.getString("rootUser");
        if(tokenService.isTokenValidforUser(rootUser,token)){
            String username=jsonObject.getString("username");
            String password=jsonObject.getString("password");
            String real_name=jsonObject.getString("real_name");
            String telephone=jsonObject.getString("telephone");

            user.setUsername(username);
            user.setPassword(password);
            user.setReal_name(real_name);
            user.setTelephone(telephone);

            //返回结果，{"state":value},value为SUCCESS时添加成功，FAIL时添加失败
            if(userService.addUser(user)){
                stringer.object();
                stringer.key("state").value("SUCCESS");
                stringer.endObject();
            }
            else{
                stringer.object();
                stringer.key("state").value("FAIL");
                stringer.endObject();
            }
        }
        else{
            stringer.object().key("state").value("FAIL").endObject();
        }
        JsonHelper.JsonToResponse(stringer,response);
    }

    /**
     *
     * 获取所有用户信息<br>
     * @param request
     * request的json格式<br>
     *     {<br>
     *         "rootUser":rootUser,<br>
     *         "token":token<br>
     *     }<br>
     * @param response
     * response的json格式<br>
     *     {<br>
     *            "state":"SUCCESS",<br>
     *            "user":[<br>
     *              {"username":username,"password":password,"real_name":real_name,"telephone":telephone},<br>
     *              ...<br>
     *            ]<br>
     *     }<br>
     *     或者{"state":"FAIL"}即失败<br>
     *     或者{"state":"NULL"}即数据为空<br>
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService=new UserService();
        TokenService tokenService=new TokenService();
        JSONStringer jsonStringer=new JSONStringer();
        User user;

        String json = URLDecoder.decode(request.getQueryString(),"utf-8");
        JSONObject jsonObject = (JSONObject)new JSONTokener(json).nextValue();

        String rootUser=jsonObject.getString("rootUser");
        String token=jsonObject.getString("token");
        System.out.println(rootUser+" "+token);
        if(tokenService.isTokenValidforUser(rootUser,token)){
            ArrayList<User> users=userService.getAllUser();
            if(users.isEmpty()){
                jsonStringer.object();
                jsonStringer.key("state").value("NULL");
                jsonStringer.endObject();
            }
            else{
                try{
                    jsonStringer.object();
                    jsonStringer.key("state").value("SUCCESS");
                    jsonStringer.key("user");
                    jsonStringer.array();
                    Iterator iterator = users.iterator();
                    while (iterator.hasNext()) {
                        user=(User)iterator.next();
                        jsonStringer.object();
                        jsonStringer.key("username").value(user.getUsername())
                                .key("real_name").value(user.getReal_name())
                                .key("telephone").value(user.getTelephone());
                        jsonStringer.endObject();
                    }
                    jsonStringer.endArray();
                    jsonStringer.endObject();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        else{
            jsonStringer.object().key("state").value("FAIL").endObject();
        }
        JsonHelper.JsonToResponse(jsonStringer,response);
    }

    /**
     * 修改用户信息
     * @param request
     * request的json格式<br>
     *         {<br>
     *             "rootUser":rootUser(管理员),<br>
     *             "token":token,<br>
     *             "username":username,<br>
     *             "password",password,<br>
     *             "real_name",real_name,<br>
     *             "telephone",telephone<br>
     *         }<br>
     * @param response
     * response的json格式<br>
     *     {<br>
     *         "state":state<br>
     *             //SUCCESS表示修改成功，FAIL表示修改失败<br>
     *     }<br>
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPut (HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        UserService userService=new UserService();
        TokenService tokenService=new TokenService();
        User user=new User();
        JSONObject jsonObject=JsonHelper.requestToJson(request);
        JSONStringer stringer=new JSONStringer();

        String rootUser= jsonObject.getString("rootUser");
        String token=jsonObject.getString("token");
        if(tokenService.isTokenValidforUser(rootUser,token)){
            String username=jsonObject.getString("username");
            String password=jsonObject.getString("password");
            String real_name=jsonObject.getString("real_name");
            String telephone=jsonObject.getString("telephone");

            user.setUsername(username);
            user.setPassword(password);
            user.setReal_name(real_name);
            user.setTelephone(telephone);

            //返回结果，{"state":value},value为SUCCESS时更新成功，FAIL时更新失败
            if(userService.updateUser(user)){
                stringer.object();
                stringer.key("state").value("SUCCESS");
                stringer.endObject();
            }
            else{
                stringer.object();
                stringer.key("state").value("FAIL");
                stringer.endObject();
            }
        }
        else stringer.object().key("state").value("FAIL").endObject();
        JsonHelper.JsonToResponse(stringer,response);
    }

    /**
     * 删除用户
     * @param request
     * request的json格式<br>
     *     {<br>
     *         "rootUser":rootUser(管理员),<br>
     *         "token":token,<br>
     *         "username":username<br>
     *     }
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doDelete(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        UserService userService=new UserService();
        TokenService tokenService=new TokenService();
        User user=new User();
        JSONObject jsonObject=JsonHelper.requestToJson(request);
        JSONStringer stringer=new JSONStringer();

        String rootUser=jsonObject.getString("rootUser");
        String token=jsonObject.getString("token");
        if(tokenService.isTokenValidforUser(rootUser,token)){
            String username=jsonObject.getString("username");
            user.setUsername(username);

            //返回结果，{"state":value},value为SUCCESS时删除成功，FAIL时删除失败
            if(userService.deleteUser(user)){
                stringer.object();
                stringer.key("state").value("SUCCESS");
                stringer.endObject();
            }
            else{
                stringer.object();
                stringer.key("state").value("FAIL");
                stringer.endObject();
            }
        }
        else stringer.object().key("state").value("FAIL").endObject();
        JsonHelper.JsonToResponse(stringer,response);
    }
}

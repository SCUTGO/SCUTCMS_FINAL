package com.scutcms.web.Servlets;

import com.scutcms.authentication.Enum.LoginResult;
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

/**
 * Created by loong on 16-6-19.
 */

/**
 *  Description:登录<br>
 *      返回用户登录信息
 *  request<br>
 *  {<br>
 *      "username":value,<br>
 *      "password":value,<br>
 *  }<br><br>
 *
 *  response<br>
 *      {<br>
 *          "result":result,<br>
 *          "token":token<br>
 *      }<br>
 *  result(int): 0(成功),1(用户名不存在),2(密码不存在),3(其他)<br>
 *
 *  @see HttpServlet
 */


@WebServlet(name = "LoginServlet",urlPatterns = "/Login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TokenService tokenService=new TokenService();
        UserService userService=new UserService();

        JSONObject jsonObject=JsonHelper.requestToJson(request);
        String username="";
        String password="";

        username=jsonObject.getString("username");
        password=jsonObject.getString("password");

        System.out.println(username+" "+password);

        LoginResult state=userService.login(username,password);
        JSONStringer stringer=new JSONStringer();
        Integer result=3;
        switch (state){
            case SUCCESS:
                result=0;
                break;
            case USERNAME_NO_VALID:
                result=1;
                break;
            case PASSWORD_NO_MATCH:
                result=2;
                break;
            case OTHER:
                result=3;
                break;
        }
        stringer.object();
        stringer.key("result").value(result.toString());
        if(state==LoginResult.SUCCESS){
            String token=tokenService.createTokenforUser(username);
            stringer.key("token").value(token);
        }
        stringer.endObject();
        JsonHelper.JsonToResponse(stringer,response);
    }
}
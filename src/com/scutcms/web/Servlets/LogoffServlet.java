package com.scutcms.web.Servlets;

import com.scutcms.authentication.Service.TokenService;
import com.scutcms.authentication.Service.UserService;
import com.scutcms.util.JsonHelper;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONStringer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by loong on 16-6-19.
 */

/**
 * Description: 登出
 */
@WebServlet(name = "LogoffServlet",urlPatterns = "/LogOff")
public class LogoffServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService=new UserService();
        TokenService tokenService=new TokenService();
        JSONObject jsonObject= JsonHelper.requestToJson(request);
        String rootUser=jsonObject.getString("rootUser");
        String token=jsonObject.getString("token");
        JSONStringer jsonStringer=new JSONStringer();
        jsonStringer.object();
        if(tokenService.isTokenValidforUser(rootUser,token)){
            String username=jsonObject.getString("username");
            userService.logoff(username);
            jsonStringer.key("state").value("SUCCESS");
        }
        else{
            jsonStringer.key("state").value("FAIL");
        }
        jsonStringer.endObject();
        JsonHelper.JsonToResponse(jsonStringer,response);
    }
}

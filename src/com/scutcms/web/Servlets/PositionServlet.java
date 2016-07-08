package com.scutcms.web.Servlets;

import com.scutcms.DAO.entity.Position;
import com.scutcms.authentication.Service.TokenService;
import com.scutcms.personnel.PositionService;
import com.scutcms.util.JsonHelper;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONStringer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by loong on 16-6-19.
 */

/**
 * Description:岗位信息相关操作
 *
 */
@WebServlet(name = "PositionServlet",urlPatterns = "/PositionManagement")
public class PositionServlet extends HttpServlet {
    /**
     * 修改岗位信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PositionService positionService=new PositionService();
        TokenService tokenService=new TokenService();
        Position position=new Position();

        JSONObject jsonObject=JsonHelper.requestToJson(request);
        String rootUser=jsonObject.getString("rootUser");
        String token=jsonObject.getString("token");
        JSONStringer stringer=new JSONStringer();
        if(tokenService.isTokenValidforUser(rootUser,token)){
            String positionName=jsonObject.getString("positionName");
            int baseSalary=Integer.parseInt(jsonObject.getString("baseSalary"));
            int wageHourly=Integer.parseInt(jsonObject.getString("wageHourly"));
            double punishment=Double.parseDouble(jsonObject.getString("punishment"));

            position.setBaseSalary(baseSalary);
            position.setPositionName(positionName);
            position.setPunishment(punishment);
            position.setWageHourly(wageHourly);


            if(positionService.updatePosition(position)){
                stringer.object().key("state").value("SUCCESS").endObject();
            }
            else{
                stringer.object().key("state").value("FAIL").endObject();
            }
        }
        else{
            stringer.object().key("state").value("FAIL").endObject();
        }
        JsonHelper.JsonToResponse(stringer,response);
    }

    /**
     * 获得岗位信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PositionService positionService=new PositionService();
        TokenService tokenService=new TokenService();
        Position position;

        JSONStringer jsonStringer=new JSONStringer();
        String json= URLDecoder.decode(request.getQueryString(),"utf-8");
        JSONObject jsonObject=JSONObject.fromObject(json);

        String rootUser=jsonObject.getString("rootUser");
        String token=jsonObject.getString("token");
        if(tokenService.isTokenValidforUser(rootUser,token)){
            ArrayList<Position> positions=positionService.getAllPosition();
            if(positions.isEmpty()){    //无数据
                jsonStringer.object();
                jsonStringer.key("state").value("NULL");
                jsonStringer.endObject();
            }
            else{
                try{
                    jsonStringer.object();
                    jsonStringer.key("state").value("SUCCESS");
                    jsonStringer.key("positions");
                    jsonStringer.array();
                    Iterator iterator = positions.iterator();
                    while (iterator.hasNext()) {
                        position=(Position) iterator.next();
                        jsonStringer.object();
                        jsonStringer.key("baseSalary").value(position.getBaseSalary())
                                .key("positionName").value(position.getPositionName())
                                .key("punishment").value(position.getPunishment())
                                .key("wageHourly").value(position.getWageHourly());
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
     * 增加岗位信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PositionService positionService=new PositionService();
        TokenService tokenService=new TokenService();
        JSONStringer stringer=new JSONStringer();
        Position position=new Position();

        JSONObject jsonObject=JsonHelper.requestToJson(req);
        String rootUser=jsonObject.getString("rootUser");
        String token=jsonObject.getString("token");
        if(tokenService.isTokenValidforUser(rootUser,token)){
            String positionName=jsonObject.getString("positionName");
            int baseSalary=Integer.parseInt(jsonObject.getString("baseSalary"));
            int wageHourly=Integer.parseInt(jsonObject.getString("wageHourly"));
            double punishment=Double.parseDouble(jsonObject.getString("punishment"));

            position.setBaseSalary(baseSalary);
            position.setPositionName(positionName);
            position.setPunishment(punishment);
            position.setWageHourly(wageHourly);

            if(positionService.insertPosition(position)){
                stringer.object().key("state").value("SUCCESS").endObject();
            }
            else{
                stringer.object().key("state").value("FAIL").endObject();
            }
        }
        else{
            stringer.object().key("state").value("FAIL").endObject();
        }
        JsonHelper.JsonToResponse(stringer,resp);
    }

    /**
     * 删除岗位信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PositionService positionService=new PositionService();
        TokenService tokenService=new TokenService();
        Position position=new Position();
        JSONStringer stringer=new JSONStringer();

        JSONObject jsonObject=JsonHelper.requestToJson(req);
        String rootUser=jsonObject.getString("rootUser");
        String token=jsonObject.getString("token");
        if(tokenService.isTokenValidforUser(rootUser,token)){
            String positionName=jsonObject.getString("positionName");
            position.setPositionName(positionName);
            if(positionService.deletePosition(position)){
                stringer.object().key("state").value("SUCCESS").endObject();
            }
            else{
                stringer.object().key("state").value("FAIL").endObject();
            }
        }
        else{
            stringer.object().key("state").value("FAIL").endObject();
        }
        JsonHelper.JsonToResponse(stringer,resp);
    }
}

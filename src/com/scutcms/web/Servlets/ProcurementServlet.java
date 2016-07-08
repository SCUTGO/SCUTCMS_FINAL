package com.scutcms.web.Servlets;

import com.scutcms.DAO.entity.Procurement;
import com.scutcms.authentication.Service.TokenService;
import com.scutcms.purchased.Service.ProcurementService;
import com.scutcms.util.JsonHelper;
import net.sf.json.JSONObject;
import net.sf.json.groovy.JsonGroovyBuilder;
import net.sf.json.util.JSONStringer;
import org.hibernate.PersistentObjectException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by loong on 16-7-3.
 */
@WebServlet(name = "ProcurementServlet", urlPatterns = "/ProcurementManagement")
public class ProcurementServlet extends HttpServlet {
    /**
     * 增加采购项目
     * @param request
     * request格式<br>
     *     {<br>
     *         "rootUser":rootUser,<br>
     *         "token":token,<br>
     *         "productName":productName,<br>
     *         "unit_price":double,<br>
     *         "amount":double,<br>
     *         "procureDate":yyyy-mm-dd<br>
     *     }<br>
     * @param response
     * response格式<br>
     *     {<br>
     *         "state":"SUCCESS"<br>
     *         //"SUCCESS"表示增加成功，"FAIL"表示增加失败<br>
     *     }<br>
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TokenService tokenService=new TokenService();
        JSONObject jsonObject= JsonHelper.requestToJson(request);
        JSONStringer stringer=new JSONStringer();

        String rootUser=jsonObject.getString("rootUser");
        String token=jsonObject.getString("token");
        if(tokenService.isTokenValidforUser(rootUser,token)){
            ProcurementService procurementService=new ProcurementService();
            Procurement procurement=new Procurement();
            String productName=jsonObject.getString("productName");
            Double unit_price=Double.parseDouble(jsonObject.getString("unit_price"));
            Double amount=Double.parseDouble(jsonObject.getString("amount"));
            String dateString=jsonObject.getString("procureDate");
            DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date procureDate = fmt.parse(dateString);
                procurement.setProcureDate(procureDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            procurement.setAmount(amount);
            procurement.setProductName(productName);
            procurement.setUnit_price(unit_price);
            procurement.setUsername(rootUser);
            if(procurementService.addProcurement(procurement))
                stringer.object().key("state").value("SUCCESS").endObject();
            else stringer.object().key("state").value("FAIL").endObject();
        }
        else stringer.object().key("state").value("FAIL").endObject();
        JsonHelper.JsonToResponse(stringer,response);
    }

    /**
     * 获得所有采购条目
     * @param request
     * request格式<br>
     *     {<br>
     *         "rootUser":rootUser,<br>
     *         "token":token<br>
     *     }<br>
     * @param response
     * response格式<br>
     *     {<br>
     *         "state":"SUCCESS",
     *         "procurement":[<br>
     *             {<br>
     *                 "procureDate":yyyy-mm-dd,<br>
     *                 "username":username,<br>
     *                 "amount":double,<br>
     *                 "productName":productName,<br>
     *                 "unit_price":double,<br>
     *             }<br>
     *             ...<br>
     *          ]<br>
     *     }<br>
     *         或者{"state":"FAIL"}即失败<br>
     *         或者{"state":"NULL"}即数据为空<br>
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TokenService tokenService=new TokenService();
        String json = URLDecoder.decode(request.getQueryString(),"utf-8");
        JSONObject jsonObject=JSONObject.fromObject(json);
        JSONStringer jsonStringer=new JSONStringer();

        String rootUser=jsonObject.getString("rootUser");
        String token=jsonObject.getString("token");
        if(tokenService.isTokenValidforUser(rootUser,token)){
            ProcurementService procurementService=new ProcurementService();
            Procurement procurement=new Procurement();
            ArrayList<Procurement>procurements=procurementService.getAllProcurement();
            if(procurements.isEmpty()){
                jsonStringer.object();
                jsonStringer.key("state").value("NULL");
                jsonStringer.endObject();
            }
            else{
                try{
                    jsonStringer.object();
                    jsonStringer.key("state").value("SUCCESS");
                    jsonStringer.key("procurement");
                    jsonStringer.array();
                    Iterator iterator = procurements.iterator();
                    while (iterator.hasNext()) {
                        procurement=(Procurement) iterator.next();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        String procureDate = sdf.format(procurement.getProcureDate());
                        jsonStringer.object();
                        jsonStringer.key("procureDate").value(procureDate)
                                .key("username").value(procurement.getUsername())
                                .key("amount").value(procurement.getAmount())
                                .key("productName").value(procurement.getProductName())
                                .key("unit_price").value(procurement.getUnit_price());
                        jsonStringer.endObject();
                    }
                    jsonStringer.endArray();
                    jsonStringer.endObject();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        else jsonStringer.object().key("state").value("FAIL").endObject();
        JsonHelper.JsonToResponse(jsonStringer,response);
    }

    /**
     * 修改采购信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TokenService tokenService=new TokenService();
        JSONObject jsonObject= JsonHelper.requestToJson(req);
        JSONStringer stringer=new JSONStringer();

        String rootUser=jsonObject.getString("rootUser");
        String token=jsonObject.getString("token");
        if(tokenService.isTokenValidforUser(rootUser,token)){
            ProcurementService procurementService=new ProcurementService();
            Procurement procurement=new Procurement();
            String productName=jsonObject.getString("productName");
            Double unit_price=Double.parseDouble(jsonObject.getString("unit_price"));
            Double amount=Double.parseDouble(jsonObject.getString("amount"));
            String dateString=jsonObject.getString("procureDate");
            DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date procureDate = fmt.parse(dateString);
                procurement.setProcureDate(procureDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            procurement.setAmount(amount);
            procurement.setProductName(productName);
            procurement.setUnit_price(unit_price);
            procurement.setUsername(rootUser);
            if(procurementService.updateProcurement(procurement)){
                stringer.object().key("state").value("SUCCESS").endObject();
            }
            else stringer.object().key("state").value("FAIL").endObject();
        }
        else stringer.object().key("state").value("FAIL").endObject();
        JsonHelper.JsonToResponse(stringer,resp);
    }

    /**
     * 删除采购信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TokenService tokenService=new TokenService();
        JSONObject jsonObject= JsonHelper.requestToJson(req);
        JSONStringer stringer=new JSONStringer();

        String rootUser=jsonObject.getString("rootUser");
        String token=jsonObject.getString("token");

        if(tokenService.isTokenValidforUser(rootUser,token)){
            ProcurementService procurementService=new ProcurementService();
            Procurement procurement=new Procurement();
            String productName=jsonObject.getString("productName");
            procurement.setProductName(productName);
            if(procurementService.deleteProcurement(procurement)){
                stringer.object().key("state").value("SUCCESS").endObject();
            }
            else stringer.object().key("state").value("FAIL").endObject();
        }
        else stringer.object().key("state").value("FAIL").endObject();
        JsonHelper.JsonToResponse(stringer,resp);
    }
}

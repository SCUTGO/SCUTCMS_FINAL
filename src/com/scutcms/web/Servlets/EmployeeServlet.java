package com.scutcms.web.Servlets;

import com.scutcms.DAO.entity.Employee;
import com.scutcms.authentication.Service.TokenService;
import com.scutcms.personnel.EmployeeService;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by loong on 16-6-19.
 */

/**
 * Description:职工信息相关操作
 *
 */
@WebServlet(name = "EmployeeServlet",urlPatterns = "/EmployeeManagement")
public class EmployeeServlet extends HttpServlet {
    /**
     * 增加职员信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject jsonObject=JsonHelper.requestToJson(request);
        EmployeeService employeeService=new EmployeeService();
        TokenService tokenService=new TokenService();
        Employee employee=new Employee();
        JSONStringer stringer=new JSONStringer();

        String rootUser=jsonObject.getString("rootUser");
        String token=jsonObject.getString("token");
        if(tokenService.isTokenValidforUser(rootUser,token)){
            String employeeID=jsonObject.getString("employeeID");
            String name=jsonObject.getString("name");
            String sex=jsonObject.getString("sex");
            int age=Integer.parseInt(jsonObject.getString("age"));
            String address=jsonObject.getString("address");
            String telephone=jsonObject.getString("telephone");
            String positionName=jsonObject.getString("positionName");
            String sfzh=jsonObject.getString("sfzh");

            String enter_time=jsonObject.getString("enter_time");
            DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = fmt.parse(enter_time);
                employee.setEnter_time(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            employee.setTelephone(telephone);
            employee.setAddress(address);
            employee.setAge(age);
            employee.setEmployeeId(employeeID);
            employee.setPositionName(positionName);
            employee.setName(name);
            employee.setSex(sex);
            employee.setSfzh(sfzh);

            if(employeeService.insert(employee))
                stringer.object().key("state").value("SUCCESS").endObject();
            else stringer.object().key("state").value("FAIL").endObject();
        }
        else{
            stringer.object().key("state").value("FAIL").endObject();
        }
        System.out.println(stringer.toString());
        JsonHelper.JsonToResponse(stringer,response);
    }

    /**
     * 获取职员信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmployeeService employeeService=new EmployeeService();
        TokenService tokenService=new TokenService();
        Employee employee=new Employee();

        String json= URLDecoder.decode(request.getQueryString(),"utf-8");
        JSONObject jsonObject=JSONObject.fromObject(json);


        JSONStringer jsonStringer=new JSONStringer();

        String rootUser= jsonObject.getString("rootUser");
        String token=jsonObject.getString("token");
        if(tokenService.isTokenValidforUser(rootUser,token)){
            ArrayList<Employee>employees=employeeService.getAllEmployee();
            if(employees.isEmpty()){
                jsonStringer.object();
                jsonStringer.key("state").value("NULL");
                jsonStringer.endObject();
            }
            else{
                try{
                    jsonStringer.object();
                    jsonStringer.key("state").value("SUCCESS");
                    jsonStringer.key("employees");
                    jsonStringer.array();
                    Iterator iterator = employees.iterator();
                    while (iterator.hasNext()) {
                        employee=(Employee) iterator.next();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        String enterTime = sdf.format(employee.getEnter_time());
                        jsonStringer.object();
                        jsonStringer.key("employeeID").value(employee.getEmployeeId())
                                .key("telephone").value(employee.getTelephone())
                                .key("address").value(employee.getAddress())
                                .key("age").value(employee.getAge())
                                .key("enter_time").value(enterTime)
                                .key("name").value(employee.getName())
                                .key("positionName").value(employee.getPositionName())
                                .key("sex").value(employee.getSex())
                                .key("sfzh").value(employee.getSfzh());
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
     * 删除职员
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doDelete(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        JSONObject object=JsonHelper.requestToJson(request);
        TokenService tokenService=new TokenService();
        EmployeeService employeeService=new EmployeeService();
        JSONStringer stringer=new JSONStringer();
        Employee employee=new Employee();

        String rootUser= object.getString("rootUser");
        String token=object.getString("token");
        if(tokenService.isTokenValidforUser(rootUser,token)){
            String employeeId=object.getString("employeeID");
            employee.setEmployeeId(employeeId);

            if(employeeService.delete(employee)){
                stringer.object().key("state").value("SUCCESS").endObject();
            }
            else stringer.object().key("state").value("FAIL").endObject();
        }
        else stringer.object().key("state").value("FAIL").endObject();
        JsonHelper.JsonToResponse(stringer,response);
    }

    /**
     * 修改职员信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPut(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        JSONObject jsonObject=JsonHelper.requestToJson(request);
        TokenService tokenService=new TokenService();
        EmployeeService employeeService=new EmployeeService();
        JSONStringer stringer=new JSONStringer();
        Employee employee=new Employee();

        String rootUser=jsonObject.getString("rootUser");
        String token=jsonObject.getString("token");
        if(tokenService.isTokenValidforUser(rootUser,token)){
            String employeeID=jsonObject.getString("employeeID");
            String name=jsonObject.getString("name");
            String sex=jsonObject.getString("sex");
            int age=Integer.parseInt(jsonObject.getString("age"));
            String address=jsonObject.getString("address");
            String telephone=jsonObject.getString("telephone");
            String positionName=jsonObject.getString("positionName");
            String sfzh=jsonObject.getString("sfzh");

            String enter_time=jsonObject.getString("enter_time");
            DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = fmt.parse(enter_time);
                employee.setEnter_time(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            employee.setTelephone(telephone);
            employee.setAddress(address);
            employee.setAge(age);
            employee.setEmployeeId(employeeID);
            employee.setPositionName(positionName);
            employee.setName(name);
            employee.setSex(sex);
            employee.setSfzh(sfzh);

            if(employeeService.update(employee)){
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
}

package com.scutcms.util;

import antlr.collections.ASTEnumeration;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONStringer;
import net.sf.json.util.JSONTokener;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * Created by loong on 16-7-3.
 */
public class JsonHelper {
    public static JSONObject requestToJson(HttpServletRequest request) throws UnsupportedEncodingException {
        String acceptjson = "";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (ServletInputStream) request.getInputStream(), "utf-8"));
            StringBuffer sb = new StringBuffer("");
            String temp;
            while ((temp = br.readLine()) != null) {
                sb.append(temp);
            }
            br.close();
            acceptjson = sb.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(acceptjson);
        JSONObject jsonObject = (JSONObject)new JSONTokener(acceptjson).nextValue();
        return jsonObject;
    }

    public static boolean JsonToResponse(JSONStringer stringer, HttpServletResponse response) throws IOException {
        response.getOutputStream().write(stringer.toString().getBytes("UTF-8"));
        response.setContentType("application/json");
        response.setHeader("Access-Control-Allow-Origin","*");
        return true;
    }
}

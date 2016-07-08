package com.scutcms.web.Filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by loong on 16-6-19.
 */

/**
 * Decription:此过滤器用来解决全站中文乱码问题
 * @see Filter
 */
@WebFilter(filterName = "CharacterEncodingFilter",urlPatterns = "/*")
public class CharacterEncodingFilter implements Filter {
    private FilterConfig filterConfig=null;
    private String defaultCharset="UTF-8"; //默认字符编码
    public void destroy() {
        filterConfig=null;
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request=(HttpServletRequest)req;
        HttpServletResponse response=(HttpServletResponse)resp;
        String charset=filterConfig.getInitParameter("charset");
        if(charset==null){
            charset=defaultCharset;
        }
        request.setCharacterEncoding(charset);
        response.setCharacterEncoding(charset);
        response.setContentType("text/html;charset="+charset);

        MyCharacterEncodingRequest requestWrapper=new MyCharacterEncodingRequest(request);
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        this.filterConfig=config;
    }

}


class MyCharacterEncodingRequest extends HttpServletRequestWrapper {
    private HttpServletRequest request;
    public MyCharacterEncodingRequest(HttpServletRequest request){
        super(request);
        this.request=request;
    }

    /**
     * 重写getParameter方法
     * @see HttpServletRequestWrapper {@link #getParameter(String)}
     */
    @Override
    public String getParameter(String name){
        try{
            //获取参数的值
            String value=this.getParameter(name);
            if(value==null){
                return null;
            }
            //如果不是以get方式提交数据的，直接返回获取到的值
            if(!this.request.getMethod().equalsIgnoreCase("get")){
                return value;
            }
            else{
                //如果是以get方式提交数据的，对获取到的值进行转码处理
                value=new String(value.getBytes("ISO8859-1"),this.request.getCharacterEncoding());
                return value;
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}

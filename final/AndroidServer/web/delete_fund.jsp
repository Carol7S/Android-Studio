<%@page import="df.cn.edu.zafu.FundBean"%>
<%@ page import="df.cn.edu.zafu.FundInfo" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%

    String name = request.getParameter("name");
    if(name!=null && name.length()>0){
        name = URLDecoder.decode(name,"utf-8");
    }

// 修改
    FundBean bean = new FundBean();
    int count = bean.Delete(name);
    out.println(count);
%>
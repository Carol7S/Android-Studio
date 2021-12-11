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
    String value = request.getParameter("value");
    if(value!=null && value.length()>0){
        value = URLDecoder.decode(value,"utf-8");
    }
    String size = request.getParameter("size");
    if(size!=null && size.length()>0){
        size = URLDecoder.decode(size,"utf-8");
    }
    String time = request.getParameter("time");
    if(time!=null && time.length()>0){
        time = URLDecoder.decode(time,"utf-8");
    }

    String DailyIncrease = request.getParameter("DailyIncrease");
    if(DailyIncrease!=null && DailyIncrease.length()>0){
        DailyIncrease = URLDecoder.decode(DailyIncrease,"utf-8");
    }
    String WeeklyIncrease = request.getParameter("WeeklyIncrease");
    if(WeeklyIncrease!=null && WeeklyIncrease.length()>0){
        WeeklyIncrease = URLDecoder.decode(WeeklyIncrease,"utf-8");
    }
    String MonthlyIncrease = request.getParameter("MonthlyIncrease");
    if(MonthlyIncrease!=null && MonthlyIncrease.length()>0){
        MonthlyIncrease = URLDecoder.decode(MonthlyIncrease,"utf-8");
    }
    String YearlyIncrease = request.getParameter("YearlyIncrease");
    if(YearlyIncrease!=null && YearlyIncrease.length()>0){
        YearlyIncrease = URLDecoder.decode(YearlyIncrease,"utf-8");
    }

    String holdNumStr = request.getParameter("holdNum");

// 字符串转成int
    int holdNum = -1;
    if(holdNumStr != null && holdNumStr.length() > 0){
        holdNum = Integer.parseInt(holdNumStr);
    }


    FundInfo info = new FundInfo(name, value, size, time ,DailyIncrease, WeeklyIncrease, MonthlyIncrease, YearlyIncrease, holdNum);
// 修改
    FundBean bean = new FundBean();
    int count = bean.Update(info, name);
    out.println(count);
%>
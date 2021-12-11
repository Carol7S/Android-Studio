<%@page import="java.util.Iterator"%>
<%@page import="df.cn.edu.zafu.FundBean"%>
<%@page import="df.cn.edu.zafu.FundInfo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    FundBean bean = new FundBean();
    ArrayList<FundInfo> data = bean.GetAll();
    StringBuilder builder = new StringBuilder();
    Iterator<FundInfo> iter = data.iterator();
    out.println("[");
    while(iter.hasNext()) {
        FundInfo info = iter.next();
        builder.append(info.toString()).append("\n");
//        String fundStr = String.format("%d, %s, %s, %s, %s, %s, %s, %s, %s, %d",
//                info.getId(), info.getname(), info.getvalue(), info.getsize(), info.gettime(), info.getDailyIncrease(), info.getWeeklyIncrease(), info.getMonthlyIncrease(), info.getYearlyIncrease(), info.getholdNum() );
        out.println("{");
        // 		"id":"1"
        out.println("\"id\":\"" + info.getId() + "\"");
        //
        out.println(", \"name\":\"" + info.getname() + "\"");
        //
        out.println(", \"value\":\"" + info.getvalue() + "\"");
        //
        out.println(", \"size\":\"" + info.getsize() + "\"");
        //
        out.println(", \"time\":\"" + info.gettime() + "\"");
        //
        out.println(", \"DailyIncrease\":\"" + info.getDailyIncrease() + "\"");
        out.println(", \"WeeklyIncrease\":\"" + info.getWeeklyIncrease() + "\"");
        out.println(", \"MonthlyIncrease\":\"" + info.getMonthlyIncrease() + "\"");
        out.println(", \"YearlyIncrease\":\"" + info.getYearlyIncrease() + "\"");
        //
        out.println(", \"holdNum\":\"" + info.getholdNum() + "\"");
        // 		}
        out.println("}");
        // 		, // 最后一个是没有的
        if( iter.hasNext() ){
            out.println(",");
        }

    }
    out.println("]");
//    System.out.printf(builder.toString());
%>
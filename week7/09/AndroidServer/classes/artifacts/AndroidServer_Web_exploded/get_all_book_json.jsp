<%@page import="java.util.Iterator"%>
<%@page import="df.cn.edu.zafu.BookBean"%>
<%@page import="df.cn.edu.zafu.BookInfo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    BookBean bean = new BookBean();
    ArrayList<BookInfo> data = bean.GetAll();
    StringBuilder builder = new StringBuilder();
    Iterator<BookInfo> iter = data.iterator();
    out.println("[");
    while(iter.hasNext()) {
        BookInfo info = iter.next();
        builder.append(info.toString()).append("\n");
        String bookStr = String.format("%d, %s,%s,%d,%f",
                info.getId(), info.getName(), info.getAuthor(), info.getPages(), info.getPrice() );
        out.println("{");
        // 		"id":"2"
        out.println("\"id\":\"" + info.getId() + "\"");
        // 		, "name":"史记"
        out.println(", \"name\":\"" + info.getName() + "\"");
        // 		, "author":"司马迁"
        out.println(", \"author\":\"" + info.getAuthor() + "\"");
        // 		, "pages":"800"
        out.println(", \"pages\":\"" + info.getPages() + "\"");
        // 		, "price":"105.3"
        out.println(", \"price\":\"" + info.getPrice() + "\"");
        // 		}
        out.println("}");
        // 		, // 最后一个是没有的
        if( iter.hasNext() ){
            out.println(",");
        }

    }
    out.println("]");
    System.out.printf(builder.toString());
%>
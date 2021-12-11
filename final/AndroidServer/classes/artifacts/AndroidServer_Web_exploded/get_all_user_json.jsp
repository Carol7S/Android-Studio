<%@page import="java.util.Iterator"%>
<%@page import="df.cn.edu.zafu.UserBean"%>
<%@page import="df.cn.edu.zafu.UserInfo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    UserBean bean = new UserBean();
    ArrayList<UserInfo> data = bean.GetAll();
    StringBuilder builder = new StringBuilder();
    Iterator<UserInfo> iter = data.iterator();
    out.println("[");
    while(iter.hasNext()) {
        UserInfo info = iter.next();
        builder.append(info.toString()).append("\n");
//        String bookStr = String.format("%d, %s,%s,%s",
//                info.getId(), info.getUsername(), info.getPassword(), info.getPermission() );
        out.println("{");

        out.println("\"id\":\"" + info.getId() + "\"");

        out.println(", \"username\":\"" + info.getUsername() + "\"");

        out.println(", \"password\":\"" + info.getPassword() + "\"");

        out.println(", \"permission\":\"" + info.getPermission() + "\"");

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
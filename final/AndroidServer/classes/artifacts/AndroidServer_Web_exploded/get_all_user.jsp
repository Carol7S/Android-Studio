<%@ page import="df.cn.edu.zafu.UserBean" %>
<%@ page import="df.cn.edu.zafu.UserInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.awt.print.Book" %><%--
  Created by IntelliJ IDEA.
  User: zhangzhechao
  Date: 2021/11/20
  Time: 12:44 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    UserBean bean = new UserBean();
    List<UserInfo> data = bean.GetAll();//建立一个列表
    StringBuilder builder = new StringBuilder();//建立一个字符串
    Iterator<UserInfo> iter = data.iterator();//建立一个迭代器，读取列表的数据
    while(iter.hasNext()){
        builder.append(iter.next()).append("\n");//把迭代器里的数据放到字符串里
    }
    System.out.println(builder.toString());
    out.println(builder.toString());
%>
</body>
</html>

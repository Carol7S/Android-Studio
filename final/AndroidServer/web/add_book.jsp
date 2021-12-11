<%@page import="java.util.Iterator"%>
<%@page import="df.cn.edu.zafu.BookInfo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="df.cn.edu.zafu.BookBean"%>
<%@ page import="java.net.URLDecoder" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%
	String name = request.getParameter("name");
	if(name!=null && name.length()>0){
		name = URLDecoder.decode(name,"utf-8");
	}
	String author = request.getParameter("author");
	if(author!=null && author.length()>0){
		author = URLDecoder.decode(author,"utf-8");
	}
// 页数 
	int pages = 0;
	String pagesStr = request.getParameter("pages");
	if(pagesStr != null && pagesStr.length() > 0){
		pages = Integer.parseInt(pagesStr);
	}
// 单价
	double price = 0;
	String priceStr = request.getParameter("price");
	if(priceStr != null && priceStr.length() > 0){
		price = Double.parseDouble(priceStr);
	}
	System.out.printf("author:%s, name: %s, pages: %d, price: %f\n", author, name, pages, price);


	BookInfo info = new BookInfo(name, author, pages, price );
//

	BookBean bean = new BookBean();
	int count = bean.Add(info);
	System.out.println(count);
	System.out.printf("一共写入%d条记录", count);
%>
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
while(iter.hasNext()) {
	BookInfo info = iter.next();
	builder.append(info.toString()).append("\n");
	String bookStr = String.format("%d, %s,%s,%d,%f", 
		info.getId(), info.getName(), info.getAuthor(), info.getPages(), info.getPrice() );
	// String bookStr = info.getId() + ", " + info.getAuthor() + ", " 
		// + info.getName() + ", " + info.getPages() + info.getPrice();
	out.println(bookStr);
}
System.out.printf(builder.toString());
%>
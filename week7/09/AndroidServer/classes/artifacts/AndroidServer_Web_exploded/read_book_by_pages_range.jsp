<%@page import="java.util.Iterator"%>
<%@page import="df.cn.edu.zafu.BookBean"%>
<%@page import="df.cn.edu.zafu.BookInfo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String minPageStr = request.getParameter("min_pages");
String maxPageStr = request.getParameter("max_pages");
int minPages = 0;
if(minPageStr != null && minPageStr.length() > 0){
	minPages = Integer.parseInt(minPageStr);
}
int maxPages = 0;
if(maxPageStr != null && maxPageStr.length() > 0){
	maxPages = Integer.parseInt(maxPageStr);
}
System.out.printf("minPages: %d, maxPages: %d", minPages, maxPages);


BookBean bean = new BookBean();
ArrayList<BookInfo> data = bean.GetByPagesRange(minPages, maxPages);
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
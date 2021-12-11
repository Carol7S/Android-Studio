<%@page import="java.util.Iterator"%>
<%@page import="df.cn.edu.zafu.UserInfo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="df.cn.edu.zafu.UserBean"%>
<%@ page import="java.net.URLDecoder" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%
    String username = request.getParameter("username");
    if(username!=null && username.length()>0){
        username = URLDecoder.decode(username,"utf-8");
    }

    String password = request.getParameter("password");
    if(password!=null && password.length()>0){
        password = URLDecoder.decode(password,"utf-8");
        password = MD5(password);
    }

    String permission = request.getParameter("permission");
    if(permission!=null && permission.length()>0){
        permission = URLDecoder.decode(permission,"utf-8");
    }

    System.out.printf(" username: %s, password:%s, permission: %s\n", username,password,permission);

    UserInfo info = new UserInfo(username, password, permission);
//

    UserBean bean = new UserBean();
    int count = bean.Add(info);
    System.out.println(count);
    System.out.printf("一共写入%d条记录", count);
%>

<%@ page import="java.security.MessageDigest"%>
<%!
    /**
     * MD5加密
     * @param plainText 要加密的字符串
     * @return
     */
    public String MD5(String plainText){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            //32位加密
            return buf.toString();
            // 16位的加密
            //return buf.toString().substring(8, 24);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
%>
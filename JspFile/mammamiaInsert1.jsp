<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>        
<%
	request.setCharacterEncoding("utf-8");
	String addrTag = request.getParameter("addrTag");
	String addrName = request.getParameter("addrName");
	String addrTel = request.getParameter("addrTel");
	String addrDetail = request.getParameter("addrDetail");	
    String addrNo = request.getParameter("addrNo");
		
//------
	String url_mysql = "jdbc:mysql://localhost/mammamia?serverTimezone=Asia/Seoul&characterEncoding=utf8&useSSL=false";
	String id_mysql = "root";
	String pw_mysql = "qwer1234";

	PreparedStatement ps = null;
	try{
	    Class.forName("com.mysql.jdbc.Driver");
	    Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
	    Statement stmt_mysql = conn_mysql.createStatement();
	
	    String A = "insert into addrlist (addrNo,addrName, addrTel, addrDetail, addrTag";
	    String B = ") values (?,?,?,?,?)";
	
	    ps = conn_mysql.prepareStatement(A+B);
         ps.setString(1, addrNo);
	    ps.setString(2, addrName);
	    ps.setString(3, addrTel);
	    ps.setString(4, addrDetail);
	    ps.setString(5, addrTag);
	    
	    ps.executeUpdate();
	
	    conn_mysql.close();
	} 
	
	catch (Exception e){
	    e.printStackTrace();
	}
	
%>


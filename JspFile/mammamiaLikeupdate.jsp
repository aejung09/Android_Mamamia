<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>        
<%
	request.setCharacterEncoding("utf-8");
	String name = request.getParameter("addrName");
	String tel = request.getParameter("addrTel");
	String addr = request.getParameter("addrAddr");
	String detail = request.getParameter("addrDetail");
	String tag = request.getParameter("addrTag");
	String num = request.getParameter("addrNo");
	String like = request.getParameter("addrLike");
	int intNum = Integer.parseInt(num);
	String addrImagePath = request.getParameter("addrImagePath");	
		
//------
	String url_mysql = "jdbc:mysql://database-2.cotrd7tmeavd.ap-northeast-2.rds.amazonaws.com/mammamia?serverTimezone=Asia/Seoul&characterEncoding=utf8&useSSL=false";
	String id_mysql = "root";
	String pw_mysql = "qwer1234";

	PreparedStatement ps = null;
	try{
	    Class.forName("com.mysql.jdbc.Driver");
	    Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
	    Statement stmt_mysql = conn_mysql.createStatement();
	
	    String A = "update addrlist set addrLike = ? where addrNo = ?";
	   
	
	    ps = conn_mysql.prepareStatement(A);
		ps.setString(1, like);
		ps.setString(2, num);


	    ps.executeUpdate();
	
	    conn_mysql.close();
	} 
	
	catch (Exception e){
	    e.printStackTrace();
	}
	
%>


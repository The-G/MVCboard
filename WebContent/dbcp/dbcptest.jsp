<%@page import="javax.sql.DataSource"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8">
<title>Insert title here</title>
<link rel="shortcut icon" href="/favicon.ico" />
</head>
<body>
<%
	Connection cn = null;
	try{
			Context context = new InitialContext();
			Context envContext = (Context)context.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/mydbcp");
// 			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/mydbcp");
			
			cn = ds.getConnection();
			out.println("연결성공");
	} catch(Exception e){
		e.printStackTrace();
	} finally {
		if(cn != null) try{cn.close();} catch(Exception e){}
	}
%>
</body>
</html>
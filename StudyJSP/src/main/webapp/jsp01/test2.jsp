<%@ page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- HTML 주석입니다. 이 주석은 웹브라우저 소스보기를 통해 확인이 가능합니다. -->
	<h1>test2.jsp</h1>
	<%--JSP 주석입니다. 이 주석은 웹브라우저 소스보기를 통해 확인이 불가능합니다.
	=> JSP 코드가 포함될 경우 HTML 주석으로는 처리가 불가능하므로
	   반드시 JSP 주석을 사용해야 한다!
	 --%>
	<%
	// 이 부분은 JSP 문서 내에서 자바 코드가 기술되는 부분으로
	// 웹브라우저에서 코드가 표시되지 않으며
	// 서버측에서 실행된 결과값만 전달되는 부분입니다.
	// 따라서, 자바에서 사용하는 주석도 사용 가능합니다.
	Date now = new Date();
	%>
	<h3>현재 시각 : <%=now %></h3>
	
	<hr>
	
	<!-- HTML 태그는 HTML 주석으로 처리하며, 실행 대상에서 제외 가능 -->
<!-- 	<h1>test2.jsp</h1> -->

	<!-- JSP 코드 부분은 HTML 주석으로 처리하더라도, 서버에서 이미 실행된 상태로 전송됨 -->
	<!-- 따라서, 코드 내용에 따라 차후에 오류가 발생할 수도 있음 -->
	<!-- <h3>현재 시각 : <%=now %></h3> -->
	<!-- 위의 코드는 웹브라우저에 표시되지는 않지만, now 부분의 현재 시각은 이미 전송된 상태 -->
	
	<%-- JSP 주석은 서버에서 해당 주석 부분 자체를 실행하지 않고(무시), 브라우저로 전송하지 않음 --%>
<%-- 	<h3>현재 시각 : <%=now %></h3> --%>
	<%-- 위의 코드는 웹브라우저에서도 보이지 않지만, 서버에서도 이미 실행 대상에서 제외됨 --%>
</body>
</html>
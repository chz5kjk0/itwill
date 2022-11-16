<%@page import="jsp09_jdbc_dao.Jsp8_2DAO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");

// 아무거나
// URL 파라미터로 전달받은 아이디 가져와서 변수에 저장
String id = request.getParameter("id");
// out.println(id);

// 회원 삭제를 위한 Jsp8_2DAO 객체의 delete() 메서드 호출
// 파라미터 : 아이디(id)    리턴타입 : int(deleteCount)
Jsp8_2DAO dao = new Jsp8_2DAO();
int deleteCount = dao.delete(id);

// 삭제 성공 시 select2.jsp 페이지로 이동
// 삭제 실패 시 자바스크립트를 사용하여 "삭제 실패!" 출력 후 이전페이지로 돌아가기
if(deleteCount > 0) { // 삭제 성공
	response.sendRedirect("select2.jsp");
} else {
	%>
	<scirpt>
		alert("회원 삭제 실패!");
		history.back();
	</scirpt>
	<%
}
%>


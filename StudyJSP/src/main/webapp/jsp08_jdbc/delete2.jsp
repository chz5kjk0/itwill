<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// URL 파라미터로 전달받은 아이디 가져와서 변수에 저장
String id = request.getParameter("id");
// out.println(id);

// jsp8_2 테이블에서 아이디가 일치하는 레코드 삭제
// 0단계.
String driver = "com.mysql.cj.jdbc.Driver";
String url = "jdbc:mysql://localhost:3306/study_jsp5";
String user = "root";
String password = "1234";

// 1단계. JDBC 드라이버 로드
Class.forName(driver);
// out.println("드라이버 로드");

// 2단계. DB 연결
Connection con = DriverManager.getConnection(url, user, password);
// out.println("DB 연결");

// 3단계. SQL 구문 작성 및 전달
String sql = "DELETE FROM jsp8_2 WHERE id=?";

PreparedStatement pstmt = con.prepareStatement(sql);

// 만능문자
pstmt.setString(1, id);

// 4단계. SQL 구문 실행 및 결과처리
int count = pstmt.executeUpdate();

// 삭제 성공 시 select2.jsp 페이지로 이동
// 삭제 실패 시 자바스크립트를 사용하여 "삭제 실패!" 출력 후 이전페이지로 돌아가기
if(count > 0) { // 삭제 성공
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


<%@page import="java.sql.Date"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function confirmDelete(id) {
		// confirm dialog 사용하여 "XXX 회원을 삭제하시겠습니까?" 확인 요청
		// => 결과값이 true 일 경우 location.href='delete2.jsp' 페이지 이동
		let result = confirm(id + "회원을 삭제하시겠습니까?");
		
		if(result) {
			location.href="delete2.jsp?id=" + id;
		}
		
	}
</script>

</head>
<body>
	<h1>회원 목록</h1>
	<table border="1">
		<tr>
			<th width="100">이름</th>
			<th width="100">아이디</th>
			<th width="200">E-Mail</th>
			<th width="50">성별</th>
			<th width="100">가입일</th>
			<th width="150"></th>
		</tr>
	
	<%
	// 인코딩 처리
	request.setCharacterEncoding("UTF-8");
	
	// 0단계. 필요한 문자열 저장
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/study_jsp5";
	String user = "root";
	String password = "1234";

	// 1단계. JDBC 드라이버 로드
	Class.forName(driver);

	// 2단계. DB 연결
	Connection con = DriverManager.getConnection(url, user, password);
	
	// 3단계. SQL 구문 작성 및 전달
	String sql = "SELECT * FROM jsp8_2";
	PreparedStatement pstmt = con.prepareStatement(sql);
	
	// 4단계. SQL 구문 실행 및 결과 처리
	ResultSet rs = pstmt.executeQuery();
	
	while(rs.next()) {
		String name = rs.getString("name");
		String id = rs.getString("id");
		String passwd = rs.getString("passwd");
		String jumin = rs.getString("jumin");
		String email = rs.getString("email");
		String job = rs.getString("job");
		String gender = rs.getString("gender");
		String hobby = rs.getString("hobby");
		String content = rs.getString("content");
		// 데이터베이스로의 날짜 정보를 사용할 경우 
		// java.sql.Date(날짜만) 또는 java.sql.TimeStamp(날짜&시각) 사용
		Date hire_date = rs.getDate("hire_date");
		
		// 테이블 내에 회원 목록 출력
		%>
		<tr>
			<td><%=name %></td>
			<td><%=id %></td>
			<td><%=email %></td>
			<td><%=gender %></td>
			<td><%=hire_date %></td>
			<td>
				<input type="button" value="상세정보" onclick="location.href='select2_detail.jsp?id=<%=id %>'">
				<input type="button" value="삭제" onclick="confirmDelete('<%=id %>')">
			</td>
		</tr>
	<%
	}
	%>
	</table>
	
</body>
</html>
package jsp09_jdbc_dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Jsp8_2DAO {

	// [제한자] 리턴타입 메서드명([매개변수...]) {}
	public int insert(Jsp8_2DTO dto) {
		System.out.println("jsp8_2DAO - insert()");
//		System.out.println(dto.getName());
		
		int insertCount = 0;
		
		// JDBC 작업 4단계.
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			// 1단계 & 2단계
			// => 공통으로 처리하는 JdbcUtil 객체의 getConnection() 메서드를 호출하여
			//    DB 연결 후 리턴되는 Connection 타입 객체를 리턴받아 저장
			// => 단, getConnection() 메서드는 static 메서드이므로
			//    JdbcUtil 클래스의 인스턴스 생성이 불필요
			con = JdbcUtil.getConnection();

			// jsp8_2 테이블에 1개 레코드에 해당하는 모든 데이터 저장
			// 단, 입사일(hire_date)는 SQL 구문의 now() 함수 사용하여 DB 서버의 현재 날짜, 시각정보를 사용
			String sql = "INSERT INTO jsp8_2 VALUES (?,?,?,?,?,?,?,?,?,now())";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getId());
			pstmt.setString(3, dto.getPasswd());
			pstmt.setString(4, dto.getJumin());
			pstmt.setString(5, dto.getEmail());
			pstmt.setString(6, dto.getJob());
			pstmt.setString(7, dto.getGender());
			pstmt.setString(8, dto.getHobby());
			pstmt.setString(9, dto.getContent());
			
			insertCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생!");
			e.printStackTrace();
		} finally {
			// DB 자원 반환(역순)
			// => JdbcUtil 클래스의 close() 메서드 호출하여 반환할 자원 전달
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
		
		return insertCount;
	}
	
	public ArrayList select() {
		System.out.println("Jsp8_2DAO - select()");

//		Jsp8_2DTO dto = new Jsp8_2DTO(); // while 문 안에서 선언 가능
		
		// 필요한 참조 데이터 선언
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList list = null;
				
		// JDBC 작업 4단계.
		try {
			// 1단계 & 2단계
			// => 공통으로 처리하는 JdbcUtil 객체의 getConnection() 메서드를 호출하여
			//    DB 연결 후 리턴되는 Connection 타입 객체를 리턴받아 저장
			con = JdbcUtil.getConnection();
			
			// 3단계. SQL 구문 작성 및 전달
			String sql = "SELECT * FROM jsp8_2";
			pstmt = con.prepareStatement(sql);
			
			// 4단계. SQL 구문 실행 및 결과 처리
			rs = pstmt.executeQuery();
			
			// 전체 레코드를 저장할 ArrayList 객체 생성
			list = new ArrayList();
			
			while(rs.next()) {
				// 1개 레코드 정보를 저장할 Jsp8_2DTO 객체 생성 후 데이터 저장
				Jsp8_2DTO dto = new Jsp8_2DTO();
				
				dto.setName(rs.getString("name"));
				dto.setId(rs.getString("id"));
				dto.setPasswd(rs.getString("passwd"));
				dto.setJumin(rs.getString("jumin"));
				dto.setEmail(rs.getString("email"));
				dto.setJob(rs.getString("job"));
				dto.setGender(rs.getString("gender"));
				dto.setHobby(rs.getString("hobby"));
				dto.setContent(rs.getString("content"));
				// 데이터베이스로의 날짜 정보를 사용할 경우 
				// java.sql.Date(날짜만) 또는 java.sql.TimeStamp(날짜&시각) 사용
				dto.setHire_date(rs.getDate("hire_date"));
				
				// 전체 레코드를 저장하는 ArrayList 객체(list)에 1개 레코드 저장
				list.add(dto);
				
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생!");
			e.printStackTrace();
		} finally {
			// DB 자원 반환(역순)
			// => JdbcUtil 클래스의 close() 메서드 호출하여 반환할 자원 전달
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
		
		return list; // select2.jsp
	}
	
	// 회원 상세정보 조회 - selectDetail()
	// => 파라미터 : 아이디(id)      리턴타입 : Jsp8_2DTO(dto)
	public Jsp8_2DTO selectDetail(String id) {
		System.out.println("Jsp8_2DAO - selectDetail()"); // 확인 완료
		System.out.println(id); // 확인 완료
		
		// 리턴타입 선언 및 초기화
		Jsp8_2DTO dto = null;
		
		// 참조 변수값 초기화
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// JDBC 4단계
		try {
			// 1단계 & 2단계
			// => 공통으로 처리하는 JdbcUtil 객체의 getConnection() 메서드를 호출하여
			//    DB 연결 후 리턴되는 Connection 타입 객체를 리턴받아 저장
			con = JdbcUtil.getConnection();
			
			// 아이디가 일치하는 레코드 조회
			String sql = "SELECT * FROM jsp8_2 WHERE id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
						
			if(rs.next()) {
				// 1개 레코드를 저장할 Jsp8_2 타입 객체 생성
				dto = new Jsp8_2DTO();
				
				// DTO 객체에 1개 레코드 데이터 저장
				dto.setName(rs.getString("name"));
				dto.setId(rs.getString("id"));
//				dto.setPasswd(rs.getString(3)); // 패스워드는 전달 대상에서 제외
				dto.setJumin(rs.getString("jumin"));
				dto.setEmail(rs.getString("email"));
				dto.setJob(rs.getString("job"));
				dto.setGender(rs.getString("gender"));
				dto.setHobby(rs.getString("hobby"));
				dto.setContent(rs.getString("content"));
				dto.setHire_date(rs.getDate("hire_date"));
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생!");
			e.printStackTrace();
		} finally {
			// DB 자원 반환(역순)
			// => JdbcUtil 클래스의 close() 메서드 호출하여 반환할 자원 전달
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);			
		}
		
		// 리턴문
		return dto;
		
	}
	
	// 회원 삭제 작업 수행할 delete() 메서드
	// => 파라미터 : 아이디     리턴타입 : int(deleteCount)
	public int delete(String id) {
		System.out.println("jsp8_2DAO - delete()"); // delete() 메서드 호출
		System.out.println(id); // 파라미터 확인
		
		int deleteCount = 0;
		
		// 객체 선언 및 초기화
		Connection con = null;
		PreparedStatement pstmt = null;
		
		// jsp8_2 테이블에서 아이디가 일치하는 레코드 삭제
		try {
			// 1단계 & 2단계
			// => 공통으로 처리하는 JdbcUtil 객체의 getConnection() 메서드를 호출하여
			//    DB 연결 후 리턴되는 Connection 타입 객체를 리턴받아 저장
			con = JdbcUtil.getConnection();
			
			// 3단계. SQL 구문 작성 및 전달
			String sql = "DELETE FROM jsp8_2 WHERE id=?";

			pstmt = con.prepareStatement(sql);

			// 만능문자
			pstmt.setString(1, id);

			// 4단계. SQL 구문 실행 및 결과처리
			deleteCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생!");
			e.printStackTrace();
		} finally {
			// DB 자원 반환(역순)
			// => JdbcUtil 클래스의 close() 메서드 호출하여 반환할 자원 전달
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
		
		return deleteCount;
		
	}
	
	// 아이디, 패스워드 판별 작업 수행하는 login() 메서드 정의
	// => 파라미터 : 아이디, 패스워드     리턴타입 : boolean(isLoginSuccess)
	public boolean login(String id, String passwd) {
		System.out.println("jsp8_2DAO - login()");
		System.out.println(id + ", " + passwd);
		
		// 로그인 판별 결과를 저장할 변수 선언
		boolean isLoginSuccess = false;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 1단계&2단계
		con = JdbcUtil.getConnection();
		
		try {
			
			// 아이디와 패스워드가 일치하는 레코드 조회
			String sql = "SELECT * FROM jsp8_2 WHERE id=? AND passwd=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id); // dto.getId()
			pstmt.setString(2, passwd); // dto.getPasswd()
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // 조회 결과가 있을 경우 = 로그인 성공
				// 로그인 판별 결과 저장 변수(isLoginSuccess) 를 true 로 변경
				isLoginSuccess = true;
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류!");
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);			
		}
		
		// 로그인 판별 결과 리턴 => loginPro2.jsp
		return isLoginSuccess;
	}
	
	
}

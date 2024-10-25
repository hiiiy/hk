package com.hk.board.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hk.board.dtos.UserDto;

//Dao 객체:DataBase에 접근하는 객체
public class UserDao {
	//1단계:드라이버 로딩
	public UserDao() {
		//강제 객체 생성
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("1단계:드라이버로딩 성공");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("1단계:드라이버로딩 실패");
		}
	}
	//DB연결을 위한 정보 정의
	//회원목록 조회기능
	public List<UserDto> getAllUser(){
		List<UserDto> list=new ArrayList<>();//회원정보들을 저장할 객체
		//2단계:DB연결
		//url:DB소프트웨어마다 약간씩 다를 수 있음
		String url="jdbc:mariadb://localhost:3306/hkedu";
		String user="root";
		String password="8538";
		Connection conn=null;//DB연결할때 사용할 객체
		PreparedStatement psmt=null;//쿼리 준비 및 실행을 위한 객체
		ResultSet rs=null;//쿼리 실행 결과를 받을 객체
		//실행쿼리 작성
		String sql="SELECT 	userID ,"
				+ "			NAME ,"
				+ "			birthYear ,"
				+ "			addr ,"
				+ "			mobile1 ,"
				+ "			mobile2 ,"
				+ "			height ,"
				+ "			mDate "
				+ " FROM usertbl ";
		try {
			//DB에 연결된 상태
			conn=DriverManager.getConnection(url, user, password);
			System.out.println("2단계:DB연결성공");
			//쿼리가 준비된 상태
			psmt=conn.prepareStatement(sql);
			System.out.println("3단계:쿼리준비성공");
			//쿼리실행
			rs=psmt.executeQuery();//결과를 반환해줌
			System.out.println("4단계:쿼리실행성공");
			//실행결과받기:DB 데이터를 JAVA에서 사용할 수 있게 변환해서 저장
			while(rs.next()) {
				//실행된 쿼리의 컬럼 순서대로 작성해야 한다.
				UserDto dto=new UserDto();
				dto.setUserID(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setBirthYear(rs.getInt(3));
				dto.setAddr(rs.getString(4));
				dto.setMobile1(rs.getString(5));
				dto.setMobile2(rs.getString(6));
				dto.setHeight(rs.getInt(7));
				dto.setmDate(rs.getDate(8));
				list.add(dto);
			}
			System.out.println("5단계:쿼리결과받기 성공");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("JDBC실패:"+getClass());
		}finally {
			try {
				if(rs!=null) {
					rs.close();
				}
				if(psmt!=null) {
					psmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
				System.out.println("6단계:DB닫기성공");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("6단계:DB닫기실패");
			}
		}
		return list;
	}
	//회원등록하ㅣ:insert 문,파라미터(회원정보)
	public boolean insertUser(UserDto dto) {
		int count=0;//쿼리성공여부
		//url:DB소프트웨어마다 약간씩 다를 수 있음
		String url="jdbc:mariadb://localhost:3306/hkedu";
		String user="root";
		String password="8538";
		Connection conn=null;//DB연결할때 사용할 객체
		PreparedStatement psmt=null;//쿼리 준비 및 실행을 위한 객체
		String sql="INSERT INTO USERTBL "+" VALUES(?,?,?,?,?,?,?,SYSDATE())";
		try {
			conn=DriverManager.getConnection(url,user,password);
			System.out.println("2단계:DB연결성공");
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, dto.getUserID());
			psmt.setString(2, dto.getName());
			psmt.setInt(3, dto.getBirthYear());
			psmt.setString(4, dto.getAddr());
			psmt.setString(5, dto.getMobile1());
			psmt.setString(6, dto.getMobile2());
			psmt.setInt(7,dto.getHeight());
			System.out.println("3단계:쿼리준비성공");
			count=psmt.executeUpdate();//반환값:업데이트된 행의 개수를 반환(int)
			System.out.println("4단계:쿼리실행성공");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("JDBC실패:"+getClass());
		}finally {
			try {
				if(psmt!=null) {
					psmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
				System.out.println("6단계:DB닫기성공");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("6단계:DB닫기실패");
			}
		}
		return count>0?true:false;
	}
	//회원 수정하기:update 문,파라미터
	public boolean updateUser(UserDto dto) {
		int count=0;//쿼리성공여부
		//url:DB소프트웨어마다 약간씩 다를 수 있음
		String url="jdbc:mariadb://localhost:3306/hkedu";
		String user="root";
		String password="8538";
		Connection conn=null;//DB연결할때 사용할 객체
		PreparedStatement psmt=null;//쿼리 준비 및 실행을 위한 객체
		String sql="UPDATE usertbl SET 	NAME = ?,"
				+ "							birthYear = ?,"
				+ "							addr = ? ,"
				+ "							mobile1 = ? ,"
				+ "							mobile2 = ? ,"
				+ "							height = ? "
				+ " WHERE userID = ? ";
		try {
			conn=DriverManager.getConnection(url,user,password);
			System.out.println("2단계:DB연결성공");
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, dto.getName());
			psmt.setInt(2, dto.getBirthYear());
			psmt.setString(3, dto.getAddr());
			psmt.setString(4, dto.getMobile1());
			psmt.setString(5, dto.getMobile2());
			psmt.setInt(6,dto.getHeight());
			psmt.setString(7, dto.getUserID());
			System.out.println("3단계:쿼리준비성공");
			count=psmt.executeUpdate();//반환값:업데이트된 행의 개수를 반환(int)
			System.out.println("4단계:쿼리실행성공");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("JDBC실패:"+getClass());
		}finally {
			try {
				if(psmt!=null) {
					psmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
				System.out.println("6단계:DB닫기성공");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("6단계:DB닫기실패");
			}
		}
		return count>0?true:false;
	}
	//회원상세정보조회
	public UserDto getUser(String userId){
		UserDto dto=new UserDto();
		//2단계:DB연결
		//url:DB소프트웨어마다 약간씩 다를 수 있음
		String url="jdbc:mariadb://localhost:3306/hkedu";
		String user="root";
		String password="8538";
		Connection conn=null;//DB연결할때 사용할 객체
		PreparedStatement psmt=null;//쿼리 준비 및 실행을 위한 객체
		ResultSet rs=null;//쿼리 실행 결과를 받을 객체
		//실행쿼리 작성
		String sql="SELECT 	userID ,"
				+ "			NAME ,"
				+ "			birthYear ,"
				+ "			addr ,"
				+ "			mobile1 ,"
				+ "			mobile2 ,"
				+ "			height ,"
				+ "			mDate "
				+ " FROM usertbl "
				+ " WHERE USERID = ? ";
		try {
			//DB에 연결된 상태
			conn=DriverManager.getConnection(url, user, password);
			System.out.println("2단계:DB연결성공");
			//쿼리가 준비된 상태
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, userId);
			System.out.println("3단계:쿼리준비성공");
			//쿼리실행
			rs=psmt.executeQuery();//결과를 반환해줌
			System.out.println("4단계:쿼리실행성공");
			//실행결과받기:DB 데이터를 JAVA에서 사용할 수 있게 변환해서 저장
			while(rs.next()) {
				//실행된 쿼리의 컬럼 순서대로 작성해야 한다.				
				dto.setUserID(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setBirthYear(rs.getInt(3));
				dto.setAddr(rs.getString(4));
				dto.setMobile1(rs.getString(5));
				dto.setMobile2(rs.getString(6));
				dto.setHeight(rs.getInt(7));
				dto.setmDate(rs.getDate(8));
			}
			System.out.println("5단계:쿼리결과받기 성공");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("JDBC실패:"+getClass());
		}finally {
			try {
				if(rs!=null) {
					rs.close();
				}
				if(psmt!=null) {
					psmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
				System.out.println("6단계:DB닫기성공");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("6단계:DB닫기실패");
			}
		}
		return dto;
	}
	//회원정보삭제하기
	public boolean deleteUser(String userId) {
		int count=0;//쿼리성공여부
		//url:DB소프트웨어마다 약간씩 다를 수 있음
		String url="jdbc:mariadb://localhost:3306/hkedu";
		String user="root";
		String password="8538";
		Connection conn=null;//DB연결할때 사용할 객체
		PreparedStatement psmt=null;//쿼리 준비 및 실행을 위한 객체
		String sql="DELETE FROM USERTBL WHERE USERID=?";
		try {
			conn=DriverManager.getConnection(url,user,password);
			System.out.println("2단계:DB연결성공");
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, userId);
			System.out.println("3단계:쿼리준비성공");
			count=psmt.executeUpdate();//반환값:업데이트된 행의 개수를 반환(int)
			System.out.println("4단계:쿼리실행성공");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("JDBC실패:"+getClass());
		}finally {
			try {
				if(psmt!=null) {
					psmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
				System.out.println("6단계:DB닫기성공");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("6단계:DB닫기실패");
			}
		}
		return count>0?true:false;
	}
}
package org.sp.chat.client.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.sp.chat.client.domain.Member;

public class Member2DAO {
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "chat";
	String password = "7777";
	
	// 레코드 1건 등록
	public int insert(Member member) {
		Connection con = null; // 접속 성공 후, 그 정보를 가진 객체 따라서 접속을 해제할때 이 객체를 이용할 수 있다
		PreparedStatement pstmt = null; // 쿼리수행 객체
		int result = 0;
		
		try {
			// 드라이버 로드
			Class.forName("com.mysql.jdbc.Driver");
			
			// 접속
			con = DriverManager.getConnection(url, user, password);
			if(con == null) {
				System.out.println("접속실패");
			} else {
				System.out.println("접속성공");
			}
			
			String sql = "insert into member2(member_idx, id, pass, name, nick, email) values (?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql); // 쿼리수행 객체 생성
			pstmt.setString(1, member.getId()); // 문장에서 첫번째로 발견된 물음표
			pstmt.setString(2, member.getPass()); // 문장에서 두번째로 발견된 물음표
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getNick());
			pstmt.setString(5, member.getEmail());
			pstmt = con.prepareStatement(sql);
			result = pstmt.executeUpdate(); // 쿼리실행..
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		// 이 메서드를 호출한 자가 그 결과를 알 수 있도록 반환하자
		return  result;
	}
	
	// 모든 레코드 가져오기
	public void selectAll() {
		
	}
	
	// 1건 수정
	public void update() {
		
	}
}

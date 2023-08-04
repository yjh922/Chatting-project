package org.sp.chat.client.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.sp.chat.client.domain.Member;

// 오직 member 테이블에 대한 CRUD만을 담당하기 위한 객체
public class MemberDAO {
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "chat";
	String password = "7777";
	
	public Member login(Member member) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member dto = null; // 로그인 후, 해당 관리자1사람 정보를 담기 위한 객체
		
		try {
			// 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DriverManager.getConnection(url, user, password);
			
			if (con == null) {
				System.out.println("접속실패");
			} else {
				String sql = "select * from member where id = ??? and pass = ???";
				pstmt = con.prepareStatement(sql); // 쿼리수행 객체 생성
				pstmt.setString(1, member.getId()); // 문장에서 첫번째로 발견된 물음표
				pstmt.setString(2, member.getPass()); // 문장에서 두번째로 발견된 물음표
				
				// 쿼리실행...
				rs = pstmt.executeQuery(); // 쿼리 수행 후 표를 반환
				if(rs.next()) { // 커서를 한칸 이동시 true가 반환된다면 레코드가 존재, 로그인 성공했다고 판단
					dto = new Member(); // 비어있는 dto 인스턴스 생성
					// 채워넣기..
					dto.setMember_idx(rs.getInt("member_idx"));
					dto.setId(rs.getString("id"));
					dto.setPass(rs.getString("pass"));
					dto.setName(rs.getString("name"));
					dto.setNick(rs.getString("nick"));
					dto.setImg(rs.getString("img"));
					
				}
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
		return dto;
	}
}

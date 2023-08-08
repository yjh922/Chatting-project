package org.sp.chat.client.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.sp.chat.client.domain.Member;

import util.DBManager;

public class Member2DAO {
	DBManager dbManager;
	
	public Member2DAO(DBManager dbManager) {
		this.dbManager=dbManager;
	}
	// 레코드 1건 등록
	public int insert(Member member) {
		Connection con = null; // 접속 성공 후, 그 정보를 가진 객체 따라서 접속을 해제할때 이 객체를 이용할 수 있다
		PreparedStatement pstmt = null; // 쿼리수행 객체
		int result = 0;
		// 드라이버 로드
		con=dbManager.connect();
		
		String sql = "insert into member(member_idx, id, pass, name, nick, email) values (seq_member.nextval, ?, ?, ?, ?, ?)";
		
			try {
				
				pstmt = con.prepareStatement(sql); // 쿼리수행 객체 생성
				
				pstmt.setString(1, member.getId()); // 문장에서 첫번째로 발견된 물음표
				pstmt.setString(2, member.getPass()); // 문장에서 두번째로 발견된 물음표
				pstmt.setString(3, member.getName());
				pstmt.setString(4, member.getNick());
				pstmt.setString(5, member.getEmail());
				
				result = pstmt.executeUpdate(); // 쿼리실행..
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				dbManager.release(con, pstmt);
			}
			
		// 이 메서드를 호출한 자가 그 결과를 알 수 있도록 반환하자
		return  result;
	}
}

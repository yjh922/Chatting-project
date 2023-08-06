package org.sp.chat.client.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.sp.chat.client.domain.Member;

import util.DBManager;

// 오직 member 테이블에 대한 CRUD만을 담당하기 위한 객체
public class MemberDAO {
	DBManager dbManager;
	
	public MemberDAO(DBManager dbManager) {
		this.dbManager=dbManager;
	}
	
	public Member login(Member member) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member dto = null; // 로그인 후, 해당 관리자1사람 정보를 담기 위한 객체
		
		try {
			con=dbManager.connect();
			
			if (con == null) {
				System.out.println("접속실패");
			} else {
				String sql = "select * from member where id = ? and pass = ?";
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
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(con, pstmt, rs);
		}
		return dto;
	}
	
	public List selectAll() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Member> list = new ArrayList<Member>();
		
		StringBuilder sb = new StringBuilder();
		sb.append("select * from member order by member_idx asc");
		
		con=dbManager.connect();
		
		try {
			pstmt = con.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Member member = new Member();
				member.setMember_idx(rs.getInt("member_idx"));
				member.setId(rs.getString("id"));
				member.setPass(rs.getString("pass"));
				member.setName(rs.getString("name"));
				member.setNick(rs.getString("nick"));
				member.setEmail(rs.getString("email"));
				member.setImg(rs.getString("img"));
				
				list.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt, rs);
		}
		
		return list;
	}
	public List searchList(Member dto, String keyword) {    //  bt   select * from member where id like '%"+변수+"%'
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Member> list = new ArrayList<Member>();
		
		StringBuilder sb = new StringBuilder();
		sb.append("select * from member where member_idx !=? and id like '%"+keyword+"%' order by member_idx asc");
		
		con=dbManager.connect();
		
		try {
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, dto.getMember_idx()); //로그인한 사람의 idx
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Member member = new Member();
				member.setMember_idx(rs.getInt("member_idx"));
				member.setId(rs.getString("id"));
				member.setPass(rs.getString("pass"));
				member.setName(rs.getString("name"));
				member.setNick(rs.getString("nick"));
				member.setEmail(rs.getString("email"));
				member.setImg(rs.getString("img"));
				
				list.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt, rs);
		}
		return list;
	}
}
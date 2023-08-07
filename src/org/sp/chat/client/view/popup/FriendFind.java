package org.sp.chat.client.view.popup;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import org.sp.chat.client.domain.Member;
import org.sp.chat.client.model.MemberDAO;
import org.sp.chat.client.view.ChatMain;
import org.sp.chat.client.view.MemberCell;

import util.DBManager;

public class FriendFind extends PopUp{
	JPanel p_north;
	JPanel p_center;
	JPanel p_south;
	JLabel la_title,la_search;
	JTextField t_search; //텍스트
	JButton bt_search; //검색 버튼
	
	ChatMain chatMain;
	JPanel p_list; //리스트를 담을 배열
	JScrollPane scroll;
	
	DBManager dbManager;
	MemberDAO memberDAO;
	List<Member> memberList;
	
	public FriendFind(ChatMain chatMain) {
		p_north = new JPanel();
		p_center = new JPanel();
		p_south = new JPanel();
		
		la_title = new JLabel("친구추가");
		t_search = new JTextField("");
		bt_search = new JButton("검색");
		
		dbManager = new DBManager();
		memberDAO = new MemberDAO(dbManager);
		
		this.chatMain=chatMain;
		p_list = new JPanel();
		scroll = new JScrollPane(p_list);
		
		this.setLayout(new BorderLayout());
		//스타일
		//p_center.setBackground(Color.yellow);
		p_center.setPreferredSize(new Dimension(270, 100));
		//p_south.setBackground(Color.GREEN);
		t_search.setPreferredSize(new Dimension(200, 30)); //텍스트 필드 크기
		
		Font f= new Font("휴먼모음T", Font.PLAIN, 22); //폰트 
		la_title.setFont(f); 
		la_title.setPreferredSize(new Dimension(90,30));
		la_title.setHorizontalAlignment(JLabel.CENTER);
		
		//스크롤내의 패널을 y축 세로 방향으로 부착하기
		p_list.setLayout(new BoxLayout(p_list, BoxLayout.Y_AXIS));
		p_list.setBackground(Color.LIGHT_GRAY);
		scroll.setPreferredSize(new Dimension(300, 450));
		
		//부착
		add(p_north, BorderLayout.NORTH);
		add(p_center);
		add(p_south, BorderLayout.SOUTH);
		
		p_north.add(la_title);
		p_center.add(t_search);
		p_center.add(bt_search);
		p_south.add(scroll);
		
		setBounds(1000, 200, 350, 590);
		
		bt_search.addActionListener((e)->{
			//System.out.println("클릭했어?");
			getList();
		});
	
	}
	
	//검색된 회원 출력하기 
	public void getList() {
		//기존 부척된 셀들은 제거 한 후 
		p_list.removeAll();
		
		List<Member> memberList=memberDAO.searchList(chatMain.member ,t_search.getText());
		
		for(int i=0; i<memberList.size();i++) {
			Member member=memberList.get(i);
			MemberCell memberCell = new MemberCell(chatMain, member); 
			p_list.add(memberCell);
		}
		//화면갱신 
		p_list.updateUI();
	}
	
}










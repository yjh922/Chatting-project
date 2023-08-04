package org.sp.chat.client.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.sp.chat.client.domain.Member;
import org.sp.chat.client.model.MemberDAO;
import org.sp.chat.client.view.popup.FriendFind;
import org.sp.chat.client.view.popup.PopUp;

import util.DBManager;
import util.ImageUtil;

public class FriendPage extends Page{
	ChatMain chatMain;
	JPanel p_list; //리스트를 담을 배열
	JScrollPane scroll;
	JLabel la_plus;
	JLabel la_title;
	DBManager dbManager;
	MemberDAO memberDAO;
	//List<Member> memberList;
	FriendFind ff;
	
	public FriendPage(ChatMain chatMain) {
		this.chatMain = chatMain;
		la_plus =new JLabel();
		la_title = new JLabel();
		la_plus.setIcon(new ImageIcon(ImageUtil.getImage("friendplus.png", 35, 35)));	
		p_list = new JPanel();
		
		la_title.setText("친구"); //제목
		
		//스크롤내의 패널을 y축 세로 방향으로 부착하기
		p_list.setLayout(new BoxLayout(p_list, BoxLayout.Y_AXIS));

		dbManager = new DBManager();
		//memberDAO = new MemberDAO(dbManager);
		//memberList = memberDAO.selectAll();
		
		/*
		for(int i=0;i<memberList.size();i++) {
			Member member=memberList.get(i);
			MemberCell memberCell = new MemberCell(chatMain, member);
			p_list.add(memberCell);
		}
		*/
		
		//부착

		p_center.setPreferredSize(new Dimension(100, 800));

		p_north.add(la_title);
		p_north.add(la_plus);
		p_center.setPreferredSize(new Dimension(340, 800));

		scroll = new JScrollPane(p_center);
		scroll.setPreferredSize(new Dimension(100,500));
		p_center.add(p_list);
		add(scroll);
		
		
		//클릭
		la_plus.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(ff==null) {
					ff = new FriendFind(); 
				}
			}
		});
		
	}
	
}

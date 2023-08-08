package org.sp.chat.client.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.sp.chat.client.domain.Member;
import org.sp.chat.client.model.FriendDAO;
import org.sp.chat.client.model.MemberDAO;
import org.sp.chat.client.model.RoomDAO;
import org.sp.chat.client.view.popup.FriendFind;

import util.DBManager;
import util.ImageUtil;

public class FriendPage extends Page{
	ChatMain chatMain;
	JLabel la_plus;
	JLabel la_title;
	JPanel p_list; //리스트를 담을 배열
	JScrollPane scroll;
	DBManager dbManager;
	FriendDAO friendDAO;
	MemberDAO memberDAO;
	RoomDAO roomDAO;
	List<Member> friendList;
	FriendFind ff;
	
	public FriendPage(ChatMain chatMain) {
		this.chatMain = chatMain;
		la_plus =new JLabel();
		la_title = new JLabel("친구");
		la_plus.setIcon(new ImageIcon(ImageUtil.getImage("friendplus.png", 35, 35)));	
		p_list = new JPanel();
		scroll = new JScrollPane(p_center);
		
		//스크롤내의 패널을 y축 세로 방향으로 부착하기
		p_list.setLayout(new BoxLayout(p_list, BoxLayout.Y_AXIS));

		dbManager = new DBManager();

		friendDAO = new FriendDAO(dbManager);

		memberDAO = new MemberDAO(dbManager);
		//memberList = memberDAO.selectAll();
		roomDAO = new RoomDAO(dbManager);

		
		p_north.setLayout(null);
		la_title.setBounds(15, 3, 40, 40);
		la_plus.setBounds(280, 3, 40, 40);
		//스타일
		//p_north.setBackground(new Color(89,185,255));
		p_center.setPreferredSize(new Dimension(310, 600));
		//p_center.setBackground(Color.white);
		scroll.setPreferredSize(new Dimension(300,500));
		la_title.setFont(new Font("휴먼모음T", Font.PLAIN, 20));
		
		//부착
		p_north.add(la_title);
		p_north.add(la_plus);
		p_center.add(p_list);
		add(scroll);
		
		//클릭
		la_plus.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(ff==null) {
					ff = new FriendFind(FriendPage.this, chatMain); 
				}
			}
		});
		
	}
	public void showFriendList() {
		//기존의 패널에 붙여진 컴포넌트들을 모두 제거 
		p_list.removeAll();
		
		friendList = friendDAO.friendAll(ChatMain.member.getMember_idx());
		
		for(int i=0;i<friendList.size();i++) {
			Member friend=friendList.get(i);
			FriendCell friendCell = new FriendCell(this, friend);
			p_list.add(friendCell);
		}
		p_list.updateUI();
	}
	
}

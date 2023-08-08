package org.sp.chat.client.view.popup;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import org.sp.chat.client.domain.Member;
import org.sp.chat.client.domain.Room;
import org.sp.chat.client.domain.Roommate;
import org.sp.chat.client.model.FriendDAO;
import org.sp.chat.client.model.RoomDAO;
import org.sp.chat.client.model.RoommateDAO;
import org.sp.chat.client.view.ChatMain;
import org.sp.chat.client.view.ChattingPage;

import util.DBManager;

public class ChattingRoom extends PopUP{
	JPanel p_north;
	JPanel p_center;
	JLabel la_title;
	JLabel la_make;
	JScrollPane scroll;
	ChatMain chatMain;
	
	JPanel p_list;
	DBManager dbManager;
	FriendDAO friendDAO;
	List<Member> friendList;
	RoomDAO roomDAO;
	RoommateDAO roommateDAO;
	ChattingPage chattingPage;

	//셀들을 접근하기 위한 리스트 
	List<ChattingRoomCell> cellList=new ArrayList<ChattingRoomCell>(); 
	String roomName;//방이름 이벽받은 값
	
	public ChattingRoom(ChattingPage chattingPage) {
		this.chattingPage=chattingPage;
		p_north = new JPanel();
		p_center = new JPanel();
		la_title = new JLabel("대화 상대 선택");
		la_make = new JLabel("확인");
		scroll = new JScrollPane(p_center);
		
		
		p_list = new JPanel();
		p_list.setLayout(new BoxLayout(p_list, BoxLayout.Y_AXIS));
		dbManager = new DBManager();
		friendDAO = new FriendDAO(dbManager);
		
		
		roomDAO=new RoomDAO(dbManager);
		roommateDAO = new RoommateDAO(dbManager);
		
		//스타일
		//p_north.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		p_north.setPreferredSize(new Dimension(330, 50));
		
		scroll.setPreferredSize(new Dimension(380,500));
		
		Dimension d = new Dimension(140, 40);
		la_title.setPreferredSize(d);
		la_make.setPreferredSize(d);
		
		Font f = new Font("휴먼모음T", Font.PLAIN, 20); //폰트
		la_title.setFont(f);
		la_make.setFont(f);
		
		la_title.setHorizontalAlignment(JLabel.LEFT);
		la_make.setHorizontalAlignment(JLabel.RIGHT);
		
		//부착
		showChoiceList();
			
		p_north.add(la_title);
		p_north.add(la_make);
		
		p_center.add(p_list);
		add(p_north, BorderLayout.NORTH);
		add(scroll);
		
		setLocationRelativeTo(null);
		
		la_make.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				roomName=JOptionPane.showInputDialog(ChattingRoom.this, "방이름을 입력하세요");
				if(roomName!=null) {
					createRoom();
					chattingPage.showRoomList();
					ChattingRoom.this.setVisible(false);
				}else if(roomName==""){
					JOptionPane.showMessageDialog(ChattingRoom.this, "방이름을 입력해 주세요");
				}
			}
		});
		
	}
	
	//룸생성 및 룸메이트 추가
	public void createRoom() {
		
		Room room=new Room();
		String name=roomName;
		room.setRoomname(name);
		
		int result=roomDAO.insert(room);
		
		if(result>0) { // if room is created...
			Roommate roommate=new Roommate();
			roommate.setRoom(room);//어느방에 
			roommate.setMember(ChatMain.member);//나
			roommateDAO.insert(roommate);
			
			//각셀안에 들어잇는 체크박스가 선택되어 잇으며, 셀이 보유한 ㅡMember access 
			for(int i=0;i<cellList.size();i++) {
				ChattingRoomCell cell =cellList.get(i);
				if(cell.box.isSelected()) {
					System.out.println(cell.friend.getName()+","+cell.friend.getMember_idx());
					//insert rommate !!
					Roommate dto=new Roommate();
					dto.setRoom(room);//어느방에 
					dto.setMember(cell.friend);//체크된 친구들
					roommateDAO.insert(dto);
				}
			}
			System.out.println("친구방 개설");
			chattingPage.p_list.updateUI();

		}

	}
	
	public void showChoiceList() {
		p_list.removeAll();
		friendList = friendDAO.friendAll(ChatMain.member.getMember_idx());
		for(int i=0; i<friendList.size(); i++) {
			Member friend =friendList.get(i);
			ChattingRoomCell chattingRoomCell = new ChattingRoomCell(friend);
			p_list.add(chattingRoomCell); //디자인 추가 
			cellList.add(chattingRoomCell); //리스트에 추가 
		}
		p_list.updateUI();
	}
}
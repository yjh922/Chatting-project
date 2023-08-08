package org.sp.chat.client.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.sp.chat.client.domain.Room;
import org.sp.chat.client.domain.Roommate;
import org.sp.chat.client.model.RoomDAO;
import org.sp.chat.client.view.popup.ChattingRoom;
import org.sp.chat.client.view.popup.ChattingRoomCell;
import org.sp.chat.network.ClientMain;

import util.DBManager;
import util.ImageUtil;

public class ChattingPage extends Page{
	ChatMain chatMain;
	DBManager dbManager;

	public JPanel p_list;
	JLabel la_title;
	JLabel la_chat;
	JScrollPane scroll;
	
	RoomDAO roomDAO;
	List<Room> roomList;
	
	ChattingRoom chattingRoom;
	List<ChattingCell> cellList=new ArrayList<ChattingCell>();
	public static Room clickRoom;
	
	
	public ChattingPage(ChatMain chatMain) {
		this.chatMain=chatMain;
		dbManager = new DBManager();
		p_list = new JPanel();
		la_title = new JLabel("채팅");
		la_chat = new JLabel(new ImageIcon(ImageUtil.getImage("chatplus.png", 35, 35)));
		scroll = new JScrollPane(p_center);
		
		roomDAO = new RoomDAO(dbManager);
		roomList =roomDAO.selectAll();
		
		//스타일
		p_list.setLayout(new BoxLayout(p_list, BoxLayout.Y_AXIS));
		p_north.setLayout(null);
		la_title.setFont(new Font("휴먼모음T", Font.PLAIN, 20));
		la_title.setBounds(20,0,45,45);
		la_chat.setBounds(280, 0, 40, 40);
		
		
		scroll.setPreferredSize(new Dimension(330,450));
		
		
		
		for(int i=0; i<roomList.size();i++) {
			Room room=roomList.get(i);
			ChattingCell chattingCell = new ChattingCell(chatMain, room);
			p_list.add(chattingCell);
			
			//채팅셀에 클릭 이벤트 처리
		}
		
		
		p_north.add(la_title);
		p_north.add(la_chat);
		
		
		p_center.add(p_list);
		
		add(scroll);
		
	
		//대화 상대 선택창 띄우기
		la_chat.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(chattingRoom==null) {
					chattingRoom = new ChattingRoom(ChattingPage.this);
				}
				
			}
		});

	}
	
	public int clickChat() {
		int result=0;
		
		for(int i=0;i<cellList.size();i++) {
			ChattingCell cell =cellList.get(i);
		
		}
		
		return result;
	}
}

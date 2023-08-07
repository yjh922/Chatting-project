package org.sp.chat.network;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.sp.chat.client.model.MemberDAO;

import util.DBManager;

public class GUIServer extends JFrame{
	JPanel p_north;
	JPanel p_center;
	JButton bt_server;
	JTextArea area;
	JScrollPane scroll;
	
	Thread acceptThread;
	ServerSocket server;
	Vector<ServerMessageThread> vec;
	List userIdx=new ArrayList();
	
	DBManager dbManager;
	MemberDAO memberDAO;
	
	public GUIServer() {
		p_north = new JPanel();
		p_center= new JPanel();
		bt_server = new JButton("서버가동");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		vec = new Vector<ServerMessageThread>();
		dbManager = new DBManager();
		memberDAO = new MemberDAO(dbManager);
		
		p_north.setPreferredSize(new Dimension(380,50));
		
		p_north.add(bt_server);
		
		add(p_north, BorderLayout.NORTH);
		add(scroll);
		
		setBounds(0, 0, 380, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		bt_server.addActionListener((e)->{
			//서버 가동하기
			acceptThread = new Thread() {
				public void run() {
					serverListen();
				}
			};
			acceptThread.start();//쓰레드 시작하기
			bt_server.setEnabled(false);//버튼을 중복해서 못누르게 처리
			area.setText("서버가 가동되었습니다.\n");
		});
		
		
	}
	
	public void serverListen() {
		
		
		try {
			server = new ServerSocket(7777);
			
			while(true) {
				Socket socket =server.accept();
				
				ServerMessageThread smt = new ServerMessageThread(this, socket);
				smt.start();
				
				vec.add(smt);
				//userIdx.add(ChatMain.member.getMember_idx());
				
				
				area.append("현재 접속자" +vec.size()+"명\n");
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new GUIServer();
	}
	
}
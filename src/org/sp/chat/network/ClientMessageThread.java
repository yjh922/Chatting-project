package org.sp.chat.network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.sp.chat.client.domain.Member;
import org.sp.chat.client.view.ChatMain;

import util.ImageUtil;

public class ClientMessageThread extends Thread{
	private static final float LEFT_ALIGNMENT = 0;
	ClientMain clientMain;
	Socket socket;
	BufferedReader buffr;// 문자기반의 버퍼처리된 입력스트림
	BufferedWriter buffw;// 문자기반의 버퍼처리된 출력스트림
	boolean flag = true;// 이 쓰레드를 죽일지 말지 결정하는 놀리값

	Member member;

	
	public ClientMessageThread(ClientMain clientMain) {
		this.clientMain=clientMain;
		this.socket=clientMain.socket;
		
		try {
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void listen() {
		String msg=null;
		
		
		try {
			msg=buffr.readLine();
			
			JSONParser jsonParser = new JSONParser();
			JSONObject json=(JSONObject)jsonParser.parse(msg);
				
			long sender=(Long)json.get("sender");
			String data=(String)json.get("data");
			
			//클라이언트 채팅창 메시지 보내기
			member=clientMain.memberDAO.select((int)sender);
			inputMsg(member.getName()+"님:"+data);
			

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	public void sendMsg(String msg) {
		try {
			buffw.write(msg+"\n");
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(flag) {
			listen();
		}
	}
	
	public void inputMsg(String message) {
		JPanel p_msg = new JPanel();
		//p_msg.setLayout(null);
		
		
		JTextArea t_msg = new JTextArea();
		t_msg.setText(message);
		t_msg.setLineWrap(true);
		t_msg.setColumns(30);
		t_msg.setBorder(null);
		t_msg.setEditable(false);

		JLabel la_icon = new JLabel(new ImageIcon(ImageUtil.getImage(member.getImg(), 20, 20)));
	
		
		p_msg.add(la_icon);
		p_msg.add(t_msg);
		
		clientMain.p_center.add(p_msg);
		clientMain.p_center.updateUI();
		
	}
	
}












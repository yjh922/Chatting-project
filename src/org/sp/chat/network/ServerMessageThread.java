package org.sp.chat.network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.List;

import org.sp.chat.client.domain.Roommate;
import org.sp.chat.client.model.RoommateDAO;
import org.sp.chat.client.view.ChattingPage;

public class ServerMessageThread extends Thread{
	GUIServer guiServer;
	Socket socket;
	BufferedReader buffr;
	BufferedWriter buffw;
	boolean flag=true;
	
	public ServerMessageThread(GUIServer guiServer, Socket socket) {
		this.guiServer=guiServer;
		this.socket=socket;
		
		try {
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//듣기
	public void listen() {
		String msg=null;
		try {
			msg=buffr.readLine(); 
			guiServer.area.append(msg+"\n");//로그 남기기
			
			//나의 메서드만 호출하지 말고, 현재 접속한 모든 유저들이 보유한
			//sendMsg()도 함께 호출하자 
			
			//메시지를 보내는 사람의 회원 idx 이용하여 이 사람이 사용중인 룸, 룸에 참여한 사람을 담아놓은 List를 이용하여 아래 포문 돌리기 
			
			
			for(int i=0;i<guiServer.vec.size();i++) {
				ServerMessageThread smt=guiServer.vec.get(i);
				smt.sendMsg(msg);//클라이언트에 보내기
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//말하기
	public void sendMsg(String msg) {
		try {
			buffw.write(msg+"\n"); //버처처리된 스트림은 \n으로 끝을 알려함
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
}
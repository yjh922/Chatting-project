package org.sp.chat.network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.sp.chat.client.domain.Member;
import org.sp.chat.client.domain.Roommate;
import org.sp.chat.client.model.RoommateDAO;
import org.sp.chat.client.view.ChattingPage;

public class ServerMessageThread extends Thread{
	GUIServer guiServer;
	Socket socket;
	BufferedReader buffr;
	BufferedWriter buffw;
	boolean flag=true;
	JSONParser jsonParser;
	List<Roommate> roommateList = new ArrayList<Roommate>();
	
	Member member;
	Vector<Integer> mateList=new Vector<Integer>();
	
	
	public ServerMessageThread(GUIServer guiServer, Socket socket) {
		this.guiServer=guiServer;
		this.socket=socket;
		
		try {
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		jsonParser = new JSONParser();
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
			try {
				JSONParser jsonParser = new JSONParser();
				JSONObject jsonObject=(JSONObject)jsonParser.parse(msg);
				String requestType = (String)jsonObject.get("requestType");
				
				switch(requestType) {
					case "login":getInfo(jsonObject);break;
					case "message":broadCast(jsonObject);break;
				}
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//1)접속한 회원의 정보를 보관 
	//2)접속한 회원의 룸메이트 정보를 이용하여 브로드케스팅 명단 만드릭 
	public void getInfo(JSONObject obj) {
		int me=(Integer)obj.get("me");
		
		member = guiServer.memberDAO.select(me);

		roommateList.removeAll(roommateList); //컬렉션 비우기
		
		JSONArray jsonArray=(JSONArray)obj.get("roommdate");
		for(int i=0; i<jsonArray.size();i++) {
			JSONObject json=(JSONObject)jsonArray.get(i);
			mateList.add((Integer)json.get("member_idx"));
		}
	}
	
	public void broadCast(JSONObject obj) {
		String data = (String)obj.get("data");
		
		//친구명단과 대화쓰레드가 들어있는 총 참여자 명단을 비교하여 , 같은 경우만 메시지 보내자 
		for(int i=0;i<guiServer.vec.size();i++) {
			ServerMessageThread smt =guiServer.vec.get(i);
			for(int a=0;a<mateList.size();a++) {
				int member_idx=mateList.get(a);
				if(member_idx == smt.member.getMember_idx()) { //메시지 전송대상 
					smt.sendMsg(data);
				}
			}
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
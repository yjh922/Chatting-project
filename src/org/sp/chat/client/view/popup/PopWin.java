package org.sp.chat.client.view.popup;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.sp.chat.client.view.ChatMain;

public class PopWin extends JFrame{
	ChatMain chatMain;
	JPanel p_center; //페이지들이 교체될 공백영역
	
	PopupPage[] pages;
	public static final int LOGINPAGE=0;
	public static final int JOINPAGE=1;
	
	public PopWin(ChatMain chatMain) {
		this.chatMain=chatMain;
		p_center = new JPanel();
		p_center.setBackground(Color.YELLOW);
				
		createPage();//페이지 생성
		
		add(p_center);
		
		//pack();
		
		setSize(380, 600);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		showHide(LOGINPAGE);
	}
	
	public void createPage() {
		pages = new PopupPage[2]; //배열준비
		
		pages[0] = new LoginPage(this, null);
		pages[1] = new JoinPage(this, null);
		
		for(int i=0;i<pages.length;i++) {
			p_center.add(pages[i]);
		}
	}
	
	//페이지 전환하기
	public void showHide(int n) {
		for(int i=0;i<pages.length;i++) {
			if(i==n) { //보여주고싶은 페이지라면..
				pages[i].setVisible(true);
			}else {
				pages[i].setVisible(false);				
			}
		}
	}
	
	public static void main(String[] args) {
		new PopWin(null);
	}
}

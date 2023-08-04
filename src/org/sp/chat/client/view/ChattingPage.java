package org.sp.chat.client.view;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.sp.chat.client.view.popup.ChattingRoom;
import org.sp.chat.client.view.popup.PopUp;

public class ChattingPage extends Page{
	
	public ChattingPage() {
		la_title.setText("채팅");
		la_plus.setText("방만들기");
		
		la_plus.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				ChattingRoom cr = new ChattingRoom();
			}
		});
	}
}

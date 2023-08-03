package org.sp.chat.client.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.sp.chat.client.view.popup.FriendFind;
import org.sp.chat.client.view.popup.Popup;

public class FriendPage extends Page{

	public FriendPage() {
		la_title.setText("친구"); //제목
		la_plus.setText("친구추가");
		
		la_plus.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				FriendFind ff = new FriendFind(); 
			}
		});
		
	}
}

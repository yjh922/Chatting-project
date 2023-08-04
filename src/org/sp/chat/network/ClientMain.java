package org.sp.chat.network;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientMain extends JFrame{
	JPanel p_north;
	JPanel p_center;
	JPanel p_south;
	JComboBox box;
	JTextField t_port;
	JButton bt_connect;
	JScrollPane scroll;
	JTextField t_input;
	JButton bt_input;
	JScrollPane scroll_input;
	
	Socket socket;
	ClientMessageThread cmt;
	
	public ClientMain() {
		p_north = new JPanel();
		p_center = new JPanel();
		p_south = new JPanel();
		box = new JComboBox();
		t_port = new JTextField("7777",6);
		bt_connect = new JButton("접속");
	
		scroll = new JScrollPane(p_center);
		t_input = new JTextField();
		bt_input = new JButton("입력");
		scroll_input = new JScrollPane(t_input);
		
		box.addItem("192.168.0.14");

		//스타일
		p_north.setPreferredSize(new Dimension(380,50));
		p_south.setPreferredSize(new Dimension(380,50));
		p_center.setPreferredSize(new Dimension(300,500));
		scroll.setPreferredSize(new Dimension(300,480));
		t_input.setPreferredSize(new Dimension(230,45));
		scroll_input.setPreferredSize(new Dimension(230,40));
		//t_input.setLineWrap(true);
		
		
		//조립
		p_north.add(box);
		p_north.add(t_port);
		p_north.add(bt_connect);
		p_south.add(scroll_input);
		p_south.add(bt_input);
		
		
		
	
		add(p_north, BorderLayout.NORTH);
		add(scroll);
		
		add(p_south, BorderLayout.SOUTH);
		
		setBounds(0, 0, 380, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
		bt_connect.addActionListener((e)->{
			connect();
		});
		
		t_input.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				int key=e.getKeyCode();
				if(key == KeyEvent.VK_ENTER) {//엔터치면..
					//서버에 메시지 전송
					send();
				
					
				}
			}
		});
		bt_input.addActionListener((e)->{
			send();
			
		});
		
		
		
	}
	
	public void connect() {
		String ip=(String)box.getSelectedItem();
		int port=Integer.parseInt(t_port.getText());
		
		try {
			socket = new Socket(ip, port);
			
			cmt = new ClientMessageThread(this);
			cmt.start();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void send() {
StringBuffer sb = new StringBuffer();
		
		//개발자가 전송 프로토콜을 정의한다..
		sb.append("{");
		sb.append("\"send\":1,");
		sb.append("\"receive\" :2, ");
		sb.append("\"id\" :\"yoon\", ");
		sb.append("\"roommate_idx\" :1,"); 
		sb.append("\"contents\" :\""+t_input.getText()+"\" ");
		sb.append("}");
	
		System.out.println(sb.toString());
		
		cmt.sendMsg(sb.toString());
		
		t_input.setText("");
	}
	
	public static void main(String[] args) {
		new ClientMain();
	}
}
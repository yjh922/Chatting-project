package org.sp.chat.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Profile extends JFrame{
	JPanel p_center; 
	JPanel p_south;
	JButton bt_con;
	JButton bt_cancel;
	JButton bt_photo; //사진찾기 파일 탐색기 띄우는 버튼
	JButton bt_regist; //수정버튼
	JTextField t_name;
	

	
	String imgprofile= "res/default.png";
	
	JFileChooser chooser;
	File file;
	Image image; //파일탐색기에서 선택한 바로 그 파일
	JPanel p_preview; //선택한 이미지 미리보기
	JButton nick;
	

	public Profile() {
		p_center = new JPanel();
		p_south = new JPanel();
		bt_con = new JButton("확인");
		bt_regist  = new JButton("수정");
		bt_cancel = new JButton("취소");
		bt_photo=new JButton("사진찾기");
		t_name= new JTextField(20);
		chooser = new JFileChooser("D:/javase_workspace/Chatting-project/src/res");
		
		p_preview = new JPanel() {
			public void paint(Graphics g) {
				g.drawImage(image, 0, 0, 130,130, p_preview);
			}
		};//추후 그림을 직접 그리기
		
		p_preview.setPreferredSize(new Dimension(130,130));
		p_preview.setBackground(Color.YELLOW);
		
		//조립
		imgprofile();
		add(p_south, BorderLayout.SOUTH);
		add(p_center, BorderLayout.CENTER);
		p_south.add(bt_regist);
		p_south.add(bt_cancel);
		p_center.add(t_name);
		 nickname();
		p_center.add(bt_photo);
		p_center.add(p_preview);

		
		p_center.setPreferredSize(new Dimension(50,600));
		p_preview.setPreferredSize(new Dimension(300, 300));
		p_preview.setBackground(Color.YELLOW);
	
		setSize(300,380);
		setVisible(true);		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
		bt_con.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 팝업 창 띄우기
				JOptionPane.showMessageDialog(null, "수정 하시겠습니까?");
			}
		});
		
		bt_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 창 닫기
				dispose();
			}
		});	
		
		//파일 찾기 버튼에 이벤트연결
		bt_photo.addActionListener((e)->{
			openFile();
		});
		
	
		
		//수정버튼처리
		bt_regist.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				int result=JOptionPane.showConfirmDialog
						(null, "수정 하시겠습니까?","프로필 수정",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE);
				if(result==JOptionPane.OK_OPTION) {
					JOptionPane.showMessageDialog(Profile.this, "수정되었습니다.");
				}
			}
		});
}
	

			
	//파일 탐색기를 띄우고, 그 안에서 원하는 이미지파일을 선택하면, 
		//해당 이미지를 얻어와 JPanel에 그리자 
		public void openFile() {
			int result = chooser.showOpenDialog(this);
			if(result==JFileChooser.APPROVE_OPTION ) {
			
				file=chooser.getSelectedFile();
				System.out.println(file.getPath());
				
				//file 객체를 이미지로 변환해보자
				try {
					image=ImageIO.read(file);
					//bt_photo 패널의 그림 다시 그리기 요청 
					//repaint() --> update() --> paint() 
					bt_photo.repaint();
				} catch (IOException e) {
					e.printStackTrace();
					
					
				}
			}
		}
	public void nickname() {
			//이름(닉네임) 변경
			t_name.setText(ChatMain.member.getName());
	}
	
		
	public void imgprofile() {
		
		URL url=ClassLoader.getSystemResource(imgprofile);
		
		try {
			BufferedImage buffImg = ImageIO.read(url);
			Image image=buffImg;
			image=image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			JLabel la_icon = new JLabel(new ImageIcon(image));
			p_center.add(la_icon);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
}


	
}
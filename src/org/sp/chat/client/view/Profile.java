package org.sp.chat.client.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
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

import org.sp.chat.client.model.MemberDAO;

public class Profile extends JFrame{
	JPanel p_center; 
	JPanel p_south;
	JButton bt_cancel;
	JButton bt_photo; //사진찾기 파일 탐색기 띄우는 버튼
	JButton bt_regist; //수정버튼
	JTextField t_name;
	JLabel la_icon;

	JFileChooser chooser;
	File file;
	Image image; //파일탐색기에서 선택한 바로 그 파일
	JButton nick;
	String imgPath; //임시 프로필 파일 경로값
	MyPage parent;
	public Profile(MyPage parent) { 
		this.parent = parent;
		p_center = new JPanel();
		p_south = new JPanel();
		bt_regist  = new JButton("수정");
		bt_cancel = new JButton("취소");
		bt_photo=new JButton("사진찾기");
		t_name= new JTextField(20);
		chooser = new JFileChooser("D:/javase_workspace/Chatting-project/src/res");

		//조립
		imgprofile();
		add(p_south, BorderLayout.SOUTH);
		add(p_center, BorderLayout.CENTER);
		p_south.add(bt_regist);
		p_south.add(bt_cancel);
		p_center.add(t_name);
		nickname();
		p_center.add(bt_photo);


		p_center.setPreferredSize(new Dimension(50,600));

		setSize(300,380);
		setVisible(true);		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

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
					ChatMain.member.setImg(imgPath);
					ChatMain.member.setName(t_name.getText());
					parent.repaint();
					MemberDAO dao = new MemberDAO();
					dao.changeProfile(ChatMain.member);
					
					JOptionPane.showMessageDialog(Profile.this, "수정되었습니다.");
					// 수정 버튼을 클릭하면 창을 닫기
			        dispose();
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
			imgPath=file.getName();
			//file 객체를 이미지로 변환해보자
			try {
				image=ImageIO.read(file);
				//bt_photo 패널의 그림 다시 그리기 요청 
				//repaint() --> update() --> paint() 
				image=image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
				la_icon.setIcon(new ImageIcon(image));
				
				file.getPath();
				//la_icon.getIcon().getIconHeight();
				//System.out.println("동작테스트"+la_icon.getIcon().getIconHeight());
				//la_icon = new JLabel(new ImageIcon(image));
				la_icon.repaint();
				//bt_photo.repaint();
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

		System.out.println(ChatMain.member.getImg());
		URL url=ClassLoader.getSystemResource("res/"+ChatMain.member.getImg());

		try {
			BufferedImage buffImg = ImageIO.read(url);
			Image image=buffImg;
			image=image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			la_icon = new JLabel(new ImageIcon(image));
			p_center.add(la_icon);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}



}
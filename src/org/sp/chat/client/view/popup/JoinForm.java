package org.sp.chat.client.view.popup;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.sp.chat.client.domain.Member;
import org.sp.chat.client.model.Member2DAO;
import org.sp.chat.client.view.ChatMain;

public class JoinForm extends PopUp {
	ChatMain chatMain;
	JTextField t_id;
	JPasswordField t_pass;
	JTextField t_name;
	JTextField t_nick;
	JTextField t_email;
	// 이미지
	JButton bt_join;
	JButton bt_login;
	
	LoginForm loginForm;
	
	Member2DAO member2DAO;
	
	public JoinForm(ChatMain chatMain) {
		this.chatMain = chatMain;
		
		t_id = new JTextField();
		t_pass = new JPasswordField();
		t_name = new JTextField();
		t_nick = new JTextField();
		t_email = new JTextField();
		
		bt_join = new JButton("Join");
		bt_login = new JButton("Login");
		member2DAO = new Member2DAO();
		
		Dimension d = new Dimension(340, 45);
		
		t_id.setPreferredSize(d);
		t_pass.setPreferredSize(d);
		t_name.setPreferredSize(d);
		t_email.setPreferredSize(d);
		
		add(t_id);
		add(t_pass);
		add(t_name);
		
		add(bt_join);
		add(bt_login);
		
		//setPreferredSize(new Dimension(500, 300));
		setLayout(new FlowLayout());
		
		bt_login.addActionListener((e) -> {
			// 로그인 처리~~
			
		});
		
		bt_join.addActionListener((e) -> {
			// 회원가입폼 보여주기
			regist();
		});
	}
	
	public void regist() {
		// 아이디, 패스워드 등 입력폼의 데이터를 하나의 DTO 담아서 insert 메서드로 전달하자
		Member member = new Member();
		
		// 비어있는 DTO 안에 입력폼의 데이터들을 채워넣기 setter이용
		member.setId(t_id.getText()); // 아이디 채우기
		member.setPass(new String(t_pass.getPassword()));
		member.setName(t_name.getText()); // 이름 채우기
		member.setEmail(t_email.getText());
		
		int result = member2DAO.insert(member);
		if(result >= 1) {
			JOptionPane.showMessageDialog(this, "가입성공");
		} else {
			JOptionPane.showMessageDialog(this, "가입실패");
		}
	}
}

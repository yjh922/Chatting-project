package org.sp.chat.client.view.popup;

import javax.swing.JPanel;

//회원가입 페이지 및 로그인 페이지들의 최상위 페이지 
//왜 사용하나? 같은 자료형으로 두어야 반복문을 사용할 수있다..( 즉 배열화 시키기 위해 ) 
public class PopupPage extends JPanel{
	PopWin popWin;
	
	public PopupPage(PopWin popWin) {
		this.popWin=popWin;
	}
}

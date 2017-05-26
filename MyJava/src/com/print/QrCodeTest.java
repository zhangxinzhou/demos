package com.print;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;

public class QrCodeTest extends JFrame{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;
	
	public QrCodeTest(String msg){
		this.setSize(420,350); 
        this.setVisible(true); 
        this.msg = msg; 
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Image image=CodeUtil.getQRcodeWithLogo(msg, 300, "C:\\Users\\Administrator\\Desktop\\20435855.jpg", 10);
		g.drawImage(image, 10, 110, this);
	}



	public static void main(String[] args) {
		new QrCodeTest("123");
	}
}

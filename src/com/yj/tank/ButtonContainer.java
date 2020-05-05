package com.yj.tank;

import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import com.yj.tank.view.TankFrame;


/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-03-10:45
 **/
public class ButtonContainer extends Container {
	private int x,y;
	public static final int WIDTH=100;
	public static final int HEIGHT=40;
	Button playButton;
	public ButtonContainer(int x,int y, Button playButton) {
		this.x=x;
		this.y=y;
		setBounds(x,y,WIDTH,HEIGHT);
		this.playButton=playButton;
		setLayout(null);
		this.setFocusable(true);
		this.setVisible(true);
		this.playButton.setBounds((TankFrame.GAME_WINDOW_WIDTH-WIDTH)/2,(TankFrame.GAME_WINDOW_HEIGHT-HEIGHT)/2,WIDTH,HEIGHT);
		this.playButton.setFocusable(true);
		this.playButton.setEnabled(true);
		this.playButton.setFont(Font.getFont("宋体"));
		this.playButton.setVisible(true);
		add(playButton);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}
}

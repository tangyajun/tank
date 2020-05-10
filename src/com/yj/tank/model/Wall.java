package com.yj.tank.model;

import java.awt.Graphics;
import java.awt.Image;

import com.yj.tank.GameModelManager;
import com.yj.tank.ResourceManager;

/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-09-12:57
 **/
public class Wall extends GameProp {

	private static Image[] images=ResourceManager.wallImages;

	public static int WIDTH=ResourceManager.wallImages[0].getWidth();

	public static int HEIGHT=ResourceManager.wallImages[0].getHeight();

	public Wall(int x,int y) {
		this(x,y,WIDTH,HEIGHT);
	}

	public Wall(int x,int y,int width,int height) {
		super(x,y,width,height);

	}

	@Override
	public void paint(Graphics graphics) {
		graphics.drawImage(images[0],x,y,null);
	}
}

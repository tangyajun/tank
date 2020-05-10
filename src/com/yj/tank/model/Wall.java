package com.yj.tank.model;

import java.awt.Graphics;
import java.awt.Image;

import com.yj.tank.GameModelManager;
import com.yj.tank.ResourceManager;
import com.yj.tank.view.TankFrame;

/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-09-12:57
 **/
public class Wall extends GameProp {

	private GameModelManager gameModelManager;

	private static Image[] images=ResourceManager.wallImages;

	public static int WIDTH=ResourceManager.wallImages[0].getWidth();

	public static int HEIGHT=ResourceManager.wallImages[0].getHeight();

	public Wall(int x,int y, GameModelManager gameModelManager) {
		this(x,y,WIDTH,HEIGHT,gameModelManager);
	}

	public Wall(int x,int y,int width,int height,GameModelManager gameModelManager) {
		super(x,y,width,height);
		this.gameModelManager=gameModelManager;
	}

	@Override
	public void paint(Graphics graphics) {
		graphics.drawImage(images[0],x,y,null);
		/*for (int i=0;i< images.length;i++) {
			graphics.drawImage(images[i],x,y,null);
			if ((y+HEIGHT)< TankFrame.GAME_WINDOW_HEIGHT) {
				y+=HEIGHT;
			}
		}*/
	}
}

package com.yj.tank.domain;

import java.awt.Graphics;

import com.yj.tank.Audio;
import com.yj.tank.ResourceManager;
import com.yj.tank.view.TankFrame;

/**
 * 爆炸
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-02-22:20
 **/
public class Explode {
	public static final int WIDTH= ResourceManager.explodes[0].getWidth();
	private static final int HEIGHT=ResourceManager.explodes[0].getHeight();
	private int x,y;
	private boolean live=true;
	int step=0;
	TankFrame tankFrame=null;
	public Explode(int x,int y,TankFrame tankFrame) {
		this.x=x+(Tank.WIDTH/2)-(WIDTH/2);
		this.y=y+(Tank.HEIGHT/2)-(HEIGHT/2);
		this.tankFrame=tankFrame;
		new Thread(() -> new Audio("audio/explode.wav").play()).start();
	}

	public void paint(Graphics graphics) {
		if (live) {
			if(step < ResourceManager.explodes.length) {
				graphics.drawImage(ResourceManager.explodes[step++], x, y, null);
			}
		}else {
			tankFrame.getExplodes().remove(this);
			this.live = false;
		}
		System.out.println("-------------------step: "+step);
	}
}

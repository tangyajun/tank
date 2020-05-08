package com.yj.tank.model;

import java.awt.Graphics;

import com.yj.tank.GameModelManager;
import com.yj.tank.ResourceManager;
import com.yj.tank.view.TankFrame;

/**
 * 爆炸
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-02-22:20
 **/
public class Explode extends AbstractExplode {
	public static final int WIDTH= ResourceManager.explodes[0].getWidth();
	private static final int HEIGHT=ResourceManager.explodes[0].getHeight();
	private boolean live=true;
	int step=0;
	GameModelManager gameModelManager=null;
	public Explode(int x,int y, GameModelManager gameModelManager) {
		super(x,y,WIDTH,HEIGHT,gameModelManager);
	}

	@Override
	public void paint(Graphics graphics) {
		if (live) {
			if(step < ResourceManager.explodes.length) {
				graphics.drawImage(ResourceManager.explodes[step++], x, y, null);
			}
		}else {
			gameModelManager.removeGameProp(this);
			this.live = false;
		}

	}
}

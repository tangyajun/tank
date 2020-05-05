package com.yj.tank.model;

import java.awt.Graphics;

import com.yj.tank.GameModelManager;
import com.yj.tank.ResourceManager;

/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-05-11:27
 **/
public class SmallTankExplode extends AbstractExplode{

	public static final int WIDTH= ResourceManager.smallTankExplodes[0].getWidth();
	private static final int HEIGHT=ResourceManager.smallTankExplodes[0].getHeight();
	int step=0;
	private boolean live=true;
	public SmallTankExplode(int x,int y, GameModelManager gameModelManager) {
		super(x,y,WIDTH,HEIGHT,gameModelManager,null,"audio/blast.wav");
	}

	@Override
	public void paint(Graphics graphics) {
		if (live) {
			if(step < ResourceManager.smallTankExplodes.length) {
				graphics.drawImage(ResourceManager.smallTankExplodes[step++], x, y, null);
			}
		}else {
			gameModelManager.getExplodes().remove(this);
			this.live = false;
		}

	}
}

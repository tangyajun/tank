package com.yj.tank.model;

import com.yj.tank.GameModelManager;
import com.yj.tank.ResourceManager;
import com.yj.tank.view.TankFrame;

/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-04-17:12
 **/
public class PlaneExplode extends AbstractExplode {
	public static final int WIDTH= ResourceManager.explodes[0].getWidth();;
	public static final int HEIGHT=ResourceManager.explodes[0].getHeight();
	public PlaneExplode(int x,int y,int width,int height, GameModelManager gameModelManager) {
		super(x,y,width,height,gameModelManager);
	}
}

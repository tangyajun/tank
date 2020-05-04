package com.yj.tank;

import java.awt.Graphics;
import java.util.List;

import com.yj.tank.model.Tank;

/**
 *  坦克线程
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-02-18:41
 **/
public class TankThread implements Runnable {
	Graphics graphics;
	List<Tank> tanks;
	public TankThread(Graphics graphics, List<Tank> tanks) {
		this.graphics=graphics;
		this.tanks=tanks;
	}

	@Override
	public void run() {
		for (int j=0;j<tanks.size();j++) {
			tanks.get(j).paint(graphics);
		}
	}
}

package com.yj.tank.model;

import java.awt.Graphics;

/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-10-11:20
 **/
public class GamersTankDecorator extends GamePropDecorator {

	public GamersTankDecorator(GameProp gameProp) {
		super(gameProp);
	}

	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);

	}
}

package com.yj.tank.model;

import java.awt.Graphics;

/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-10-11:08
 **/
public class GamePropDecorator extends GameProp {

	GameProp gameProp;
	@Override
	public void paint(Graphics graphics) {
		gameProp.paint(graphics);
	}

	public GamePropDecorator(GameProp gameProp) {
		this.gameProp=gameProp;
	}
}

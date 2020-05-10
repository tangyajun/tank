package com.yj.tank.model;

import java.awt.Graphics;
import java.util.List;

/**
 *  场景
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-10-9:25
 **/
public class Scene extends GameProp {

	List<Wall> walls;

	public Scene() {
		super();
	}

	public Scene(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public void paint(Graphics graphics) {

	}
}

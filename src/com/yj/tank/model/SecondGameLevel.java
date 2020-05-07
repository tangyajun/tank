package com.yj.tank.model;

/**
 *  第二关
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-07-15:32
 **/
public class SecondGameLevel extends AbstractGameLevel {
	public static SecondGameLevel INSTANCE=new SecondGameLevel();
	private SecondGameLevel() {

	}

	public static SecondGameLevel getInstance() {
		return INSTANCE;
	}

	@Override
	public void start() {

	}
}

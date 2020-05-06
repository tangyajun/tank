package com.yj.tank.model;

/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-06-23:57
 **/
public class FirstGameLevel extends AbstractGameLevel {

	public static FirstGameLevel INSTANCE=new FirstGameLevel();

	private FirstGameLevel() {

	}

	public static FirstGameLevel getInstance() {
		return INSTANCE;
	}


}

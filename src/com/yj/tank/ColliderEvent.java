package com.yj.tank;

import com.yj.tank.model.GameProp;

/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-10-13:19
 **/
public class ColliderEvent implements GamePropEvent {

	private GameProp gameProp;

	public ColliderEvent(GameProp gameProp) {
		this.gameProp=gameProp;
	}

	@Override
	public <T> T getSource() {
		return (T)gameProp;
	}
}

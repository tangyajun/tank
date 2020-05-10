package com.yj.tank.factory;

import com.yj.tank.GameModelManager;
import com.yj.tank.constant.Dir;

/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-09-23:49
 **/
public abstract class GamePropFactory {

	abstract <T> T crateGameProp(int x,int y, Dir dir, GameModelManager gameModelManager);
}

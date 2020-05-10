package com.yj.tank.factory;

import java.util.ArrayList;
import java.util.List;

import com.yj.tank.GameModelManager;
import com.yj.tank.model.Wall;

/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-09-23:48
 **/
public class WallFactory {

	private WallFactory() {

	}

	private static class WallFactoryHolder {
		private static WallFactory INSTANCE=new WallFactory();
	}

	public static WallFactory getInstance() {
		return WallFactoryHolder.INSTANCE;
	}

	public Wall createWall(int x,int y) {
		return new Wall(x,y);
	}

	public List<Wall> createWalls(int x,int y) {
		List<Wall> walls=new ArrayList<>();
		for (int i=0;i<6;i++) {
			walls.add(new Wall(x,y));
			x+=Wall.WIDTH;
		}
		System.out.println("------------------------------------x: "+x);
		return walls;
	}

}

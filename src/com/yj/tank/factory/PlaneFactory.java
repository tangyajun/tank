package com.yj.tank.factory;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.yj.tank.GameModelManager;
import com.yj.tank.TankFireBulletStrategy;
import com.yj.tank.constant.Dir;
import com.yj.tank.constant.Group;
import com.yj.tank.model.Plane;

/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-04-18:42
 **/
public class PlaneFactory implements WeaponFactory<Plane> {

	public static PlaneFactory INSTANCE=new PlaneFactory();

	private PlaneFactory() {

	}

	public static PlaneFactory getInstance() {
		return INSTANCE;
	}

	@Override
	public Plane createWeapon(int x, int y, Dir dir,  Group group) {
		return new Plane(x,y,dir,group,new TankFireBulletStrategy());
	}

	@Override
	public List<Plane> createWeapons(int num,Group group, int distance, Dir dir) {
		List<Plane> tanks=new LinkedList<>();
		int x=140;
		int y=40;
		for (int i=0;i<num;i++) {
			tanks.add(createWeapon(x,y,dir,group));
			x+=distance;
		}
		return tanks;
	}

	@Override
	public List<Plane> createWeapons(int num, Group group, int distance, Dir dir, int x, int y) {
		List<Plane> tanks=new LinkedList<>();
		for (int i=0;i<num;i++) {
			tanks.add(createWeapon(x,y,dir,group));
			x+=distance;
		}
		return tanks;
	}

}

package com.yj.tank.factory;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.yj.tank.GameModelManager;
import com.yj.tank.SmallTankFireBulletStrategy;
import com.yj.tank.constant.Dir;
import com.yj.tank.constant.Group;
import com.yj.tank.model.SmallTank;

/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-05-11:49
 **/
public class SmallTankFactory implements WeaponFactory<SmallTank> {
	public static SmallTankFactory INSTANCE=new SmallTankFactory();

	private SmallTankFactory() {

	}

	public static SmallTankFactory getInstance() {
		return INSTANCE;
	}

	static Dir[] dirs={Dir.UP,Dir.DOWN,Dir.LEFT,Dir.RIGHT};

	@Override
	public  SmallTank createWeapon(int x,int y,Dir dir,  Group group) {
		return new SmallTank(x,y,dir,group,new SmallTankFireBulletStrategy());
	}

	/**
	 *
	 * @param num 坦克数量
	 * @param
	 * @param group 坦克分组
	 * @param distance 坦克间距
	 * @param dir 坦克方向
	 * @param x 坦克x坐标 默认 140
	 * @param y 坦克 y坐标 默认 40
	 * @return
	 */
	@Override
	public List<SmallTank> createWeapons(int num, Group group, final int distance, Dir dir,int x,int y) {
		List<SmallTank> tanks=new LinkedList<>();
		for (int i=0;i<num;i++) {
			tanks.add(createWeapon(x,y,dir,group));
			x+=distance;
		}
		return tanks;
	}

	@Override
	public List<SmallTank> createWeapons(int num,Group group, final int distance,Dir dir) {
		List<SmallTank> tanks=new LinkedList<>();
		int x=140;
		int y=40;
		for (int i=0;i<num;i++) {
			tanks.add(createWeapon(x,y,dir,group));
			x+=distance;
		}
		return tanks;
	}
}

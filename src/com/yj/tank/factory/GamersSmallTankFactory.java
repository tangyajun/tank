package com.yj.tank.factory;

import java.util.LinkedList;
import java.util.List;

import com.yj.tank.GameModelManager;
import com.yj.tank.SmallTankFireBulletStrategy;
import com.yj.tank.constant.Dir;
import com.yj.tank.constant.Group;
import com.yj.tank.model.GamersTank;

/**
 *  玩家坦克工厂
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-06-18:26
 **/
public class GamersSmallTankFactory implements WeaponFactory<GamersTank> {

	public static GamersSmallTankFactory INSTANCE=new GamersSmallTankFactory();

	private GamersSmallTankFactory() {

	}

	public static GamersSmallTankFactory getInstance() {
		return INSTANCE;
	}

	static Dir[] dirs={Dir.UP,Dir.DOWN,Dir.LEFT,Dir.RIGHT};

	@Override
	public GamersTank createWeapon(int x,int y,Dir dir, GameModelManager gameModelManager, Group group) {
		return new GamersTank(x,y,dir,gameModelManager,group,new SmallTankFireBulletStrategy());
	}

	/**
	 *
	 * @param num 坦克数量
	 * @param gameModelManager
	 * @param group 坦克分组
	 * @param distance 坦克间距
	 * @param dir 坦克方向
	 * @param x 坦克x坐标 默认 140
	 * @param y 坦克 y坐标 默认 40
	 * @return
	 */
	@Override
	public List<GamersTank> createWeapons(int num, GameModelManager gameModelManager, Group group, final int distance, Dir dir,int x,int y) {
		List<GamersTank> tanks=new LinkedList<>();
		for (int i=0;i<num;i++) {
			tanks.add(createWeapon(x,y,dir,gameModelManager,group));
			x+=distance;
		}
		return tanks;
	}

	@Override
	public List<GamersTank> createWeapons(int num,GameModelManager gameModelManager,Group group, final int distance,Dir dir) {
		List<GamersTank> tanks=new LinkedList<>();
		int x=140;
		int y=40;
		for (int i=0;i<num;i++) {
			tanks.add(createWeapon(x,y,dir,gameModelManager,group));
			x+=distance;
		}
		return tanks;
	}
}

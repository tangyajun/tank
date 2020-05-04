package com.yj.tank;

import java.util.LinkedList;
import java.util.List;

import com.yj.tank.constant.Dir;
import com.yj.tank.constant.Group;
import com.yj.tank.domain.Tank;
import com.yj.tank.view.TankFrame;

/**
 *  坦克工厂
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-02-17:32
 **/
public class TankFactory {
	static Dir[] dirs={Dir.UP,Dir.DOWN,Dir.LEFT,Dir.RIGHT};
	public static Tank createTank(int x,int y,Dir dir, TankFrame tankFrame, Group group) {
		return new Tank(x,y,dir,tankFrame,group);
	}

	/**
	 *
	 * @param num 坦克数量
	 * @param tankFrame
	 * @param group 坦克分组
	 * @param distance 坦克间距
	 * @param dir 坦克方向
	 * @param x 坦克x坐标 默认 140
	 * @param y 坦克 y坐标 默认 40
	 * @return
	 */
	public static List<Tank> createTanks(int num,TankFrame tankFrame,Group group, final int distance,Dir dir,int x,int y) {
		List<Tank> tanks=new LinkedList<>();
		for (int i=0;i<num;i++) {
			tanks.add(createTank(x,y,dir,tankFrame,group));
			x+=distance;
		}
		return tanks;
	}

	public static List<Tank> createTanks(int num,TankFrame tankFrame,Group group, final int distance,Dir dir) {
		List<Tank> tanks=new LinkedList<>();
		int x=140;
		int y=40;
		for (int i=0;i<num;i++) {
			tanks.add(createTank(x,y,dir,tankFrame,group));
			x+=distance;
		}
		return tanks;
	}
}

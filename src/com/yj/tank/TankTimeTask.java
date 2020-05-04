package com.yj.tank;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

import com.yj.tank.constant.Dir;
import com.yj.tank.constant.Group;
import com.yj.tank.domain.AbstractMilitaryWeapon;
import com.yj.tank.domain.Tank;
import com.yj.tank.factory.TankFactory;
import com.yj.tank.view.TankFrame;

/**
 *  坦克定时任务
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-03-0:21
 **/
public class TankTimeTask {

	private TankFrame tkFrame;

	/**
	 * 定时任务当前执行到第几波
	 */
	public static AtomicInteger curTimes=new AtomicInteger(1);

	/**
	 * 定时任务总的执行次数
	 */
	public  int total=3;

	/**
	 * 总的坦克数量
	 */
	public int tankNum=5;

	/**
	 * 坦克间距
	 */
	int distance;

	/**
	 * 坦克速度
	 */
	int badTankSpeed=1;

	/**
	 * 坦克方向
	 */
	Dir badTankDir=Dir.DOWN;

	/**
	 * 定时任务执行的延时时间
	 */
	int delay=10000;

	public TankTimeTask(TankFrame tkFrame,int distance) {
		this.tkFrame=tkFrame;
		this.distance=distance;
	}

	/**
	 * 开始定时任务
	 * @param period 定时任务触发间隔时间
	 * @param curLevelCount 当前关卡数
	 */
	public void start(long period,int curLevelCount) {
		new Timer("tankTimeTask").schedule(new Task(curLevelCount),delay,period);
	}

	public class Task extends TimerTask {

		private int curLevelCount;

		public Task(int curLevelCount) {
			this.curLevelCount=curLevelCount;
		}

		@Override
		public void run() {
			if (curTimes.intValue()<total) {
				if (curLevelCount<3) {
					//List<Tank> tanks = TankFactory.createTanks(tankNum, tkFrame, Group.BAD, distance, badTankDir);
					List<AbstractMilitaryWeapon> tanks =tkFrame.getWeaponFactory().createWeapons(TankFrame.TANK_NUM,tkFrame, Group.BAD,120,Dir.DOWN);
					tanks.stream().forEach(tank -> {
						tank.setSpeedBad(badTankSpeed);
					});
					tkFrame.getTanks().addAll(tanks);
					System.out.println("ScheduledTask");
					curTimes.getAndIncrement();
				}else {
					if (curTimes.intValue()==1 || curTimes.intValue()==4 || curTimes.intValue() ==7) {
						//List<Tank> tanks = TankFactory.createTanks(tankNum, tkFrame, Group.BAD, distance, badTankDir);
						List<AbstractMilitaryWeapon> tanks =tkFrame.getWeaponFactory().createWeapons(TankFrame.TANK_NUM,tkFrame, Group.BAD,120,Dir.DOWN);
						tanks.stream().forEach(tank -> {
							tank.setSpeedBad(badTankSpeed);
						});
						tkFrame.getTanks().addAll(tanks);
						System.out.println("ScheduledTask");
						curTimes.getAndIncrement();
					}else if (curTimes.intValue()==2 || curTimes.intValue() ==5) {
						//List<Tank> tanks = TankFactory.createTanks(tankNum, tkFrame, Group.BAD, distance, Dir.RIGHT,0,120);
						List<AbstractMilitaryWeapon> tanks =tkFrame.getWeaponFactory().createWeapons(TankFrame.TANK_NUM,tkFrame, Group.BAD,120,Dir.DOWN);
						tanks.stream().forEach(tank -> {
							tank.setSpeedBad(badTankSpeed);
						});
						tkFrame.getTanks().addAll(tanks);
						System.out.println("ScheduledTask");
						curTimes.getAndIncrement();
					}else if (curTimes.intValue()==3 || curTimes.intValue()==6) {
						//List<Tank> tanks = TankFactory.createTanks(tankNum, tkFrame, Group.BAD, distance, Dir.RIGHT,400,120);
						List<AbstractMilitaryWeapon> tanks =tkFrame.getWeaponFactory().createWeapons(TankFrame.TANK_NUM,tkFrame, Group.BAD,distance,Dir.RIGHT,400,200);
						tanks.stream().forEach(tank -> {
							tank.setSpeedBad(badTankSpeed);
						});
						tkFrame.getTanks().addAll(tanks);
						System.out.println("ScheduledTask");
						curTimes.getAndIncrement();
					}
				}
			}
		}
	}

	public TankFrame getTkFrame() {
		return tkFrame;
	}

	public void setTkFrame(TankFrame tkFrame) {
		this.tkFrame = tkFrame;
	}

	public static AtomicInteger getCurTimes() {
		return curTimes;
	}

	public static void setCurTimes(AtomicInteger curTimes) {
		TankTimeTask.curTimes = curTimes;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getTankNum() {
		return tankNum;
	}

	public void setTankNum(int tankNum) {
		this.tankNum = tankNum;
	}

	public int getBadTankSpeed() {
		return badTankSpeed;
	}

	public void setBadTankSpeed(int badTankSpeed) {
		this.badTankSpeed = badTankSpeed;
	}

	public Dir getBadTankDir() {
		return badTankDir;
	}

	public void setBadTankDir(Dir badTankDir) {
		this.badTankDir = badTankDir;
	}
}

package com.yj.tank;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.yj.tank.constant.Dir;
import com.yj.tank.constant.Group;
import com.yj.tank.model.AbstractMilitaryWeapon;
import com.yj.tank.view.TankFrame;

/**
 *  坦克定时任务
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-03-0:21
 **/
public class TankTimeTask {

	private GameModelManager gameModelManager;

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
	 * 定时任务执行的延时时间,两次开始执行最小间隔时间
	 */
	int delay=50;

	//Timer timer=new Timer();

	public static AtomicInteger execTimes=new AtomicInteger(0);

	ScheduledExecutorService scheduledExecutorService= Executors.newSingleThreadScheduledExecutor();

	public TankTimeTask(GameModelManager gameModelManager,int distance) {
		this.gameModelManager=gameModelManager;
		this.distance=distance;
	}

	/**
	 * 开始定时任务
	 * @param period 经过period时间后再次执行调度
	 * @param curLevelCount 当前关卡数
	 */
	public void start(long period,int curLevelCount) {
		//scheduledExecutorService.scheduleWithFixedDelay(new Task(curLevelCount),delay,period, TimeUnit.SECONDS);
		CompletableFuture.supplyAsync(() -> {
			for(int i=0; i<total; i++){
				try {
					Thread.sleep(period);
					createTankTask(curLevelCount);
				} catch (Exception e) { }
			}
			return null;
		});
	}

	public class Task extends TimerTask {

		private int curLevelCount;

		public Task(int curLevelCount) {
			this.curLevelCount=curLevelCount;
		}

		@Override
		public void run() {
			System.out.println("任务执行次数："+execTimes.get());
			System.out.println("定时任务执行："+System.currentTimeMillis());

			execTimes.incrementAndGet();
			System.out.println("定时任务执行次数："+execTimes.get());
		}
	}

	public void createTankTask(int curLevelCount) {
		if (curTimes.intValue()<total) {
			if (curLevelCount<3) {
				//List<Tank> tanks = TankFactory.createTanks(tankNum, tkFrame, Group.BAD, distance, badTankDir);
				List<AbstractMilitaryWeapon> tanks =gameModelManager.getWeaponFactory().createWeapons(GameModelManager.ENEMY_TANK_NUM,gameModelManager, Group.BAD,GameModelManager.ENEMY_TANK_DISTANCE,Dir.DOWN);
				tanks.stream().forEach(tank -> {
					tank.setSpeedBad(badTankSpeed);
				});
				gameModelManager.getEnemyTanks().addAll(tanks);
				System.out.println("ScheduledTask");
				curTimes.getAndIncrement();
				System.out.println("---------------------------满足条件1当前等级："+curLevelCount);
			}else {

				if (curTimes.intValue()==1 || curTimes.intValue()==4 || curTimes.intValue() ==7) {
					//List<Tank> tanks = TankFactory.createTanks(tankNum, tkFrame, Group.BAD, distance, badTankDir);
					List<AbstractMilitaryWeapon> tanks =gameModelManager.getWeaponFactory().createWeapons(GameModelManager.ENEMY_TANK_NUM,gameModelManager, Group.BAD,GameModelManager.ENEMY_TANK_DISTANCE,Dir.DOWN);
					tanks.stream().forEach(tank -> {
						tank.setSpeedBad(badTankSpeed);
					});
					gameModelManager.getEnemyTanks().addAll(tanks);
					System.out.println("ScheduledTask");
					curTimes.getAndIncrement();
					System.out.println("---------------------------满足条件2当前等级："+curLevelCount);
				}else if (curTimes.intValue()==2 || curTimes.intValue() ==5) {
					//List<Tank> tanks = TankFactory.createTanks(tankNum, tkFrame, Group.BAD, distance, Dir.RIGHT,0,120);
					List<AbstractMilitaryWeapon> tanks =gameModelManager.getWeaponFactory().createWeapons(GameModelManager.ENEMY_TANK_NUM,gameModelManager, Group.BAD,GameModelManager.ENEMY_TANK_DISTANCE,Dir.DOWN);
					tanks.stream().forEach(tank -> {
						tank.setSpeedBad(badTankSpeed);
					});
					gameModelManager.getEnemyTanks().addAll(tanks);
					System.out.println("ScheduledTask");
					curTimes.getAndIncrement();
					System.out.println("---------------------------满足条件3当前等级："+curLevelCount);
				}else if (curTimes.intValue()==3 || curTimes.intValue()==6) {
					//List<Tank> tanks = TankFactory.createTanks(tankNum, tkFrame, Group.BAD, distance, Dir.RIGHT,400,120);
					List<AbstractMilitaryWeapon> tanks =gameModelManager.getWeaponFactory().createWeapons(GameModelManager.ENEMY_TANK_NUM,gameModelManager, Group.BAD,distance,Dir.RIGHT,400,200);
					tanks.stream().forEach(tank -> {
						tank.setSpeedBad(badTankSpeed);
					});
					gameModelManager.getEnemyTanks().addAll(tanks);
					System.out.println("ScheduledTask");
					curTimes.getAndIncrement();
					System.out.println("---------------------------满足条件4当前等级："+curLevelCount);
				}
			}
		}
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

	public GameModelManager getGameModelManager() {
		return gameModelManager;
	}

	public void setGameModelManager(GameModelManager gameModelManager) {
		this.gameModelManager = gameModelManager;
	}

	public static AtomicInteger getCurTimes() {
		return curTimes;
	}

	public static void setCurTimes(AtomicInteger curTimes) {
		TankTimeTask.curTimes = curTimes;
	}
}

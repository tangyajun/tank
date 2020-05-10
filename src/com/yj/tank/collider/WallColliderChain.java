package com.yj.tank.collider;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.yj.tank.ConfigProperties;
import com.yj.tank.collider.Collider;
import com.yj.tank.model.GameProp;
import com.yj.tank.util.ColliderLoadUtils;

/**
 *  游戏中的道具跟墙碰撞检测器
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-09-9:30
 **/
public class WallColliderChain implements Collider {

	private List<Collider> colliders=new LinkedList<>();

	public WallColliderChain() {
		ColliderLoadUtils.initCollider(colliders,"wall.colliders");
	}

	public void addCollider(Collider collider) {
		colliders.add(collider);
	}

	public void removeCollider(Collider collider) {
		colliders.remove(collider);
	}

	@Override
	public boolean collide(GameProp gameProp1, GameProp gameProp2) {
		Iterator<Collider> colliderIterator=colliders.iterator();
		boolean flag=false;
		while(colliderIterator.hasNext()) {
			Collider collider=colliderIterator.next();
			flag=collider.collide(gameProp1,gameProp2);
			if (flag){
				break;
			}
		}
		return flag;
	}
}

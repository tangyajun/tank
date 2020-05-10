package com.yj.tank.collider;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.yj.tank.ConfigProperties;
import com.yj.tank.model.GameProp;
import com.yj.tank.util.ColliderLoadUtils;

/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-05-21:46
 **/
public class ColliderChain implements Collider {

	private List<Collider> colliders=new LinkedList<>();

	public ColliderChain() {
		ColliderLoadUtils.initCollider(colliders,"tank.colliders");
	}

	public void addCollider(Collider collider) {
		colliders.add(collider);
	}

	public void removeCollider(Collider collider) {
		colliders.remove(collider);
	}

	public List<Collider> getColliders() {
		return colliders;
	}

	@Override
	public boolean collide(GameProp gameProp1,GameProp gameProp2) {
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

	public static void main(String[] args) {
		ColliderChain colliderChain=new ColliderChain();

		System.out.println("test");
	}
}

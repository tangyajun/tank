package com.yj.tank;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.yj.tank.model.GameProp;

/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-05-21:46
 **/
public class ColliderChain {

	private static ColliderChain INSTANCE=new ColliderChain();

	private List<Collider> colliders=new LinkedList<>();

	private ColliderChain() {

	}

	public static ColliderChain getInstance() {
		return INSTANCE;
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

	public void chain(GameProp gameProp1,GameProp gameProp2) {
		Iterator<Collider> colliderIterator=colliders.iterator();
		while(colliderIterator.hasNext()) {
			Collider collider=colliderIterator.next();
			if (collider.collide(gameProp1,gameProp2)){
				break;
			}
		}
	}
}

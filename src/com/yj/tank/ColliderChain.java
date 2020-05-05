package com.yj.tank;

import java.util.LinkedList;
import java.util.List;

/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-05-21:46
 **/
public class ColliderChain {

	private static ColliderChain INSTANCE=new ColliderChain();

	private ColliderChain() {

	}

	public static ColliderChain getInstance() {
		return INSTANCE;
	}

	private List<Collider> colliders=new LinkedList<>();

	public void addCollider(Collider collider) {
		colliders.add(collider);
	}

	public void removeCollider(Collider collider) {
		colliders.remove(collider);
	}
}

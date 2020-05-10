package com.yj.tank.util;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.yj.tank.ConfigProperties;
import com.yj.tank.collider.Collider;

/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-09-13:07
 **/
public abstract class ColliderLoadUtils {

	public static void initCollider(List<Collider> colliders,String colliderKey) {
		String colliderNameStr= ConfigProperties.getInstance().getString(colliderKey);
		String[] colliderNames=colliderNameStr.split(",");
		for (String colliderName : colliderNames) {
			try {
				Class clazz=Class.forName(colliderName.trim());
				Collider collider=(Collider) clazz.getDeclaredConstructor().newInstance();
				colliders.add(collider);
			}catch (ClassNotFoundException | NoSuchMethodException |
					IllegalAccessException | InstantiationException |
					InvocationTargetException e)
			{
				e.printStackTrace();
			}
		}
	}
}

package com.yj.tank.domain;

import com.yj.tank.ResourceManager;
import com.yj.tank.view.TankFrame;
import jdk.internal.org.objectweb.asm.tree.FieldInsnNode;

/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-04-17:12
 **/
public class PlaneExplode extends AbstractExplode {
	public static final int WIDTH= ResourceManager.explodes[0].getWidth();;
	public static final int HEIGHT=ResourceManager.explodes[0].getHeight();
	public PlaneExplode(int x,int y,int width,int height, TankFrame tankFrame) {
		super(x,y,width,height,tankFrame);
	}
}

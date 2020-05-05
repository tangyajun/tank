package com.yj.tank.model;

import java.awt.Image;

import com.yj.tank.Audio;
import com.yj.tank.GameModelManager;
import com.yj.tank.ResourceManager;

/**
 * 爆炸抽象类
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-04-16:35
 **/
public abstract class AbstractExplode extends GameProps {

	/**
	 * 宽度
	 */
	protected int width;

	/**
	 * 高度
	 */
	protected int height;

	protected int step=0;

	protected GameModelManager gameModelManager=null;

	/**
	 * 爆炸图片
	 */
	protected Image[] images=ResourceManager.explodes;

	/**
	 * 声音文件路径
	 */
	protected String audioFilePath="audio/explode.wav";

	public AbstractExplode(int x,int y,GameModelManager gameModelManager) {
		this(x,y,gameModelManager,null,null);
	}

	public AbstractExplode(int x,int y,GameModelManager gameModelManager,Image[] images) {
		this(x,y,gameModelManager,images,null);
	}

	public AbstractExplode(int x,int y,GameModelManager gameModelManager,Image[] images,String audioFilePath) {
		super(x,y);
		this.gameModelManager=gameModelManager;
		if (images !=null) {
			this.images = images;
		}
		if (audioFilePath != null && !"".equals(audioFilePath)) {
			new Thread(() -> new Audio(audioFilePath).play()).start();
		}else {
			new Thread(() -> new Audio(this.audioFilePath).play()).start();
		}
	}

	public AbstractExplode(int x,int y,int width,int height,GameModelManager gameModelManager) {
		this(x,y,width,height,gameModelManager,null,null);
	}

	public AbstractExplode(int x,int y,int width,int height,GameModelManager gameModelManager,Image[] images) {
		this(x,y,width,height,gameModelManager,images,null);
	}

	public AbstractExplode(int x,int y,int width,int height,GameModelManager gameModelManager,Image[] images,String audioFilePath) {
		super(x,y);
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.gameModelManager=gameModelManager;
		if (images !=null) {
			this.images = images;
		}
		if (audioFilePath != null && !"".equals(audioFilePath)) {
			new Thread(() -> new Audio(audioFilePath).play()).start();
		}else {
			new Thread(() -> new Audio(this.audioFilePath).play()).start();
		}
	}

}

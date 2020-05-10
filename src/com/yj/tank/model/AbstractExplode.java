package com.yj.tank.model;

import java.awt.Image;

import com.yj.tank.Audio;
import com.yj.tank.GameModelManager;
import com.yj.tank.ResourceManager;
import com.yj.tank.thread.ThreadConfig;

/**
 * 爆炸抽象类
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-04-16:35
 **/
public abstract class AbstractExplode extends GameProp {

	protected int step=0;

	/**
	 * 爆炸图片
	 */
	protected Image[] images=ResourceManager.explodes;

	/**
	 * 声音文件路径
	 */
	protected String audioFilePath="audio/explode.wav";

	public AbstractExplode(int x,int y,int width,int height) {
		this(x,y,width,height,null,null);
	}

	public AbstractExplode(int x,int y,int width,int height,Image[] images) {
		this(x,y,width,height,images,null);
	}

	public AbstractExplode(int x,int y,int width,int height,Image[] images,String audioFilePath) {
		super(x,y,width,height);
		if (images !=null) {
			this.images = images;
		}
		if (audioFilePath != null && !"".equals(audioFilePath)) {
			ThreadConfig.executorService.execute(() -> new Audio(audioFilePath).play());
			//new Thread(() -> new Audio(audioFilePath).play()).start();
		}else {
			//new Thread(() -> new Audio(this.audioFilePath).play()).start();
			ThreadConfig.executorService.execute(() -> new Audio(audioFilePath).play());
		}
	}

}

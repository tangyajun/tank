package com.yj.tank;

import com.yj.tank.view.TankFrame;

/**
 * 程序主入口
 * @author tangyajun
 *
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {
		start();
	}

	public static void start() throws InterruptedException {
		TankFrame tankFrame=new TankFrame();

		//new Thread(()->new Audio("audio/war1.wav").loop()).start();
		//new Thread(()->new Audio("audio/start.wav").loop()).start();
		while (true) {
			try {
				Thread.sleep(50);
				tankFrame.repaint();
				tankFrame.getPlayContainer().repaint();
			}
			catch (InterruptedException ex) {
				throw ex;
			}
		}
	}

	public static void pause() {

	}
}

package com.yj.tank;

import java.awt.Button;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.yj.tank.constant.GameStatus;
import com.yj.tank.view.TankFrame;


/**
 *  游戏开始监听器
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-03-9:27
 **/
public class PlayListener  extends MouseAdapter {
	TankFrame tankFrame;
	GameModelManager modelManager=GameModelManager.getInstance();
	public PlayListener(TankFrame tankFrame) {
		this.tankFrame=tankFrame;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		modelManager.clear();
		tankFrame.setGameStatus(GameStatus.RUNNING);
		modelManager.init();
		Button button=(Button) e.getSource();
		button.setVisible(false);
		//modelManager.getTankTimeTask().start(15000,GameModelManager.curLevelCount);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Button button=(Button) e.getSource();
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
}

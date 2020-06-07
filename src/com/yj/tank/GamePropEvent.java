package com.yj.tank;

/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-10-13:13
 **/
public interface GamePropEvent {
	/**
	 * 获取事件源对象
	 * @param <T>
	 * @return
	 */
	<T> T getSource();
}

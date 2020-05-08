package com.yj.tank.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 *
 *  @Description TO DO
 *  @author tangyajun
 *  @create 2020-05-08-21:21
 **/
public class ThreadConfig {

	public static ScheduledExecutorService executorService=Executors.newScheduledThreadPool(10);

}

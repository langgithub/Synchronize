package com.lang.excutors;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class MyExcutor {

	public static void main(String[] args) {
		ExecutorService excutor=Executors.newFixedThreadPool(3);
		//Executors.newCachedThreadPool();
		//Executors.newSingleThreadExecutor();
		for(int j=0;j<10;j++){
			final int task=j;
			excutor.execute(new Runnable() {
				
				public void run() {
					// TODO Auto-generated method stub
					for(int i=0;i<5;i++){
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println(Thread.currentThread()+"在做"+"task "+task+"中第"+i+"步");
					}
					System.out.println("task "+task+"完成！！！");
				}
			});
		}
		//excutor.shutdown();
		//excutor.shutdownNow();
		Executors.newScheduledThreadPool(2).schedule(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("boming!");
			}
		}, 1, TimeUnit.SECONDS);
		
		
		Executors.newScheduledThreadPool(2).scheduleAtFixedRate(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("bom bom bom");
			}
		}, 4, 1, TimeUnit.SECONDS);
		
		//reent
	}
}

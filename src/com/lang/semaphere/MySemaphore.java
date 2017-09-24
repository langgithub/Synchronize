package com.lang.semaphere;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class MySemaphore {

	public static void main(String[] args) {
		ExecutorService executorService=Executors.newCachedThreadPool();
		
		final Semaphore semaphore=new Semaphore(1, true);
		for(int i=0;i<10;i++){
			executorService.execute(new Runnable() {
				
				public void run() {
					// TODO Auto-generated method stub
					try {
						semaphore.acquire();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread()+"正在工作，已用信号灯的"+(3-semaphore.availablePermits()));
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
					semaphore.release();
					System.out.println(Thread.currentThread()+"离开，当前已有并发："+(3-semaphore.availablePermits()));
				}
			});
			
			
		}
	}
}

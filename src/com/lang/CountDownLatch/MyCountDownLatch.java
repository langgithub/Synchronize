package com.lang.CountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyCountDownLatch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ExecutorService executorService=Executors.newCachedThreadPool();
		final CountDownLatch count1=new CountDownLatch(1);
		final CountDownLatch count2=new CountDownLatch(3);
		for(int i=0;i<3;i++){
			executorService.execute(new Runnable() {
				
				public void run() {
					// TODO Auto-generated method stub
					System.out.println(Thread.currentThread().getName()+"准备就绪");
					try {
						count1.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					try {
						Thread.sleep((long) (Math.random()*5000));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+"跑步完成");
					
					count2.countDown();
					
				}
			});
		}
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		count1.countDown();
		
		try {
			count2.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("统计结果");
	}

}

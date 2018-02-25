package com.lang.description;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;

/**
 * 
 * @author lang
 *
 */
public class Test2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//final SynchronousQueue<String> queue=new SynchronousQueue<String>();
		final ArrayBlockingQueue<String> queue=new ArrayBlockingQueue<String>(1);
		final Semaphore semaphore=new Semaphore(1);
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				public void run() {
					// TODO Auto-generated method stub
					try {
						semaphore.acquire();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						String food=queue.take();
						doSomeThing(food);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					semaphore.release();
				}
			}).start();
		}
		for(int i=0;i<10;i++){
			String productFood="生产者生产的food"+i;
			try {
				queue.put(productFood);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	public static void doSomeThing(String str){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+"消费者消费food来致："+str);
	}

}

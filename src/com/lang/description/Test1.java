package com.lang.description;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 开启四个线程打印16条数据
 * @author lang
 *
 */
public class Test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final ArrayBlockingQueue<String> queue=new ArrayBlockingQueue<String>(16);
		for(int i=0;i<4;i++){
			new Thread(new Runnable() {
				
				public void run() {
					// TODO Auto-generated method stub
					while(true){
						try {
							String log=queue.take();
							print(log);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
	 
		for(int i=0;i<16;i++){
			String log="日志 "+i;
			try {
				queue.put(log);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	
	}
	
	public static void print(String str){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+" 打印数据："+str);
	}
	

}

package com.lang.synchronize;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTraditionalSynchronized {

	private static boolean isSub=true;//子线程先执行
	public static void main(String[] args) {
		
		final Task task=new Task();
		new Thread(new Runnable() {
			public void run() {
				for(int i=0;i<50;i++){
					task.sub();
				}
			}
		}).start();
		
		for(int i=0;i<50;i++){
			task.mainT();
		}
	}
	
	static class Task{
		Lock lock=new ReentrantLock();
		Condition condition=lock.newCondition();
		public void sub(){
			
			lock.lock();
			try{
				while(!isSub){
					try {
						condition.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				for(int j=0;j<10;j++){
					System.out.println("subThread:"+j);
				}
				isSub=false;
				condition.signal();
			}finally{
				lock.unlock();
			}
		}
		public void mainT() {	
			lock.lock();
			try{
				while(isSub){
					try {
						condition.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				for(int j=0;j<20;j++){
					System.out.println("mainThread:"+j);
				}
				isSub=true;
				condition.signal();
				
			}finally{
				lock.unlock();
			}
				
		}
	}
}

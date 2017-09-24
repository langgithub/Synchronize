package com.lang.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyCyclicBarrier {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ExecutorService executorService=Executors.newCachedThreadPool();
		final CyclicBarrier cyclicbarrir=new CyclicBarrier(3);
		for(int i=0;i<3;i++){
			executorService.execute(new Runnable() {
				
				public void run() {
					// TODO Auto-generated method stub
					try {
						Thread.sleep((long) (Math.random()*10000));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+"�����ǰ�ȴ�����"
							+ "��"+cyclicbarrir.getNumberWaiting()+
							((cyclicbarrir.getNumberWaiting()==2)?"���ﵽ���ˣ����Գ���":"������û��"));
					try {
						cyclicbarrir.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					System.out.println("go");
					
				}
			});
		}
	}

}

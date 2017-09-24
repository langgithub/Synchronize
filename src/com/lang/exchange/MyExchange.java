package com.lang.exchange;

import java.util.concurrent.Exchanger;

public class MyExchange {

	public static void main(String[] args) {
		
		final Exchanger<String> change=new Exchanger<String>();
		new Thread(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				String data="数据一";
				try {
					Thread.sleep((long) (Math.random()*5000));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					System.out.println(Thread.currentThread()+data+"准备成功");
					String other=change.exchange(data);
					System.out.println(Thread.currentThread()+"获得数据"+other);

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		
		
		new Thread(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				String data="数据二";
				try {
					Thread.sleep((long) (Math.random()*5000));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					System.out.println(Thread.currentThread()+data+"准备成功");
					String other=change.exchange(data);
					System.out.println(Thread.currentThread()+"获得数据"+other);

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}).start();
	}
}

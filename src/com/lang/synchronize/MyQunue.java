package com.lang.synchronize;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.lang.synchronize.MySynchronize.Task;

public class MyQunue {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final Task task=new MyQunue().new Task();
		new Thread(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				for(int i=0;i<50;i++){
					task.subThreahTask();
				}
			}
		}).start();
		
		for(int i=0;i<50;i++){
			task.mainThreakTask();
		}
	}
	
    class Task{
    	BlockingQueue<Integer> queue1=new ArrayBlockingQueue<Integer>(1);
    	BlockingQueue<Integer> queue2=new ArrayBlockingQueue<Integer>(1);
    	{
    		try {
				queue1.put(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	public void subThreahTask(){
            try {
				queue1.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		for(int i=0;i<10;i++){
    			System.out.println("subThreakTask"+i);
    		}
    		try {
				queue2.put(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}
    	
    	public void mainThreakTask(){
    		try {
				queue2.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		for(int i=0;i<20;i++){
    			System.out.println("mainThreakTask"+i);
    		}
    		try {
				queue1.put(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }

}

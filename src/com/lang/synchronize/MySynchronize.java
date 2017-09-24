package com.lang.synchronize;

public class MySynchronize {

	public static void main(String[] args) {
		final Task task=new MySynchronize().new Task();
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
    	private boolean isSub=true;
    	public synchronized void subThreahTask(){
    		while(!isSub){	
    			try {
    				wait();
    			} catch (InterruptedException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
    		for(int i=0;i<10;i++){
    			System.out.println("subThreakTask"+i);
    		}
    		isSub=false;
    		this.notify();
    		
    		
    	}
    	
    	public synchronized void mainThreakTask(){
    		while(isSub){
    			try {
    				wait();
    			} catch (InterruptedException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
    		for(int i=0;i<20;i++){
    			System.out.println("mainThreakTask"+i);
    		}
    		isSub=true;
    		this.notify();
    		
    	}
    }
}

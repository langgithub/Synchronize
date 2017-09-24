package com.lang.timer;

import java.util.Timer;
import java.util.TimerTask;



public class TraditionalTimer {

	static int ti=0;
	public static void main(String[] args) {
		class job extends TimerTask{

			public void run() {
				// TODO Auto-generated method stub
				int i=(ti++)%2;
				System.out.println("boming!"+i);
				new Timer().schedule(new job(), 2000+i*2000);
			}
			
		}
		new Timer().schedule(new job(), 2000);
	}
	
	
}

package com.lang.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheDemo {

	private Map map=new HashMap<String, String>();
	public static void main(String[] args) {
		//ReentrantLock  ReentrantReadWriteLock
	}
	
	public /*synchronized*/ Object getData(String key){
		ReentrantReadWriteLock rwl=new ReentrantReadWriteLock();
		rwl.readLock().lock();
		String value=null;
		try{
			value=(String) map.get(key);
			if(value==null){
				rwl.readLock().unlock();
				rwl.writeLock().lock();
				try{
					if(value==null){
						value="13";
					}
				}finally{
					rwl.writeLock().unlock();
				}
				rwl.readLock().lock();
			}
		}finally{
			rwl.readLock().unlock();
		}
		return value;
	}

	public void getSource(){
		ReentrantLock reentrantLock=new ReentrantLock();
		Object key = map.get("key");
		if(key==null){
			try {
				reentrantLock.lock();
				while (key==null){
					key=new Object();
				}
			}finally {
				reentrantLock.unlock();
			}
		}
	}
	public void getSource1(){
		Object key = map.get("key");
		if(key==null){
			synchronized (CacheDemo.class){
				while (key==null){
					key=new Object();
				}
			}
		}
	}

	public void getSource2(){
		ReentrantReadWriteLock reentrant=new ReentrantReadWriteLock();
		try {
			reentrant.readLock().lock();
			Object key = map.get("key");
			if(key==null){
				reentrant.readLock().unlock();
				try {
					reentrant.writeLock().lock();
					while (key==null){
						key=new Object();
					}
				}finally {
					reentrant.writeLock().unlock();
				}
				reentrant.readLock().lock();
			}
		}finally {
			reentrant.readLock().unlock();
		}
	}



}

package com.lang.synchronize;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by lang on 2017/9/24.
 */
public class Version2ReentranLock {

    public static void main(String[] args){

        Share share = new Version2ReentranLock().new Share();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    share.tSub();
                }
            }
        }).start();

        while (true){
            share.tMain();
        }
    }

    class Share{
        private boolean isSub=true;
        ReentrantLock reentrantLock=new ReentrantLock();
        Condition condition = reentrantLock.newCondition();
        public void tSub(){
            try {
                reentrantLock.lock();
                while(!isSub){
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int i=0;i<20;i++){
                    System.out.println("第"+i+"次"+Thread.currentThread().getName());
                }
                isSub=false;
                condition.signal();
            }finally {
                reentrantLock.unlock();
            }
        }
        public synchronized void tMain(){
           try {
               reentrantLock.lock();
               while(isSub){
                   try {
                       condition.await();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
               for (int i=0;i<50;i++){
                   System.out.println("第"+i+"次"+Thread.currentThread().getName());
               }
               isSub=true;
               condition.signal();
           }finally {
               reentrantLock.unlock();
           }
        }
    }
}

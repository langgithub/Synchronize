package com.lang.synchronize;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by lang on 2017/9/24.
 */
public class Vsersion2Qunue {

    public static void main(String[] args){

        Share share = new Vsersion2Qunue().new Share();
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
        private BlockingQueue<Integer> qunue1=new ArrayBlockingQueue<Integer>(1);
        private BlockingQueue<Integer> qunue2=new ArrayBlockingQueue<Integer>(1);
        {
            try {
                qunue1.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public void tSub(){
            try {
                qunue1.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i=0;i<20;i++){
                System.out.println("第"+i+"次"+Thread.currentThread().getName());
            }
            try {
                qunue2.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public void tMain(){
            try {
                qunue2.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i=0;i<50;i++){
                System.out.println("第"+i+"次"+Thread.currentThread().getName());
            }
            try {
                qunue1.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

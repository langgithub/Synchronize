package com.lang.synchronize;

/**
 * Created by lang on 2017/9/24.
 */
public class Version2Traditional {

    public static void main(String[] args){

        Share share = new Version2Traditional().new Share();

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
        public synchronized void tSub(){
            while(!isSub){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i=0;i<20;i++){
                System.out.println("第"+i+"次"+Thread.currentThread().getName());
            }
            isSub=false;
            notify();
        }
        public synchronized void tMain(){
            while (isSub){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i=0;i<50;i++){
                System.out.println("第"+i+"次"+Thread.currentThread().getName());
            }
            isSub=true;
            notify();
        }
    }

}

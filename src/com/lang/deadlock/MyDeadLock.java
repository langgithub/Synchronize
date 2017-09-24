package com.lang.deadlock;


/**
 * Created by lang on 2017/9/24.
 */
public class MyDeadLock {


    private Object o=new Object();

    public static void main(String[] args){

        MyDeadLock myDeadLock=new MyDeadLock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                myDeadLock.getSource();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                myDeadLock.getSource();
            }
        }).start();
    }

    public void getSource(){
        System.out.print("资源申请");
        synchronized (o){
            try {
                o.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("资源释放");
    }
}

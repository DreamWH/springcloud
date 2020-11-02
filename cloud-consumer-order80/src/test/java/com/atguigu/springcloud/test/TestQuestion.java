package com.atguigu.springcloud.test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class TestQuestion {

    static AtomicInteger atomicInteger = new AtomicInteger(100);
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100,1);

    public static void main(String[] args) {

        System.out.println("========ABA问题的解决==========");
        //AtomicStampedReference 使用这个类增加版本号，t1线程虽然也经过了ABA过程，但是在每次都会更新版本号，这时t2线程在使用和t1初始相同的版本号进行操作时，就会操作失败，因为版本号不相同
        new Thread(new Runnable() {
            @Override
            public void run() {
                int stamp = atomicStampedReference.getStamp();
                System.out.println(Thread.currentThread().getName()+"\t当前版本号："+ stamp);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                boolean b = atomicStampedReference.compareAndSet(100, 101, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
                System.out.println(Thread.currentThread().getName()+"\t第一次修改"+b+"\t第二次的版本号："+atomicStampedReference.getStamp());
                boolean b1 = atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
                System.out.println(Thread.currentThread().getName()+"\t第二次修改"+b+"\t第三次的版本号："+atomicStampedReference.getStamp());
            }
        },"t1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int stamp = atomicStampedReference.getStamp();
                System.out.println(Thread.currentThread().getName()+"\t当前版本号："+ stamp);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                boolean b = atomicStampedReference.compareAndSet(100, 101, stamp, atomicStampedReference.getStamp() + 1);
                System.out.println(Thread.currentThread().getName()+"\t第一次修改"+b+"\t当前最新的版本号："+atomicStampedReference.getStamp());
            }
        },"t2").start();



    }
}

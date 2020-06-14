package com.puyang.bjpowernode.java;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ThreadTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Thread t1 = new Consumer(list);
        Thread t2 = new Producer(list);

        t1.setName("Consumer");
        t2.setName("Producer");

        t1.start();
        t2.start();
    }
}

class Consumer extends Thread {
    private final List<Integer> list;


    public Consumer(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        String prev = "";
        String curt = "";
        while (true) {
            synchronized (list) {
                if (prev.equals(curt)) {
                    System.out.println(Thread.currentThread().getName() + "再次抢到，UUID: " + curt);
                }
                prev = curt;
                curt = UUID.randomUUID().toString();
                if (list.size() == 0) {
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + "消费第" + list.get(0) + "个产品");
                list.remove(0);
                list.notifyAll();
            }
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}

class Producer extends Thread {
    private final List<Integer> list;
    private int i;

    public Producer(List<Integer> list) {
        this.list = list;
        this.i = 0;
    }

    @Override
    public void run() {
        String prev = "";
        String curt = "";
        while (true) {
            synchronized (list) {
                if (prev.equals(curt)) {
                    System.out.println(Thread.currentThread().getName() + "再次抢到，UUID: " + curt);
                }
                prev = curt;
                curt = UUID.randomUUID().toString();
                if (list.size() > 10) {
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                list.add(++i);
                System.out.println(Thread.currentThread().getName() + "生产第" + i + "个产品");
                list.notifyAll();
            }
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}

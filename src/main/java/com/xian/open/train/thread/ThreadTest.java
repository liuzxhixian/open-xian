package com.xian.open.train.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xian
 * @description
 * @createTime 2021/11/4 11:14
 */
public class ThreadTest {

    AtomicInteger index = new AtomicInteger(1);
    StringBuffer buffer = new StringBuffer();




}

class HandleA implements Runnable {
    String str;
    int index;

    public HandleA(String str) {
        this.str = str;
    }

    @Override
    public void run() {

    }

}

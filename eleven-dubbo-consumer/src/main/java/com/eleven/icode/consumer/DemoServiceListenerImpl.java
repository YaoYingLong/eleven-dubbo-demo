package com.eleven.icode.consumer;

import com.eleven.icode.DemoServiceListener;

public class DemoServiceListenerImpl implements DemoServiceListener {
    @Override
    public void changed(String msg) {
        System.out.println("被回调了：" + msg);
    }
}

package com.eleven.icode.comsumer;

import com.eleven.icode.framework.proxy.ProxyFactory;
import com.eleven.icode.provider.api.HelloService;

public class Consumer {

    public static void main(String[] args) {

//        Invocation invocation = new Invocation(HelloService.class.getName(), "sayHello", new Class[]{String.class}, new Object[]{"eleven大都督"});
//        NettyClient nettyClient = new NettyClient();
//        String result = nettyClient.send("localhost", 8080, invocation);
//        System.out.println(result);

        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        for (int i = 0; i < 10; i++) {
            String result = helloService.sayHello("eleven大大111");
            System.out.println(result);
        }

    }
}

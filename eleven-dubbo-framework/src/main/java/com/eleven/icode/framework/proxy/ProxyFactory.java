package com.eleven.icode.framework.proxy;

import com.eleven.icode.framework.LoadBalance;
import com.eleven.icode.framework.URL;
import com.eleven.icode.framework.protocol.Invocation;
import com.eleven.icode.framework.protocol.NettyClient;
import com.eleven.icode.framework.register.ZookeeperRegister;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class ProxyFactory<T> {
    @SuppressWarnings("unchecked")
    public static <T> T getProxy(final Class interfaceClass) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) {
                Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(), method.getParameterTypes(), args);
                try {
                    NettyClient nettyClient = new NettyClient();
                    List<URL> urls = ZookeeperRegister.get(interfaceClass.getName());
                    URL url = LoadBalance.random(urls);
                    System.out.println("消费者选择的服务提供者地址是：" + url.toString());
                    String result = nettyClient.send(url.getHostname(), url.getPort(), invocation);
                    return result;
                } catch (Exception e) {
                    e.printStackTrace();
                    return doMock(invocation);
                }
            }
        });
    }

    private static Object doMock(Invocation invocation) {
        return "容错逻辑";
    }
}

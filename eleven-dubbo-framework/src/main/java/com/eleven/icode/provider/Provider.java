package com.eleven.icode.provider;

import com.eleven.icode.framework.URL;
import com.eleven.icode.framework.protocol.NettyServer;
import com.eleven.icode.framework.register.LocalRegister;
import com.eleven.icode.framework.register.ZookeeperRegister;
import com.eleven.icode.provider.api.HelloService;
import com.eleven.icode.provider.impl.HelloServiceImpl;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Provider {

    public static void main(String[] args) throws UnknownHostException, InterruptedException {
        String interfaceName = HelloService.class.getName();
        URL url = new URL(InetAddress.getLocalHost().getHostAddress(), 8081);

        LocalRegister.regist(interfaceName, HelloServiceImpl.class);
        ZookeeperRegister.regist(interfaceName, url);

        NettyServer nettyServer = new NettyServer();
        nettyServer.start(url.getHostname(), url.getPort());
        System.out.println(String.format("success, 成功暴露%s服务，地址为%s", interfaceName, url.toString()));
        Thread.sleep(Integer.MAX_VALUE);
    }
}

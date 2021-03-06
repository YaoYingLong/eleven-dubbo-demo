package com.eleven.icode.consumer;

import com.eleven.icode.DemoService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

@EnableAutoConfiguration
public class TimeoutDubboConsumerDemo {

    @Reference(version = "generic", timeout = 6000)
    private DemoService demoService;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TimeoutDubboConsumerDemo.class);
        DemoService demoService = context.getBean(DemoService.class);
        // 服务调用超时时间为1秒，默认为3秒，如果这1秒内没有收到服务结果，则会报错
        System.out.println(demoService.sayHello("eleven")); //xxservestub
    }
}

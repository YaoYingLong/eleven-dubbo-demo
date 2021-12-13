package com.eleven.icode.consumer;

import com.eleven.icode.DemoService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@EnableAutoConfiguration
public class StubDubboConsumerDemo {
    @Reference(version = "timeout", timeout = 1000, stub = "com.eleven.icode.DemoServiceStub")
//    @Reference(version = "timeout", timeout = 1000, stub = "true")
    private DemoService demoService;

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = SpringApplication.run(StubDubboConsumerDemo.class);
        DemoService demoService = context.getBean(DemoService.class);
        System.out.println(demoService.sayHello("eleven"));
    }
}

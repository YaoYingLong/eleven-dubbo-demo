package com.eleven.icode.consumer;

import com.eleven.icode.DemoService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@EnableAutoConfiguration
public class MockDubboConsumerDemo {
//    @Reference(version = "timeout", timeout = 1000, mock = "throw")
    @Reference(version = "timeout", timeout = 1000, mock = "force:throw com.eleven.icode.MockException")
//    @Reference(version = "timeout", mock = "true")
    private DemoService demoService;

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = SpringApplication.run(MockDubboConsumerDemo.class);
        DemoService demoService = context.getBean(DemoService.class);
        System.out.println(demoService.sayHello("eleven"));
    }
}

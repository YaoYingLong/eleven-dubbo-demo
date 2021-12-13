package com.eleven.icode.consumer;

import com.eleven.icode.DemoService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@EnableAutoConfiguration
public class LoadBalanceDubboConsumerDemo {
    /**
     * random：随机，按权重设置随机概率，在一个截面上碰撞的概率高，但调用量越大分布越均匀，而且按概率使用权重后也比较均匀，有利于动态调整提供者权重。
     * roundrobin：轮询，按公约后的权重设置轮询比率，存在慢的提供者累积请求的问题
     * leastactive：最少活跃调用数，相同活跃数的随机，活跃数指调用前后计数差,使慢的提供者收到更少请求，因为越慢的提供者的调用前后计数差会越大
     * consistenthash：一致性Hash，相同参数的请求总是发到同一提供者
     */
    @Reference(version = "default", loadbalance = "leastactive")
    private DemoService demoService;

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = SpringApplication.run(LoadBalanceDubboConsumerDemo.class);
        DemoService demoService = context.getBean(DemoService.class);
        // 用来负载均衡
//        for (int i = 0; i < 1000; i++) {
//            System.out.println((demoService.sayHello("eleven")));
//            try {
//                Thread.sleep(1 * 1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        // 一致性hash算法测试
        for (int i = 0; i < 1000; i++) {
            System.out.println(demoService.sayHello(i % 5 + "eleven"));
            try {
                Thread.sleep(1 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

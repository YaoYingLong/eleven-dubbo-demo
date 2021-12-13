package com.eleven.icode.consumer;

import com.eleven.icode.DemoService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@EnableAutoConfiguration
public class ClusterDubboConsumerDemo {
    /**
     * 默认：failover
     * failover：失败自动切换，当出现失败，重试其它服务器。通常用于读操作，但重试会带来更长延迟。可通过 retries=2来设置重试次数，不含第一次。
     * failfast：快速失败，只发起一次调用，失败立即报错。通常用于非幂等性的写操作，比如新增记录
     * failsafe：失败安全，出现异常时，直接忽略。通常用于写入审计日志等操作
     * failback：失败自动恢复，后台记录失败请求，定时重发。通常用于消息通知操作
     * forking：并行调用多个服务器，只要一个成功即返回。通常用于实时性要求较高的读操作，但需要浪费更多服务资源。可通过forks=2来设置最大并行数
     * broadcast：广播调用所有提供者，逐个调用，任意一台报错则报错。通常用于通知所有提供者更新缓存或日志等本地资源信息
     */
    @Reference(version = "timeout", timeout = 1000, cluster = "failback")
    private DemoService demoService;

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = SpringApplication.run(ClusterDubboConsumerDemo.class);
        DemoService demoService = context.getBean(DemoService.class);
        System.out.println(demoService.sayHello("eleven"));
    }
}

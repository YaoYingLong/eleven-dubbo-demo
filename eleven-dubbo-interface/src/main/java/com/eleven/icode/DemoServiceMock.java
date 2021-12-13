package com.eleven.icode;

public class DemoServiceMock implements DemoService {
    private final DemoService demoService;
    // 构造函数传入真正的远程代理对象
    public DemoServiceMock(DemoService demoService){
        this.demoService = demoService;
    }

    @Override
    public String sayHello(String name) {
        return "出现Rpc异常，进行了mock";
    }
}

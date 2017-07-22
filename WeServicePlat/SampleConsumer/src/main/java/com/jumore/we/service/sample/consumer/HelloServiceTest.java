package com.jumore.we.service.sample.consumer;

import com.jumore.we.service.client.ServiceProxy;
import com.jumore.we.service.sample.api.HelloService;

public class HelloServiceTest {

    public static void main(String[] args){
        HelloService helloService = ServiceProxy.newProxy(HelloService.class);
        String result = helloService.sayHello("yexinzhou" , "huizhou" , 23);
        System.out.println(result);
    }
}

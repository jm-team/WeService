package com.jumore.we.service.sample.consumer;

import com.jumore.we.service.client.ServiceProxy;
import com.jumore.we.service.sample.api.HelloService;

public class HelloServiceTest {

    public static void main(String[] args){
        String serverstring = "192.168.23.161:2181,192.168.23.117:2181,192.168.23.116:2181";
        ServiceProxy serviceProxy = ServiceProxy.createDefault(serverstring);
        HelloService helloService = serviceProxy.newProxy(HelloService.class);
        String result = helloService.sayHello("joe", "china", 20);
        System.out.println(result);
    }
}

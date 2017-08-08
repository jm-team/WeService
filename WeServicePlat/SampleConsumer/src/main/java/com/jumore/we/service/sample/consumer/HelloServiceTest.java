package com.jumore.we.service.sample.consumer;

import java.util.Date;

import com.jumore.we.service.client.ServiceProxy;
import com.jumore.we.service.sample.api.HelloService;
import com.jumore.we.service.sample.model.Hello;
import com.jumore.we.service.sample.model.User;

public class HelloServiceTest {

    public static void main(String[] args){
        User user = new User();
        user.setName("joe");
        user.setAge(19);
        user.setBirthday(new Date());
        user.setAccounts(new String[]{"joe", "neo"});
        
        Hello hello = new Hello();
        hello.setUser(user);
        hello.setMessages(new String[]{"hello", "byebye"});
        hello.setDate(new Date());
        
        String serverstring = "192.168.23.161:2181,192.168.23.117:2181,192.168.23.116:2181";
        ServiceProxy serviceProxy = ServiceProxy.createDefault(serverstring);
        HelloService helloService = serviceProxy.newProxy(HelloService.class);
        Hello result = helloService.sayHello("hello", hello, 20);
        System.out.println(result);
    }
}

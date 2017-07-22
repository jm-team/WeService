package com.jumore.we.service.sample.api;

import com.jumore.we.service.WeService;

public interface HelloService extends WeService{

    public String sayHello(String userName , String from , int age);
    
}

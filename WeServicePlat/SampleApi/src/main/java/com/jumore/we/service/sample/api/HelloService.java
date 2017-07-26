package com.jumore.we.service.sample.api;

import javax.inject.Named;

import com.jumore.we.service.WeService;

public interface HelloService extends WeService{

    public String sayHello(@Named(value="userName") String userName , @Named(value="from") String from , @Named(value="age") int age);
    
}

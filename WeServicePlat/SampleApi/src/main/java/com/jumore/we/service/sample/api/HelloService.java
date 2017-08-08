package com.jumore.we.service.sample.api;

import javax.inject.Named;

import com.jumore.we.service.WeService;
import com.jumore.we.service.sample.model.Hello;

public interface HelloService extends WeService {

    public Hello sayHello(@Named(value = "userName") String userName, @Named(value = "hello") Hello hello, @Named(value = "age") int age);

}

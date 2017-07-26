package com.jumore.we.service.sample.provider;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jumore.we.service.sample.api.HelloService;


@Controller
@RequestMapping(value = "/hello")
public class HelloServiceImpl implements HelloService{

    @ResponseBody
    @RequestMapping(value = "sayHello")
    public String sayHello(String userName, String from, int age) {
        return "efw";
    }

}

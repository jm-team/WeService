package com.jumore.we.service.sample.provider;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jumore.we.service.sample.api.HelloService;
import com.jumore.we.service.sample.model.Hello;


@Controller
@RequestMapping(value = "/hello")
public class HelloServiceImpl implements HelloService{

    @ResponseBody
    @RequestMapping(value = "sayHello")
    public Hello sayHello(String userName, Hello hello, int age) {
        return hello;
    }
    
    @ResponseBody
    @RequestMapping(value = "test")
    public Map<String, String> sayHello(Map<String, String> ids) {
        return ids;
    }

}

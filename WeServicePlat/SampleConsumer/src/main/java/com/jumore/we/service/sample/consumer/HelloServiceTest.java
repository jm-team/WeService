package com.jumore.we.service.sample.consumer;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URI;

import org.I0Itec.zkclient.ZkClient;

import com.jumore.we.service.client.ServiceIntercept;
import com.jumore.we.service.client.ServiceProxy;
import com.jumore.we.service.client.mapping.ZookeeperServiceMapping;
import com.jumore.we.service.client.mapping.selector.RandomServiceSelector;
import com.jumore.we.service.client.mapping.selector.ServiceSelector;
import com.jumore.we.service.client.mapping.selector.WeightRandomServiceSelector;
import com.jumore.we.service.sample.api.HelloService;
import com.thoughtworks.paranamer.AnnotationParanamer;
import com.thoughtworks.paranamer.BytecodeReadingParanamer;
import com.thoughtworks.paranamer.CachingParanamer;

public class HelloServiceTest {

    public static void main(String[] args){
        String serverstring = "192.168.23.161:2181,192.168.23.117:2181,192.168.23.116:2181";
        ServiceProxy serviceProxy = ServiceProxy.createDefault(serverstring);
        HelloService helloService = serviceProxy.newProxy(HelloService.class);
        String result = helloService.sayHello("joe", "china", 20);
        System.out.println(result);
    }
}

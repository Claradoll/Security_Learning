package com.example.dubboprovider.service;

import com.api.IHello;
import org.apache.dubbo.config.annotation.Service;

@Service
public class HelloService implements IHello {

    @Override
    public String IHello(String name) {
        return "Hello "+name;
    }

    @Override
    public Object IObject(Object o) {
        return o;
    }
}

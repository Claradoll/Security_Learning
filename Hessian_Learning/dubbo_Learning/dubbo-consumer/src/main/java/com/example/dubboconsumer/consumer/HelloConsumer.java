package com.example.dubboconsumer.consumer;

import com.api.IHello;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloConsumer {

    @Reference
    private IHello iHello;

    @RequestMapping("/hello")
    public String hello(@RequestParam(name = "name")String name){
        String h = iHello.IHello(name);
        System.out.println("调用Provider");
        return h;
    }

    @RequestMapping("/calc")
    public void Hessian_Ser() throws Exception {
        Object o = Hessian_Payload.getPayload();
        Object b = iHello.IObject(o);
    }
}

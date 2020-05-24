package com.vic.nacos.controller;

import com.vic.springcloud.entities.CommonResult;
import com.vic.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author：vic
 * @date： 2020/5/23 16:17
 * @desc: nacos 采用ribbon做负载
 */
@RestController

public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Long, Payment> hashMap = new HashMap<>(16);
    //模拟数据
    static {
        hashMap.put(1L,new Payment(1L,"28a8cle3bc2742d8848568981b422181"));
        hashMap.put(2L,new Payment(2L,"28a8cle3bc2742d8848568981b422182"));
        hashMap.put(3L,new Payment(3L,"28a8cle3bc2742d8848568981b422183"));
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/paymentSQL/{id}")
    public CommonResult<Payment> paymentSql(@PathVariable("id")Long id){
        Payment payment = hashMap.get(id);

        return new CommonResult<Payment>(200,"from sql,server port:"+serverPort,payment);
    }
}

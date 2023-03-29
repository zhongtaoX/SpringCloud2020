package com.zt.springcloud.controller;

import com.zt.springcloud.entities.CommonResult;
import com.zt.springcloud.entities.Payment;
import com.zt.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult creat(@RequestBody Payment payment) {

        int result = paymentService.create(payment);
        log.info("插入结果：" + result);

        if (result > 0) {
            return new CommonResult<>(200, "实体创建成功", result);
        } else {
            return new CommonResult<>(444, "实体创建失败", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {

        Payment payment = paymentService.getPaymentById(id);
        log.info("插入结果：" + payment);

        if (payment != null) {
            return new CommonResult<>(200, "查询成功，server.port" + serverPort, payment);
        } else {
            return new CommonResult<>(444, "无数据", null);
        }
    }
}

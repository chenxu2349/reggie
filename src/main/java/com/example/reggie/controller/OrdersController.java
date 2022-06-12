package com.example.reggie.controller;

import com.example.reggie.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName OrdersController
 * @Description
 * @Author chenxu
 * @Date 2022/6/12 23:09
 **/
@RestController
@Slf4j
@RequestMapping("/order")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    /**
     *@Description 订单信息分页查询
     *@Author chenxu
     *@Date 2022/6/12 23:27
     **/

}

package com.example.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.reggie.mapper.OrdersMapper;
import com.example.reggie.pojo.Orders;
import com.example.reggie.service.OrdersService;
import org.springframework.stereotype.Service;

/**
 * @ClassName OrdersServiceImpl
 * @Description
 * @Author chenxu
 * @Date 2022/6/12 23:07
 **/
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {
}

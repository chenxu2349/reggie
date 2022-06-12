package com.example.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.reggie.pojo.Orders;
import org.apache.ibatis.annotations.Mapper;

/**
 * @InterfaceName OrdersMapper
 * @Description
 * @Author chenxu
 * @Date 2022/6/12 23:04
 **/
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {
}

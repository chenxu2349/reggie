package com.example.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.reggie.pojo.Dish;
import org.apache.ibatis.annotations.Mapper;

/**
 * @InterfaceName DishMapper
 * @Description
 * @Author chenxu
 * @Date 2022/6/4 14:45
 **/
@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}

package com.example.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.reggie.common.Result;
import com.example.reggie.dto.DishDto;
import com.example.reggie.pojo.Dish;

import java.util.List;

/**
 * @InterfaceName DishService
 * @Description
 * @Author chenxu
 * @Date 2022/6/4 14:47
 **/
public interface DishService extends IService<Dish> {

    //新增菜品，同时插入菜品对应的口味数据
    public void saveWithFlavor(DishDto dishDto);

    public DishDto selectDishDtoById(Long id);

    public void updateWithFlavor(DishDto dishDto);

    public void updateStatus(Long ids);

    public Result<List<Dish>> getDishListById(Long categoryId);
}

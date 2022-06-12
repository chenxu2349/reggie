package com.example.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.reggie.pojo.SetmealDish;

import java.util.List;

/**
 * @InterfaceName SetmealDishService
 * @Description
 * @Author chenxu
 * @Date 2022/6/9 22:13
 **/
public interface SetmealDishService extends IService<SetmealDish> {

    public List<SetmealDish> getDishes(Long id);
}

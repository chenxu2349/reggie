package com.example.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.reggie.mapper.SetmealDishMapper;
import com.example.reggie.pojo.SetmealDish;
import com.example.reggie.service.SetmealDishService;
import com.example.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SetmealDishServiceImpl
 * @Description
 * @Author chenxu
 * @Date 2022/6/9 22:14
 **/
@Service
public class SetmealDishServiceImpl extends ServiceImpl<SetmealDishMapper, SetmealDish> implements SetmealDishService {

    @Override
    public List<SetmealDish> getDishes(Long id) {
        LambdaQueryWrapper<SetmealDish> setmealDishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealDishLambdaQueryWrapper.eq(SetmealDish::getSetmealId, id);
        return(this.list(setmealDishLambdaQueryWrapper));
    }
}

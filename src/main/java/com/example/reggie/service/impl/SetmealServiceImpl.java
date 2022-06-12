package com.example.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.reggie.dto.SetmealDto;
import com.example.reggie.mapper.SetmealMapper;
import com.example.reggie.pojo.DishFlavor;
import com.example.reggie.pojo.Setmeal;
import com.example.reggie.pojo.SetmealDish;
import com.example.reggie.service.SetmealDishService;
import com.example.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName SetmealServiceImpl
 * @Description
 * @Author chenxu
 * @Date 2022/6/4 17:08
 **/
@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    @Autowired
    private SetmealDishService setmealDishService;

    @Override
    @Transactional
    public void addWithSetDishes(SetmealDto setmealDto) {
        this.save(setmealDto);

        Long setmealId = setmealDto.getId();
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes = setmealDishes.stream().map((item) -> {
            item.setSetmealId(setmealId);
            return item;
        }).collect(Collectors.toList());

        setmealDishService.saveBatch(setmealDishes);
    }

    @Override
    @Transactional
    public void removeSetmealById(Long id) {
        this.removeById(id);

        //把该套餐id下的套餐菜查出来，并且删除
        LambdaQueryWrapper<SetmealDish> setmealDishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealDishLambdaQueryWrapper.eq(SetmealDish::getSetmealId, id);
        List<SetmealDish> list = setmealDishService.list(setmealDishLambdaQueryWrapper);
        for(int i=0; i< list.size(); i++){
            setmealDishService.removeById(list.get(i).getId());
        }
    }

    @Override
    @Transactional
    public void updateSetmealInfo(SetmealDto setmealDto) {
        //更新基础信息
        this.updateById(setmealDto);
        //更新套餐菜品信息(先删除原来的菜，再把页面数据中的菜保存进去)
        LambdaQueryWrapper<SetmealDish> setmealDishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealDishLambdaQueryWrapper.eq(SetmealDish::getSetmealId, setmealDto.getId());
        setmealDishService.remove(setmealDishLambdaQueryWrapper);

        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        Long setmealId = setmealDto.getId();
//        setmealDishes = setmealDishes.stream().map((item) -> {
//            item.setSetmealId(setmealDto.getId());
//            return item;
//        }).collect(Collectors.toList());
        for(int i=0; i<setmealDishes.size(); i++){
            setmealDishes.get(i).setSetmealId(setmealId);
        }
        setmealDishService.saveBatch(setmealDishes);
    }
}

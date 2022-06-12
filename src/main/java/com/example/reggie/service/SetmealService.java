package com.example.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.reggie.dto.SetmealDto;
import com.example.reggie.pojo.Setmeal;

/**
 * @InterfaceName SetmealService
 * @Description
 * @Author chenxu
 * @Date 2022/6/4 17:07
 **/
public interface SetmealService extends IService<Setmeal> {

    public void addWithSetDishes(SetmealDto setmealDto);

    public void removeSetmealById(Long id);

    public void updateSetmealInfo(SetmealDto setmealDto);

}

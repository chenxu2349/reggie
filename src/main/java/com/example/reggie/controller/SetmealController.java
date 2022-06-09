package com.example.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.reggie.common.Result;
import com.example.reggie.dto.SetmealDto;
import com.example.reggie.pojo.Dish;
import com.example.reggie.pojo.Setmeal;
import com.example.reggie.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName SetmealController
 * @Description
 * @Author chenxu
 * @Date 2022/6/4 17:01
 **/
@Slf4j
@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Autowired
    private SetmealService setmealService;

    /**
     *@Description 套餐分页查询
     *@Author chenxu
     *@Date 2022/6/9 20:31
     **/
    @GetMapping("/page")
    public Result<Page> getSetmealPages(int page, int pageSize, String name){
        //构造分页构造器
        Page pageInfo = new Page(page, pageSize);
        //构造条件构造器（name可能会传过来）
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper();
        //过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(name), Setmeal::getName, name);
        //排序条件
        queryWrapper.orderByDesc(Setmeal::getCreateTime);
        //执行查询
        setmealService.page(pageInfo,queryWrapper);

        return Result.success(pageInfo);
    }

    /**
     *@Description 添加套餐
     *@Author chenxu
     *@Date 2022/6/9 20:31
     **/
    @PostMapping
    public Result<String> addSetmeal(@RequestBody SetmealDto setmealDto){
        setmealService.addWithSetDishes(setmealDto);
        return Result.success("添加成功");
    }


}

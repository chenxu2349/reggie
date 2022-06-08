package com.example.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.reggie.common.Result;
import com.example.reggie.dto.DishDto;
import com.example.reggie.pojo.Category;
import com.example.reggie.pojo.Dish;
import com.example.reggie.pojo.Employee;
import com.example.reggie.service.CategoryService;
import com.example.reggie.service.DishService;
import com.example.reggie.service.impl.DishServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName DishController
 * @Description
 * @Author chenxu
 * @Date 2022/6/4 14:46
 **/
@Slf4j
@RestController
@RequestMapping("/dish")
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private CategoryService categoryService;

    /**
     *@Description 分页查询
     *@Author chenxu
     *@Date 2022/6/6 22:25
     **/
    @GetMapping("/page")
    public Result<Page> getDishPages(int page, int pageSize, String name){
        log.info("前端传回的GET参数：page={},pageSize={},name={}",page,pageSize,name);

        //构造分页构造器
        Page pageInfo = new Page(page,pageSize);
        //构造条件构造器（name可能会传过来）
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper();
        //过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(name),Dish::getName,name);
        //排序条件
        queryWrapper.orderByDesc(Dish::getSort);
        //执行查询
        dishService.page(pageInfo,queryWrapper);

        return Result.success(pageInfo);
    }
    
    /**
     *@Description 添加菜品
     * 值得注意的是：这里传进来的参数包含口味属性，而Dish原实体没有这个属性，这里需要操作dish和dishflavor两张表
     *@Author chenxu
     *@Date 2022/6/6 22:51
     **/
    @PostMapping
    public Result<String> addDish(@RequestBody DishDto dishDto){
        //打印接收到页面的参数
        log.info(dishDto.toString());

        dishService.saveWithFlavor(dishDto);

        return Result.success("新增菜品成功");
    }

    /**
     *@Description 根据id获取菜品信息
     *@Author chenxu
     *@Date 2022/6/7 23:06
     **/
    @GetMapping("/{id}")
    public Result<DishDto> getDishById(@PathVariable Long id){
        //Dish dish = dishService.getById(id);
        return Result.success(dishService.selectDishDtoById(id));
    }

    /**
     *@Description 修改菜品信息
     *@Author chenxu
     *@Date 2022/6/7 23:12
     **/
    @PutMapping
    public Result<String> updateDishInfo(@RequestBody DishDto dishDto){
        log.info(dishDto.toString());

        dishService.updateWithFlavor(dishDto);

        return Result.success("菜品信息修改成功");
    }
}

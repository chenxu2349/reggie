package com.example.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.reggie.common.Result;
import com.example.reggie.dto.DishDto;
import com.example.reggie.mapper.DishMapper;
import com.example.reggie.pojo.Dish;
import com.example.reggie.pojo.DishFlavor;
import com.example.reggie.service.DishFlavorService;
import com.example.reggie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName DishServiceImpl
 * @Description
 * @Author chenxu
 * @Date 2022/6/4 14:47
 **/
@Service
@Slf4j
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    @Autowired
    private DishFlavorService dishFlavorService;

    /**
     *@Description 新增菜品，同时保存口味
     *@Author chenxu
     *@Date 2022/6/7 22:20
     **/
    @Override
    @Transactional //这里涉及多表操作，需要加入事务控制
    public void saveWithFlavor(DishDto dishDto) {

        this.save(dishDto);

        Long dishId = dishDto.getId();

        //每一个口味都需要对应一个菜品id
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors = flavors.stream().map((item) -> {
            item.setDishId(dishId);
            return item;
        }).collect(Collectors.toList());

        //saveBatch批量保存（集合）
        dishFlavorService.saveBatch(flavors);
    }

    /**
     *@Description 根据id构造DishDto用于页面回显数据
     *@Author chenxu
     *@Date 2022/6/8 20:38
     **/
    @Override
    @Transactional
    public DishDto selectDishDtoById(Long id) {
        DishDto dishDto = new DishDto();
        //Dish dish = dishMapper.selectById(id);
        Dish dish = this.getById(id);

        //设置除口味外的其他字段
        dishDto.setId(dish.getId());
        dishDto.setName(dish.getName());
        dishDto.setCategoryId(dish.getCategoryId());
        dishDto.setPrice(dish.getPrice());
        dishDto.setImage(dish.getImage());
        dishDto.setDescription(dish.getDescription());

        //上述代码可以用更简便的方法实现
        //BeanUtils.copyProperties(dish, dishDto);


        //根据菜品id查到该菜品对应的口味列表，设置给dto用于页面回显
        LambdaQueryWrapper<DishFlavor> dishFlavorLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishFlavorLambdaQueryWrapper.eq(DishFlavor::getDishId, id);
        List<DishFlavor> list = dishFlavorService.list(dishFlavorLambdaQueryWrapper);
        dishDto.setFlavors(list);

        return dishDto;
    }

    /**
     *@Description 修改菜品信息，这里是根据具体菜品id来修改
     *@Author chenxu
     *@Date 2022/6/8 20:39
     **/
    @Override
    @Transactional
    public void updateWithFlavor(DishDto dishDto) {
        //更新dish基本信息
        this.updateById(dishDto);

        Long dishId = dishDto.getId();

        //清除原来的flavor，再把提交的口味填上
        LambdaQueryWrapper<DishFlavor> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(DishFlavor::getDishId, dishDto.getId());
        dishFlavorService.remove(lambdaQueryWrapper);

        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors = flavors.stream().map((item) -> {
            item.setDishId(dishId);
            return item;
        }).collect(Collectors.toList());
        dishFlavorService.saveBatch(flavors);

    }

    @Override
    public void updateStatus(Long ids) {
        return;
    }

    /**
     *@Description 获取菜品分类下的菜品列表
     *@Author chenxu
     *@Date 2022/6/9 20:55
     **/
    @Override
    public Result<List<Dish>> getDishListById(Long categoryId) {

        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, categoryId);
        List<Dish> list = this.list(dishLambdaQueryWrapper);

        return Result.success(list);
    }
}

package com.example.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.reggie.common.CustomException;
import com.example.reggie.common.Result;
import com.example.reggie.mapper.CategoryMapper;
import com.example.reggie.pojo.Category;
import com.example.reggie.pojo.Dish;
import com.example.reggie.pojo.Setmeal;
import com.example.reggie.service.CategoryService;
import com.example.reggie.service.DishService;
import com.example.reggie.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CategoryServiceImpl
 * @Description
 * @Author chenxu
 * @Date 2022/6/3 21:00
 **/
@Slf4j
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 删除之前，需要先判断该分类下是否包含菜品或者套餐
     * chenxu
     * 2022/6/4 19:51
     **/
    public void deleteCategory(Long id){
        log.info("需要删除的分类id是{}",id);

        //这里为什么是Dish
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();

        //添加查询条件，根据分类id进行查询
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId, id);

        //查询当前分类是否关联了菜品或者套餐

        int count1 = dishService.count(dishLambdaQueryWrapper);
        int count2 = setmealService.count(setmealLambdaQueryWrapper);

        if(count1 > 0){
            throw new CustomException("已关联菜品，不能删除");
        }
        if(count2 > 0){
            throw new CustomException("已关联套餐，不能删除");
        }

        //没有关联菜品和套餐，正常删除
        //???
        super.removeById(id);
    }

    public Result<List<Category>> getByType(int type){
        //List<Category> list = new ArrayList<>();
        LambdaQueryWrapper<Category> categoryLambdaQueryWrapper = new LambdaQueryWrapper<>();
        categoryLambdaQueryWrapper.eq(Category::getType, type);
        List<Category> list = categoryMapper.selectList(categoryLambdaQueryWrapper);
        return Result.success(list);
    }
}

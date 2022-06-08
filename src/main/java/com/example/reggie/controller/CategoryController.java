package com.example.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.reggie.common.Result;
import com.example.reggie.pojo.Category;
import com.example.reggie.pojo.Employee;
import com.example.reggie.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName CategoryController
 * @Description
 * @Author chenxu
 * @Date 2022/6/3 21:07
 **/
@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    
    /**
     * 分类信息分页查询
     * chenxu
     * 2022/6/3 22:26
     **/
    @GetMapping("/page")
    public Result<Page> getCategoryPages(int page, int pageSize){
        log.info("前端传回的GET参数：page={},pageSize={}",page,pageSize);

        //构造分页构造器
        Page pageInfo = new Page(page,pageSize);
        //构造条件构造器
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper();
        //排序条件
        queryWrapper.orderByDesc(Category::getSort);
        //执行查询
        categoryService.page(pageInfo,queryWrapper);

        return Result.success(pageInfo);
    }

    /**
     * 新增分类方法: 新增菜品分类和新增套餐分类可用一个方法处理
     * chenxu
     * 2022/6/3 22:05
     **/
    @PostMapping
    public Result<String> addCategory(@RequestBody Category category){
        log.info("新增分类是：{}",category);

        //如果出现重名异常，会有全局异常处理类GlobalExceptionHandler处理
        categoryService.save(category);

        return Result.success("新增分类成功");
    }

    /**
     * 修改分类
     * chenxu
     * 2022/6/3 23:43
     **/
    @PutMapping
    public Result<String> updateCategory(HttpServletRequest request, @RequestBody Category category){
        log.info(category.toString());

        categoryService.updateById(category);

        return Result.success("修改成功");
    }

    /**
     * 删除分类
     * chenxu
     * 2022/6/4 10:59
     **/
    @DeleteMapping
    public Result<String> deleteCategory(Long ids){
        log.info("要删除的分类id：{}",ids);

        //categoryService.removeById(ids);

        //这个是完善后自己写的删除方法
        categoryService.deleteCategory(ids);

        return Result.success("删除分类成功");
    }

    /**
     *@Description 获取菜品分类列表
     *@Author chenxu
     *@Date 2022/6/6 22:44
     **/
    @GetMapping("/list")
    public Result<List<Category>> getList(int type){

        return categoryService.getByType(type);

    }
}

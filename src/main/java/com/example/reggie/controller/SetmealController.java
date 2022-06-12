package com.example.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.reggie.common.Result;
import com.example.reggie.dto.SetmealDto;
import com.example.reggie.pojo.Setmeal;
import com.example.reggie.pojo.SetmealDish;
import com.example.reggie.service.SetmealDishService;
import com.example.reggie.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Autowired
    private SetmealDishService setmealDishService;

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

    /**
     * 删除套餐（可批量）,需要操作两张表setmeal和setmealDish
     * chenxu
     * 2022/6/11 22:23
     **/
    @DeleteMapping
    public Result<String> deleteSetmeal(Long[] ids){

        for (int i=0; i< ids.length; i++){
            try {
                setmealService.removeSetmealById(ids[i]);
            }catch (Exception e){
                return Result.error("删除异常");
            }
        }

        return Result.success("删除套餐成功");
    }

    /**
     * 更改售卖状态
     * chenxu
     * 2022/6/11 23:46
     **/
    @PostMapping("/status/{status}")
    public Result<String> updateStatus(@PathVariable int status, Long[] ids){
        for (int i=0; i< ids.length; i++){
            Setmeal setmeal = setmealService.getById(ids[i]);
            if(status==0) {
                setmeal.setStatus(0);
            }else {
                setmeal.setStatus(1);
            }
            setmealService.updateById(setmeal);
        }

        return Result.success("状态更改成功");
    }

    /**
     * 修改套餐信息
     * chenxu
     * 2022/6/11 23:53
     **/
    @PutMapping
    public Result<String> updateSetmealInfo(@RequestBody SetmealDto setmealDto){
        setmealService.updateSetmealInfo(setmealDto);
        return Result.success("修改套餐成功");
    }
    
    /**
     * 修改套餐前先获取套餐信息用于回显
     * chenxu
     * 2022/6/12 0:03
     **/
    @GetMapping("/{id}")
    public Result<SetmealDto> getSetmealDtoById(@PathVariable Long id){
        Setmeal setmeal = setmealService.getById(id);
        SetmealDto setmealDto = new SetmealDto();
        setmealDto.setId(id);
        setmealDto.setName(setmeal.getName());
        setmealDto.setCategoryId(setmeal.getCategoryId());
        setmealDto.setPrice(setmeal.getPrice());
        setmealDto.setImage(setmeal.getImage());
        setmealDto.setDescription(setmeal.getDescription());
        List<SetmealDish> dishes = setmealDishService.getDishes(id);
        setmealDto.setSetmealDishes(dishes);
        return Result.success(setmealDto);
    }

}

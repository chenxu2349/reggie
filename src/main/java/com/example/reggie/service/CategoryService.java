package com.example.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.reggie.common.Result;
import com.example.reggie.mapper.CategoryMapper;
import com.example.reggie.pojo.Category;

import java.util.List;

/**
 * @InterfaceName CategoryService
 * @Description
 * @Author chenxu
 * @Date 2022/6/3 20:59
 **/
public interface CategoryService extends IService<Category> {

    //根据id删除分类
    public void deleteCategory(Long id);

    //根据type类型查找分类（菜品分类还是套餐分类）
    public Result<List<Category>> getByType(int type);
}

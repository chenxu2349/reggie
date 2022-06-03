package com.example.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.reggie.mapper.CategoryMapper;
import com.example.reggie.pojo.Category;
import com.example.reggie.service.CategoryService;
import org.springframework.stereotype.Service;

/**
 * @ClassName CategoryServiceImpl
 * @Description
 * @Author chenxu
 * @Date 2022/6/3 21:00
 **/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}

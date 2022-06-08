package com.example.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.reggie.mapper.DishFlavorMapper;
import com.example.reggie.pojo.DishFlavor;
import com.example.reggie.service.DishFlavorService;
import com.example.reggie.service.DishService;
import org.springframework.stereotype.Service;

/**
 * @ClassName DishFlavorServiceImpl
 * @Description
 * @Author chenxu
 * @Date 2022/6/7 20:32
 **/
@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}

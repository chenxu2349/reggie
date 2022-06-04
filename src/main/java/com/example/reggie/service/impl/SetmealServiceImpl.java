package com.example.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.reggie.mapper.SetmealMapper;
import com.example.reggie.pojo.Setmeal;
import com.example.reggie.service.SetmealService;
import org.springframework.stereotype.Service;

/**
 * @ClassName SetmealServiceImpl
 * @Description
 * @Author chenxu
 * @Date 2022/6/4 17:08
 **/
@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
}

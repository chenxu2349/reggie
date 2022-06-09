package com.example.reggie.dto;

import com.example.reggie.pojo.Setmeal;
import com.example.reggie.pojo.SetmealDish;
import lombok.Data;

import java.util.List;

/**
 * @ClassName SetmealDto
 * @Description
 * @Author chenxu
 * @Date 2022/6/9 21:16
 **/
@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;

}

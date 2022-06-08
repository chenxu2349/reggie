package com.example.reggie.dto;

import com.example.reggie.pojo.Dish;
import com.example.reggie.pojo.DishFlavor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DishDto
 * @Description DTO，即data transfer object，数据传输对象，一般用于展示层与服务层之间的数据传输，用于封装页面提交的数据
 * @Author chenxu
 * @Date 2022/6/7 21:58
 **/
@Data
public class DishDto extends Dish {

    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;

}

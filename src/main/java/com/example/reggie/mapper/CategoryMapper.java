package com.example.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.reggie.pojo.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * @InterfaceName CategoryMapper
 * @Description
 * @Author chenxu
 * @Date 2022/6/3 20:57
 **/
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}

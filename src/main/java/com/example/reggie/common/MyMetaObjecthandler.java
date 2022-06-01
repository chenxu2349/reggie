package com.example.reggie.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

/**
 * @ClassName MyMetaObjecthandler
 * @Description 自定义元数据对象处理器，公共字段自动填充
 * @Author chenxu
 * @Date 2022/6/1 22:20
 **/
@Slf4j
public class MyMetaObjecthandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("公共字段自动填充[insert]...");
        log.info(metaObject.toString());

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("公共字段自动填充[update]...");
        log.info(metaObject.toString());

    }
}

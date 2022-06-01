package com.example.reggie.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 员工实体类
 */

@Data   //在实体类上添加@Data注解，可以省去代码中大量的 get()、 set()、 toString() 等方法，提高代码的简洁
public class Employee implements Serializable {
    //serializable接口的作用：
    // 1、存储对象在存储介质中，以便在下次使用的时候，可以很快捷的重建一个副本；
    // 2、便于数据传输，尤其是在远程调用的时候。

    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String name;

    private String password;

    private String phone;

    private String sex;

    //身份证号码，驼峰命名与数据库字段可以自动映射
    private String idNumber;

    //当前员工账号状态，1表示正常使用，0表示账号被锁定不能登录
    private Integer status;

    //插入时填充字段
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    //插入和更新时填充字段
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

}

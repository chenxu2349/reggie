package com.example.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.reggie.common.Result;
import com.example.reggie.pojo.Employee;
import com.example.reggie.service.EmployeeService;
import com.example.reggie.service.impl.EmployeeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;

    /**
     * 员工登录方法
     * @param request
     * @param employee
     * @return
     */
    @PostMapping("/login")  //因为前端使用账户密码时是使用post方法传给后台，这样更安全
    public Result<Employee> login(HttpServletRequest request, @RequestBody Employee employee){
        //使用RequestMapping注解将传入的json封装成对象

        /**
         * 1.将页面提交的密码进行md5加密
         * 2.查询用户名，密码，员工状态
         * 3.返回登录结果
         */
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        //包装一个查询对象，根据用户名查询，这里用户名已经设置为唯一
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        //public Children eq(     R column,Object val )
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);
        //用户名查询结果
        if(emp == null){
            return Result.error("用户名不存在");
        }

        if(!emp.getPassword().equals(password)){
            return Result.error("密码错误");
        }

        if(emp.getStatus() == 0){
            return Result.error("账号已禁用");
        }
        //登录成功
        request.getSession().setAttribute("employee",emp.getId());
        return Result.success(emp);
    }

    /**
     * 员工退出方法
     * 1.清理session中的用户id
     * 2.返回结果
     */
    @PostMapping("/logout")
    public Result<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("employee");
        return Result.success("退出成功");
    }

    /**
     * 新增员工方法
     */
    @PostMapping
    public Result<String> addemployee(HttpServletRequest request, @RequestBody Employee employee){
        log.info("检测到新增员工信息：{}",employee.toString());
        //设置默认密码并加密
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));

        //获取当前时间
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());

        //获取添加人信息
        Long operatorID = (Long) request.getSession().getAttribute("employee");
        employee.setUpdateUser(operatorID);
        employee.setCreateUser(operatorID);

        //保存入数据库
        employeeService.save(employee);
        return Result.success("员工添加成功");
    }

    /**
     * 员工信息列表分页查询，使用MP的一些方法
     * chenxu
     * 2022/5/28 21:43
     **/
    @GetMapping("/page")
    public Result<Page> page(int page, int pageSize, String name){
        log.info("page={},pageSize={},name={}",page,pageSize,name);

        //构造分页构造器
        Page pageInfo = new Page(page,pageSize);
        //构造条件构造器（name可能会传过来）
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper();
        //过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(name),Employee::getName,name);
        //排序条件
        queryWrapper.orderByDesc(Employee::getUpdateTime);
        //执行查询
        employeeService.page(pageInfo,queryWrapper);
        return Result.success(pageInfo);
    }

    /**
     *@Description 更新员工信息
     *@Author chenxu
     *@Date 2022/5/30 21:25
     **/
    @PutMapping
    public Result<String> update(HttpServletRequest request, @RequestBody Employee employee){
        log.info(employee.toString());

        Long empId = (Long)request.getSession().getAttribute("employee");
        employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateUser(empId);
        //提交数据库更新信息
        employeeService.updateById(employee);
        return Result.success("员工信息修改成功");
    }

    /**
     *@Description 根据id查询员工，参数被添加在访问路径中
     *@Author chenxu
     *@Date 2022/6/1 21:46
     **/
    @GetMapping("/{id}")
    public Result<Employee> getById(@PathVariable Long id){
        log.info("根据id查询员工信息...");
        Employee employee = employeeService.getById(id);
        if(employee != null){
            return Result.success(employee);
        }
        return Result.error("查询失败");
    }
}

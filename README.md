# reggie take out
# 瑞吉外卖项目代码
### 1.使用到的技术
* springboot
* mybatis,mybatis-plus
* redis
* vue

### 2.项目进度


* [设置超链接](https://maven.apache.org/guides/index.html)
* 在开始写业务代码前需要自己造一些基础类（实体类，配置类，结果返回封装类），基础写好后面写起来才会顺畅
* 5.23 创建空spring boot工程，导入pom依赖坐标，建立好数据库环境，运行SQL脚本文件建库建表
* 5.24 导入前端所需文件，完善项目结构，创建controller,service,mapper,pojo等包
* 5.25 设置统一结果返回类型，开发登录功能、退出功能
* 5.26 完善登录功能，设置过滤器或者拦截器，不登陆无法访问系统页面
* 5.28 添加员工功能，以及设置全局异常拦截器
* 6.3 添加和更新员工信息时需要填写的公共字段设置成自动填充，由Mybatis-Plus实现
* 6.12 完成分类、菜品、套餐模块的相关功能




DEBUG日志：
1.多表操作未加@Transanctional导致字段值设置不上
2.更新套餐和菜品时，页面数据是不显示id的，需要自己添加进Dto中用于回显
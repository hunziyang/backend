# 框架说明

## 框架结构说明

框架分成了四个模块，分别是`core`、`module`、`parent`、`security`。
- `core`：配置 `jackson`、`redis`、`mybatis-plus`、全局异常处理、响应结果、元素基础字段类、请求及响应结果记录、 自定义注解、工具类、定时器日志记录。
- `parent`: 定义 `core`及 `security` 版本
- `security`: 鉴权认证模块
- `module`: 为功能模块

## 项目模块使用说明

- 新建项目包的前缀必须以`com.techking.portal`，启动类必须加`@TechkingApplication`
- `Controller`类必须加`@TechkingController`,`@TechkingController` 整合了`@RestController`、`@RequestMapping`,如果需要配置`Controller`的请求前缀需要`@TechkingController("/test")`
- 如果不需要登录也能访问增加注解`@UrlPass`
- 如果需要指定权限才能访问增加注解`@TechkingPermission`,`value`值填写所需要权限

## 管理员账号

- 账号:techking@techking.com
- 密码:techking123@
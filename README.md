# 开发文档

## 包结构

* bean
* component
* config
* controller
* exception
* imageCode
* mapper
* secutity
* utils

## resources

* cn.edu.lingnan.projectmant.mapper
* public
* statci
* templates
* application.properties
* application.yml
* kaptcha.properties



## 包结构

### bean（实体对象包）

* MyUserDatails(spring security登录认证实体)

spring security登录认证成功后，会给这个实体复制

* User(暂不需要)

### component（组件包）

* LoginHandlerInterceptor(过滤器，暂不需要)

### config

* DruidConfig(Druid连接池)

配置druid连接池和web监控

* MyBatisConfig(MyBatis配置)

配置驼峰命名

* MyMvcConfig(Web配置)

跳转页面配置

* SecurityConfig(Security配置)

1.退出登录配置

2.rememberme配置

3.跨域请求配置

4.登录认证配置

5.权限配置

6.session配置

7.登录认证service

8.密码加密

9.静态资源开放

10.rememberme存储数据库

### exception

* AJaxReponse(定义成功失败返回JSON)
* CustomException(Exception实体)
* CustomException(CustomException枚举类)

### imageCiode（图片验证）

* CaptchaCode(图片验证实体)
* CaptchaCodeFilter(图片验证过滤器)
* CaptchaConfig(图片验证配置)

### mapper（mapper接口）

* MyRBACServiceMapper(RBAC权限管理)
* MyUserDetailsMapper(UserMapper登录验证)
* UserMapper(暂时不用)

### security（Spring Secuity）

* MyAuthenticationFailureHandler(登陆失败处理器)
* MyAuthenticationSuccessHandler(登录成功处理器)
* MyExpiredSessionStrategy(只允许一处登录)
* MyLogoutSuccessHandler(退出登录处理器)

### service（Service接口层）

* MyRBACService(RBAC权限Service接口)
* MyUserDetailsService(UserDetailsService接口)
* UserService(暂时不用)

### service.impl

* MyRBACServiceImpl(RBAC权限Service实现层)
* MyUserDetailService(UserDetailsService实现层)
* UserServiceImpl(UserService实现层)

### utils

MyContants(定义常量)



## resources

### cn.edu.lingnan.projectmant.mapper（SQL语句）

* MyRBACServiceMapper(RBAC权限管理SQL代码)
* MyUserDetailsMapper(登录认证SQL代码)
* UserMapper(暂时不用)

### public

### static（静态资源）

* assets(静态资源代码)

### templates（thymeleaf模板）

### application.properties（全局配置）

### application.yml（全局配置）

### kaptcha.properties（图片验证码配置）

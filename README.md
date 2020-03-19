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

## 数据库表

* sys_user
* sys_user_role
* sys_role
* sys_role_menu
* sys_menu
* sys_org
* persistent_logins



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



## 数据库表

### sys_user（用户信息表）

描述：用户信息表格

| 属性名      | 类型     | 长度 | null | 主键 | 描述     |
| ----------- | -------- | ---- | ---- | ---- | -------- |
| id          | int      |      | no   | yes  | 自增id   |
| email       | verchar  | 32   |      |      | 登录邮箱 |
| username    | varchar  | 255  |      |      | 用户名   |
| password    | varchar  | 255  |      |      | 密码     |
| phone       | varchar  | 16   |      |      | 电话     |
| create_time | datetime |      |      |      | 创建时间 |
| enabled     | int      |      |      |      | 是否可用 |
| org_id      | int      |      |      |      | 暂时不用 |



### sys_user_role（用户角色信息表）

描述：用户对应角色表格，spring security通过此表用户找到角色id

| 属性名  | 类型 | 长度 | null | 主键 | 描述   |
| ------- | ---- | ---- | ---- | ---- | ------ |
| id      | int  |      | no   | yes  | 自增id |
| user_id | int  |      |      |      | 用户id |
| role_id | int  |      |      |      | 角色id |



### sys_role（角色表）

描述：角色信息表

| 属性名      | 类型     | 长度 | null | 主键 | 描述                   |
| ----------- | -------- | ---- | ---- | ---- | ---------------------- |
| id          | int      |      | no   | yes  | 自增id                 |
| role_name   | varchar  | 64   |      |      | 角色名                 |
| role_code   | varchar  | 64   |      |      | 角色代码               |
| role_desc   | varchar  | 64   |      |      | 角色描述               |
| sort        | int      |      |      |      | 角色排序               |
| status      | int      |      |      |      | 0代表可用，1代表不可用 |
| create_time | datetime |      |      |      | 创建时间               |



### sys_role_menu（角色菜单表）

描述：角色菜单表，通过角色找到可以访问路径

| 属性名  | 类型 | 长度 | null | 主键 | 描述   |
| ------- | ---- | ---- | ---- | ---- | ------ |
| id      | int  |      | no   | yes  | 自增id |
| role_id | int  |      |      |      | 角色id |
| menu_id | int  |      |      |      | 菜单id |



### sys_menu（菜单表）

描述：菜单表，pid,pids等属性还没使用

| 属性名     | 类型    | 长度 | null | 主键 | 描述                   |
| ---------- | ------- | ---- | ---- | ---- | ---------------------- |
| id         | int     |      | no   | yes  | 自增id                 |
| menu_pid   | int     |      |      |      | 父目录                 |
| menu_pids  | varchar | 255  |      |      | 祖先目录               |
| is_leaf    | tinyint |      |      |      | 是否叶子节点           |
| menu_name  | varchar | 16   |      |      | 菜单名称               |
| url        | varchar | 64   |      |      | 菜单路径               |
| icon       | varchar | 64   |      |      | 图标                   |
| icon_color | varchar | 16   |      |      | 图标颜色               |
| sort       | tinyint |      |      |      | 排序                   |
| level      | tinyint |      |      |      | 层级                   |
| status     | tinyint |      |      |      | 0代表可用，1代表不可用 |



### sys_org（部门表，暂时不用）



### persistent_logins（cookie表）

描述：浏览器缓存cookie

| 属性      | 类型      | 长度 | null | 主键 | 描述         |
| --------- | --------- | ---- | ---- | ---- | ------------ |
| username  | varchar   | 64   |      |      | 用户名       |
| series    | varchar   | 64   | no   | yes  | 序列号       |
| token     | varchar   | 64   |      |      | token        |
| last_used | timestamp |      |      |      | 最近登录时间 |



### projects（项目表,待完善）

描述：项目表格

| 属性             | 类型     | 长度 | null | 主键 | 描述       |
| ---------------- | -------- | ---- | ---- | ---- | ---------- |
| id               | int      |      | no   | yes  | 自增id     |
| name             | varchar  | 64   |      |      | 项目名称   |
| charge_user_id   | int      |      |      |      | 项目管理员 |
| code_line_number | int      |      |      |      | 项目代码行 |
| update_count     | int      |      |      |      | 提交次数   |
| type             | varchar  | 64   |      |      | 项目类型   |
| create_time      | datatime |      |      |      | 创建时间   |
| delete_flag      | tinyint  |      |      |      | 是否删除   |
|                  |          |      |      |      |            |
|                  |          |      |      |      |            |




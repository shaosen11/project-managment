# 开发文档a

## 包结构

- bean
- config
- controller
- exception
- imageCode
- mapper
- secutity
- utils



## resources

- cn.edu.lingnan.projectmant.mapper
- public
- statci
- templates
- application.properties
- application.yml
- kaptcha.properties



## 数据库表

- documents
- documents_record
- persistent_logins
- projects
- projects_function
- projects_package
- projects_user
- sys_menu
- sys_role_menu
- sys_user
- sys_role
- sys_user_role
- user_record

## RUL

- 公共访问
- 权限访问

## 包结构

### bean（实体对象包）

- Documents(文件实体)
- DocumentsRecord(文件记录实体)
- MyUserDatails(spring security登录认证实体)

spring security登录认证成功后，会给这个实体复制

* Projects(项目实体)
* ProjectsPackage(项目包实体)
* ProjectsPackageList(项目包列表，暂未优化)
* ProjectsUser(项目角色关系实体)

- UserRecord(用户登录、退出日志)

- UserRole(用户角色实体)



### config

- DruidConfig(Druid连接池)

1.配置druid连接池和web监控

- MyBatisConfig(MyBatis配置)

1.配置驼峰命名

- MyMvcConfig(Web配置)

1.跳转页面配置

- SecurityConfig(Security配置)

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



### controller

- CapthaController
- DocumentsController
- ProjectsController
- ProjectsPackageController
- ProjectsUserController
- UserController



### exception

- AJaxReponse(定义成功失败返回JSON)
- CustomException(Exception实体)
- CustomException(CustomException枚举类)



### imageCiode（图片验证）

- CaptchaCode(图片验证实体)
- CaptchaCodeFilter(图片验证过滤器)
- CaptchaConfig(图片验证配置)



### mapper（mapper接口）

* DocumentsMapper(文件mapper接口)
* DocumentsRecordMapper(文件记录mapper接口)

- MyRBACServiceMapper(RBAC权限管理mapper接口)
- MyUserDetailsMapper(UserMapper登录验证mapper接口)
- ProjectsMapper(项目mapper接口)
- ProjectsPackageMapper(项目包mapper接口)
- ProjectsUserMapper(项目用户关系mapper接口)
- UserMapper(用户mapper接口)
- UserRecordMapper(用户日志记录mapper接口)
- UserRoleMapper(用户角色mapper接口)



### security（Spring Secuity）

- MyAuthenticationFailureHandler(登陆失败处理器)
- MyAuthenticationSuccessHandler(登录成功处理器)
- MyExpiredSessionStrategy(只允许一处登录)
- MyLogoutSuccessHandler(退出登录处理器)



### service（Service接口层）

- DocumentsRecordService(文件记录Service接口)
- DocumentsService(文件Service接口)
- MyRBACService(RBAC权限Service接口)
- MyUserDetailsService(UserDetailsService接口)
- ProjectService(项目Service接口)
- ProjectsPackageService(项目包Service接口)
- ProjectsUserService(项目角色Service接口)
- UserRecordService(用户日志接口)
- UserRoleService(用户角色接口)
- UserService(暂时不用)



### service.impl

* DocumentsRecordService(DocumentsRecordService实现层)
* DocumentsServiceImpl(DocumentsService实现层)

- MyRBACServiceImpl(RBAC权限Service实现层)
- MyUserDetailServiceImpl(UserDetailsService实现层)
- ProjectServiceImpl(ProjectService实现层)
- ProjectsPackageServiceImpl(ProjectsPackageService实现层)
- ProjectsUserServiceImpl(ProjectsUserService实现层)
- UserRecordService(UserRecordService实现层)
- UserRoleServiceImpl(UserRoleService实现层)
- UserServiceImpl(UserService实现层)



### utils

- afterLoginOrLoginOutHandler(用户登录退出日志)
- FileUtil(文件上传工具)
- MyContants(定义常量)
- IPUtil(获取IP工具类)



## resources

### cn.edu.lingnan.projectmant.mapper（SQL语句）

- DocumentsMapper(文件SQL代码)
- DocumentsRecordMapper(文件记录SQL代码)
- MyRBACServiceMapper(RBAC权限管理SQL代码)
- MyUserDetailsMapper(登录认证SQL代码)
- ProjectsMapper(项目SQL代码)
- ProjectsPackageMapper(项目包SQL代码)
- ProjectsUserMapper(项目用户关系SQL代码)
- UserMapper(用户SQL代码)
- UserRecordMapper.xml(用户日志SQL代码)
- UserRoleMapper.xml(用户角色SQL代码)



### public

### static（静态资源）

- assets(静态资源代码)

### templates（thymeleaf模板）

### application.properties（全局配置）

### application.yml（全局配置）

### kaptcha.properties（图片验证码配置）

## 数据库表

### sys_user（用户信息表）

描述：用户信息表格

| 属性名                 | 类型     | 长度 | null | 主键 | 描述         |
| ---------------------- | -------- | ---- | ---- | ---- | ------------ |
| id                     | int      |      | no   | yes  | 自增id       |
| email                  | verchar  | 32   |      |      | 登录邮箱     |
| username               | varchar  | 255  |      |      | 用户名       |
| password               | varchar  | 255  |      |      | 密码         |
| phone                  | varchar  | 16   |      |      | 电话         |
| birthday               | date     |      |      |      | 生日         |
| gender                 | tinyint  |      |      |      | 性别         |
| address                | varchar  | 255  |      |      | 地址         |
| introduce              | varchar  | 255  |      |      | 介绍         |
| last_login_time        | datetime |      |      |      | 最近登录时间 |
| create_time            | datetime |      |      |      | 创建时间     |
| enabled                | int      |      |      |      | 是否可用     |
| account_non_expired    | boolean  |      |      |      | 是否过期     |
| account_non_locked     | boolean  |      |      |      | 是否被锁     |
| credential_non_expired | boolean  |      |      |      | 证书是否被锁 |



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

| 属性名    | 类型    | 长度 | null | 主键 | 描述                   |
| --------- | ------- | ---- | ---- | ---- | ---------------------- |
| id        | int     |      | no   | yes  | 自增id                 |
| menu_name | varchar | 16   |      |      | 菜单名称               |
| url       | varchar | 64   |      |      | 菜单路径               |
| status    | tinyint |      |      |      | 0代表可用，1代表不可用 |



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

| 属性                      | 类型      | 长度 | null | 主键 | 描述         |
| ------------------------- | --------- | ---- | ---- | ---- | ------------ |
| id                        | int       |      | no   | yes  | 自增id       |
| name                      | varchar   | 64   |      |      | 项目名称     |
| charge_user_id            | int       |      |      |      | 项目管理员   |
| code_line_number          | int       |      |      |      | 项目代码行   |
| schedule                  | varchar   | 64   |      |      | 项目进度     |
| function_points           | int       |      |      |      | 功能点       |
| completed_function_points | int       |      |      |      | 已完成功能点 |
| update_count              | int       |      |      |      | 提交次数     |
| type                      | varchar   | 64   |      |      | 项目类型     |
| store_count               | int       |      |      |      | 收藏次数     |
| like_count                | int       |      |      |      | 点赞次数     |
| characterization          | varchar   | 1000 |      |      | 项目描述     |
| create_time               | timestamp |      |      |      | 创建时间     |
| enabled（1可用，0失效）   | tinyint   |      |      |      | 是否删除     |



### projects_user（项目人员表）

描述：项目人员情况

| 属性        | 类型    | 长度 | null | 主键 | 描述         |
| ----------- | ------- | ---- | ---- | ---- | ------------ |
| id          | int     |      | no   | yes  | 自增id       |
| projects_id | int     |      |      |      | 项目id       |
| user_id     | int     |      |      |      | 用户id       |
| code_update | int     |      |      |      | 用户上传次数 |
| delete_flag | tinyint |      |      |      | 是否删除     |



### projects_function（项目功能表）

描述：项目功能点情况

| 属性            | 类型    | 长度 | null | 主键 | 描述           |
| --------------- | ------- | ---- | ---- | ---- | -------------- |
| id              | int     |      | no   | yes  | 自增id         |
| projects_id     | int     |      |      |      | 项目id         |
| function_id     | int     |      |      |      | 功能id         |
| function_name   | varchar | 64   |      |      | 功能名称       |
| function_status | tinyint |      |      |      | 功能状态码     |
| user_id         | int     |      |      |      | 实现功能用户id |
| delete_flag     | tinyint |      |      |      | 是否删除       |



### projects_package（项目包结构，待考虑）

描述：项目包结构

| 属性           | 类型     | 长度 | null | 主键 | 描述     |
| -------------- | -------- | ---- | ---- | ---- | -------- |
| id             | int      |      | no   | yes  | 自增id   |
| projects_id    | int      |      |      |      | 项目id   |
| package_id     | int      |      |      |      | 包id     |
| package_name   | varchar  | 64   |      |      | 包名称   |
| documents_name | varchar  | 64   |      |      | 文件名称 |
| create_time    | datatime |      |      |      | 创建时间 |
| user_id        | int      |      |      |      | 用户id   |
| delete_flag    | tinyint  |      |      |      | 是否删除 |



### documents（文件）

描述：文件

| 属性                     | 类型     | 长度 | null | 主键 | 描述                                                |
| ------------------------ | -------- | ---- | ---- | ---- | --------------------------------------------------- |
| id                       | int      |      | no   | yes  | 自增id                                              |
| name                     | varchar  | 255  |      |      | 文件名                                              |
| user_id                  | int      |      |      |      | 用户id                                              |
| version                  | int      |      |      |      | 版本                                                |
| version_massage          | varchar  | 255  |      |      | 版本信息                                            |
| serial_number            | varchar  | 255  |      |      | 存储UUID                                            |
| projects_id              | int      |      |      |      | 项目id                                              |
| upload_time              | datetime |      |      |      | 上传时间                                            |
| version_flag             | int      |      |      |      | 确定版本号，0位过去版本，1为当前版本，2位待审核版本 |
| code_line_number(待考虑) | int      |      |      |      | 文件代码行                                          |
| delete_flag              | tinyint  |      |      |      | 是否删除                                            |



### documents_record（文件上传日志）

描述：记录文件上传记录

| 属性            | 类型     | 长度 | null | 主键 | 描述     |
| --------------- | -------- | ---- | ---- | ---- | -------- |
| id              | int      |      | no   | yes  | 自增id   |
| projects_id     | int      |      |      |      | 项目id   |
| user_id         | int      |      |      |      | 用户id   |
| operate_time    | datetime |      |      |      | 上传时间 |
| operate_massage | varchar  | 255  |      |      | 上传信息 |
| ip              | varchar  | 64   |      |      | ip地址   |
| delete_flag     | tinyint  |      |      |      | 是否删除 |



### projects_record（项目操作日志）

描述：项目操作记录

| 属性           | 类型     | 长度 | null | 主键 | 描述     |
| -------------- | -------- | ---- | ---- | ---- | -------- |
| id             | int      |      | no   | yes  | 自增id   |
| projects_id    | int      |      |      |      | 项目id   |
| user_id        | int      |      |      |      | 用户id   |
| operate_time   | datetime |      |      |      | 操作时间 |
| operate_massge | varcahr  | 255  |      |      | 操作信息 |
| ip             | varchar  | 64   |      |      | ip地址   |
| delete_flag    | tinyint  |      |      |      | 是否删除 |



### user_record（用户日志）

描述：用户操作日志

| 属性           | 类型     | 长度 | null | 主键 | 描述     |
| -------------- | -------- | ---- | ---- | ---- | -------- |
| id             | int      |      | no   | yes  | 自增id   |
| user_id        | int      |      |      |      | 用户id   |
| operate_time   | datetime |      |      |      | 操作时间 |
| operate_massge | varchar  | 255  |      |      | 操作信息 |
| ip             | varchar  | 64   |      |      | ip地址   |

### user_store（用户收藏）

描述：用户的项目收藏

| 属性                    | 类型    | 长度 | null | 主键 | 描述     |
| ----------------------- | ------- | ---- | ---- | ---- | -------- |
| id                      | int     |      | no   | yes  | 自增id   |
| user_id                 | int     |      |      |      | 用户id   |
| projects_id             | int     |      |      |      | 项目id   |
| delete_flag(1可用0失效) | tinyint |      |      |      | 是否删除 |

### user_like（用户点赞）

描述：项目的点赞

| 属性                    | 类型    | 长度 | null | 主键 | 描述     |
| ----------------------- | ------- | ---- | ---- | ---- | -------- |
| id                      | int     |      | no   | yes  | 自增id   |
| user_id                 | int     |      |      |      | 用户id   |
| projects_id             | int     |      |      |      | 项目id   |
| delete_flag(1可用0失效) | tinyint |      |      |      | 是否删除 |



## URL

自定义规则：

.html：都转发页面，防止再次请求

xxxyyyzzz：表示页面

xxx_yyy_zzz：表示controller请求



### 公共访问：

| 路径             | 请求方式（默认get） | 描述         |
| ---------------- | ------------------- | ------------ |
| /                |                     | 登录页面     |
| /login           |                     | 登录页面     |
| /login.html      |                     | 登录页面     |
| /kaptcha         |                     | 图片验证     |
| /login_user      |                     | 登录URL      |
| /login_out       |                     | 注销URL      |
| /user            | get                 | 查询用户     |
| /user            | post                | 注册用户     |
| /forgetpassword  |                     | 忘记密码页面 |
| /forget_password |                     | 忘记密码请求 |
| /resetpassword   |                     | 修改密码页面 |
| /reset_password  |                     | 修改密码请求 |
|                  |                     |              |
|                  |                     |              |
|                  |                     |              |
|                  |                     |              |
|                  |                     |              |
|                  |                     |              |
|                  |                     |              |
|                  |                     |              |



### 权限访问：

| 路径              | 请求方式（默认get） | 描述               |
| ----------------- | ------------------- | ------------------ |
| /index.html       |                     | 转发到index        |
| /userprofile      |                     | 用户信息页面       |
| /userprofile.html |                     | 装发到userprofile  |
| /user             | put                 | 修改用户信息       |
| /projectview      |                     | 项目详情页面       |
| /projectsPackages | get                 | 查询项目所有包结构 |
| /projectsPackage  | post                | 增加包             |
| /projectsPackage  | get                 | 查询包是否存在     |
|                   |                     |                    |
|                   |                     |                    |
|                   |                     |                    |
|                   |                     |                    |
|                   |                     |                    |
|                   |                     |                    |
|                   |                     |                    |
|                   |                     |                    |
|                   |                     |                    |
|                   |                     |                    |

---
typora-copy-images-to: photo

---

#  项目管理系统
https://github.com/shaosen11/project-managment



## 一、创作灵感

因为我们的专业问题，我们经常需要分组去完成一个项目。在开发过程中，我们对于项目的进度，代码的整合，项目需求沟通了解的比较缓慢，导致效率慢。因此就想弄一个项目管理系统，帮助团队实现代码托管和协助开发，方便团队开发。



## 二、项目介绍

项目管理系统主要功能是帮助个人、团队实现代码托管和协助开发。主要分为游客和已注册用户。游客可以浏览项目主页和人才市场。已注册的用户可以在系统上创建项目，加入项目，以及查看我的信息，我的项目，我的收藏，我的消息。项目主页采用协同过滤根据用户喜好向不同的用户推荐不同的项目，同时也向用户推荐了今日热门项目和本周热门项目。用户遇到喜欢的项目可以收藏或点赞。加入项目后，每个项目有项目详情，项目计划，项目功能点，项目人员页面协助用户开发。用户可以根据任务指派，完成功能点后，上传代码，也可下载代码。用户可以申请加入喜欢的项目，项目负责人也可去人才市场寻找人员并发出邀请。项目的功能点变化，会消息通知相对应的负责人。项目管理系统大大方便了团队开发。



## 三、作品特色及技术实现

项目管理系统主要用了spring cloud、spring boot、Mybatis、Redis、Spring Security技术。

1、 主界面：项目推荐（协同过滤）、今日热门项目（点击率）、本周热门项目（点击率）；

2、 项目页面：项目详情、项目计划、项目功能点、项目人员页面、功能点详情；

3、 个人界面：我的信息、我的项目、我的收藏、我的消息；

4、 登录注册、找回密码；在登录注册以及权限管理使用的是Spring Security安全框架，通过过滤器拦截进入请求，推断是否已经登录认证且具訪问相应请求的权限。找回密码使用发邮件的形式，发送随机激活链接。

5、 文件处理：上传文件，下载文件，文件显示；



## 四、需求分析

项目的功能图：

![1589633822673](photo/1589633822673.png)

## 五、数据库结构

### 1、ER图

#### 1.1 document服务

![](photo/2.png)

#### 1.2 project服务

![](photo/1.png)

#### 1.3 user服务

![](photo/3.png)

### 2、表结构

### sys_user（用户信息表）

描述：用户信息表格

| 属性名                  | 类型      | 长度 | null | 主键 | 描述             |
| ----------------------- | --------- | ---- | ---- | ---- | ---------------- |
| id                      | int       | 11   | no   | yes  | 自增id           |
| email                   | verchar   | 32   |      |      | 登录邮箱         |
| username                | varchar   | 255  |      |      | 用户名           |
| password                | varchar   | 255  |      |      | 密码             |
| phone                   | varchar   | 16   |      |      | 电话             |
| birthday                | date      |      |      |      | 生日             |
| gender                  | int       | 4    |      |      | 性别             |
| address                 | varchar   | 255  |      |      | 地址             |
| introduce               | varchar   | 255  |      |      | 介绍             |
| last_login_time         | datetime  |      |      |      | 最近登录时间     |
| create_time             | datetime  |      |      |      | 创建时间         |
| enabled（1可用，0失效） | int       | 1    |      |      | 是否可用         |
| account_non_expired     | int       | 1    |      |      | 是否过期         |
| account_non_locked      | int       | 1    |      |      | 是否被锁         |
| credential_non_expired  | int       | 1    |      |      | 证书是否被锁     |
| validata_code           | varchar   | 255  |      |      | 邮箱找回秘钥     |
| out_date                | timestamp |      |      |      | 邮箱找回过期时间 |

### sys_user_role（用户角色信息表）

描述：用户对应角色表格，spring security通过此表用户找到角色id

| 属性名  | 类型 | 长度 | null | 主键 | 描述   |
| ------- | ---- | ---- | ---- | ---- | ------ |
| id      | int  | 11   | no   | yes  | 自增id |
| user_id | int  | 11   |      |      | 用户id |
| role_id | int  | 11   |      |      | 角色id |

### sys_role（角色表）

描述：角色信息表

| 属性名      | 类型     | 长度 | null | 主键 | 描述                   |
| ----------- | -------- | ---- | ---- | ---- | ---------------------- |
| id          | int      | 11   | no   | yes  | 自增id                 |
| role_name   | varchar  | 64   |      |      | 角色名                 |
| role_code   | varchar  | 64   |      |      | 角色代码               |
| role_desc   | varchar  | 64   |      |      | 角色描述               |
| sort        | int      | 11   |      |      | 角色排序               |
| status      | int      | 11   |      |      | 0代表可用，1代表不可用 |
| create_time | datetime |      |      |      | 创建时间               |

### sys_role_menu（角色菜单表）

描述：角色菜单表，通过角色找到可以访问路径

| 属性名  | 类型 | 长度 | null | 主键 | 描述   |
| ------- | ---- | ---- | ---- | ---- | ------ |
| id      | int  | 11   | no   | yes  | 自增id |
| role_id | int  | 11   |      |      | 角色id |
| menu_id | int  | 11   |      |      | 菜单id |

### sys_menu（菜单表）

描述：菜单表，pid,pids等属性还没使用

| 属性名    | 类型    | 长度 | null | 主键 | 描述                   |
| --------- | ------- | ---- | ---- | ---- | ---------------------- |
| id        | int     | 11   | no   | yes  | 自增id                 |
| menu_name | varchar | 16   |      |      | 菜单名称               |
| url       | varchar | 64   |      |      | 菜单路径               |
| status    | int     | 4    |      |      | 0代表可用，1代表不可用 |

### persistent_logins（cookie表）

描述：浏览器缓存cookie

| 属性      | 类型      | 长度 | null | 主键 | 描述         |
| --------- | --------- | ---- | ---- | ---- | ------------ |
| username  | varchar   | 64   |      |      | 用户名       |
| series    | varchar   | 64   | no   | yes  | 序列号       |
| token     | varchar   | 64   |      |      | token        |
| last_used | timestamp |      |      |      | 最近登录时间 |

### message 个人消息表

描述：个人消息

| 属性         | 类型    | 长度 | null | 主键 | 描述                                                         |
| ------------ | ------- | ---- | ---- | ---- | ------------------------------------------------------------ |
| id           | int     | 11   |      |      | 自增id                                                       |
| type_id      | int     | 4    | no   | yes  | 消息类型                                                     |
| from_user_id | int     | 11   |      |      | 来自谁                                                       |
| to_user_id   | int     | 11   |      |      | 给谁                                                         |
| message      | varchar | 255  |      |      | 消息体                                                       |
| time         | varchar | 64   |      |      | 时间                                                         |
| is_read      | tinyint | 4    | no   |      | 是否已读，1未读，0已读                                       |
| delete_flag  | tinyint | 4    | no   |      | 是否删除，1不删，0删除                                       |
| need_to_do   | int     | 4    | no   |      | 是否需要去做，1表示需要去做，为代办，0表示不需要去做，为通知 |

### message_type 个人消息类型表

描述：个人消息类型

| 属性        | 类型    | 长度 | null | 主键 | 描述                 |
| ----------- | ------- | ---- | ---- | ---- | -------------------- |
| id          | int     | 11   | no   | yes  | 自增id               |
| type        | varchar | 64   |      |      | 消息名               |
| icon        | varchar | 64   |      |      | 图标                 |
| background  | varchar | 64   |      |      | 背景色1              |
| delete_flag | int     | 4    |      |      | 1表示可用，0表示删除 |
| background2 | varchar | 64   |      |      | 时背景色2            |

### message_need_to_do_relationship 个人消息关联表

描述：个人消息关联表，为了方便处理待办消息

| 属性                          | 类型 | 长度 | null | 主键 | 描述                 |
| ----------------------------- | ---- | ---- | ---- | ---- | -------------------- |
| id                            | int  | 11   | no   | yes  | 自增id               |
| message_id                    | int  | 11   |      |      | 消息id               |
| projects_user_cooperration_id | int  | 11   |      |      | 项目邀请id           |
| delete_flag                   | int  | 4    |      |      | 1表示可用，0表示删除 |

### project（项目表）

描述：项目表格

| 属性                        | 类型      | 长度 | null | 主键 | 描述           |
| --------------------------- | --------- | ---- | ---- | ---- | -------------- |
| id                          | int       | 11   | no   | yes  | 自增id         |
| name                        | varchar   | 64   |      |      | 项目名称       |
| charge_user_id              | int       | 11   |      |      | 项目管理员     |
| code_line_number            | int       | 11   |      |      | 上传次数统计   |
| code_update_count           | int       | 11   |      |      | 上传代码行统计 |
| schedule_id                 | int       | 4    |      |      | 项目进度       |
| function_points             | int       | 11   |      |      | 功能点         |
| completed_function_points   | int       | 11   |      |      | 已完成功能点   |
| last_update_time            | datetime  |      |      |      | 最近更新时间   |
| type_id                     | int       | 4    |      |      | 项目类型       |
| store_count                 | int       | 11   |      |      | 收藏次数       |
| like_count                  | int       | 11   |      |      | 点赞次数       |
| characterization            | varchar   | 1000 |      |      | 项目描述       |
| create_time                 | timestamp |      |      |      | 创建时间       |
| delete_flag（1可用，0失效） | int       | 4    |      |      | 是否删除       |
| user_count                  | int       | 11   |      |      | 项目人数       |
| planned_start_time          | data      |      |      |      | 计划开始时间   |
| planned_end_time            | data      |      |      |      | 计划结束时间   |
| actual_start_time           | data      |      |      |      | 实际开始时间   |
| actual_end_time             | data      |      |      |      | 实际结束时间   |

### project_schedule（项目进度）

| 属性     | 类型    | 长度 | null | 主键 | 描述     |
| -------- | ------- | ---- | ---- | ---- | -------- |
| id       | int     | 11   | no   | yes  | 自增id   |
| schedule | varchar | 64   |      |      | 进度描述 |

### project_type（项目类型）

| 属性 | 类型    | 长度 | null | 主键 | 描述     |
| :--- | ------- | ---- | ---- | ---- | -------- |
| id   | int     | 11   | no   | yes  | 自增id   |
| type | varchar | 64   |      |      | 项目类型 |

### project_code_line（ 项目代码行表）

描述：记录项目每个时间段代码行

| 属性                    | 类型 | 长度 | null | 主键 | 描述     |
| ----------------------- | ---- | ---- | ---- | ---- | -------- |
| id                      | int  | 11   | no   | yes  | 自增id   |
| project_id              | int  | 11   |      |      | 项目id   |
| code_line_number        | int  | 11   |      |      | 代码行   |
| upload_time             | data |      |      |      | 上传时间 |
| delete_flag(1可用0失效) | int  | 4    |      |      | 是否删除 |

### projects_user（项目人员表）

描述：项目人员情况

| 属性                    | 类型     | 长度 | null | 主键 | 描述           |
| ----------------------- | -------- | ---- | ---- | ---- | -------------- |
| id                      | int      | 11   | no   | yes  | 自增id         |
| project_id              | int      | 11   |      |      | 项目id         |
| user_id                 | int      | 11   |      |      | 用户id         |
| duty_code               | int      | 4    |      |      | 角色码         |
| code_devote_line        | int      | 11   |      |      | 用户贡献代码行 |
| code_update             | int      | 11   |      |      | 用户上传次数   |
| delete_flag(1可用0失效) | int      | 4    |      |      | 是否删除       |
| join_time               | datatime |      |      |      | 加入时间       |

### projects_user_duty（ 项目用户角色表）

描述：项目人员角色描述

| 属性        | 类型    | 长度 | null | 主键 | 描述     |
| ----------- | ------- | ---- | ---- | ---- | -------- |
| id          | int     | 11   | no   | yes  | 自增id   |
| duty_name   | varchar | 255  |      |      | 角色名称 |
| delete_flag | int     | 11   |      |      | 是否删除 |

### projects_user_cooperation （项目用户合作关系）

描述：记录邀请非项目人员信息

| 属性                   | 类型     | 长度 | null | 主键 | 描述                                   |
| ---------------------- | -------- | ---- | ---- | ---- | -------------------------------------- |
| id                     | int      | 11   | no   | yes  | 自增id                                 |
| project_id             | int      | 11   |      |      | 项目id                                 |
| in_project_user_id     | int      | 11   |      |      | 在项目的用户id                         |
| not_in_project_user_id | int      | 11   |      |      | 不在项目的用户id                       |
| duty_code              | int      | 4    | no   |      | 项目角色id                             |
| time                   | datetime |      |      |      | 时间                                   |
| delete_flag            | int      | 4    | no   |      | 是否删除                               |
| invite_flag            | int      | 4    | no   |      | 是不是邀请记录，1是                    |
| join_flag              | int      | 4    | no   |      | 是不是加入记录，1是                    |
| finish_flag            | int      | 4    | no   |      | 是否完成邀请或加入，0是未完成，1是完成 |
| success_flag           | int      | 4    | no   |      | 标志是否成，1为成功，-1位拒绝          |

### projects_function（项目功能表）

描述：项目功能点情况

| 属性                                           | 类型    | 长度 | null | 主键 | 描述           |
| ---------------------------------------------- | ------- | ---- | ---- | ---- | -------------- |
| id                                             | int     | 11   | no   | yes  | 自增id         |
| project_id                                     | int     | 11   |      |      | 项目id         |
| function_id                                    | int     | 11   |      |      | 功能id         |
| function_name                                  | varchar | 64   |      |      | 功能名称       |
| function_status(0待办,1进行中,2已完成,3已验收) | int     | 4    |      |      | 功能状态码     |
| publish_user_id                                | int     | 11   |      |      | 发布功能用户id |
| realize_user_id                                | int     | 11   |      |      | 实现功能用户id |
| planned_start_time                             | data    |      |      |      | 计划开始时间   |
| planned_end_time                               | data    |      |      |      | 计划结束时间   |
| actual_start_time                              | data    |      |      |      | 实际开始时间   |
| actual_end_time                                | data    |      |      |      | 实际结束时间   |
| description                                    | varchar | 255  |      |      | 功能描述       |
| del_reason                                     | varchar | 255  |      |      | 删除原因       |
| delete_flag(1可用0失效)                        | int     |      |      |      | 是否删除       |

### project_package（项目包结构）

描述：项目包结构

| 属性                    | 类型     | 长度 | null | 主键 | 描述     |
| ----------------------- | -------- | ---- | ---- | ---- | -------- |
| id                      | int      | 11   | no   | yes  | 自增id   |
| project_id              | int      | 11   |      |      | 项目id   |
| package_id              | int      | 11   |      |      | 包id     |
| package_name            | varchar  | 64   |      |      | 包名称   |
| document_name           | varchar  | 64   |      |      | 文件名称 |
| create_time             | datatime |      |      |      | 创建时间 |
| user_id                 | int      | 11   |      |      | 用户id   |
| delete_flag(1可用0失效) | int      | 4    |      |      | 是否删除 |

### projects_message （项目消息表）

描述：项目消息

| 属性         | 类型    | 长度 | null | 主键 | 描述                                                         |
| ------------ | ------- | ---- | ---- | ---- | ------------------------------------------------------------ |
| id           | int     | 11   |      |      | 自增id                                                       |
| project_id   | int     | 11   |      |      | 项目id                                                       |
| type_id      | int     | 4    | no   | yes  | 消息类型                                                     |
| from_user_id | int     | 11   |      |      | 来自谁                                                       |
| to_user_id   | int     | 11   |      |      | 给谁                                                         |
| message      | varchar | 255  |      |      | 消息体                                                       |
| time         | varchar | 64   |      |      | 时间                                                         |
| is_read      | int     | 4    | no   |      | 是否已读，1未读，0已读                                       |
| all_flag     | int     | 4    | no   |      | 1代表项目所有人可见                                          |
| admin_flag   | int     | 4    | no   |      | 1代表仅管理员可见                                            |
| delete_flag  | int     | 4    | no   |      | 是否删除，1不删，0删除                                       |
| need_to_do   | int     | 4    | no   |      | 是否需要去做，1表示需要去做，为代办，0表示不需要去做，为通知 |

### project_message_type （项目消息类型）

描述：项目消息类型

| 属性        | 类型    | 长度 | null | 主键 | 描述                 |
| ----------- | ------- | ---- | ---- | ---- | -------------------- |
| id          | int     | 11   | no   | yes  | 自增id               |
| type        | varchar | 64   |      |      | 类型名称             |
| icon        | varchar | 64   |      |      | 网页图标             |
| background  | varchar | 64   |      |      | 背景色               |
| delete_flag | int     | 4    |      |      | 1表示可用，0表示删除 |

### project_message_need_to_do_relationship（ 项目消息关联表）

描述：项目消息关联表，为了方便处理待办消息

| 属性               | 类型 | 长度 | null | 主键 | 描述                 |
| ------------------ | ---- | ---- | ---- | ---- | -------------------- |
| id                 | int  | 11   | no   | yes  | 自增id               |
| project_message_id | int  | 11   |      |      | 项目消息id           |
| document_id        | int  | 11   |      |      | 待审核文件id         |
| delete_flag        | int  | 4    |      |      | 1表示可用，0表示删除 |

### document（文件）

描述：文件

| 属性                    | 类型     | 长度 | null | 主键 | 描述                                                |
| ----------------------- | -------- | ---- | ---- | ---- | --------------------------------------------------- |
| id                      | int      | 11   | no   | yes  | 自增id                                              |
| name                    | varchar  | 255  |      |      | 文件名                                              |
| user_id                 | int      | 11   |      |      | 用户id                                              |
| version                 | int      | 11   |      |      | 版本                                                |
| version_massage         | varchar  | 255  |      |      | 版本信息                                            |
| serial_number           | varchar  | 255  |      |      | 存储UUID                                            |
| projects_id             | int      | 11   |      |      | 项目id                                              |
| upload_time             | datetime |      |      |      | 上传时间                                            |
| version_flag            | int      | 11   |      |      | 确定版本号，0位过去版本，1为当前版本，2位待审核版本 |
| code_line_number        | int      | 11   |      |      | 文件代码行                                          |
| delete_flag(1可用0失效) | int      | 4    |      |      | 是否删除                                            |

### document_record（文件上传日志）

描述：记录文件上传记录

| 属性                      | 类型     | 长度 | null | 主键 | 描述     |
| ------------------------- | -------- | ---- | ---- | ---- | -------- |
| id                        | int      | 11   | no   | yes  | 自增id   |
| project_id                | int      | 11   |      |      | 项目id   |
| user_id                   | int      | 11   |      |      | 用户id   |
| operate_time              | datetime |      |      |      | 上传时间 |
| operate_massage（待考虑） | varchar  | 255  |      |      | 上传信息 |
| ip                        | varchar  | 64   |      |      | ip地址   |
| delete_flag(1可用0失效)   | int      | 4    |      |      | 是否删除 |

### project_record（项目操作日志）

描述：项目操作记录

| 属性                    | 类型     | 长度 | null | 主键 | 描述     |
| ----------------------- | -------- | ---- | ---- | ---- | -------- |
| id                      | int      | 11   | no   | yes  | 自增id   |
| project_id              | int      | 11   |      |      | 项目id   |
| user_id                 | int      | 11   |      |      | 用户id   |
| operate_time            | datetime |      |      |      | 操作时间 |
| operate_massge          | varcahr  | 255  |      |      | 操作信息 |
| ip                      | varchar  | 64   |      |      | ip地址   |
| delete_flag(1可用0失效) | int      | 4    |      |      | 是否删除 |

### user_record（用户日志）

描述：用户操作日志

| 属性           | 类型     | 长度 | null | 主键 | 描述     |
| -------------- | -------- | ---- | ---- | ---- | -------- |
| id             | int      | 11   | no   | yes  | 自增id   |
| user_id        | int      | 11   |      |      | 用户id   |
| operate_time   | datetime |      |      |      | 操作时间 |
| operate_massge | varchar  | 255  |      |      | 操作信息 |
| ip             | varchar  | 64   |      |      | ip地址   |

### user_store（用户收藏）

描述：用户的项目收藏

| 属性                    | 类型     | 长度 | null | 主键 | 描述     |
| ----------------------- | -------- | ---- | ---- | ---- | -------- |
| id                      | int      | 11   | no   | yes  | 自增id   |
| user_id                 | int      | 11   |      |      | 用户id   |
| project_id              | int      | 11   |      |      | 项目id   |
| delete_flag(1可用0失效) | int      | 4    |      |      | 是否删除 |
| store_time              | datetime |      |      |      |          |

### user_like（用户点赞）

描述：项目的点赞

| 属性                    | 类型     | 长度 | null | 主键 | 描述     |
| ----------------------- | -------- | ---- | ---- | ---- | -------- |
| id                      | int      | 11   | no   | yes  | 自增id   |
| user_id                 | int      | 11   |      |      | 用户id   |
| project_id              | int      | 11   |      |      | 项目id   |
| like_time               | datetime |      |      |      | 点赞时间 |
| delete_flag(1可用0失效) | int      | 4    |      |      | 是否删除 |

### user_click（用户浏览记录表）

描述：用户浏览项目记录

| 属性        | 类型     | 长度 | null | 主键 | 描述     |
| ----------- | -------- | ---- | ---- | ---- | -------- |
| id          | int      | 11   | no   | yes  | 自增id   |
| user_id     | int      | 11   |      |      | 用户id   |
| project_id  | int      | 11   |      |      | 项目id   |
| click_time  | datatime |      |      |      | 点击时间 |
| delete_flag | int      | 4    |      |      | 是否删除 |

### user_code_update_record（ 用户上传记录）

描述：用户上传记录

| 属性              | 类型 | 长度 | null | 主键 | 描述     |
| ----------------- | ---- | ---- | ---- | ---- | -------- |
| id                | int  | 11   | no   | yes  | 自增id   |
| user_id           | int  | 11   |      |      | 用户id   |
| time              | date |      |      |      | 上传日期 |
| code_update_count | int  | 11   |      |      | 上传次数 |
| delete_flag       | int  | 4    |      |      | 是否删除 |

## 六、成果图

### 主页


![image-20200516211528173](photo/image-20200516211528173.png)

### 登录、注册、找回密码  

<img src="photo/image-20200516211905823.png" alt="image-20200516211905823" style="zoom: 67%;" />

<img src="photo/image-20200516212001811.png" alt="image-20200516212001811" style="zoom:67%;" />

<img src="photo/image-20200516212028155.png" alt="image-20200516212028155" style="zoom:67%;" />

### 项目界面  

#### （1）项目主页面

![image-20200516212207208](photo/image-20200516212207208.png)

#### （2）项目计划

![image-20200516212306813](photo/image-20200516212306813.png)

#### （3）项目功能点

![image-20200516212357084](photo/image-20200516212357084.png)

#### （4）项目人员

### ![image-20200516212430666](photo/image-20200516212430666.png)

### 人才市场  

![image-20200516212514375](photo/image-20200516212514375.png)

### 消息提醒

![image-20200516212544458](photo/image-20200516212544458.png)

### 个人消息提醒

![image-20200516220905714](photo\image-20200516220905714.png)

###  我的信息  

![image-20200516212640030](photo/image-20200516212640030.png)

### 我的项目

![image-20200622095802615](photo/image-20200622095802615.png)

### 创建项目

![image-20200516220801339](photo\image-20200516220801339.png)

###  我的收藏  

![image-20200516212719781](photo/image-20200516212719781.png)

###  代码显示  

![](photo\image-20200516220624630.png)

### 上传文件

![image-20200516220705605](photo\image-20200516220705605.png)

### 新建包

![image-20200516220721121](photo\image-20200516220721121.png)



## 七、组员工作划分

**1、邵森（组长）**

登录、注册（以及表单验证）、验证码、通过邮箱验证码修改密码，完成document,document_record,project,project_code_line,project_message,project_message_type,project_message_need_to_do,project_package,project_record,project_user,project_user_cooperation，project_user_duty增删查改



**2、谢梁琦**

完成项目功能点、项目计划、我的项目、我的收藏页面的需求。项目功能点页面包括多各个状态的功能点的分页展示，以及相应权限的用户可以添加功能点和删除功能点。项目计划页面展示整个项目的安排计划。我的项目页面可以查看我参与的所有项目或我负责的所有项目。



**3、黄雅婷**

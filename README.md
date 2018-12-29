# SignInServer
#### 数据库说明
>用户表

| Field    | Type        | Null | Key | Default | 说明 |
|-|-|-|-|-|-|
| name     | varchar(10) | NO   |     | NULL    |    姓名   |
| account  | varchar(15) | NO   | PRI | NULL    |   账号    |
| password | varchar(50) | NO   |     | NULL    |  密码     |
| role     | varchar(20) | NO   |     | NULL    |     职位 选填teacher或student  |
| mac      | varchar(50) | NO   |     | 0       |  mac地址     |
| phone    | varchar(15) | NO   |     | NULL    |  电话号码     |

>班级表

| Field     | Type           | Null | Key | Default | 说明 |
|-|-|-|-|-|-|
| name      | varchar(50)    | NO   |     | NULL    |   班级名称    |
| teacher   | varchar(15)    | NO   |     | NULL    |   老师姓名    |
| code      | varchar(4)     | NO   | PRI | NULL    |   课堂暗号    |
| student   | varchar(15000) | NO   |     | NULL    |   学生姓名名单 为空时为" "即空格 有人时为" 张三 李四 "即空格甲空格乙空格|
| longitude | double         | NO   |     | NULL    |   创建时的经度    |
| latitude  | double         | NO   |     | NULL    |   创建时的纬度    |
| start     | int(11)        | YES  |     | NULL    |   是否开始 0为未开始 1为开始    |
| next      | int(11)        | NO   |     | NULL    |   签到次数 初始为0 第一次开始签到时为1 第二次开始时为2    |

>日志表

| Field | Type           | Null | Key | Default | 说明 |
|-|-|-|-|-|-|
| class | varchar(4)     | NO   | PRI | NULL    |   课堂暗号   |
| time  | varchar(3)     | NO   | PRI | NULL    |   第几次    |
| list  | varchar(15000) | NO   |     | NULL    |   到课学生姓名名单 格式类似班级表中的student字段    |

#### 接口文档
> 用户相关

- 用户登录
  - 地址:http://106.15.187.231:8080/SignInServer/login
  - 请求方式:GET/POST
  - 请求参数及说明:

    | 参数名 | 是否必填 | 说明 |
    | - | - | - |
    |account|是|账号|
    |password|是|密码|
    ```
    GET示例
    http://106.15.187.231:8080/SignInServer/login?account=1&password=1
    ```
  - 返回json:

    |参数名|类型|说明|
    | - | - | - |
    |code|int|0:学生 1:老师 -1:失败|
    ```
    示例
    {"code":-1} 这表示登陆失败，可能是用户名或密码错误
    ```
    
- 用户注册
  - 地址:http://106.15.187.231:8080/SignInServer/register
  - 请求方式:GET/POST
  - 请求参数及说明:

    | 参数名 | 是否必填 | 说明 |
    | - | - | - |
    |account|是|账号|
    |password|是|密码|
    |name|是|姓名|
    |role|是|职位 学生填student老师填teacher|
    |mac|是|mac地址 (我记得老师说需要绑定mac地址的，注册的时候先获取mac地址直接提交上来)|
    |phone|是|电话号码|
    ```
    GET示例
    http://106.15.187.231:8080/SignInServer/register?account=1&password=1&name=张三&role=teacher&mac=00-01-6C-06-A6-29&phone=18811111111
    ```
  - 返回json:

    |参数名|类型|说明|
    | - | - | - |
    |code|int|0:成功 -1:失败|
    ```
    示例
    {"code":-1} 这表示注册失败，可能是用户名被占用
    ```
    
- 用户修改密码
  - 地址:http://106.15.187.231:8080/SignInServer/changePassword
  - 请求方式:GET/POST
  - 请求参数及说明:

    | 参数名 | 是否必填 | 说明 |
    | ------| ------- | ------- |
    |account|是|账号|
    |password|是|新密码|
    ```
    GET示例
    http://106.15.187.231:8080/SignInServer/changePassword?account=1&password=2
    ```
  - 返回json:

    |参数名|类型|说明|
    | - | - | - |
    |code|int|0:成功|
    ```
    示例
    {"code":0} 这表示修改成功
    ```
    
- 用户修改电话号码
  - 地址:http://106.15.187.231:8080/SignInServer/changePhone
  - 请求方式:GET/POST
  - 请求参数及说明:

    | 参数名 | 是否必填 | 说明 |
    | - | - | - |
    |account|是|账号|
    |phone|是|新手机号|
    ```
    GET示例
    http://106.15.187.231:8080/SignInServer/changePhone?account=1&phone=18822222222
    ```
  - 返回json:

    |参数名|类型|说明|
    | - | - | - |
    |code|int|0:成功|
    ```
    示例
    {"code":0} 这表示修改成功
    ```
- 用户注销
  - 地址:http://106.15.187.231:8080/SignInServer/logout
  - 请求方式:GET/POST
  - 请求参数及说明:
    无
 
    ```
    GET示例
    http://106.15.187.231:8080/SignInServer/logout
    ```
  - 返回json:

    |参数名|类型|说明|
    | - | - | - |
    |code|int|0:成功|
    ```
    示例
    {"code":0} 这表示注销成功
    ```
- 获取当前用户信息
  - 地址:http://106.15.187.231:8080/SignInServer/getCurrent
  - 请求方式:GET/POST
  - 请求参数及说明:
    无
    ```
    GET示例
    http://106.15.187.231:8080/SignInServer/getCurrent
    ```
  - 返回json:

    |参数名|类型|说明|
    | - | - | - |
    |code|int|0:成功|
    |data|Users|当前登录人|
    ```
    示例
    {"code":0,
    "data":{"account":"916106840777",
            "mac":"test",
            "name":"张三",
            "password":"123456",
            "phone":"10086",
            "role":"student"
            }
    }
    这表示获取成功
    ```
> 班级相关
- 创建班级
  - 地址:http://106.15.187.231:8080/SignInServer/createClazz
  - 请求方式:GET/POST
  - 请求参数及说明:
  
    | 参数名 | 是否必填 | 说明 |
    | ------| ------- | ------- |
    |name|是|班级名|
    |longitude|是|经度|
    |latitude|是|纬度|
    ```
    GET示例
    http://106.15.187.231:8080/SignInServer/createClazz?name=测试1&longitude=120.123&latitude=48.456
    ```
  - 返回json:

    |参数名|类型|说明|
    | - | - | - |
    |code|int|0:成功|
    |key|String|课堂暗号|
    ```
    示例
    {"code":0,"key":"567c"}
    这表示创建成功，暗号为567c
    ```
- 加入班级
  - 地址:http://106.15.187.231:8080/SignInServer/joinClazz
  - 请求方式:GET/POST
  - 请求参数及说明:
  
    | 参数名 | 是否必填 | 说明 |
    | ------| ------- | ------- |
    |code|是|课堂暗号|
    ```
    GET示例
    http://106.15.187.231:8080/SignInServer/joinClazz?code=567c
    ```
  - 返回json:

    |参数名|类型|说明|
    | - | - | - |
    |code|int|0:成功 -1:没有此班级|
    ```
    示例
    {"code":0}
    这表示加入成功
    ```
- 删除班级
  - 地址:http://106.15.187.231:8080/SignInServer/deleteClazz
  - 请求方式:GET/POST
  - 请求参数及说明:
  
    | 参数名 | 是否必填 | 说明 |
    | ------| ------- | ------- |
    |code|是|课堂暗号|
    ```
    GET示例
    http://106.15.187.231:8080/SignInServer/deleteClazz?code=567c
    ```
  - 返回json:

    |参数名|类型|说明|
    | - | - | - |
    |code|int|0:成功|
    ```
    示例
    {"code":0}
    这表示删除成功
    ```
- 退出班级
  - 地址:http://106.15.187.231:8080/SignInServer/quitClazz
  - 请求方式:GET/POST
  - 请求参数及说明:
  
    | 参数名 | 是否必填 | 说明 |
    | ------| ------- | ------- |
    |code|是|课堂暗号|
    ```
    GET示例
    http://106.15.187.231:8080/SignInServer/quitClazz?code=567c
    ```
  - 返回json:

    |参数名|类型|说明|
    | - | - | - |
    |code|int|0:成功|
    ```
    示例
    {"code":0}
    这表示退出成功
    ```
- 开始签到
  - 地址:http://106.15.187.231:8080/SignInServer/startSignIn
  - 请求方式:GET/POST
  - 请求参数及说明:
  
    | 参数名 | 是否必填 | 说明 |
    | ------| ------- | ------- |
    |code|是|课堂暗号|
    ```
    GET示例
    http://106.15.187.231:8080/SignInServer/startSignIn?code=567c
    ```
  - 返回json:

    |参数名|类型|说明|
    | - | - | - |
    |code|int|0:成功|
    ```
    示例
    {"code":0}
    这表示开始签到成功
    ```
- 停止签到
  - 地址:http://106.15.187.231:8080/SignInServer/stopSignIn
  - 请求方式:GET/POST
  - 请求参数及说明:
  
    | 参数名 | 是否必填 | 说明 |
    | ------| ------- | ------- |
    |code|是|课堂暗号|
    ```
    GET示例
    http://106.15.187.231:8080/SignInServer/stopSignIn?code=567c
    ```
  - 返回json:

    |参数名|类型|说明|
    | - | - | - |
    |code|int|0:成功|
    ```
    示例
    {"code":0}
    这表示停止签到成功
    ```
- 获取班级列表
  - 地址:http://106.15.187.231:8080/SignInServer/getClazz
  - 请求方式:GET/POST
  - 请求参数及说明:
    无
    ```
    GET示例
    http://106.15.187.231:8080/SignInServer/getClazz
    ```
  - 返回json:

    |参数名|类型|说明|
    | - | - | - |
    |code|int|0:成功|
    |count|int|班级数量|
    |data|List<Classes>|班级的List|
    ```
    示例
    {"code":0,
    "data":[{"code":"567c",
             "latitude":48.456,
             "longitude":120.123,
             "name":"测试1",
             "next":0,
             "start":0,
             "student":" 1 ",
             "teacher":"张三"},
            {"code":"ec3a",
            "latitude":456.78,
            "longitude":123.45,
            "name":"哈哈",
            "next":0,
            "start":0,
            "student":" ",
            "teacher":"张三"},
            {"code":"ee7a",
            "latitude":456.78,
            "longitude":123.45,
            "name":"测试",
            "next":0,
            "start":0,
            "student":" ",
            "teacher":"张三"}
            ],
    "count":3
    }
    这表示获取成功
    ```
- 签到
  - 地址:http://106.15.187.231:8080/SignInServer/signIn
  - 请求方式:GET/POST
  - 请求参数及说明:
  
    | 参数名 | 是否必填 | 说明 |
    | ------| ------- | ------- |
    |code|是|课堂暗号|
    |newLongitude|是|当前经度|
    |newLatitude|是|当前纬度|
    |newMac|是|当前MAC地址
    ```
    GET示例
    http://106.15.187.231:8080/SignInServer/signIn?code=567c&newLongitude=123.45&newLatitude=67.89&newMac=00-01-6C-06-A6-29
    ```
  - 返回json:

    |参数名|类型|说明|
    | - | - | - |
    |code|int|0:成功 -1:未开始签到 -2:mac地址不对 -3:位置不对 -4:没有此班级|
    ```
    示例
    {"code":0}
    这表示签到成功
    ```
- 获取日志
  - 地址:http://106.15.187.231:8080/SignInServer/getLog
  - 请求方式:GET/POST
  - 请求参数及说明:
  
    | 参数名 | 是否必填 | 说明 |
    | ------| ------- | ------- |
    |code|是|课堂暗号|
    ```
    GET示例
    http://106.15.187.231:8080/SignInServer/getLog?code=567c
    ```
  - 返回json:

    |参数名|类型|说明|
    | - | - | - |
    |code|int|0:成功|
    |count|int|日志数量|
    |data|List<Logs>|日志的List|
    ```
    示例
    {"code":0,
     "data":[{"clazz":"567c","list":" 1 ","time":"1"}],
     "count":1
    }
    这表示获取成功
    ```
    
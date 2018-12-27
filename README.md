# SignInServer
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
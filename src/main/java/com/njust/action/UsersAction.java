package com.njust.action;

import com.njust.service.user.UserService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

@Controller
@Scope("prototype")
@ParentPackage("json-default")
@Namespace("/")
public class UsersAction extends ActionSupport{
    @Autowired
    private UserService userService;

    private Map<String, Object> map;
    private String account;

    private String password;


    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    @Action(
            value = "login",
            results = {
                    @Result(name = "json", type = "json", params = {"root", "map"})
            }
    )
    public String login() {
        map = new HashMap<String, Object>();
        int tem = userService.login(getAccount(),getPassword());
        if (tem == -1)
            map.put("code",-1);//用户名密码错误
        if (tem == 0)
            map.put("code",0);//学生
        if (tem == 1)
            map.put("code",1);//老师
        return "json";
    }


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

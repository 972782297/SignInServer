package com.njust.action;

import com.njust.entity.Users;
import com.njust.service.user.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
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
public class UsersAction extends ActionSupport implements ModelDriven<Users> {
    @Autowired
    private UserService userService;

    private Map<String, Object> map;

    private Users u = new Users();

    @Override
    public Users getModel() {
        return u;
    }


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
        int tem = userService.login(u.getAccount(),u.getPassword());
        if (tem == -1)
            map.put("code",-1);//用户名密码错误
        if (tem == 0)
            map.put("code",0);//学生
        if (tem == 1)
            map.put("code",1);//老师
        return "json";
    }

    @Action(
            value = "register",
            results = {
                    @Result(name = "json",type = "json",params = {"root","map"})
            }
    )
    public String register(){
        map=new HashMap<String,Object>();
        boolean f=userService.register(u.getAccount(),u.getPassword(),u.getRole(),u.getName(),u.getPhone(),u.getMac());
        if (f)
            map.put("code",0);//成功
        else
            map.put("code",-1);//失败，例如用户名被占用
        return "json";
    }

    @Action(
            value = "changePassword",
            results = {
                    @Result(name = "json",type = "json",params = {"root","map"})
            }
    )
    public String changePassword(){
        map=new HashMap<String,Object>();
        userService.changePassword(u.getAccount(),u.getPassword());
        map.put("code",0);
        return "json";
    }

    @Action(
            value = "changePhone",
            results = {
                    @Result(name = "json",type = "json",params = {"root","map"})
            }
    )
    public String changePhone(){
        map=new HashMap<String,Object>();
        userService.changePhone(u.getAccount(),u.getPhone());
        map.put("code",0);
        return "json";
    }

    @Action(
            value = "logout",
            results = {
                    @Result(name = "json",type = "json",params = {"root","map"})
            }
    )
    public String logout(){
        map=new HashMap<String,Object>();
        userService.logout();
        map.put("code",0);
        return "json";
    }

    @Action(
            value = "getCurrent",
            results = {
                    @Result(name = "json",type = "json",params = {"root","map"})
            }
    )
    public String getCurrent(){
        Users uu = (Users) ActionContext.getContext().getSession().get("user");
        map = new HashMap<String, Object>();
        map.put("code",0);
        map.put("data",uu);
        return "json";
    }
}

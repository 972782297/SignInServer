package com.njust.action;

import com.njust.entity.Classes;
import com.njust.service.clazz.ClazzService;
import com.njust.service.log.LogService;
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
import java.util.List;
import java.util.Map;

@Controller
@Scope("prototype")
@ParentPackage("json-default")
@Namespace("/")
public class ClazzAction extends ActionSupport implements ModelDriven<Classes> {
    @Autowired
    private ClazzService clazzService;
    @Autowired
    private LogService logService;

    private Map<String, Object> map;

    private Classes c=new Classes();

    private String newMac;
    private double newLongitude,newLatitude;

    @Override
    public Classes getModel() {
        return c;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    //0 成功 返回暗号
    @Action(
            value = "createClazz",
            results = {
                    @Result(name = "json", type = "json", params = {"root", "map"})
            }
    )
    public String createClazz(){
        map=new HashMap<String,Object>();
        String code=clazzService.createClazz(c.getName(),c.getLongitude(),c.getLatitude());
        map.put("code",0);
        map.put("key",code);
        return "json";
    }

    //0 成功 -1没有此班级
    @Action(
            value = "joinClazz",
            results = {
                    @Result(name = "json", type = "json", params = {"root", "map"})
            }
    )
    public String joinClazz(){
        map=new HashMap<>();
        map.put("code",clazzService.joinClazz(c.getCode()));
        return "json";
    }

    //0 成功
    @Action(
            value = "deleteClazz",
            results = {
                    @Result(name = "json", type = "json", params = {"root", "map"})
            }
    )
    public String deleteClazz(){
        map=new HashMap<>();
        clazzService.deleteClazz(c.getCode());
        map.put("code",0);
        return "json";
    }
    //0 成功
    @Action(
            value = "quitClazz",
            results = {
                    @Result(name = "json", type = "json", params = {"root", "map"})
            }
    )
    public String quitClazz(){
        map=new HashMap<>();
        clazzService.quitClazz(c.getCode());
        map.put("code",0);
        return "json";
    }
    //0 成功
    @Action(
            value = "startSignIn",
            results = {
                    @Result(name = "json", type = "json", params = {"root", "map"})
            }
    )
    public String startSignIn(){
        map=new HashMap<>();
        clazzService.startSignIn(c.getCode());
        map.put("code",0);
        return "json";
    }

    //0 成功
    @Action(
            value = "stopSignIn",
            results = {
                    @Result(name = "json", type = "json", params = {"root", "map"})
            }
    )
    public String stopSignIn(){
        map=new HashMap<>();
        clazzService.stopSignIn(c.getCode());
        map.put("code",0);
        return "json";
    }

    //0成功 返回班级列表
    @Action(
            value = "getClazz",
            results = {
                    @Result(name = "json", type = "json", params = {"root", "map"})
            }
    )
    public String getClazz(){
        map=new HashMap<>();
        List l=clazzService.getClazz();
        map.put("code",0);
        map.put("count",l.size());
        map.put("data",l);
        return "json";
    }

    //0成功 -1未开始签到 -2mac地址不对 -3位置不对 -4没有此班级
    @Action(
            value = "signIn",
            results = {
                    @Result(name = "json", type = "json", params = {"root", "map"})
            }
    )
    public String signIn(){
        map=new HashMap<>();
        map.put("code",clazzService.signIn(c.getCode(),getNewMac(),getNewLongitude(),getNewLatitude()));
        return "json";
    }

    //0 成功 返回日志列表
    @Action(
            value = "getLog",
            results = {
                    @Result(name = "json", type = "json", params = {"root", "map"})
            }
    )
    public String getLog(){
        map=new HashMap<>();
        map.put("code",0);
        List l=logService.getLog(c.getCode());
        map.put("count",l.size());
        map.put("data",l);
        return "json";
    }

    public double getNewLongitude() {
        return newLongitude;
    }

    public void setNewLongitude(double newLongitude) {
        this.newLongitude = newLongitude;
    }

    public double getNewLatitude() {
        return newLatitude;
    }

    public void setNewLatitude(double newLatitude) {
        this.newLatitude = newLatitude;
    }

    public String getNewMac() {
        return newMac;
    }

    public void setNewMac(String newMac) {
        this.newMac = newMac;
    }
}

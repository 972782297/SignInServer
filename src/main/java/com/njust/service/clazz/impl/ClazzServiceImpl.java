package com.njust.service.clazz.impl;

import com.njust.dao.BaseDAO;
import com.njust.service.log.LogService;
import com.njust.entity.Classes;
import com.njust.entity.Logs;
import com.njust.entity.Users;
import com.njust.service.clazz.ClazzService;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Scope("prototype")
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private BaseDAO baseDAO;
    @Autowired
    private LogService logService;

    @Override
    public String createClazz(String name,double longitude,double latitude) {
        String code;
        List clazz;
        while (true)
        {
            code= UUID.randomUUID().toString().substring(0,4);
            clazz = baseDAO.find("from Classes clazz where clazz.code='" + code + "'");
            if (clazz.size() == 0)
                break;
        }
        Classes c=new Classes();
        c.setCode(code);
        c.setLatitude(latitude);
        c.setLongitude(longitude);
        c.setName(name);
        c.setStart(0);
        c.setStudent(" ");
        c.setNext(0);
        Users uu=(Users)ActionContext.getContext().getSession().get("user");
        c.setTeacher(uu.getName());
        baseDAO.add(c);
        return code;
    }
    //0成功 -1没有此班级
    @Override
    public int joinClazz(String code){
        Users uu=(Users)ActionContext.getContext().getSession().get("user");
        List clazz=baseDAO.find("from Classes clazz where clazz.code='" + code + "'");
        if (clazz.size()==0)
            return -1;
        else
        {
            Classes c=(Classes)clazz.get(0);
            String tem=c.getStudent();
            tem = tem+uu.getName()+" ";
            c.setStudent(tem);
            baseDAO.update(c);
            return 0;
        }
    }
    @Override
    public void deleteClazz(String code){
        List clazz=baseDAO.find("from Classes clazz where clazz.code='" + code + "'");
        List log=baseDAO.find("from Logs log where log.clazz='" + code + "'");
        Classes c=(Classes)clazz.get(0);
        baseDAO.delete(c);
        while(log.size()>0){
            baseDAO.delete((Logs)log.get(0));
            log.remove(0);
        }
    }
    @Override
    public void quitClazz(String code){
        Users uu=(Users)ActionContext.getContext().getSession().get("user");
        List clazz=baseDAO.find("from Classes clazz where clazz.code='" + code + "'");
        Classes c=(Classes)clazz.get(0);
        String tem=c.getStudent();
        tem=tem.replaceAll("\\s"+uu.getName()+"\\s"," ");
        c.setStudent(tem);
        baseDAO.update(c);
    }
    @Override
    public void startSignIn(String code){
        List clazz=baseDAO.find("from Classes clazz where clazz.code='" + code + "'");
        Classes c=(Classes)clazz.get(0);
        c.setStart(1);
        c.setNext(c.getNext()+1);
        logService.addLog(code,c.getNext());
        baseDAO.update(c);
    }
    @Override
    public void stopSignIn(String code){
        List clazz=baseDAO.find("from Classes clazz where clazz.code='" + code + "'");
        Classes c=(Classes)clazz.get(0);
        c.setStart(0);
        baseDAO.update(c);
    }
    @Override
    public List getClazz(){
        Users uu=(Users)ActionContext.getContext().getSession().get("user");
        List clazz=baseDAO.find("from Classes clazz where clazz.student like '%"+
                uu.getName()+"%' or clazz.teacher like '%"+uu.getName()+"%'");
        return clazz;
    }
    //0成功 -1未开始签到 -2mac地址不对 -3位置不对 -4没有此班级
    @Override
    public int signIn(String code,String mac,double newLongitude,double newLatitude){
        Users uu=(Users)ActionContext.getContext().getSession().get("user");
        double epsilon=0.0001;
        List clazz=baseDAO.find("from Classes clazz where clazz.code='" + code + "'");
        if (clazz.size()==0)
            return -4;
        Classes c=(Classes)clazz.get(0);
        if (c.getStart()==0)
            return -1;
        if (!uu.getMac().equals(mac))
            return -2;
        double oldLat,oldLon;
        oldLat=c.getLatitude();
        oldLon=c.getLongitude();
        if (oldLat+epsilon>newLatitude && oldLat-epsilon<newLatitude
                && oldLon+epsilon>newLongitude && oldLon-epsilon<newLongitude)
        {
            logService.updateLog(code,c.getNext(),uu.getName());
            return 0;
        }
        else
            return -3;
    }
}
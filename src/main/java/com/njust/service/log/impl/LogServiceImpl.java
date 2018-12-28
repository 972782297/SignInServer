package com.njust.service.log.impl;

import com.njust.dao.BaseDAO;
import com.njust.entity.Logs;
import com.njust.service.log.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope("prototype")
public class LogServiceImpl implements LogService {
    @Autowired
    private BaseDAO baseDAO;

    @Override
    public void addLog(String code,int time){
        Logs logs=new Logs();
        logs.setClazz(code);
        logs.setList(" ");
        logs.setTime(String.valueOf(time));
        baseDAO.add(logs);
    }
    @Override
    public void updateLog(String code,int time,String student){
        List log=baseDAO.find("from Logs log where log.clazz='" + code + "' and log.time='"+time+"'");
        Logs l=(Logs)log.get(0);
        String tem=l.getList();
        tem = tem+student+" ";
        l.setList(tem);
        baseDAO.update(l);
    }
    @Override
    public List getLog(String code){
        return baseDAO.find("from Logs log where log.clazz='"+code+"'");
    }
}

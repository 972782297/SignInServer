package com.njust.service.log;

import java.util.List;

public interface LogService {
    void addLog(String code,int time);
    void updateLog(String code,int time,String student);
    //开发时间有限，学生不能查看自己的签到情况
    List getLog(String code);
}

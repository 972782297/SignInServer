package com.njust.service.clazz;

import java.util.List;

public interface ClazzService {
    //返回code
    String createClazz(String name,double longitude,double latitude);
    //0成功 -1没有此班级
    int joinClazz(String code);
    void deleteClazz(String code);
    void quitClazz(String code);
    void startSignIn(String code);
    void stopSignIn(String code);
    List getClazz();
    //0成功 -1未开始签到 -2mac地址不对 -3位置不对 -4没有此班级
    int signIn(String code,String mac,double newLongitude,double newLatitude);
}

package com.njust.service.clazz;

public interface ClazzService {
    //成功返回code 失败返回-1 设置start为0 student为空
    String createClazz(String name,String teacher,double longitude,double latitude);

}

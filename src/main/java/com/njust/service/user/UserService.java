package com.njust.service.user;

public interface UserService {
    int login(String account, String password);

    boolean register(String account, String password,String role,String name,String phone,String mac);

    void changePassword(String account,String newPassword);

    void changePhone(String account,String newPhone);

    void logout();
}

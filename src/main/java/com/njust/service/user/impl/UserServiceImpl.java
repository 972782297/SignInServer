package com.njust.service.user.impl;

import com.njust.dao.BaseDAO;
import com.njust.entity.Users;
import com.njust.service.user.UserService;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Scope("prototype")
public class UserServiceImpl implements UserService {
    @Autowired
    private BaseDAO baseDAO;

    @Override
    public int login(String account, String password) {
        List users = baseDAO.find("from Users user where user.account='" + account + "' and user.password='" + password+"'");
        if (users.size() != 0) {
            Users tem = (Users) users.get(0);
            ActionContext.getContext().getSession().put("user", tem);
            if (tem.getRole().equals("teacher"))
                return 1;
            else
                return 0;
        }
        else
            return -1;
    }

    @Override
    public boolean register(String account, String password,String role,String name,String phone,String mac){
        List users = baseDAO.find("from Users user where user.account='" + account +"'");
        if (users.size() != 0) {
            return false;
        } else {
            Users u = new Users();
            u.setName(name);
            u.setPassword(password);
            u.setAccount(account);
            u.setRole(role);
            u.setPhone(phone);
            u.setMac(mac);
            baseDAO.add(u);
            return true;
        }
    }

    @Override
    public void changePassword(String account,String newPassword){
        Users u=new Users();
        u.setAccount(account);
        u.setPassword(newPassword);
        baseDAO.update(u);
    }

    @Override
    public void changePhone(String account,String newPhone){
        Users u=new Users();
        u.setAccount(account);
        u.setPhone(newPhone);
        baseDAO.update(u);
    }

    @Override
    public void logout(){
        ActionContext.getContext().getSession().clear();
    }
}

package com.spring.demo.service;

import com.spring.demo.entity.User;
import com.spring.demo.mapper.UserMapper;
import com.spring.demo.utils.BasePage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public User getUser(Integer id){
        return userMapper.getUser(id);
    }

    public BasePage<User> getUserBageList(BasePage page){
        //开始分页
        BasePage<User> user=userMapper.getUserBageList(page);
        return user;
    }

    @Transactional
    public void addUser(User user){
        int size = userMapper.addUser(user);
        if(size != 1 ){
            System.out.println("插入失败");
        }
    }

    @Transactional
    public void updateUser(User user){
        int size = userMapper.updateUser(user);
        if(size != 1 ){
            System.out.println("更新失败");
        }
    }

    @Transactional
    public void deleteUser(User user){
        int size = userMapper.deleteUser(user);
        if(size != 1 ){
            System.out.println("删除失败");
        }
    }
}

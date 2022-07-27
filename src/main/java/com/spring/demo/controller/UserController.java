package com.spring.demo.controller;

import com.spring.demo.entity.User;
import com.spring.demo.service.RedisService;
import com.spring.demo.service.UserService;
import com.spring.demo.utils.BasePage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userController")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;

    /**
     * 查询 放入redis中
     */
    @GetMapping("/getUserBageList")
    public void getUserBageList() {
        //分页查询--将结果缓存到 redis
        int pageNum = 1;   //页数
        int pageSize = 2;  //每页大小
        BasePage page = new BasePage(pageNum, pageSize);
        BasePage<User> userPageInfo = userService.getUserBageList(page);
        List<User> userPageList = userPageInfo.getRecords();
        for (User user : userPageList) {
            //查询时增加将结果缓存到 redis
            redisService.set(user.getName(), user);
            //输出缓存结果
            System.out.println(redisService.get(user.getName()));
        }
    }

    /**
     * 根据 主键id查询并放入放入redis中
     */
    @GetMapping("/getUserById")
    public void getUserById() {
        int id = 1;
        User user = userService.getUser(id);
        //查询时增加将结果缓存到 redis
        redisService.set(user.getName(), user);
        //输出缓存结果
        System.out.println(redisService.get(user.getName()));

    }

    /**
     * 新增添加事物方式
     */
    @PostMapping("/add")
    public void add() {
        User user = new User();
        user.setAge(12);
        user.setName("阿拉蕾");
        user.setSex("男");
        userService.addUser(user);
    }

    /**
     * 通过主注解事物修改
     */
    @PostMapping("/update")
    public void update() {
        int id = 3;
        User user = userService.getUser(id);
        user.setAge(33);
        userService.updateUser(user);
    }

    /**
     * 通过注解事物删除
     */
    @DeleteMapping("/deleteUserInfo")
    public void deleteUserInfo() throws Exception {
        int id = 4;
        User user = userService.getUser(id);
        if (null == user) {
            throw new Exception("当前用户不存在");
        } else {
            userService.deleteUser(user);
        }

    }
}


package com.spring.demo.mapper;


import com.spring.demo.entity.User;
import com.spring.demo.utils.BasePage;
import com.spring.demo.utils.IBasePage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface UserMapper {

    User  getUser(@Param("id") Integer id);

    BasePage<User> getUserBageList(IBasePage page);

    int addUser(@Param("user") User user);

    int updateUser(@Param("user") User user);

    int deleteUser(@Param("user") User user);
}

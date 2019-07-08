package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;

import java.util.List;

public interface IUserDao {

    /**
     * 用户登录相关
     * @param username
     * @return
     */
    UserInfo findByUsername(String username);

    /**
     * 查询所有 user
     * @return
     */
    List<UserInfo> findAll();

    /**
     * 添加用户
     * @param user
     */
    void saveUser(UserInfo user);

    /**
     * 根据 id 查询 user
     * @param id
     * @return
     */
    UserInfo findById(String id);

}

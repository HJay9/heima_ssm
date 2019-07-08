package com.itheima.ssm.service;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @author Jay
 */
public interface IUserService extends UserDetailsService{

    /**
     * 查询所有
     * @return
     */
    List<UserInfo> findAll();

    /**
     * 新增一条记录
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

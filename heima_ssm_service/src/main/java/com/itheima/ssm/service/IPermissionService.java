package com.itheima.ssm.service;

import com.itheima.ssm.dao.IPermissionDao;
import com.itheima.ssm.domain.Permission;

import java.util.List;

/**
 * @author Jay
 * @date 2019/7/7
 */
public interface IPermissionService {


    /**
     * 根据 id 查询 permission
     * @param id
     * @return
     */
    Permission findById(String id);

    /**
     * 查询所有
     * @return
     */
    List<Permission> findAll();

    /**
     * 新增一条记录
     * @param permission
     */
    void savePermission(Permission permission);

    /**
     * 删除一条记录
     * @param id
     */
    void deletePermission(String id);

}

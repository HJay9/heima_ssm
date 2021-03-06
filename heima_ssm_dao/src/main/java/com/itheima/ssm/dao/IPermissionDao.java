package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Permission;

import java.util.List;

public interface IPermissionDao {

    /**
     * 根据 roleId 关联查询 Permission
     * @param roleId
     * @return
     */
    Permission findByRoleId(String roleId);

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

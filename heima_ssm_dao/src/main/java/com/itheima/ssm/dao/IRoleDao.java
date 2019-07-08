package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IRoleDao {

    /**
     * 根据 userId 关联查询 role
     * @param userId
     * @return
     */
    Role findByUserId(String userId);

    /**
     * 根据 id 查询 Role
     * @param id
     * @return
     */
    Role findById(String id);

    /**
     * 查询所有
     * @return
     */
    List<Role> findAll();

    /**
     * 新增一条记录
     * @param role
     */
    void saveRole(Role role);

    /**
     * 删除角色
     * @param id
     */
    void deleteRole(String id);

    /**
     * 查询可选角色
     * @param userId
     * @return
     */
    List<Role> findOptionalRole(String userId);

    /**
     * 更新对应用户的角色
     * @param userId
     * @param roleId
     */
    void updateRoleByUser(@Param("userId") String userId, @Param("roleId") String roleId);

    /**
     * 查询可选权限
     * @param roleId
     * @return
     */
    List<Permission> findOptionalPermission(String roleId);

    /**
     * 更新对应角色的权限
     * @param roleId
     * @param permissionId
     */
    void updatePermissionByRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId);

}

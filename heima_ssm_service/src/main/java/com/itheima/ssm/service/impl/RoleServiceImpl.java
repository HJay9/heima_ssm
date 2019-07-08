package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.IRoleDao;
import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;


    /**
     * 根据 id 查询 Role
     *
     * @param id
     * @return
     */
    @Override
    public Role findById(String id) {
        return roleDao.findById(id);
    }

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    /**
     * 新增一条记录
     *
     * @param role
     */
    @Override
    public void saveRole(Role role) {
        roleDao.saveRole(role);
    }

    /**
     * 删除角色
     *
     * @param id
     */
    @Override
    public void deleteRole(String id) {
        roleDao.deleteRole(id);
    }

    /**
     * 查询可选角色
     *
     * @param userId
     * @return
     */
    @Override
    public List<Role> findOptionalRole(String userId) {
        return roleDao.findOptionalRole(userId);
    }

    /**
     * 更新对应用户的角色
     *
     * @param userId
     * @param roleIds
     */
    @Override
    public void updateRoleByUser(String userId, String[] roleIds) {
        for (String roleId : roleIds) {
            roleDao.updateRoleByUser(userId, roleId);
        }
    }

    /**
     * 查询可选角色
     *
     * @param roleId
     * @return
     */
    @Override
    public List<Permission> findOptionalPermission(String roleId) {
        return roleDao.findOptionalPermission(roleId);
    }

    /**
     * 更新对应用户的角色
     *
     * @param roleId
     * @param permissionIds
     */
    @Override
    public void updatePermissionByRole(String roleId, String[] permissionIds) {
        for (String permissionId : permissionIds) {
            roleDao.updatePermissionByRole(roleId, permissionId);
        }
    }
}

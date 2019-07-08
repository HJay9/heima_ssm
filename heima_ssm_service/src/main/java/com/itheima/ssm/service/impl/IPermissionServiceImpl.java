package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.IPermissionDao;
import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Jay
 * @date 2019/7/7
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class IPermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionDao permissionDao;

    /**
     * 根据 id 查询 permission
     *
     * @param id
     * @return
     */
    @Override
    public Permission findById(String id) {
        return permissionDao.findById(id);
    }

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    /**
     * 新增一条记录
     *
     * @param permission
     */
    @Override
    public void savePermission(Permission permission) {
        permissionDao.savePermission(permission);
    }

    /**
     * 删除一条记录
     *
     * @param id
     */
    @Override
    public void deletePermission(String id) {
        permissionDao.deletePermission(id);
    }
}

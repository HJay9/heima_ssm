package com.itheima.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.ssm.dao.ISysLogDao;
import com.itheima.ssm.domain.SysLog;
import com.itheima.ssm.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Jay
 * @date 2019/7/8
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ISysLogServiceImpl implements ISysLogService {

    @Autowired
    private ISysLogDao sysLogDao;

    /**
     * 查询所有日志
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public List<SysLog> findAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return sysLogDao.findAll();
    }

    /**
     * 保存日志
     *
     * @param sysLog
     */
    @Override
    public void saveLog(SysLog sysLog) {
        sysLogDao.save(sysLog);
    }
}

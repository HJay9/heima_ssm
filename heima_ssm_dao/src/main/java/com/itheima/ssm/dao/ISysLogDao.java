package com.itheima.ssm.dao;

import com.itheima.ssm.domain.SysLog;

import java.util.List;

public interface ISysLogDao {

    /**
     * 查询所有日志
     * @return
     */
    List<SysLog> findAll();

    /**
     * 保存日志记录
     * @param sysLog
     */
    void save(SysLog sysLog);
}

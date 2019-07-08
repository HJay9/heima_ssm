package com.itheima.ssm.service;

import com.itheima.ssm.domain.SysLog;

import java.util.List;

/**
 * @author Jay
 * @date 2019/7/8
 */
public interface ISysLogService {

    /**
     * 查询所有日志
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<SysLog> findAll(Integer pageNum, Integer pageSize);

    /**
     * 保存日志
     * @param sysLog
     */
    void saveLog(SysLog sysLog);
}

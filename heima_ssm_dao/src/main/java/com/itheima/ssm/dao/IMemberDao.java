package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Member;

public interface IMemberDao {

    /**
     * 根据 id 查询会员
     * @param id
     * @return
     */
    Member findById(String id);

}

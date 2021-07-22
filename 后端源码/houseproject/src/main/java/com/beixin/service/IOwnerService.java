package com.beixin.service;

import com.beixin.model.Owner;
import com.github.pagehelper.PageInfo;

public interface IOwnerService {
    /**
     * 分页查询户主信息
     */
    PageInfo<Owner> findOwnerAll(int page,int limit,Owner owner);


    /**
     * 添加功能
     */
    int add(Owner owner);


    /**
     * 删除
     */

    int delete(Long id);

    /**
     * 修改
     */
    int updateDate(Owner owner);
}

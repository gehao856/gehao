package com.beixin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.beixin.model.UserRole;

/**
 * <p>
 * 用户角色关联表 服务类
 * </p>
 *
 * @author gehao
 * @since 2021-04-01
 */
public interface IUserRoleService extends IService<UserRole> {

    /**
     * 查询用户角色关联表分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<UserRole>
     */
    IPage<UserRole> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加用户角色关联表
     *
     * @param userRole 用户角色关联表
     * @return int
     */
    int add(UserRole userRole);

    /**
     * 删除用户角色关联表
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改用户角色关联表
     *
     * @param userRole 用户角色关联表
     * @return int
     */
    int updateData(UserRole userRole);

    /**
     * id查询数据
     *
     * @param id id
     * @return UserRole
     */
    UserRole findById(Long id);
}

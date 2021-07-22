package com.beixin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beixin.dao.UserRoleMapper;
import com.beixin.model.UserRole;
import com.beixin.service.IUserRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author gehao
 * @since 2021-04-01
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Override
    public IPage<UserRole> findListByPage(Integer page, Integer pageCount){
        IPage<UserRole> wherePage = new Page<>(page, pageCount);
        UserRole where = new UserRole();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(UserRole userRole){
        return baseMapper.insert(userRole);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(UserRole userRole){
        return baseMapper.updateById(userRole);
    }

    @Override
    public UserRole findById(Long id){
        return  baseMapper.selectById(id);
    }
}

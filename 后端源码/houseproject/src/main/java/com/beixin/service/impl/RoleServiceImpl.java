package com.beixin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beixin.dao.RoleMapper;
import com.beixin.model.Role;
import com.beixin.service.IRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author gehao
 * @since 2021-04-01
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Override
    public IPage<Role> findListByPage(Integer page, Integer pageCount){
        IPage<Role> wherePage = new Page<>(page, pageCount);
        Role where = new Role();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Role role){
        return baseMapper.insert(role);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Role role){
        return baseMapper.updateById(role);
    }

    @Override
    public Role findById(Long id){
        return  baseMapper.selectById(id);
    }
}

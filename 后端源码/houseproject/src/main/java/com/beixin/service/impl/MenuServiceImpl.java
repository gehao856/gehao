package com.beixin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beixin.dao.MenuMapper;
import com.beixin.model.Menu;
import com.beixin.service.IMenuService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单信息表 服务实现类
 * </p>
 *
 * @author gehao
 * @since 2021-04-01
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Override
    public IPage<Menu> findListByPage(Integer page, Integer pageCount){
        IPage<Menu> wherePage = new Page<>(page, pageCount);
        Menu where = new Menu();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Menu menu){
        return baseMapper.insert(menu);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Menu menu){
        return baseMapper.updateById(menu);
    }

    @Override
    public Menu findById(Long id){
        return  baseMapper.selectById(id);
    }
}

package com.beixin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beixin.dao.OwnerMapper;
import com.beixin.model.Owner;
import com.beixin.service.IOwnerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ownerService")
public class OwnerServiceImpl extends ServiceImpl<OwnerMapper,Owner> implements IOwnerService {

    @Autowired
    private OwnerMapper ownerDao;
    @Override
    public PageInfo<Owner> findOwnerAll(int page, int limit, Owner owner) {
        //分页
        PageHelper.startPage(page,limit);
        //查询所有的户主信息
        List<Owner> list = ownerDao.queryOwnerAll(owner);
        PageInfo<Owner> pageinfo = new PageInfo<>(list);

        return pageinfo;
    }

    @Override
    public int add(Owner owner) {
        return baseMapper.insert(owner);
    }

    @Override
    public int delete(Long id) {
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateDate(Owner owner) {
        return baseMapper.updateById(owner);
    }
}

package com.beixin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beixin.dao.LookhouseMapper;
import com.beixin.model.Lookhouse;
import com.beixin.service.ILookhouseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 看房信息表 服务实现类
 * </p>
 *
 * @author gehao
 * @since 2021-04-01
 */
@Service
public class LookhouseServiceImpl extends ServiceImpl<LookhouseMapper, Lookhouse> implements ILookhouseService {

    @Autowired
    private LookhouseMapper lookhouseDao;
    @Override
    public PageInfo<Lookhouse> findLookHouseAll(int page, int limit, Lookhouse lookhouse) {
        PageHelper.startPage(page,limit);
        List<Lookhouse> list=lookhouseDao.queryLookHouseAll(lookhouse);
        PageInfo<Lookhouse> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }


    @Override
    public IPage<Lookhouse> findListByPage(Integer page, Integer pageCount){
        IPage<Lookhouse> wherePage = new Page<>(page, pageCount);
        Lookhouse where = new Lookhouse();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Lookhouse lookhouse){
        return baseMapper.insert(lookhouse);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Lookhouse lookhouse){
        return baseMapper.updateById(lookhouse);
    }

    @Override
    public Lookhouse queryById(Integer id) {
        return lookhouseDao.queryById(id);
    }
}

package com.beixin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beixin.dao.RepairMapper;
import com.beixin.model.Lookhouse;
import com.beixin.model.Repair;
import com.beixin.model.TongJi;
import com.beixin.service.IRepairService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 报修信息表 服务实现类
 * </p>
 *
 * @author gehao
 * @since 2021-04-01
 */
@Service
public class RepairServiceImpl extends ServiceImpl<RepairMapper, Repair> implements IRepairService {

    @Autowired
    private RepairMapper repairDao;


    @Override
    public PageInfo<Repair> findRepairAll(int page, int limit, Repair repair) {
        PageHelper.startPage(page,limit);
        List<Repair> list=repairDao.queryList(repair);
        PageInfo<Repair> pageInfo=new PageInfo<>(list);
        return pageInfo;

}

    @Override
    public IPage<Repair> findListByPage(Integer page, Integer pageCount){
        IPage<Repair> wherePage = new Page<>(page, pageCount);
        Repair where = new Repair();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Repair repair){
        return baseMapper.insert(repair);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Repair repair){
        return baseMapper.updateById(repair);
    }

    @Override
    public Repair findById(Long id){
        return  baseMapper.selectById(id);
    }

    @Override
    public List<TongJi> queryCounts() {
        return repairDao.queryCount();
    }
}

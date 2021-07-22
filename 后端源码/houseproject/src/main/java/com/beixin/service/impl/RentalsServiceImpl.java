package com.beixin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beixin.dao.RentalsMapper;
import com.beixin.model.Rentals;
import com.beixin.model.RentalsVo;
import com.beixin.model.TongJi;
import com.beixin.service.IRentalsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 收租信息表 服务实现类
 * </p>
 *
 * @author gehao
 * @since 2021-04-01
 */
@Service
public class RentalsServiceImpl extends ServiceImpl<RentalsMapper, Rentals> implements IRentalsService {

    @Autowired
    private RentalsMapper rentalsDao;

    @Override
    public PageInfo<RentalsVo> findRentalsAll(int page, int limit, RentalsVo rentalsVo) {
        PageHelper.startPage(page,limit);
        List<RentalsVo> list= rentalsDao.queryList(rentalsVo);
        PageInfo<RentalsVo> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public IPage<Rentals> findListByPage(Integer page, Integer pageCount){
        IPage<Rentals> wherePage = new Page<>(page, pageCount);
        Rentals where = new Rentals();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Rentals rentals){
        return baseMapper.insert(rentals);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Rentals rentals){
        return baseMapper.updateById(rentals);
    }

    @Override
    public Rentals findById(Long id){
        return  baseMapper.selectById(id);
    }

    @Override
    public List<TongJi> queryCount() {
        return rentalsDao.queryCount();
    }
}

package com.beixin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beixin.dao.ContractMapper;
import com.beixin.model.Contract;
import com.beixin.model.HeTong;
import com.beixin.service.IContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 合同信息表 服务实现类
 * </p>
 *
 * @author gehao
 * @since 2021-04-01
 */
@Service
public class ContractServiceImpl extends ServiceImpl<ContractMapper, Contract> implements IContractService {


    @Autowired
    private ContractMapper contractDao;

    @Override
    public IPage<Contract> findListByPage(Integer page, Integer pageCount){
        IPage<Contract> wherePage = new Page<>(page, pageCount);
        Contract where = new Contract();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Contract contract){
        return baseMapper.insert(contract);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Contract contract){
        return baseMapper.updateById(contract);
    }

    @Override
    public Contract findById(Long id){

        return  baseMapper.selectById(id);
    }

    @Override
    public HeTong findById(Integer id) {
        return contractDao.queryByHeTongId(id);
    }
}

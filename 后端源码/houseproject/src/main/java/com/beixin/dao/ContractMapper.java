package com.beixin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.beixin.model.Contract;
import com.beixin.model.HeTong;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 合同信息表 Mapper 接口
 * </p>
 *
 * @author gehao
 * @since 2021-04-01
 */
@Component("contractDao")
public interface ContractMapper extends BaseMapper<Contract> {
    /**
     * 查询合同相关详细信息
     */
     HeTong queryByHeTongId(Integer id);
}

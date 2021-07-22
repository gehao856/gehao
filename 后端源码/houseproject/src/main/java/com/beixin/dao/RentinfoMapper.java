package com.beixin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.beixin.model.Rentinfo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 租赁信息表 Mapper 接口
 * </p>
 *
 * @author gehao
 * @since 2021-04-01
 */
@Component("rentinfoDao")
public interface RentinfoMapper extends BaseMapper<Rentinfo> {
    /**
     * 查询所有的租赁信息 支持高级查询
     */
    List<Rentinfo> queryRentinfoAll(Rentinfo rentinfo);

    //根据房子id查询客户信息
    Rentinfo queryRentInfoByHouId(Integer houId);

}

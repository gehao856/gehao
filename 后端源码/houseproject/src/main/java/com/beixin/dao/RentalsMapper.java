package com.beixin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.beixin.model.Rentals;
import com.beixin.model.RentalsVo;
import com.beixin.model.TongJi;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * <p>
 * 收租信息表 Mapper 接口
 * </p>
 *
 * @author gehao
 * @since 2021-04-01
 */
@Component("rentalsDao")
public interface RentalsMapper extends BaseMapper<Rentals> {
    /**
     * 查询缴费信息列表
     */
   List<RentalsVo> queryList(RentalsVo rentalsVo);

    /**
     * 按月统计收租的金额
     */
    List<TongJi> queryCount();
}

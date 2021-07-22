package com.beixin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.beixin.model.Rentinfo;
import com.beixin.model.Repair;
import com.beixin.model.TongJi;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 报修信息表 Mapper 接口
 * </p>
 *
 * @author gehao
 * @since 2021-04-01
 */
@Component("repairDao")
public interface RepairMapper extends BaseMapper<Repair> {

    List<Repair> queryList(Repair repair);

    //按月份维修数据的统计
    List<TongJi> queryCount();
}

package com.beixin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.beixin.model.Lookhouse;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 看房信息表 Mapper 接口
 * </p>
 *
 * @author gehao
 * @since 2021-04-01
 */
@Component("LookhouseDao")
public interface LookhouseMapper extends BaseMapper<Lookhouse> {
    /**
     * 查询所有的看房申请列表支持高级查询信息
     */
    List<Lookhouse> queryLookHouseAll(Lookhouse lookhouse);

    /**
     * 根据看房申请id获取相关记录信息
     * @param id
     * @return
     */
    Lookhouse queryById(Integer id);
}

package com.beixin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.beixin.model.Houses;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 房屋信息管理 Mapper 接口
 * </p>
 *
 * @author gehao
 * @since 2021-04-01
 */
@Component("housesMapper")
public interface HousesMapper extends BaseMapper<Houses> {
    /**
     * 根据条件查询房子信息
     * @param houses
     * @return
     */
    List<Houses> queryHouseAll(Houses houses);

    @Select("SELECT id,numbers,owner_id,address,TYPE,imgs,province,city,county,sort,STATUS,remarks,create_time,create_by,update_time,AREA,price,owner_id FROM houses WHERE id=#{id}")
    Houses findById(Long id);

    List<Houses> queryHouseAll2(Integer id);



}

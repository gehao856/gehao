package com.beixin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.beixin.model.Owner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("ownerDao")
public interface OwnerMapper extends BaseMapper<Owner> {
    /*
    * 查询所有的户主信息
     */
    List<Owner> queryOwnerAll(Owner owner);

}

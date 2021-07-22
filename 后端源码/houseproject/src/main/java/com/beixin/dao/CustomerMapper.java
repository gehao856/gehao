package com.beixin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.beixin.model.Customer;
import com.beixin.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 租户信息表 Mapper 接口
 * </p>
 *
 * @author gehao
 * @since 2021-04-01
 */
@Component("customerMapper")
public interface CustomerMapper extends BaseMapper<Customer> {

    List<Customer> queryAll(Customer customer);

    /**
     * 根据用户名密码判断获取用户信息
     */
    Customer queryUserByNameAndPwd(@Param("username") String username, @Param("password")String password);


}

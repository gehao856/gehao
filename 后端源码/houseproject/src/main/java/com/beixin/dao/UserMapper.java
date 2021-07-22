package com.beixin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.beixin.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author gehao
 * @since 2021-04-01
 */
@Component("UserDao")
public interface UserMapper extends BaseMapper<User> {
    /*
          查询所有的看房申请列表  支持高级查询
        */
    List<User> queryUserAll(User user);

    /**
     * 根据用户名密码判断获取用户信息
     */
    User queryUserByNameAndPwd(@Param("username") String username, @Param("password")String password);
}

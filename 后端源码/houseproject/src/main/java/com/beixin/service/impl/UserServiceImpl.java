package com.beixin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beixin.dao.UserMapper;
import com.beixin.model.User;
import com.beixin.service.IUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author gehao
 * @since 2021-04-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {



    @Autowired
    private UserMapper userDao;

    @Override
    public PageInfo<User> findUserAll(int page, int limit, User user) {
        PageHelper.startPage(page,limit);
        List<User> list=userDao.queryUserAll(user);
        PageInfo<User> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }
    @Override
    public IPage<User> findListByPage(Integer page, Integer pageCount){
        IPage<User> wherePage = new Page<>(page, pageCount);
        User where = new User();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(User user){
        return baseMapper.insert(user);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(User user){
        return baseMapper.updateById(user);
    }

    @Override
    public User findById(Long id){
        return  baseMapper.selectById(id);
    }

    @Override
    public User queryUserByNameAndPwd(String username, String pwd) {
        return userDao.queryUserByNameAndPwd(username,pwd);
    }
}

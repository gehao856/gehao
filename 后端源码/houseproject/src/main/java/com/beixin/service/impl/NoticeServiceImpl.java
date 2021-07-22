package com.beixin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beixin.dao.NoticeMapper;
import com.beixin.model.Notice;
import com.beixin.service.INoticeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 公告信息表 公布最新优质房源 服务实现类
 * </p>
 *
 * @author gehao
 * @since 2021-04-01
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {

    @Autowired
    private NoticeMapper noticeDao;
    @Override
    public IPage<Notice> findListByPage(Integer page, Integer pageCount){
        IPage<Notice> wherePage = new Page<>(page, pageCount);
        Notice where = new Notice();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Notice notice){
        return baseMapper.insert(notice);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Notice notice){
        return baseMapper.updateById(notice);
    }

    @Override
    public Notice findById(Long id){
        return  baseMapper.selectById(id);
    }

    @Override
    public PageInfo<Notice> queryNoticeAll(int page, int limit, Notice notice) {
        PageHelper.startPage(page,limit);
        List<Notice> list=noticeDao.queryNoticeAll(notice);
        PageInfo<Notice> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }
}

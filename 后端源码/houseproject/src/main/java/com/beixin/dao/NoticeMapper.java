package com.beixin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.beixin.model.Notice;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 公告信息表 公布最新优质房源 Mapper 接口
 * </p>
 *
 * @author gehao
 * @since 2021-04-01
 */
@Component("noticeDao")
public interface NoticeMapper extends BaseMapper<Notice> {

    //List<Notice> queryNoticeAll(@Param("Content") String Content);
    List<Notice> queryNoticeAll(Notice notice);

}

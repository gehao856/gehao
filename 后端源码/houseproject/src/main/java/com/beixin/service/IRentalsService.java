package com.beixin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import com.beixin.model.Rentals;
import com.beixin.model.RentalsVo;
import com.beixin.model.TongJi;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <p>
 * 收租信息表 服务类
 * </p>
 *
 * @author gehao
 * @since 2021-04-01
 */
public interface IRentalsService extends IService<Rentals> {

    /**
     * 查询所有的列表信息
     */
    PageInfo<RentalsVo> findRentalsAll(int page, int limit, RentalsVo rentalsVo);


    /**
     * 查询收租信息表分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Rentals>
     */
    IPage<Rentals> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加收租信息表
     *
     * @param rentals 收租信息表
     * @return int
     */
    int add(Rentals rentals);

    /**
     * 删除收租信息表
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改收租信息表
     *
     * @param rentals 收租信息表
     * @return int
     */
    int updateData(Rentals rentals);

    /**
     * id查询数据
     *
     * @param id id
     * @return Rentals
     */
    Rentals findById(Long id);

    /**
     * 按月统计收租数据
     */
    List<TongJi> queryCount();
}

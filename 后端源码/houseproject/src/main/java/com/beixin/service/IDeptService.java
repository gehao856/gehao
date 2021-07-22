package com.beixin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.beixin.model.Dept;

/**
 * <p>
 * 部门信息表 服务类
 * </p>
 *
 * @author gehao
 * @since 2021-04-01
 */
public interface IDeptService extends IService<Dept> {

    /**
     * 查询部门信息表分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Dept>
     */
    IPage<Dept> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加部门信息表
     *
     * @param dept 部门信息表
     * @return int
     */
    int add(Dept dept);

    /**
     * 删除部门信息表
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改部门信息表
     *
     * @param dept 部门信息表
     * @return int
     */
    int updateData(Dept dept);

    /**
     * id查询数据
     *
     * @param id id
     * @return Dept
     */
    Dept findById(Long id);
}

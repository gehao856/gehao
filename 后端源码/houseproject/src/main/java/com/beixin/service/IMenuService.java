package com.beixin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.beixin.model.Menu;

/**
 * <p>
 * 菜单信息表 服务类
 * </p>
 *
 * @author gehao
 * @since 2021-04-01
 */
public interface IMenuService extends IService<Menu> {

    /**
     * 查询菜单信息表分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Menu>
     */
    IPage<Menu> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加菜单信息表
     *
     * @param menu 菜单信息表
     * @return int
     */
    int add(Menu menu);

    /**
     * 删除菜单信息表
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改菜单信息表
     *
     * @param menu 菜单信息表
     * @return int
     */
    int updateData(Menu menu);

    /**
     * id查询数据
     *
     * @param id id
     * @return Menu
     */
    Menu findById(Long id);
}

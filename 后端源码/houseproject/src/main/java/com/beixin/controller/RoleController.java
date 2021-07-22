package com.beixin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.beixin.model.Role;
import com.beixin.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 角色信息表 前端控制器
 * </p>
 *
 * @author gehao
 * @since 2021-04-01
 */
@Api(tags = {"角色信息表"})
@RestController
@RequestMapping("/role")
public class RoleController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IRoleService roleService;


    @ApiOperation(value = "新增角色信息表")
    @PostMapping()
    public int add(@RequestBody Role role){
        return roleService.add(role);
    }

    @ApiOperation(value = "删除角色信息表")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id){
        return roleService.delete(id);
    }

    @ApiOperation(value = "更新角色信息表")
    @PutMapping()
    public int update(@RequestBody Role role){
        return roleService.updateData(role);
    }

    @ApiOperation(value = "查询角色信息表分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<Role> findListByPage(@RequestParam Integer page,
                                      @RequestParam Integer pageCount){
        return roleService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询角色信息表")
    @GetMapping("{id}")
    public Role findById(@PathVariable Long id){
        return roleService.findById(id);
    }

}

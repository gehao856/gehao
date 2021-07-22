package com.beixin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.beixin.model.*;
import com.beixin.service.IContractService;
import com.beixin.service.IHousesService;
import com.beixin.service.ILookhouseService;
import com.beixin.service.IRentinfoService;
import com.beixin.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;


/**
 * <p>
 * 合同信息表 前端控制器
 * </p>
 *
 * @author gehao
 * @since 2021-04-01
 */
@Api(tags = {"合同信息表"})
@RestController
@RequestMapping("/contract")
public class ContractController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IContractService contractService;

    @Resource
    private IHousesService housesService;

    @Resource
    private IRentinfoService rentinfoService;

    @Resource
    private ILookhouseService lookhouseService;

    @ApiOperation(value = "新增合同信息表")
    @RequestMapping("/add")
    public R add(@RequestBody Contract contract){
        //合同信息
        contractService.add(contract);

        //添加新增记录租赁
        Rentinfo rentinfo=new Rentinfo();
        rentinfo.setCreateTime(new Date());
        rentinfo.setCustomerId(contract.getCustomerId());
        rentinfo.setContractId(contract.getId());
        rentinfo.setHousesId(contract.getHousesId());
        rentinfoService.add(rentinfo);

        //房子状态改成已经租赁状态
        Houses houses=new Houses();
        //根据房子id查询房子记录
        Houses hou=housesService.findById(new Long(contract.getHousesId()));
        houses.setStatus(2);
        houses.setId(contract.getHousesId());
        houses.setPrice(hou.getPrice());
        houses.setArea(hou.getArea());
        housesService.updateData(houses);

        //更改看房状态
        Lookhouse lookhouse=new Lookhouse();
        lookhouse.setId(contract.getRentId());
        lookhouse.setStutas(1);//g更改为已看房但不租赁
        lookhouseService.updateData(lookhouse);

        return R.ok();
    }


    @ApiOperation(value = "删除合同信息表")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id){
        return contractService.delete(id);
    }

    @ApiOperation(value = "更新合同信息表")
    @PutMapping()
    public int update(@RequestBody Contract contract){
        return contractService.updateData(contract);
    }

    @ApiOperation(value = "查询合同信息表分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<Contract> findListByPage(@RequestParam Integer page,
                                          @RequestParam Integer pageCount){
        return contractService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询合同信息表")
    @GetMapping("{id}")
    public Contract findById(@PathVariable Long id){
        return contractService.findById(id);
    }

    /**
     * 查询记录信息
     */
    @RequestMapping("/queryById")
    public HeTong queryById(Integer id){
        return contractService.findById(id);
    }
}

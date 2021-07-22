package com.beixin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.beixin.model.Customer;
import com.beixin.model.Houses;
import com.beixin.model.Rentinfo;
import com.beixin.service.IHousesService;
import com.beixin.service.IRentinfoService;
import com.beixin.util.JsonObject;
import com.beixin.util.JwtUtils2;
import com.beixin.util.R;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 租赁信息表 前端控制器
 * </p>
 *
 * @author gehao
 * @since 2021-04-01
 */
@Api(tags = {"租赁信息表"})
@RestController
@RequestMapping("/rentinfo")
public class RentinfoController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IRentinfoService rentinfoService;

    @Resource
    private IHousesService housesService;

    @RequestMapping("/queryRentinfoeAll")
    public Object queryRentinfoeAll(String numbers, String identity, Rentinfo rentinfo, @RequestParam(defaultValue = "1") Integer page ,
                                    @RequestParam(defaultValue = "10") Integer limit){
        Houses houses=new Houses();
        houses.setNumbers(numbers);
        rentinfo.setHouses(houses);
        //组装
        Customer customer=new Customer();
        customer.setIdentity(identity);
        rentinfo.setCustomer(customer);
        System.out.println(rentinfo);
        JsonObject object=new JsonObject();
        PageInfo<Rentinfo> pageInfo=rentinfoService.findRentinfoAll(page,limit,rentinfo);
        object.setMsg("ok");
        object.setData(pageInfo.getList());
        object.setCode(0);
        object.setCount(pageInfo.getTotal());
        return object;
    }

    @RequestMapping("/queryRentinfoeAll2")
    public Object queryRentinfoeAll2(HttpServletRequest request , Rentinfo rentinfo, @RequestParam(defaultValue = "1") Integer page ,
                                     @RequestParam(defaultValue = "10") Integer limit){
        //登录账号
        String token=request.getHeader("token");
        Integer userId= JwtUtils2.getUserId(token);
        rentinfo.setCustomerId(userId);
        JsonObject object=new JsonObject();
        PageInfo<Rentinfo> pageInfo=rentinfoService.findRentinfoAll(page,limit,rentinfo);
        object.setMsg("ok");
        object.setData(pageInfo.getList());
        object.setCode(0);
        object.setCount(pageInfo.getTotal());
        return object;
    }
    @ApiOperation(value = "新增租赁信息表")
    @PostMapping()
    public int add(@RequestBody Rentinfo rentinfo){
        return rentinfoService.add(rentinfo);
    }

    @ApiOperation(value = "删除租赁信息表")
    @RequestMapping("/deleteByIds")
    public R delete(String ids){
        List<String> list = Arrays.asList(ids.split(","));
        for (String id:list){
            rentinfoService.delete(Long.parseLong(id));
        }
        return R.ok();
    }

    @ApiOperation(value = "终止合同处理")
    @RequestMapping("/update")
    public R update(Integer id ){
        //更改租赁状态为退租
        Rentinfo infos=new Rentinfo();
        infos.setId(id);
        infos.setStatus(1);//退租
        rentinfoService.updateData(infos);
        // 更改房屋状态为未出租
        //Rentinfo rentinfo = rentinfoService.findById(new Long(id));
        QueryWrapper<Rentinfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.select(Rentinfo.class,
                info->!info.getColumn().equals("customer")
                        &&!info.getColumn().equals("houses")&&!info.getColumn().equals("contract"));
        queryWrapper.eq("id",id);
        Rentinfo rentinfo= rentinfoService.getOne(queryWrapper);
        Integer houId= rentinfo.getHousesId();
        System.out.println(houId);
        Houses houses = new Houses();
        houses.setId(houId);
        houses.setStatus(3);
        housesService.updateData(houses);
        return R.ok();
    }

    @ApiOperation(value = "查询租赁信息表分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<Rentinfo> findListByPage(@RequestParam Integer page,
                                          @RequestParam Integer pageCount){
        return rentinfoService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询租赁信息表")
    @GetMapping("{id}")
    public Rentinfo findById(@PathVariable Long id){
        return rentinfoService.findById(id);
    }

}

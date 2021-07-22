package com.beixin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.beixin.model.Customer;
import com.beixin.model.Houses;
import com.beixin.model.Lookhouse;
import com.beixin.service.ILookhouseService;
import com.beixin.util.JsonObject;
import com.beixin.util.JwtUtils2;
import com.beixin.util.R;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 看房信息表 前端控制器
 * </p>
 *
 * @author gehao
 * @since 2021-04-01
 */
@Api(tags = {"看房信息表"})
@RestController
@RequestMapping("/lookhouse")
public class LookhouseController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private ILookhouseService lookhouseService;

    @RequestMapping("/queryLookHouseAll")
    public Object queryLookHouseAll(String numbers ,String identity,Lookhouse lookhouse,@RequestParam(defaultValue = "1") Integer page ,
                                    @RequestParam(defaultValue = "10") Integer limit){
        Houses houses = new Houses();
        houses.setNumbers(numbers);
        lookhouse.setHouses(houses);
        //组装
        Customer customer = new Customer();
        customer.setIdentity(identity);
        lookhouse.setCustomer(customer);

        JsonObject object=new JsonObject();
        PageInfo<Lookhouse> pageInfo=lookhouseService.findLookHouseAll(page,limit,lookhouse);
        object.setMsg("ok");
        object.setData(pageInfo.getList());
        object.setCode(0);
        object.setCount(pageInfo.getTotal());
        return object;
    }


    @ApiOperation(value = "新增看房信息表")
    @PostMapping("/add")
    public R add(@RequestBody Lookhouse lookhouse, HttpServletRequest request){
        String token=request.getHeader("token");
        Integer userId=  JwtUtils2.getUserId(token);
        lookhouse.setCustomerId(userId);
        lookhouse.setDate(new Date());
        lookhouse.setStutas(0);
        lookhouseService.add(lookhouse);
        return R.ok();
    }

    @ApiOperation(value = "删除看房信息表")
    @RequestMapping("/deleteByIds")
    public R delete(String ids){
        List<String> list = Arrays.asList(ids.split(","));
       for (String id:list){
          lookhouseService.delete(Long.parseLong(id));
       }
       return R.ok();
    }

    @ApiOperation(value = "更新看房信息状态表")
    @RequestMapping("/update")
    public R update(Integer id ){
        Lookhouse lookhouse=new Lookhouse();
        lookhouse.setId(id);
        lookhouse.setStutas(1);//g更改为已看房但不租赁
        lookhouse.setUpdateTime(new Date());
        lookhouseService.updateData(lookhouse);
        return R.ok();
    }

    @ApiOperation(value = "查询看房信息表分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<Lookhouse> findListByPage(@RequestParam Integer page,
                                           @RequestParam Integer pageCount){
        return lookhouseService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询看房信息表")
    @RequestMapping("/queryByID")
    public Lookhouse findById(Integer id){
        Lookhouse lookhouse = lookhouseService.queryById(id);
        return lookhouse;
    }


}

package com.beixin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.beixin.model.Houses;
import com.beixin.model.Rentinfo;
import com.beixin.model.Repair;
import com.beixin.model.TongJi;
import com.beixin.service.IRentinfoService;
import com.beixin.service.IRepairService;
import com.beixin.util.JsonObject;
import com.beixin.util.JwtUtils2;
import com.beixin.util.R;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 报修信息表 前端控制器
 * </p>
 *
 * @author gehao
 * @since 2021-04-01
 */
@Api(tags = {"报修信息表"})
@RestController
@RequestMapping("/repair")
public class RepairController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IRepairService repairService;
    @Resource
    private IRentinfoService rentinfoService;

    @RequestMapping("/queryRepairAll")
    public Object queryRepairAll(String numbers, String identity, Repair repair, @RequestParam(defaultValue = "1") Integer page,
                                 @RequestParam(defaultValue = "10") Integer limit) {
        Houses houses = new Houses();
        houses.setNumbers(numbers);
        repair.setHouses(houses);

        JsonObject object = new JsonObject();
        PageInfo<Repair> pageInfo = repairService.findRepairAll(page, limit, repair);
        object.setMsg("ok");
        object.setData(pageInfo.getList());
        object.setCode(0);
        object.setCount(pageInfo.getTotal());
        return object;
    }

    @RequestMapping("/queryRepairAll2")
    public Object queryRepairAll2(HttpServletRequest request , Repair repair, @RequestParam(defaultValue = "1") Integer page ,
                                  @RequestParam(defaultValue = "10") Integer limit){

        String token=request.getHeader("token");
        Integer userId=  JwtUtils2.getUserId(token);
        repair.setCustomerId(userId);
        JsonObject object=new JsonObject();
        PageInfo<Repair> pageInfo=repairService.findRepairAll(page,limit,repair);
        object.setMsg("ok");
        object.setData(pageInfo.getList());
        object.setCode(0);
        object.setCount(pageInfo.getTotal());
        return object;
    }

    @ApiOperation(value = "新增报修信息表")
    @PostMapping("/add")
    public R add(@RequestBody Repair repair) {
        Integer houId = repair.getHousesId();
        //根据房子的信息可以获取当前的租客信息
        Rentinfo info = rentinfoService.queryRentInfoByHouId(houId);
        repair.setCustomerId(info.getCustomerId());
        repair.setStatus(0);
        repair.setDate(new Date());
        repairService.add(repair);
        return R.ok();
    }


    @RequestMapping("/deleteByIds")
    public R delete(String ids) {
        List<String> list = Arrays.asList(ids.split(","));
        for (String id : list) {
            repairService.delete(Long.parseLong(id));
        }
        return R.ok();
    }

    @ApiOperation(value = "更新报修信息表")
    @RequestMapping("/update")
    public R update(@RequestBody Repair repair) {
        repairService.updateData(repair);
        return R.ok();
    }

    @ApiOperation(value = "查询报修信息表分页数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码"),
            @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<Repair> findListByPage(@RequestParam Integer page,
                                        @RequestParam Integer pageCount) {
        return repairService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询报修信息表")
    @GetMapping("{id}")
    public Repair findById(@PathVariable Long id) {
        return repairService.findById(id);
    }

    /**
     * /**
     * 统计查询的接口
     */
    @RequestMapping("/queryTongJi")
    public Object queryTongJi() {
        //获取到数据
        List<TongJi> list = repairService.queryCounts();
        for (int i = 1; i <= 12; i++) {
            //定义标识
            boolean bs = false;
            for (TongJi info : list) {
                int month = Integer.parseInt(info.getMonths());
                if (month == i) {
                    bs = true;
                }
            }
            if (!bs) {
                TongJi tongji = new TongJi();
                tongji.setMonths(new Integer(i).toString());
                tongji.setCounts(0);
                list.add(tongji);
            }
            //排序
            Collections.sort(list);

        }
        return list;
    }
}

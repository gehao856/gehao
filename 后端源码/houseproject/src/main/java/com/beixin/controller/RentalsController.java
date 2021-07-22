package com.beixin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.beixin.model.Rentals;
import com.beixin.model.RentalsVo;
import com.beixin.model.TongJi;
import com.beixin.service.IRentalsService;
import com.beixin.util.DateTimeCom;
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
 * 收租信息表 前端控制器
 * </p>
 *
 * @author gehao
 * @since 2021-04-01
 */
@Api(tags = {"收租信息表"})
@RestController
@RequestMapping("/rentals")
public class RentalsController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IRentalsService rentalsService;

    /**
     * 查询列表信息
     * @param
     * @return
     */
    @RequestMapping("/queryRentalsAll")
    public JsonObject queryRentalsAll(RentalsVo rentalsVo, @RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "10")Integer limit){
        JsonObject object=new JsonObject();
        PageInfo<RentalsVo> pageInfo=rentalsService.findRentalsAll(page,limit,rentalsVo);
        object.setCode(0);
        object.setCount(pageInfo.getTotal());
        object.setMsg("ok");
//        object.setData(pageInfo.getList());
         /*
            交租状态
            1、如果截至日期小于当前天 超时
            2、如果截至日期小于5天 快缴费了
            3、否则已经缴费
          */
        //获取缴费列表信息
        List<RentalsVo> list=pageInfo.getList();

        for(RentalsVo rentals :list){
            //获取截至时间
            Date jzsj= rentals.getJzrq();
            int day=DateTimeCom.getBetweenDay(new Date(),jzsj);
            System.out.println(day);
            if(day<=0){
                //根据房屋编号查询是否是最新记录 如果是超时，否则可能是上月信息
                rentalsVo.setNumbers(rentals.getNumbers());
                PageInfo<RentalsVo> pageInfo2=rentalsService.findRentalsAll(1,1,rentalsVo);
                if(pageInfo2.getList().get(0).getId()==rentals.getId()){
                    rentals.setStatus("超时");
                }else{
                    rentals.setStatus("已经缴费");
                }
            }else if(day<5){
                rentals.setStatus("要缴费了");
            }else{
                rentals.setStatus("已经缴费");
            }
        }
        object.setData(list);
        return object;
    }


    /**
     * 查询列表信息
     * @return
     */
    @RequestMapping("/queryRentalsAll2")
    public JsonObject queryRentalsAll2(HttpServletRequest request , RentalsVo rentalsVo, @RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "10")Integer limit){
        String token=request.getHeader("token");
        Integer userId=  JwtUtils2.getUserId(token);
        rentalsVo.setCustomerId(userId);
        JsonObject object=new JsonObject();
        PageInfo<RentalsVo> pageInfo=rentalsService.findRentalsAll(page,limit,rentalsVo);
        object.setCode(0);
        object.setCount(pageInfo.getTotal());
        object.setMsg("ok");
//        object.setData(pageInfo.getList());
         /*
            交租状态
            1、如果截至日期小于当前天 超时
            2、如果截至日期小于5天 快缴费了
            3、否则已经缴费
          */
        //获取缴费列表信息
        List<RentalsVo> list=pageInfo.getList();

        for(RentalsVo rentals :list){
            //获取截至时间
            Date jzsj= rentals.getJzrq();
            int day=DateTimeCom.getBetweenDay(new Date(),jzsj);
            System.out.println(day);
            if(day<=0){
                //根据房屋编号查询是否是最新记录 如果是超时，否则可能是上月信息
                rentalsVo.setNumbers(rentals.getNumbers());
                PageInfo<RentalsVo> pageInfo2=rentalsService.findRentalsAll(1,1,rentalsVo);
                if(pageInfo2.getList().get(0).getId()==rentals.getId()){
                    rentals.setStatus("超时");
                }else{
                    rentals.setStatus("已经缴费");
                }
            }else if(day<5){
                rentals.setStatus("要缴费了");
            }else{
                rentals.setStatus("已经缴费");
            }
        }
        object.setData(list);
        return object;
    }


    @ApiOperation(value = "新增收租信息表")
    @RequestMapping("/add")
    public R add(@RequestBody Rentals rentals){
        rentals.setDate(new Date());
        rentalsService.add(rentals);
        return R.ok();
    }

    @ApiOperation(value = "删除收租信息表")
    @RequestMapping("/deleteByIds")
    public R deleteByIds(String ids){
        List <String> list
                = Arrays.asList(ids.split(","));
        for(String id:list){
            rentalsService.delete(Long.parseLong(id));
        }
        return R.ok();
    }

    @ApiOperation(value = "更新收租信息表")
    @PutMapping()
    public int update(@RequestBody Rentals rentals){
        return rentalsService.updateData(rentals);
    }

    @ApiOperation(value = "查询收租信息表分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<Rentals> findListByPage(@RequestParam Integer page,
                                         @RequestParam Integer pageCount){
        return rentalsService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询收租信息表")
    @GetMapping("{id}")
    public Rentals findById(@PathVariable Long id){
        return rentalsService.findById(id);
    }

    /**
     * 统计查询的接口
     */
    @RequestMapping("/queryTongJi")
    public Object queryTongJi() {
        //获取到数据
        List<TongJi> list = rentalsService.queryCount();
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
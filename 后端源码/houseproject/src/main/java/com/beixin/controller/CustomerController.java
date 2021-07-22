package com.beixin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.beixin.model.Customer;
import com.beixin.model.User;
import com.beixin.service.ICustomerService;
import com.beixin.util.JsonObject;
import com.beixin.util.JwtUtils2;
import com.beixin.util.MD5Utils;
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
import java.util.List;

/**
 * <p>
 * 租户信息表 前端控制器
 * </p>
 *
 * @author gehao
 * @since 2021-04-01
 */
@Api(tags = {"租户信息表"})
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private ICustomerService customerService;

    /**
     * 高级查询
     * @param customer
     * @return
     */
    @RequestMapping("/queryCustomerAll")
     private Object findCustomer(Customer customer,@RequestParam(defaultValue = "1") Integer page,
                                 @RequestParam(defaultValue = "10")Integer limit ){
         JsonObject object = new JsonObject();
         //获取记录信息
         PageInfo<Customer> pageInfo = customerService.queryAll(page, limit, customer);
         object.setMsg("ok");
         object.setData(pageInfo.getList());
         object.setCode(0);
         object.setCount(pageInfo.getTotal());
         return object;
     }

    @ApiOperation(value = "新增租户信息表")
    @PostMapping("/addCustomer")
    public R add(@RequestBody Customer customer){
        String password = MD5Utils.md5(customer.getPassword());
        customer.setPassword(password);
        int num = customerService.add(customer);
       if (num>0){
           return R.ok();
       }
       return R.fail("租客信息添加失败,请重新添加");
    }

    @ApiOperation(value = "修改密码")
    @RequestMapping("/updatePassword")
    public R update(HttpServletRequest request,String oldPwd, String newPwd,Integer id){
        String token=request.getHeader("token");
        Integer userId=  JwtUtils2.getUserId(token);
        id=userId;
        String password = MD5Utils.md5(oldPwd);
        //根据id获取当前的数据记录
        Customer customer = customerService.findById(new Long(id));
        if(password.equals(customer.getPassword())){//输入的老密码和原密码一致
            String password2 = MD5Utils.md5(newPwd);
            customer.setPassword(password2);
            customerService.updateData(customer);
            return R.ok();
        }else{
            return R.fail("两次密码不一致");
        }
    }


    @ApiOperation(value = "删除租户信息表")
    @RequestMapping("/deleteByIds")
    public R delete(String ids){
        //遍历获取前端传来的ids 集合
        List<String> list = Arrays.asList(ids.split(","));
        //遍历list集合
        for (String id : list){
            Long idLong = Long.parseLong(id);
            customerService.delete(idLong);
        }
        return R.ok();
    }

    @ApiOperation(value = "更新租户信息表")
    @RequestMapping("/updateCustomer")
      public R update(@RequestBody Customer customer){
        int num = customerService.updateData(customer);
        if (num>0){
            return R.ok();
        }
        return R.fail("失败");

    }


    @ApiOperation(value = "查询租户信息表分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<Customer> findListByPage(@RequestParam Integer page,
                                          @RequestParam Integer pageCount){
        return customerService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询租户信息表")
    @GetMapping("{id}")
    public Customer findById(@PathVariable Long id){
        return customerService.findById(id);
    }


    @RequestMapping("/queryCustomerAllByName")
    public Object queryCustomerAllByName(){
        PageInfo<Customer> pageInfo = customerService.queryAll(1,100,null);
        return pageInfo.getList();

    }

}

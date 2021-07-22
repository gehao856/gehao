package com.beixin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.beixin.model.User;
import com.beixin.service.IUserService;
import com.beixin.util.JsonObject;
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
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author gehao
 * @since 2021-04-01
 */
@Api(tags = {"用户信息表"})
@RestController
@RequestMapping("/user")
public class UserController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IUserService userService;

    @RequestMapping("/queryUserAll")
    public Object queryUserAll( @RequestParam(defaultValue = "1") Integer page ,
                                @RequestParam(defaultValue = "10") Integer limit){

        JsonObject object=new JsonObject();
        PageInfo<User> pageInfo=userService.findUserAll(page,limit,null);
        object.setMsg("ok");
        object.setData(pageInfo.getList());
        object.setCode(0);
        object.setCount(pageInfo.getTotal());
        return object;
    }

    @ApiOperation(value = "新增用户信息表")
    @PostMapping("/add")
    public R add(@RequestBody User user){
        String password = MD5Utils.md5(user.getPassword());
        user.setPassword(password);
        userService.add(user);
        return R.ok();
    }

    @ApiOperation(value = "删除用户信息表")
    @RequestMapping("/deleteByIds")
    public R delete(String ids){
        //获取前端传过来的ids集合   "1,2,3,4,5"
        List<String> list= Arrays.asList(ids.split(","));
        //遍历list集合进行删除操作
        for(String id:list){
            Long idLong=Long.parseLong(id);
            userService.delete(idLong);
        }
        return R.ok();
    }


    @ApiOperation(value = "修改密码")
    @RequestMapping("/updatePassword")
    public R update(String oldPwd, String newPwd, Integer id){
        //根据id获取当前的数据记录
        User user=userService.findById(new Long(id));
        String password = MD5Utils.md5(oldPwd);
        if(password.equals(user.getPassword())){//输入的老密码和原密码一致
            String password2 = MD5Utils.md5(newPwd);
            user.setPassword(password2);
            userService.updateData(user);
            return R.ok();
        }else{
            return R.fail("两次密码不一致");
        }
    }

    @ApiOperation(value = "查询用户信息表分页数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码"),
            @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<User> findListByPage(@RequestParam Integer page,
                                      @RequestParam Integer pageCount){
        return userService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询用户信息表")
    @GetMapping("{id}")
    public User findById(@PathVariable Long id){
        return userService.findById(id);
    }
}

package com.beixin.controller;

import com.beixin.model.Owner;
import com.beixin.service.IOwnerService;
import com.beixin.util.JsonObject;
import com.beixin.util.R;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("owner")
public class OwnerController {
    @Autowired
    private IOwnerService ownerService;
    /**
     * 按条件查询我们的房主信息
     */
    @RequestMapping("/queryOwnerAll")
    @ResponseBody
    public Object queryOwnerAll(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit, Owner owner){
        JsonObject object = new JsonObject();
       //分页查询所有的记录信息
       PageInfo<Owner> pageInfo = ownerService.findOwnerAll(page,limit,owner);
       object.setCode(0);
       object.setCount(pageInfo.getTotal());
       object.setData(pageInfo.getList());
       object.setMsg("ok");
       return object;
    }

    @RequestMapping("/findOwnerAll")
    @ResponseBody
    public Object findOwnerAll (){
        PageInfo<Owner> pageInfo = ownerService.findOwnerAll(1,300,null);
        return pageInfo.getList();
    }

    /**
     * 添加房主信息
     */
    @RequestMapping("/addOwner")
    @ResponseBody
    public R add(@RequestBody Owner owner){
        int num = ownerService.add(owner);
        if (num>0){
          return R.ok();
        }
        return R.fail(400,"添加失败");
    }

    /**
     * 删除功能
     */
    @RequestMapping("/deleteOwner")
    @ResponseBody
   public R delete(String ids){
       //遍历获取前端传来的ids 集合
       List<String> list = Arrays.asList(ids.split(","));
       //遍历list集合
       for (String id : list){
           Long idLong = Long.parseLong(id);
           ownerService.delete(idLong);
       }
       return R.ok();

   }
    /**
     * 修改房主信息
     */
    @RequestMapping("/updateOwner")
    @ResponseBody
    public R update(@RequestBody Owner owner){
        int num = ownerService.updateDate(owner);
        if (num>0){
            return R.ok();
        }
        return R.fail(400,"修改失败");
    }


}

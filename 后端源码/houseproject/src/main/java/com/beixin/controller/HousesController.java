package com.beixin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.beixin.util.JwtUtils2;
import com.github.pagehelper.PageInfo;
import com.beixin.model.Houses;
import com.beixin.service.IHousesService;
import com.beixin.util.JsonObject;
import com.beixin.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 房屋信息管理 前端控制器
 * </p>
 *
 * @author gehao
 * @since 2021-04-01
 */
@Api(tags = {"房屋信息管理"})
@RestController
@RequestMapping("/houses")
public class HousesController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IHousesService housesService;


    @RequestMapping("/queryHouseAll")
    public Object queryHouseAll(Houses houses, @RequestParam(defaultValue = "1") Integer page ,
                                @RequestParam(defaultValue = "10") Integer limit){
        JsonObject object=new JsonObject();
        //获取记录信息
        PageInfo<Houses> pageInfo= housesService.findHouseAll(page,limit,houses);
        object.setMsg("ok");
        object.setData(pageInfo.getList());
        object.setCode(0);
        object.setCount(pageInfo.getTotal());
        return object;
    }

    @RequestMapping("/queryHouseAll2")
    public Object queryHouseAll2(HttpServletRequest request , @RequestParam(defaultValue = "1") Integer page ,
                                 @RequestParam(defaultValue = "10") Integer limit){
        String token=request.getHeader("token");
        Integer userId=  JwtUtils2.getUserId(token);
        JsonObject object=new JsonObject();
        //获取记录信息
        PageInfo<Houses> pageInfo= housesService.findHouseAll2(page,limit,userId);
        object.setMsg("ok");
        object.setData(pageInfo.getList());
        object.setCode(0);
        object.setCount(pageInfo.getTotal());
        return object;
    }


    /**
     * 上传图片
     * @param
     * @return
     */
    @PostMapping("/fileUpload")
    public R fileUpload(@RequestParam(value="file")MultipartFile file){
        if(file.isEmpty()){
            System.out.println("图片不存在");
        }
        String fileName= file.getOriginalFilename();
        //获取后缀名称
        String suffixName=fileName.substring(fileName.lastIndexOf("."));
        //更新未新的文件名信息
        fileName= UUID.randomUUID()+suffixName;
        //设置路径信息
        String filePath="E://images//";
        //上传
        File dest=new File(filePath,fileName);
        if(!dest.getParentFile().exists()){//如果文件路径不存在需要创建
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        }catch(IOException e){
            e.printStackTrace();
        }
        String filename="/images/"+fileName;

        return R.ok(fileName,null);
    }


    @ApiOperation(value = "新增房屋信息管理")
    @PostMapping("/addHouses")
    public R add(@RequestBody Houses houses){
        int num= housesService.add(houses);
        if(num>0){
            return  R.ok();
        }
        return R.fail("添加信息失败");
    }

    @ApiOperation(value = "删除房屋信息管理")
    @RequestMapping("/deleteByIds")
    public R delete(String ids){
        //把字符串转集合
        List<String> list= Arrays.asList(ids.split(","));
        for(String id:list){
            housesService.delete(Long.parseLong(id));
        }
        return R.ok();
    }

    @ApiOperation(value = "更新房屋信息管理")
    @RequestMapping("/updateHouses")
    public R update(@RequestBody Houses houses){
        int num= housesService.updateData(houses);
        if(num>0){
            return  R.ok();
        }
        return R.fail("修改信息失败");
    }

    @ApiOperation(value = "查询房屋信息管理分页数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码"),
            @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<Houses> findListByPage(@RequestParam Integer page,
                                        @RequestParam Integer pageCount){
        return housesService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询房屋信息管理")
    @GetMapping("{id}")
    public Houses findById(@PathVariable Long id){
        return housesService.findById(id);
    }

    @RequestMapping("/housesByNumbers")
    public Object housesByNumbers(){
        PageInfo<Houses> pageInfo=housesService.findHouseAll(1,100,null);
        return pageInfo.getList();
    }
}

package com.beixin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.beixin.model.AdminInfo;
import com.beixin.model.Notice;
import com.beixin.service.INoticeService;
import com.beixin.util.JsonObject;
import com.beixin.util.JwtUtils2;
import com.beixin.util.R;
import com.github.pagehelper.PageInfo;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.security.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spi.service.contexts.SecurityContext;
import sun.plugin.liveconnect.SecurityContextHelper;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Security;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 公告信息表 公布最新优质房源 前端控制器
 * </p>
 *
 * @author gehao
 * @since 2021-04-01
 */
@Api(tags = {"公告信息表 公布最新优质房源"})
@RestController
@RequestMapping("/notice")
public class NoticeController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private INoticeService noticeService;

    //详细转
    @RequestMapping("/noticeAll")
    public Object noticeAll(Notice notice, @RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "15")Integer limit){
        PageInfo<Notice> pageInfo= noticeService.queryNoticeAll(page,limit,notice);
        JsonObject object=new JsonObject();
        object.setMsg("ok");
        object.setData(pageInfo.getList());
        object.setCode(0);
        object.setCount(pageInfo.getTotal());
        return object;
    }


    @ApiOperation(value = "新增公告信息表 公布最新优质房源")
    @RequestMapping("/add")
    public R add(Notice notice, HttpServletRequest request){
        //获取当前登录者姓名 添加即可  省略
        String token = request.getHeader("token");
        String userName = JwtUtils2.getUserName(token);
        notice.setDjr(userName);
        notice.setDate(new Date());
        noticeService.add(notice);
        return R.ok();
    }

    @RequestMapping("/deleteByIds")
    public R delete(String ids){
        //获取前端传过来的ids集合   "1,2,3,4,5"
        List<String> list= Arrays.asList(ids.split(","));
        //遍历list集合进行删除操作
        for(String id:list){
            Long idLong=Long.parseLong(id);
            noticeService.delete(idLong);
        }
        return R.ok();
    }
    @ApiOperation(value = "更新公告信息表 公布最新优质房源")
    @PutMapping()
    public int update(@RequestBody Notice notice){
        return noticeService.updateData(notice);
    }

    @ApiOperation(value = "查询公告信息表 公布最新优质房源分页数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码"),
            @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<Notice> findListByPage(@RequestParam Integer page,
                                        @RequestParam Integer pageCount){
        return noticeService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询公告信息表 公布最新优质房源")
    @GetMapping("{id}")
    public Notice findById(@PathVariable Long id){
        return noticeService.findById(id);
    }
}

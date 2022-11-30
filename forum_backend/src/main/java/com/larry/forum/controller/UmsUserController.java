package com.larry.forum.controller;

import cn.hutool.core.util.ObjectUtil;
import com.larry.forum.common.api.ApiResult;
import com.larry.forum.model.dto.LoginDTO;
import com.larry.forum.model.dto.RegisterDTO;
import com.larry.forum.model.entity.UmsUser;
import com.larry.forum.service.IUmsUserService;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

import static com.larry.forum.jwt.JwtUtil.USER_NAME;


@RestController
@RequestMapping("/ums/user")
public class UmsUserController extends BaseController {
    @Resource
    private IUmsUserService iUmsUserService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ApiResult<Map<String, Object>> register(@Valid @RequestBody RegisterDTO dto) {
        UmsUser user = iUmsUserService.executeRegister(dto);
        if (ObjectUtils.isEmpty(user)) {
            return ApiResult.failed("账号注册失败");
        }
        Map<String, Object> map = new HashMap<>(16);
        map.put("user", user);
        return ApiResult.success(map);
    }

    @RequestMapping(value = "/login" , method = RequestMethod.POST)
    public ApiResult<Map<String , String>> login(@Valid @RequestBody LoginDTO dto){

        String token = iUmsUserService.executeLogin(dto);

        if(ObjectUtil.isEmpty(token)){
            return ApiResult.failed("账号密码错误");
        }
        Map<String , String > map = new HashMap<>(16);
        map.put("token",token);

        return ApiResult.success(map,"登陆成功");

    }
    
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ApiResult<UmsUser> getUser(@RequestHeader(value = USER_NAME) String userName) {
        UmsUser user = iUmsUserService.getUserByUsername(userName);
        return ApiResult.success(user);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ApiResult<Object> logOut() {
        return ApiResult.success(null, "注销成功");
    }

}
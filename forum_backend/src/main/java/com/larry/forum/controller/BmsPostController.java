package com.larry.forum.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.larry.forum.model.dto.CreateTopicDTO;
import com.larry.forum.common.api.ApiResult;
import com.larry.forum.model.entity.BmsPost;
import com.larry.forum.model.entity.UmsUser;
import com.larry.forum.model.vo.PostVO;
import com.larry.forum.service.IBmsPostService;
import com.larry.forum.service.IUmsUserService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

import java.util.Map;

import static com.larry.forum.jwt.JwtUtil.USER_NAME;

@RestController
@RequestMapping("/post")
public class BmsPostController extends BaseController {

    @Resource
    private IBmsPostService iBmsPostService;

    @Resource
    private IUmsUserService umsUserService;

    @GetMapping("/list")
    public ApiResult<Page<PostVO>> list(@RequestParam(value = "tab", defaultValue = "latest") String tab,
                                        @RequestParam(value = "pageNo", defaultValue = "1")  Integer pageNo,
                                        @RequestParam(value = "size", defaultValue = "10") Integer pageSize) {
        Page<PostVO> list = iBmsPostService.getList(new Page<>(pageNo, pageSize), tab);
        return ApiResult.success(list);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ApiResult<BmsPost> create(@RequestHeader(value = USER_NAME) String userName
            , @RequestBody CreateTopicDTO dto) {
        UmsUser user = umsUserService.getUserByUsername(userName);
        BmsPost topic = iBmsPostService.create(dto, user);
        return ApiResult.success(topic);
    }

    @GetMapping()
    public ApiResult<Map<String, Object>> view(@RequestParam("id") String id) {
        Map<String, Object> map = iBmsPostService.viewTopic(id);
        System.out.println("这是一个map");
        System.out.println(map);
        return ApiResult.success(map);
    }
}
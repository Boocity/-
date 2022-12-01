package com.larry.forum.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.larry.forum.model.dto.CommentDTO;
import com.larry.forum.model.entity.BmsComment;
import com.larry.forum.model.entity.UmsUser;
import com.larry.forum.model.vo.CommentVO;

import java.util.List;


public interface IBmsCommentService extends IService<BmsComment> {
    /**
     *
     *
     * @param topicid
     * @return {@link BmsComment}
     */
    List<CommentVO> getCommentsByTopicID(String topicid);

    BmsComment create(CommentDTO dto, UmsUser principal);
}

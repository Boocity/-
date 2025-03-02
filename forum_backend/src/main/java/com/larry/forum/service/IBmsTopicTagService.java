package com.larry.forum.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.larry.forum.model.entity.BmsTag;
import com.larry.forum.model.entity.BmsTopicTag;

import java.util.List;
import java.util.Set;

public interface IBmsTopicTagService extends IService<BmsTopicTag> {

    /**
     * 获取Topic Tag 关联记录
     *
     * @param topicId TopicId
     * @return
     */
    List<BmsTopicTag> selectByTopicId(String topicId);
    /**
     * 创建中间关系
     *
     * @param id
     * @param tags
     * @return
     */
    void createTopicTag(String id, List<BmsTag> tags);

}

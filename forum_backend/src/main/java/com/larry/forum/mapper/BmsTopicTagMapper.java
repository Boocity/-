package com.larry.forum.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.larry.forum.model.entity.BmsTopicTag;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface BmsTopicTagMapper extends BaseMapper<BmsTopicTag> {

}
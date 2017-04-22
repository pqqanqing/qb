package com.wjs.qb.service.topic;

import com.wjs.common.base.base.BasePageResults;
import com.wjs.qb.api.dto.TopicDTO;
import com.wjs.qb.domain.topic.Topic;

public interface TopicService {

    TopicDTO create(String memberId, TopicDTO topicDTO);

    TopicDTO soldOut(Long topicId);

    BasePageResults<TopicDTO> query(TopicDTO topicDTO);

    TopicDTO queryLatestTopic();
}

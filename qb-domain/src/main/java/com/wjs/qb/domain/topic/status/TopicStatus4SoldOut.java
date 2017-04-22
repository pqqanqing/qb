package com.wjs.qb.domain.topic.status;

import com.wjs.common.base.annotation.StatusAndClassNum;
import com.wjs.qb.domain.topic.Topic;

import static com.wjs.qb.api.dto.TopicDTO.STATUS_SOLD_OUT;

@StatusAndClassNum(superClass = TopicStatus.class, number = STATUS_SOLD_OUT, describe = "下架", parasitClass = Topic.class)
public class TopicStatus4SoldOut extends TopicStatus {
    private static TopicStatus instance = new TopicStatus4SoldOut();

    private TopicStatus4SoldOut() {
    }

    public static TopicStatus getInstance() {
        return instance;
    }
}

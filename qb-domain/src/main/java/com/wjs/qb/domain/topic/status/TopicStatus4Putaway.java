package com.wjs.qb.domain.topic.status;

import com.wjs.common.base.annotation.StatusAndClassNum;
import com.wjs.qb.domain.topic.Topic;

import static com.wjs.qb.api.dto.TopicDTO.STATUS_PUTAWAY;

@StatusAndClassNum(superClass = TopicStatus.class, number = STATUS_PUTAWAY, describe = "上架", parasitClass = Topic.class)
public class TopicStatus4Putaway extends TopicStatus {
    private static TopicStatus instance = new TopicStatus4Putaway();

    private TopicStatus4Putaway() {
    }

    public static TopicStatus getInstance() {
        return instance;
    }

    @Override
    public TopicStatus soldOut() {
        return TopicStatus4SoldOut.getInstance();
    }

    @Override
    public boolean canSendDynamic() {
        return true;
    }

    @Override
    public boolean canPublishComment() {
        return true;
    }

}

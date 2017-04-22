package com.wjs.qb.domain.topic.status;

public abstract class TopicStatus {

    public TopicStatus putaway() {
        throw new RuntimeException("话题状态【" + this + "】不可以转化为上架操作！");
    }

    public TopicStatus soldOut() {
        throw new RuntimeException("话题状态【" + this + "】不可以转化为下架操作！");
    }


    public boolean canSendDynamic() {
        return false;
    }
    
    public boolean canPublishComment() {
    	return false;
    }
}

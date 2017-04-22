package com.wjs.qb.domain.dynamic;

import com.wjs.common.base.execption.BusinessExecption;
import com.wjs.qb.api.dto.CommentDTO;
import com.wjs.qb.api.dto.DynamicDTO;
import com.wjs.qb.domain.comment.Comment;
import com.wjs.qb.domain.topic.Topic;
import com.wjs.qb.domain.topic.TopicRpt;
import lombok.Getter;
import lombok.Setter;

import static com.wjs.common.base.spring.SpringBeanContext.getBean;
import static java.util.Objects.isNull;

@Setter
@Getter
public class Dynamic4Topic extends Dynamic {

    protected Topic topic;

    public Dynamic4Topic() {
    }

    public Dynamic4Topic(DynamicDTO dynamicDTO) {
        super(dynamicDTO);
        this.topic = getBean(TopicRpt.class).getById(dynamicDTO.getTopic().getId());
        if (!topic.canSendDynamic()) throw new BusinessExecption("qb8");
        topic.addDynamic(this);
    }

    @Override
    public Comment createComment(CommentDTO commentDTO) {
        if (!isNull(topic) && !topic.canPublishComment()) throw new BusinessExecption("qb9");
        return super.createComment(commentDTO);
    }

    @Override
    public void stick() {
        throw new BusinessExecption("qb18");
    }

    @Override
    public void essence() {
        throw new BusinessExecption("qb19");
    }

    public String getTopicName() {
        if (isNull(topic)) return null;
        return topic.getTitle();
    }

    public Long getTopicId() {
        if (isNull(topic)) return null;
        return topic.getId();
    }
}

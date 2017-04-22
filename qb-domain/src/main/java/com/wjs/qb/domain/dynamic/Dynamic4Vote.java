package com.wjs.qb.domain.dynamic;

import com.wjs.common.base.execption.BusinessExecption;
import com.wjs.qb.api.dto.CommentDTO;
import com.wjs.qb.api.dto.DynamicDTO;
import com.wjs.qb.domain.comment.Comment;
import com.wjs.qb.domain.dynamic.status.DynamicStatus4Succ;
import com.wjs.qb.domain.topic.Topic;
import com.wjs.qb.domain.topic.TopicRpt;
import lombok.Getter;
import lombok.Setter;

import static com.wjs.common.base.spring.SpringBeanContext.getBean;
import static java.util.Objects.isNull;

@Setter
@Getter
public class Dynamic4Vote extends Dynamic {
    private Topic voteTopic;
    /** 投票 true:赞成  false:反对 */
    private Boolean vote = true;

    public Dynamic4Vote() {
    }

    public Dynamic4Vote(DynamicDTO dynamicDTO) {
        super(dynamicDTO);
        this.voteTopic = getBean(TopicRpt.class).getById(dynamicDTO.getTopic().getId());
        if (!voteTopic.canSendDynamic()) throw new BusinessExecption("qb8");
        voteTopic.addDynamic(this);
        this.vote = dynamicDTO.getVote();
        this.status = DynamicStatus4Succ.getInstance();
    }

    @Override
    public Comment createComment(CommentDTO commentDTO) {
        if (!isNull(voteTopic) && !voteTopic.canPublishComment()) throw new BusinessExecption("qb9");
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
}

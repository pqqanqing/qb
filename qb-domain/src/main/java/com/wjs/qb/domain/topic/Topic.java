package com.wjs.qb.domain.topic;

import com.wjs.common.base.base.BaseEntity;
import com.wjs.qb.api.dto.TopicDTO;
import com.wjs.qb.domain.dynamic.Dynamic;
import com.wjs.qb.domain.member.Member;
import com.wjs.qb.domain.topic.status.TopicStatus;
import com.wjs.qb.domain.topic.status.TopicStatus4Putaway;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static com.wjs.common.base.util.BeanPropertiesUtil.copyProperties;
import static java.util.Collections.unmodifiableList;

@Setter
@Getter
public class Topic extends BaseEntity<TopicStatus> {

    private String title;
    private Member member;
    private List<Dynamic> dynamics = new ArrayList<>();

    public Topic() {
    }

    public Topic(Member member, TopicDTO topicDTO) {
        this.member = member;
        this.title = topicDTO.getTitle();
        this.status = TopicStatus4Putaway.getInstance();
    }

    public void soldOut() {
        status = status.soldOut();
    }

    public void addDynamic(Dynamic dynamic) {
        dynamics.add(dynamic);
    }

    public List<Dynamic> getDynamics() {
        return unmodifiableList(dynamics);
    }

    public boolean canSendDynamic() {
        return status.canSendDynamic();
    }

    public boolean canPublishComment() {
        return status.canPublishComment();
    }

    public int getDynamicCount() {
        int count = 0;
        for(Dynamic dynamic : dynamics) {
            if (dynamic.getStatus().canComment()) count++;
        }
        return count;
    }

    public String getManager() {
        return member.getName();
    }

    public TopicDTO makeDTO() {
        return copyProperties(this, TopicDTO.class);
    }
}

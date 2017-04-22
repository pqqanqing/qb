package com.wjs.qb.rpt.topic;


import com.wjs.common.base.base.BaseQueryBuilder;
import com.wjs.qb.domain.topic.Topic;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.hibernate.criterion.Restrictions.like;

@Service("topicQueryBuilder")
@Scope("prototype")
public class TopicQueryBuilder extends BaseQueryBuilder<Topic> {

    public void setTitle(String title) {
        if (isBlank(title)) return;
        add(like("title", "%" + title + "%"));
    }
}

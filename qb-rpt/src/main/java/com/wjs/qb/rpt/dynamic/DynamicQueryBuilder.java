package com.wjs.qb.rpt.dynamic;


import com.wjs.common.base.base.BaseQueryBuilder;
import com.wjs.qb.api.dto.GroupDTO;
import com.wjs.qb.api.dto.MemberDTO;
import com.wjs.qb.api.dto.TopicDTO;
import com.wjs.qb.domain.dynamic.Dynamic;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.like;
import static org.hibernate.sql.JoinType.LEFT_OUTER_JOIN;

@Service("dynamicQueryBuilder")
@Scope("prototype")
public class DynamicQueryBuilder extends BaseQueryBuilder<Dynamic> {

    public void setTitle(String title) {
        if (isNull(title)) return;
        add(like("title", "%" + title + "%"));
    }

    public void setMember(MemberDTO member) {
        if (isNull(member)) return;
        createAlias("member", "member", LEFT_OUTER_JOIN);
        if (isNotBlank(member.getNickname())) {
            add(eq("member.nickname", member.getNickname()));
        }
        if (nonNull(member.getId())) {
            add(eq("member.id", member.getId()));
        }
        if (isNotBlank(member.getOpenId())) {
            add(eq("member.openId", member.getOpenId()));
        }
    }

    public void setGroup(GroupDTO group) {
        if (isNull(group)) return;
        createAlias("group", "group", LEFT_OUTER_JOIN);
        if (nonNull(group.getId())) {
            add(eq("group.id", group.getId()));
        }
    }

    public void setTopic(TopicDTO topic) {
        if (isNull(topic)) return;
        createAlias("topic", "topic", LEFT_OUTER_JOIN);
        if (nonNull(topic.getId())) {
            add(eq("topic.id", topic.getId()));
        }
    }

    public void setOrderByStickEssence(Boolean orderByStickEssence) {
        if (nonNull(orderByStickEssence) && orderByStickEssence) {
            orderByProperty(false, "stick");
            orderByProperty(false, "essence");
        }
    }
}

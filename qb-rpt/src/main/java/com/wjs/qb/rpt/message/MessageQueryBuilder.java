package com.wjs.qb.rpt.message;


import com.wjs.common.base.base.BaseQueryBuilder;
import com.wjs.qb.api.dto.MemberDTO;
import com.wjs.qb.domain.message.Message;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.sql.JoinType.LEFT_OUTER_JOIN;

@Service("messageQueryBuilder")
@Scope("prototype")
public class MessageQueryBuilder extends BaseQueryBuilder<Message> {

    public void setMember(MemberDTO member) {
        if (isNull(member)) return;
        createAlias("member", "member", LEFT_OUTER_JOIN);
        if (nonNull(member.getId())) {
            add(eq("member.id", member.getId()));
        }
        if (isNotBlank(member.getOpenId())) {
            add(eq("member.openId", member.getOpenId()));
        }
    }
}

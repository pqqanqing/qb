package com.wjs.qb.rpt.member;


import com.wjs.common.base.base.BaseQueryBuilder;
import com.wjs.qb.domain.member.Member;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.hibernate.criterion.Restrictions.like;
import static org.hibernate.criterion.Restrictions.neProperty;

@Service("memberQueryBuilder")
@Scope("prototype")
public class MemberQueryBuilder extends BaseQueryBuilder<Member> {

    public void setNickname(String nickname) {
        if (isBlank(nickname)) return;
        add(like("nickname", "%" + nickname + "%"));
    }

    public void setIsManager(boolean manager) {
        if (manager) add(neProperty("openId", "memberId"));
    }
}

package com.wjs.qb.rpt.group;


import com.wjs.common.base.base.BaseQueryBuilder;
import com.wjs.qb.domain.group.Group;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import static org.hibernate.criterion.Restrictions.like;

@Service("groupQueryBuilder")
@Scope("prototype")
public class GroupQueryBuilder extends BaseQueryBuilder<Group> {
    @Override
    public void setName(String name) {
        add(like("name", "%" + name + "%"));
    }
}

package com.wjs.qb.rpt.comment;


import com.wjs.common.base.base.BaseQueryBuilder;
import com.wjs.qb.api.dto.MemberDTO;
import com.wjs.qb.domain.comment.Comment;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.sql.JoinType.LEFT_OUTER_JOIN;

@Service("commentQueryBuilder")
@Scope("prototype")
public class CommentQueryBuilder extends BaseQueryBuilder<Comment> {

    public void setCommentator(MemberDTO commentator) {
        if (isNull(commentator)) return;
        createAlias("commentator", "commentator", LEFT_OUTER_JOIN);
        if (nonNull(commentator.getId())) {
            add(eq("commentator.id", commentator.getId()));
        }
        if (isNotBlank(commentator.getOpenId())) {
            add(eq("commentator.openId", commentator.getOpenId()));
        }
    }

}

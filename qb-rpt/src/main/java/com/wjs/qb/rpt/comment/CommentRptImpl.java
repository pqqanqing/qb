package com.wjs.qb.rpt.comment;

import com.wjs.common.base.rpt.BaseRptImpl;
import com.wjs.qb.domain.comment.Comment;
import com.wjs.qb.domain.comment.CommentRpt;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public class CommentRptImpl extends BaseRptImpl<Comment, Serializable> implements CommentRpt{

	@Override
	public List<Comment> queryByParentID(Long parentID) {
		String hql = "from Comment c where c.parentId = :parentId";
		Query query = getSession().createQuery(hql);
		query.setParameter("parentId", parentID);
		return query.list();
	}

	
}

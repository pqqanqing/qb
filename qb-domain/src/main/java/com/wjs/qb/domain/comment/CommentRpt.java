package com.wjs.qb.domain.comment;

import com.wjs.common.base.rpt.BaseRpt;

import java.io.Serializable;
import java.util.List;

public interface CommentRpt extends BaseRpt<Comment, Serializable>{

	List<Comment> queryByParentID(Long parentID);
}

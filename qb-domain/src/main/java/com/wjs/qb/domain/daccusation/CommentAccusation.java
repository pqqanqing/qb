package com.wjs.qb.domain.daccusation;

import com.wjs.common.base.base.BaseEntity;
import com.wjs.qb.domain.accusation.Accusation;
import com.wjs.qb.domain.comment.Comment;
import com.wjs.qb.domain.member.Member;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentAccusation extends BaseEntity{
    private Comment comment;
    private Accusation accusation;
    private Member member;
    
    public CommentAccusation() {
    }
    
	public CommentAccusation(Comment comment, Accusation accusation, Member member) {
		this.comment = comment;
		this.accusation = accusation;
		this.member = member;
	}
}

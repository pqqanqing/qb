package com.wjs.qb.domain.message;

import com.wjs.common.base.base.BaseEntity;
import com.wjs.qb.domain.comment.Comment;
import com.wjs.qb.domain.dynamic.Dynamic;
import com.wjs.qb.domain.member.Member;
import lombok.Getter;
import lombok.Setter;

import static java.util.Objects.isNull;

@Setter
@Getter
public class Message extends BaseEntity {
    private Member member;
    private Dynamic dynamic;
    private Comment comment;
    private Comment childComment;

    public Message() {
    }

    public Message(Dynamic dynamic, Comment comment) {
        this.dynamic = dynamic;
        this.member = dynamic.getMember();
        this.comment = comment;
    }

    public Message(Comment comment, Comment child) {
        this.comment = comment;
        this.member = comment.getCommentator();
        this.childComment = child;
    }

    public String getNickname() {
        if (isNull(member)) return null;
        return member.getNickname();
    }

    public String getHead() {
        if (isNull(member) || isNull(member.getAttachment())) return null;
        return member.getAttachment().getPath();
    }

    public String getDynamicContent() {
        if (isNull(dynamic)) return null;
        return dynamic.getContent();
    }

    public void delete() {
        setLogicDelete(true);
    }
}

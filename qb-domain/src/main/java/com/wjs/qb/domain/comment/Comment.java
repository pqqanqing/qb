package com.wjs.qb.domain.comment;

import com.wjs.common.base.base.BaseEntity;
import com.wjs.common.base.execption.BusinessExecption;
import com.wjs.qb.api.dto.CommentDTO;
import com.wjs.qb.common.sensitive.SensitiveWordFilter;
import com.wjs.qb.domain.accusation.Accusation;
import com.wjs.qb.domain.daccusation.CommentAccusation;
import com.wjs.qb.domain.dynamic.Dynamic;
import com.wjs.qb.domain.member.Member;
import com.wjs.qb.domain.member.MemberRpt;
import com.wjs.qb.domain.message.Message;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

import static com.wjs.common.base.spring.SpringBeanContext.getBean;
import static com.wjs.common.base.util.BeanPropertiesUtil.copyProperties;
import static java.util.Objects.isNull;
import static org.springframework.util.CollectionUtils.isEmpty;

@Setter
@Getter
public class Comment extends BaseEntity {

    private String content;
    private Comment parent;
    private Dynamic dynamic;
    private Member commentator;
    private List<Comment> comments = new ArrayList<Comment>();//子评论
    private Set<CommentAccusation> commentAccusations = new HashSet<>();//举报
    private Comment superior;

    public Comment() {
    }

    public Comment(Dynamic dynamic, CommentDTO commentDTO) {
        this.dynamic = dynamic;
        setProperties(commentDTO);
    }

    public Comment publishChild(Comment comment, CommentDTO commentDTO) {
        return new Comment(comment, commentDTO);
    }

    public Comment(Comment comment, CommentDTO commentDTO) {
        this.superior = comment;
        setProperties(commentDTO);
    }

    private void setProperties(CommentDTO commentDTO) {
        this.content = commentDTO.getContent();
        if (isNull(commentDTO.getCommentator())) throw new BusinessExecption("qb16");
        this.commentator = getBean(MemberRpt.class).queryByOpenId(commentDTO.getCommentator().getOpenId());
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void delete() {
        setLogicDelete(true);
        if (isEmpty(comments)) return;
        comments.stream().forEach(comment -> {
            comment.setLogicDelete(true);
        });
        if (isEmpty(commentAccusations)) return;
        commentAccusations.stream().forEach(commentAccusation -> {
            commentAccusation.setLogicDelete(true);
        });
    }

    public void accusation(Member member, Accusation accusation) {
        this.commentator.addReportCommentCount();
        addCommentAccusation(new CommentAccusation(this, accusation, member));
    }

    private void addCommentAccusation(CommentAccusation commentAccusation) {
        commentAccusations.add(commentAccusation);
    }

    public void cancelAccusation() {
        int size = commentAccusations.size();
        this.commentator.reduceReportCommentCount(size);
        commentAccusations.stream().forEach(commentAccusation -> {
            commentAccusation.setLogicDelete(true);
        });
    }

    public String getHead() {
        if (isNull(commentator) || isNull(commentator.getAttachment())) return null;
        return commentator.getAttachment().getPath();
    }

    public String getNickname() {
        if (isNull(commentator)) return null;
        return commentator.getNickname();
    }

    public Message createMessage(Comment child) {
        Message message = new Message(this, child);
        commentator.addMessage(message);
        return message;
    }

    public Comment getRootParent() {
        return isNull(parent) ? this : parent;
    }

    public String getSname() {
        if (isNull(superior)) return null;
        return superior.getNickname();
    }

    public Long getSid() {
        if (isNull(superior)) return null;
        return superior.getId();
    }

    public String getScontent() {
        if (isNull(superior)) return null;
        return superior.getContent();
    }

    public String getDynamicContent() {
        if (Objects.isNull(dynamic)) return null;
        return dynamic.getContent();
    }

    public String getContent() {
        return SensitiveWordFilter.replaceSensitive(content);
    }

    public CommentDTO makeDTO() {
        return copyProperties(this, CommentDTO.class);
    }

}

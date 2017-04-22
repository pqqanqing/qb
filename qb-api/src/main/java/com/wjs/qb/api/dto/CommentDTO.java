package com.wjs.qb.api.dto;

import com.wjs.common.base.base.BaseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(value = {"parent", "dynamic", "superior", "dynamicAccusations", "commentator"}, allowSetters = true)
public class CommentDTO extends BaseDTO {
    /**
     * 评论内容
     */
    private String content;
    /**
     * 父级评论
     */
    private CommentDTO parent;
    /**
     * 动态
     */
    private DynamicDTO dynamic;
    /**
     * 子评论
     */
    private CommentDTO[] comments;
    /**
     * 举报
     */
    private DynamicAccusationDTO[] dynamicAccusations;
    /**
     * 评论人
     */
    private MemberDTO commentator;
    /**
     * 上级评论
     */
    private CommentDTO superior;
    /**
     * 头像
     */
    private String head;
    /**
     * 昵称
     */
    private String nickname;

    /**
     * 上级昵称
     */
    private String sname;

    /**
     * 上级ID
     */
    private Long sid;

    /**
     * 动态内容
     */
    private String dynamicContent;

    /**
     * 上级内容
     */
    private String scontent;

    public CommentDTO() {
    }
}

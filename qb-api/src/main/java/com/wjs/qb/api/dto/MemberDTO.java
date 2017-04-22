package com.wjs.qb.api.dto;

import com.wjs.common.base.base.BaseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@JsonIgnoreProperties(value = {"beginCreateTime", "endCreateTime"}, allowSetters = true)
public class MemberDTO extends BaseDTO {
    /**
     * 状态：正常
     */
    public static final String NORMAL = "0";
    /**
     * 状态：禁言
     */
    public static final String SHUT_UP = "1";

    /**
     * 会员id
     */
    private String memberId;
    /**
     * openId
     */
    private String openId;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private AttachmentDTO attachment;
    /**
     * 举报动态数
     */
    private int reportDynamicCount;
    /**
     * 举报评论数
     */
    private int reportCommentCount;
    /**
     * 查询创建时间开始
     */
    private Date beginCreateTime;
    /**
     * 查询创建时间结束
     */
    private Date endCreateTime;

    /**
     * true 查询管理员
     */
    private Boolean isManager;

    public MemberDTO() {
    }
}

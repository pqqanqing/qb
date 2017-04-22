package com.wjs.qb.api.dto;

import com.wjs.common.base.base.BaseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@JsonIgnoreProperties(value = {"member", "attachments", "group",
        "topic", "dynamicCmd", "dynamicAccusations",
        "beginCreateTime", "endCreateTime", "orderByCreateTimeDesc"}, allowSetters = true)
public class DynamicDTO extends BaseDTO {

    /**
     * 待审核
     */
    public static final String STATUS_WAIT_CHECK = "0";
    /**
     * 审核成功
     */
    public static final String STATUS_SUCC = "1";
    /**
     * 拒绝
     */
    public static final String STATUS_REFUSE = "2";
    /**
     * 删帖
     */
    public static final String STATUS_DELETE = "3";

    /**
     * 普通动态
     */
    public static final int TYPE_ORDINARY = 1;
    /**
     * 话题动态
     */
    public static final int TYPE_TOPIC = 2;
    /**
     * 小组动态
     */
    public static final int TYPE_GROUP = 3;
    /**
     * 话题投票
     */
    public static final int TYPE_VOTE = 4;
    /**
     * 会员信息
     */
    private MemberDTO member;
    /**
     * 附件列表
     */
    private AttachmentDTO[] attachments;
    /**
     * 组
     */
    private GroupDTO group;
    /**
     * 话题
     */
    private TopicDTO topic;
    /**
     * 评论数
     */
    private CommentDTO[] comments;
    /**
     * 操作
     */
    private DynamicCmdDTO[] dynamicCmd;
    /**
     * 举报
     */
    private DynamicAccusationDTO[] dynamicAccusations;
    /**
     * 内容
     */
    private String content;
    /**
     * 评论数
     */
    private Integer commentCount;
    /**
     * 阅读数
     */
    private Integer readCount;
    /**
     * 标题
     */
    private String title;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String head;
    /**
     * 小组名称
     */
    private String groupName;
    /**
     * 话题名称
     */
    private String topicName;
    /**
     * 状态值
     */
    private String statusVal;
    /**
     * 动态里面图片
     */
    private String[] attachmentPaths;

    /**
     * 查询创建时间开始
     */
    private Date beginCreateTime;
    /**
     * 查询创建时间结束
     */
    private Date endCreateTime;
    /**
     * true排序
     */
    private Boolean orderByCreateTimeDesc;
    /**
     * 组ID
     */
    private Long groupId;
    /**
     * 话题ID
     */
    private Long topicId;
    /**
     * 置顶
     */
    private Boolean stick;
    /**
     * 精华
     */
    private Boolean essence;

    /**
     * 按照置顶优先,精华其次排序,true正排,false反之
     */
    private Boolean orderByStickEssence;

    private String[] statusArr;

    private Boolean vote = Boolean.TRUE;

    public DynamicDTO() {
    }

}

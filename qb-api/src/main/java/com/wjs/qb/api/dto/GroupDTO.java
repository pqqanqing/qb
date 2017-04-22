package com.wjs.qb.api.dto;

import com.wjs.common.base.base.BaseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@JsonIgnoreProperties(value = {"beginCreateTime", "endCreateTime", "orderByCreateTimeDesc",
        "member", "manager", "attachment", "dynamics"}, allowSetters = true)
public class GroupDTO extends BaseDTO {

    /**
     * 名称
     */
    private String name;
    /**
     * 简介
     */
    private String brief;
    /**
     * logo
     */
    private String logo;
    /**
     * 管理员名称
     */
    private String memberName;
    /**
     * 管理员Id
     */
    private Long managerId;
    /**
     * 管理员头像
     */
    private String memberHead;
    /**
     * 动态数
     */
    private int dynamicCount;

    /**
     * 查询创建时间开始
     */
    private Date beginCreateTime;
    /**
     * 查询创建时间结束
     */
    private Date endCreateTime;
    /**
     * 排序
     */
    private Boolean orderByCreateTimeDesc;
    /**
     * 创建小组人员
     */
    private MemberDTO member;
    /**
     * 管理员
     */
    private MemberDTO manager;
    /**
     * 附件
     */
    private AttachmentDTO attachment;
    /**
     * 小组动态
     */
    private DynamicDTO[] dynamics;

    public GroupDTO() {
    }

}

package com.wjs.qb.api.dto;

import com.wjs.common.base.base.BaseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(value = {"member", "dynamics", "orderByCreateTimeDesc"}, allowSetters = true)
public class TopicDTO extends BaseDTO {

    /**
     * 上架
     */
    public static final String STATUS_PUTAWAY = "0";
    /**
     * 下架
     */
    public static final String STATUS_SOLD_OUT = "1";
    /**
     * 标题
     */
    private String title;
    /**
     * 动态数
     */
    private int dynamicCount;
    /**
     * 发布者
     */
    private String manager;
    /**
     * 状态值
     */
    private String statusVal;

    /**
     * 会员
     */
    private MemberDTO member;
    /**
     * 动态列表
     */
    private DynamicDTO[] dynamics;
    /**
     * 排序
     */
    private Boolean orderByCreateTimeDesc;

    /**
     * 赞成票数
     */
    private Integer approvalNum = 0;

    /**
     * 反对票数
     */
    private Integer opposedNum = 0;

    public TopicDTO() {
    }

}

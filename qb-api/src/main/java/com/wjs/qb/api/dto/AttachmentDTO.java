package com.wjs.qb.api.dto;

import com.wjs.common.base.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AttachmentDTO extends BaseDTO {

    public static final String OWNER_TYPE_DYNAMIC = "dynamic";
    public static final String OWNER_TYPE_GROUP = "group";

    /**
     * logo类
     */
    public static final String CATEGORY_LOGO = "logo";
    /**
     * 贴图
     */
    public static final String CATEGORY_CHARTLET = "chartlet";

    /**
     * 分类
     */
    private String category;
    /**
     * 路径
     */
    private String path;
    private Long ownerId;
    private String ownerType;

    public AttachmentDTO() {
    }
}

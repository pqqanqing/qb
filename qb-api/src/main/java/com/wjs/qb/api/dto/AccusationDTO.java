package com.wjs.qb.api.dto;

import com.wjs.common.base.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AccusationDTO extends BaseDTO {
    /**
     * 举报内容
     */
    private String content;

    public AccusationDTO() {
    }
}

package com.wjs.qb.api.dto;

import com.wjs.common.base.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DynamicCmdDTO extends BaseDTO {
    private MemberDTO member;
    private DynamicDTO dynamic;

    public DynamicCmdDTO() {
    }
}

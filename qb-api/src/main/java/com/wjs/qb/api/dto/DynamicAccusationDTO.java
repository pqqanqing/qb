package com.wjs.qb.api.dto;

import com.wjs.common.base.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DynamicAccusationDTO extends BaseDTO {
    private DynamicDTO dynamic;
    private AccusationDTO accusation;
    private MemberDTO member;

    public DynamicAccusationDTO() {
    }
}

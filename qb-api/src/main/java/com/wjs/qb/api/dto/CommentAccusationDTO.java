package com.wjs.qb.api.dto;

import lombok.Getter;
import lombok.Setter;
import com.wjs.common.base.base.BaseDTO;

@Setter
@Getter
public class CommentAccusationDTO extends BaseDTO{
    private CommentDTO comment;
    private AccusationDTO accusation;
    private MemberDTO member;
    
	public CommentAccusationDTO() {
	}
    
    
}

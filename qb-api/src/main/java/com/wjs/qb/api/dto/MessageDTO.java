package com.wjs.qb.api.dto;

import com.wjs.common.base.base.BaseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(value = {"member", "dynamic"}, allowSetters = true)
public class MessageDTO extends BaseDTO {

    private MemberDTO member;
    private DynamicDTO dynamic;
    private CommentDTO comment;
    private CommentDTO childComment;

    private String head;//头像
    private String nickname;//昵称
    private String dynamicContent;//对动态的内容

    public MessageDTO() {
    }

}

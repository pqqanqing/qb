package com.wjs.qb.domain.dynamiccmd;

import com.wjs.common.base.execption.BusinessExecption;
import com.wjs.qb.api.dto.DynamicDTO;
import com.wjs.qb.domain.dynamic.*;
import com.wjs.qb.domain.member.MemberRpt;
import lombok.Getter;
import lombok.Setter;

import static com.wjs.common.base.spring.SpringBeanContext.getBean;
import static com.wjs.qb.api.dto.DynamicDTO.*;
import static java.util.Objects.isNull;

@Setter
@Getter
public class DynamicCmd4Send extends DynamicCmd {

    private String openId;
    private DynamicDTO dynamicDTO;

    public DynamicCmd4Send() {

    }

    public DynamicCmd4Send(String openId, DynamicDTO dynamicDTO) {
        this.openId = openId;
        this.dynamicDTO = dynamicDTO;
    }

    @Override
    protected void doExec() {
        this.member = getBean(MemberRpt.class).queryByOpenId(openId);
        if (isNull(member)) throw new BusinessExecption("qb13");
        this.dynamic = createDynamic(dynamicDTO);
        this.dynamic.setMember(member);
        getBean(DynamicRpt.class).save(dynamic);
    }

    private Dynamic createDynamic(DynamicDTO dynamicDTO) {
        switch (dynamicDTO.getType()) {
            case TYPE_ORDINARY:
                return new Dynamic4Ordinary(dynamicDTO);
            case TYPE_TOPIC:
                return new Dynamic4Topic(dynamicDTO);
            case TYPE_GROUP:
                return new Dynamic4Group(dynamicDTO);
            case TYPE_VOTE:
                return new Dynamic4Vote(dynamicDTO);
            default:
                throw new BusinessExecption("qb4");
        }
    }
}

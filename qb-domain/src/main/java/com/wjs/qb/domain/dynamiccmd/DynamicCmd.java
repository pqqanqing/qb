package com.wjs.qb.domain.dynamiccmd;

import com.wjs.common.base.base.BaseEntity;
import com.wjs.qb.api.dto.DynamicDTO;
import com.wjs.qb.domain.dynamic.Dynamic;
import com.wjs.qb.domain.member.Member;
import lombok.Getter;
import lombok.Setter;

import static com.wjs.common.base.util.BeanPropertiesUtil.copyProperties;

@Setter
@Getter
public abstract class DynamicCmd extends BaseEntity {
    protected Dynamic dynamic;
    protected Member member;

    public DynamicCmd() {
    }

    public void exec() {
        doExec();
        dynamic.addDynamicCmd(this);
    }

    protected abstract void doExec();

    public DynamicDTO makeDynamicDTO() {
        return copyProperties(dynamic, DynamicDTO.class);
    }
}

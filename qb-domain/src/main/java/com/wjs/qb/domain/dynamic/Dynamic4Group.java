package com.wjs.qb.domain.dynamic;

import com.wjs.qb.api.dto.DynamicDTO;
import com.wjs.qb.domain.group.Group;
import com.wjs.qb.domain.group.GroupRpt;
import lombok.Getter;
import lombok.Setter;

import static com.wjs.common.base.spring.SpringBeanContext.getBean;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Setter
@Getter
public class Dynamic4Group extends Dynamic {
    private Group group;

    public Dynamic4Group() {
    }

    public Dynamic4Group(DynamicDTO dynamicDTO) {
        super(dynamicDTO);
        this.group = getBean(GroupRpt.class).getById(dynamicDTO.getGroup().getId());
        group.addDynamic(this);
    }

    @Override
    public void stick() {
        Dynamic dynamic = getBean(DynamicRpt.class).queryByStick(group);
        if (nonNull(dynamic) && dynamic.stick) dynamic.unStick();
    }

    public String getGroupName() {
        if (isNull(group)) return null;
        return group.getName();
    }

    public Long getGroupId() {
        if (isNull(group)) return null;
        return group.getId();
    }

}

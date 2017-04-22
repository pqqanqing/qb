package com.wjs.qb.domain.dynamic;

import com.wjs.qb.api.dto.DynamicDTO;
import lombok.Getter;
import lombok.Setter;

import static com.wjs.common.base.spring.SpringBeanContext.getBean;
import static com.wjs.qb.api.dto.DynamicDTO.TYPE_ORDINARY;
import static java.util.Objects.nonNull;

@Setter
@Getter
public class Dynamic4Ordinary extends Dynamic {

    public Dynamic4Ordinary() {
    }

    public Dynamic4Ordinary(DynamicDTO dynamicDTO) {
        super(dynamicDTO);
    }

    @Override
    public void stick() {
        Dynamic dynamic = getBean(DynamicRpt.class).queryByStick(TYPE_ORDINARY);
        if (nonNull(dynamic) && dynamic.stick) dynamic.unStick();
    }
}

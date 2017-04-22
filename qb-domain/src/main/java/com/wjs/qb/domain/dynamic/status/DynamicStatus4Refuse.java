package com.wjs.qb.domain.dynamic.status;


import com.wjs.common.base.annotation.StatusAndClassNum;
import com.wjs.qb.domain.dynamic.Dynamic;

import static com.wjs.qb.api.dto.DynamicDTO.STATUS_REFUSE;

@StatusAndClassNum(superClass = DynamicStatus.class, number = STATUS_REFUSE, describe = "拒绝", parasitClass = Dynamic.class)
public class DynamicStatus4Refuse extends DynamicStatus {
    private static DynamicStatus instance = new DynamicStatus4Refuse();

    private DynamicStatus4Refuse() {
    }

    public static DynamicStatus getInstance() {
        return instance;
    }
}

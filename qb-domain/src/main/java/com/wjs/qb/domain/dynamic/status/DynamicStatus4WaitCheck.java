package com.wjs.qb.domain.dynamic.status;


import com.wjs.common.base.annotation.StatusAndClassNum;
import com.wjs.qb.domain.dynamic.Dynamic;

import static com.wjs.qb.api.dto.DynamicDTO.STATUS_WAIT_CHECK;


@StatusAndClassNum(superClass = DynamicStatus.class, number = STATUS_WAIT_CHECK, describe = "待审核", parasitClass = Dynamic.class)
public class DynamicStatus4WaitCheck extends DynamicStatus {
    private static DynamicStatus instance = new DynamicStatus4WaitCheck();

    private DynamicStatus4WaitCheck() {
    }

    public static DynamicStatus getInstance() {
        return instance;
    }

    @Override
    public DynamicStatus succ() {
        return DynamicStatus4Succ.getInstance();
    }

    @Override
    public DynamicStatus refuse() {
        return DynamicStatus4Refuse.getInstance();
    }

    @Override
    public boolean isCheck() {
        return true;
    }
}

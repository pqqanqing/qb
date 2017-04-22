package com.wjs.qb.domain.dynamic.status;


import com.wjs.common.base.annotation.StatusAndClassNum;
import com.wjs.qb.domain.dynamic.Dynamic;

import static com.wjs.qb.api.dto.DynamicDTO.STATUS_SUCC;

@StatusAndClassNum(superClass = DynamicStatus.class, number = STATUS_SUCC, describe = "发布成功", parasitClass = Dynamic.class)
public class DynamicStatus4Succ extends DynamicStatus {
    private static DynamicStatus instance = new DynamicStatus4Succ();

    private DynamicStatus4Succ() {
    }

    public static DynamicStatus getInstance() {
        return instance;
    }

    @Override
    public DynamicStatus delete() {
        return DynamicStatus4Delete.getInstance();
    }

    @Override
    public boolean canDelete() {
        return true;
    }

    @Override
    public boolean canComment() {
        return true;
    }
}

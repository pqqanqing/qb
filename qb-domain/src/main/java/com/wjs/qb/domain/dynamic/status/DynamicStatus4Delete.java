package com.wjs.qb.domain.dynamic.status;


import com.wjs.common.base.annotation.StatusAndClassNum;
import com.wjs.qb.api.dto.DynamicDTO;
import com.wjs.qb.domain.dynamic.Dynamic;


@StatusAndClassNum(superClass = DynamicStatus.class, number = DynamicDTO.STATUS_DELETE, describe = "被删帖", parasitClass = Dynamic.class)
public class DynamicStatus4Delete extends DynamicStatus {
    private static DynamicStatus instance = new DynamicStatus4Delete();

    private DynamicStatus4Delete() {
    }

    public static DynamicStatus getInstance() {
        return instance;
    }

    @Override
    public DynamicStatus succ() {
        return DynamicStatus4Succ.getInstance();
    }

    @Override
    public boolean canRecover() {
        return true;
    }
}

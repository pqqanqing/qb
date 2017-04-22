package com.wjs.qb.domain.dynamic.status;

public abstract class DynamicStatus {

    public DynamicStatus waitCheck() {
        throw new RuntimeException("动态状态【" + this + "】不可以转化为待审核操作！");
    }

    public DynamicStatus succ() {
        throw new RuntimeException("动态状态【" + this + "】不可以转化为成功操作！");
    }

    public DynamicStatus refuse() {
        throw new RuntimeException("动态状态【" + this + "】不可以转化为拒绝操作！");
    }

    public DynamicStatus delete() {
        throw new RuntimeException("动态状态【" + this + "】不可以转化为被删除操作！");
    }

    public boolean canDelete() {
        return false;
    }

    public boolean isCheck() {
        return false;
    }

    public boolean canComment() {
        return false;
    }

    public boolean canRecover() {
        return false;
    }
}


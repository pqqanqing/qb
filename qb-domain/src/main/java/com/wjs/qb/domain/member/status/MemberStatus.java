package com.wjs.qb.domain.member.status;

public abstract class MemberStatus {

    public MemberStatus normal() {
        throw new RuntimeException("会员状态【" + this + "】不可以转化为非禁言操作！");
    }

    public MemberStatus shutUp() {
        throw new RuntimeException("会员状态【" + this + "】不可以转化为禁言操作！");
    }

    public boolean canPublishComment(){return false;}

}

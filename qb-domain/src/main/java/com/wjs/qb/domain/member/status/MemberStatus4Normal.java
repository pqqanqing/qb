package com.wjs.qb.domain.member.status;

import com.wjs.common.base.annotation.StatusAndClassNum;
import com.wjs.qb.domain.member.Member;

import static com.wjs.qb.api.dto.MemberDTO.NORMAL;

@StatusAndClassNum(superClass = MemberStatus.class, number = NORMAL, describe = "正常用户",parasitClass = Member.class)
public class MemberStatus4Normal extends MemberStatus{
    private static MemberStatus instance = new MemberStatus4Normal();
    public MemberStatus4Normal() {
    }

    public static MemberStatus getInstance() {return instance;}

    @Override
    public MemberStatus shutUp() {
        return MemberStatus4ShutUp.getInstance();
    }

    @Override
    public boolean canPublishComment() {
        return true;
    }
}

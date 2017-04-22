package com.wjs.qb.domain.member.status;

import com.wjs.common.base.annotation.StatusAndClassNum;
import com.wjs.qb.domain.member.Member;

import static com.wjs.qb.api.dto.MemberDTO.SHUT_UP;

@StatusAndClassNum(superClass = MemberStatus.class, number = SHUT_UP, describe = "禁言用户",parasitClass = Member.class)
public class MemberStatus4ShutUp extends MemberStatus{
    private static MemberStatus instance = new MemberStatus4ShutUp();

    public MemberStatus4ShutUp() {
    }

    public static MemberStatus getInstance() {return instance;}

    @Override
    public MemberStatus normal() {
        return MemberStatus4Normal.getInstance();
    }
}

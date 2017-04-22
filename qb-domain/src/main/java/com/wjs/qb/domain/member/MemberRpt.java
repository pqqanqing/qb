package com.wjs.qb.domain.member;

import com.wjs.common.base.rpt.BaseRpt;

import java.io.Serializable;

public interface MemberRpt extends BaseRpt<Member, Serializable> {
    Member queryByOpenId(String openId);
    
    Member queryByMemberId(String memberId);
}

package com.wjs.qb.service.member;

import com.wjs.common.base.base.BasePageResults;
import com.wjs.qb.api.dto.MemberDTO;

public interface MemberService {

    MemberDTO create(MemberDTO memberDTO);

    BasePageResults<MemberDTO> query(MemberDTO memberDTO);

    MemberDTO shutup(String openId);

    MemberDTO cancelShutup(String openId);
}

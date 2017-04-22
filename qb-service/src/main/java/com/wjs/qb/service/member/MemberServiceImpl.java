package com.wjs.qb.service.member;


import com.wjs.common.base.base.BasePageResults;
import com.wjs.common.base.execption.BusinessExecption;
import com.wjs.common.base.query.QueryService;
import com.wjs.qb.api.dto.MemberDTO;
import com.wjs.qb.domain.member.Member;
import com.wjs.qb.domain.member.MemberRpt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRpt memberRpt;
    @Autowired
    private QueryService queryService;

    @Override
    public MemberDTO create(MemberDTO memberDTO) {
        if (isNull(memberDTO.getOpenId())) {
            throw new BusinessExecption("openId不能为空!");
        }
        Member member = memberRpt.queryByOpenId(memberDTO.getOpenId());
        if (nonNull(member)) return member.makeDTO();
        Member newMember = new Member(memberDTO);
        memberRpt.save(newMember);

        return newMember.makeDTO();
    }

    @Override
    public MemberDTO shutup(String openId) {
        Member member = memberRpt.queryByOpenId(openId);
        member.shutup();
        return member.makeDTO();
    }

    @Override
    public MemberDTO cancelShutup(String openId) {
        Member member = memberRpt.queryByOpenId(openId);
        member.cancelShutup();
        return member.makeDTO();
    }

    @Override
    public BasePageResults<MemberDTO> query(MemberDTO memberDTO) {
        BasePageResults<MemberDTO> memberDTOBasePageResults = queryService.queryDTO(memberDTO);
        return memberDTOBasePageResults;
    }
}

package com.wjs.qb.service.dynamic;

import com.wjs.common.base.base.BasePageResults;
import com.wjs.common.base.execption.BusinessExecption;
import com.wjs.common.base.query.QueryService;
import com.wjs.qb.api.dto.DynamicDTO;
import com.wjs.qb.domain.accusation.Accusation;
import com.wjs.qb.domain.accusation.AccusationRpt;
import com.wjs.qb.domain.dynamic.Dynamic;
import com.wjs.qb.domain.dynamic.DynamicRpt;
import com.wjs.qb.domain.dynamiccmd.*;
import com.wjs.qb.domain.member.Member;
import com.wjs.qb.domain.member.MemberRpt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Service
@Transactional
public class DynamicServiceImpl implements DynamicService {

    @Autowired
    private DynamicRpt dynamicRpt;

    @Autowired
    private MemberRpt memberRpt;

    @Autowired
    private AccusationRpt accusationRpt;

    @Autowired
    private QueryService queryService;

    @Override
    public DynamicDTO sendDynamic(String openId, DynamicDTO dynamicDTO) {
        if (isNull(dynamicDTO.getType())) throw new BusinessExecption("qb3");
        if (isBlank(dynamicDTO.getTitle())) throw new BusinessExecption("qb14");
        Member member = memberRpt.queryByOpenId(openId);
        // 校验话题投票的用户唯一性
        List dynamics = dynamicRpt.queryByMember4TopicVote(member.getId(), dynamicDTO.getTopic().getId());
        if(!CollectionUtils.isEmpty(dynamics)) {
            throw new BusinessExecption("qb21");
        }
        DynamicCmd dynamicCmd = new DynamicCmd4Send(openId, dynamicDTO);
        dynamicCmd.exec();
        return dynamicCmd.makeDynamicDTO();
    }

    @Override
    public DynamicDTO checkDynamic(Long id, String memberId, boolean flag) {
        Dynamic dynamic = dynamicRpt.getById(id);
        DynamicCmd dynamicCmd = new DynamicCmd4Check(memberId, dynamic, flag);
        dynamicCmd.exec();
        return dynamicCmd.makeDynamicDTO();
    }

    @Override
    public DynamicDTO deleteDynamic(Long id, String memberId) {
        Dynamic dynamic = dynamicRpt.getById(id);
        DynamicCmd dynamicCmd = new DynamicCmd4Delete(memberId, dynamic);
        dynamicCmd.exec();
        return dynamicCmd.makeDynamicDTO();
    }

    @Override
    public DynamicDTO recoverDynamic(Long id, String memberId) {
        Dynamic dynamic = dynamicRpt.getById(id);
        DynamicCmd dynamicCmd = new DynamicCmd4Recover(memberId, dynamic);
        dynamicCmd.exec();
        return dynamicCmd.makeDynamicDTO();
    }

    @Override
    public DynamicDTO addReadCount(Long id) {
        Dynamic dynamic = dynamicRpt.getById(id);
        dynamic.addReadCount();
        return dynamic.makeDTO();
    }

    @Override
    public DynamicDTO accusation(Long id, String openId, Long accusationId) {
        Dynamic dynamic = dynamicRpt.getById(id);
        Member member = memberRpt.queryByOpenId(openId);
        if (isNull(member)) throw new BusinessExecption("qb13");
        Accusation accusation = accusationRpt.getById(accusationId);
        if (isNull(accusation)) throw new BusinessExecption("qb15");
        dynamic.accusation(member, accusation);
        return dynamic.makeDTO();
    }

    @Override
    public DynamicDTO cancelAccusation(Long id) {
        Dynamic dynamic = dynamicRpt.getById(id);
        dynamic.cancelAccusation();
        return dynamic.makeDTO();
    }

    @Override
    public BasePageResults<DynamicDTO> query(DynamicDTO dynamicDTO) {
        BasePageResults<DynamicDTO> dynamicDTOBasePageResults = queryService.queryDTO(dynamicDTO);
        return dynamicDTOBasePageResults;
    }

    @Override
    public void delete(Long id) {
        dynamicRpt.deleteById(id);
    }

    @Override
    public List<DynamicDTO> queryByMemberId(String memberId) {
        Member member = memberRpt.queryByMemberId(memberId);
        List<Dynamic> dynamicList = dynamicRpt.queryByMember(member);
        List<DynamicDTO> dtoList = new ArrayList<>();
        for (Dynamic dynamic : dynamicList) {
            DynamicDTO dto = dynamic.makeDTO();
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public DynamicDTO stick(Long id) {
        Dynamic dynamic = dynamicRpt.getById(id);
        dynamic.doStick();
        return dynamic.makeDTO();
    }

    @Override
    public DynamicDTO essence(Long id) {
        Dynamic dynamic = dynamicRpt.getById(id);
        dynamic.essence();
        return dynamic.makeDTO();
    }

    @Override
    public DynamicDTO unessence(Long id) {
        Dynamic dynamic = dynamicRpt.getById(id);
        dynamic.unessence();
        return dynamic.makeDTO();
    }

    @Override
    public DynamicDTO sendDynamic4Manager(String openId, DynamicDTO dynamic) {
        // 发布状态
        DynamicDTO dynamicDTO = sendDynamic(openId, dynamic);
        Member member = memberRpt.queryByOpenId(openId);
        // 审核
        dynamicDTO = checkDynamic(dynamicDTO.getId(), member.getMemberId(), true);
        return dynamicDTO;
    }
}

package com.wjs.qb.service.group;

import com.wjs.common.base.base.BasePageResults;
import com.wjs.common.base.execption.BusinessExecption;
import com.wjs.common.base.query.QueryService;
import com.wjs.qb.api.dto.GroupDTO;
import com.wjs.qb.domain.dynamic.Dynamic;
import com.wjs.qb.domain.dynamic.DynamicRpt;
import com.wjs.qb.domain.group.Group;
import com.wjs.qb.domain.group.GroupRpt;
import com.wjs.qb.domain.member.Member;
import com.wjs.qb.domain.member.MemberRpt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Objects.isNull;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRpt groupRpt;

    @Autowired
    private MemberRpt memberRpt;

    @Autowired
    private DynamicRpt dynamicRpt;

    @Autowired
    private QueryService queryService;


    @Override
    public GroupDTO createOrModify(String memberId, GroupDTO groupDTO) {
        Member member = memberRpt.queryByMemberId(memberId);
        if (isNull(member)) throw new BusinessExecption("qb13");
        Group group = isNull(groupDTO.getId()) ? member.createGroup(groupDTO) : member.modifyGroup(groupDTO);
        groupRpt.saveOrUpdate(group);
        return group.makeDTO();
    }

    @Override
    public void delete(String memberId, Long id) {
        Member member = memberRpt.queryByMemberId(memberId);
        if (isNull(member)) throw new BusinessExecption("qb13");
        Group group = groupRpt.getById(id);
        List<Dynamic> dynamics = dynamicRpt.queryByGroup(group);
        dynamicRpt.deleteAll(dynamics);
        member.deleteGroup(group);
    }

    @Override
    public BasePageResults<GroupDTO> query(GroupDTO groupDTO) {
        BasePageResults<GroupDTO> groupDTOBasePageResults = queryService.queryDTO(groupDTO);
        return groupDTOBasePageResults;
    }
}

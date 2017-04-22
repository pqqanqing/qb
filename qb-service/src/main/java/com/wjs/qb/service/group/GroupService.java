package com.wjs.qb.service.group;

import com.wjs.common.base.base.BasePageResults;
import com.wjs.qb.api.dto.GroupDTO;

public interface GroupService {

    GroupDTO createOrModify(String memberId, GroupDTO groupDTO);

    void delete(String memberId, Long id);

    BasePageResults<GroupDTO> query(GroupDTO groupDTO);
}

package com.wjs.qb.service.dynamic;

import com.wjs.common.base.base.BasePageResults;
import com.wjs.qb.api.dto.DynamicDTO;
import java.util.List;

public interface DynamicService {

    DynamicDTO sendDynamic(String openId, DynamicDTO dynamicDTO);

    DynamicDTO checkDynamic(Long id, String memberId, boolean flag);

    DynamicDTO deleteDynamic(Long id, String memberId);

    DynamicDTO recoverDynamic(Long id, String memberId);

    DynamicDTO addReadCount(Long id);

    DynamicDTO accusation(Long id, String openId, Long accusationId);

    DynamicDTO cancelAccusation(Long id);

    BasePageResults<DynamicDTO> query(DynamicDTO dynamicDTO);

    void delete(Long id);

    /** 查询memberId对应的会员所有的被举报的动态 */
    List<DynamicDTO> queryByMemberId(String memberId);

    DynamicDTO stick(Long id);

    DynamicDTO essence(Long id);

    DynamicDTO unessence(Long id);

    DynamicDTO sendDynamic4Manager(String openId, DynamicDTO dynamicDTO);

}

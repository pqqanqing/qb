package com.wjs.qb.service.accusation;

import com.wjs.common.base.base.BasePageResults;
import com.wjs.qb.api.dto.AccusationDTO;

public interface AccusationService {

    BasePageResults<AccusationDTO> query();
}

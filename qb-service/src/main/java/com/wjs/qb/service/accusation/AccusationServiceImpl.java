package com.wjs.qb.service.accusation;

import com.wjs.common.base.base.BasePageResults;
import com.wjs.common.base.query.QueryService;
import com.wjs.qb.api.dto.AccusationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccusationServiceImpl implements AccusationService {

    @Autowired
    private QueryService queryService;

    @Override
    public BasePageResults<AccusationDTO> query() {
        AccusationDTO accusationDTO = new AccusationDTO();
        BasePageResults<AccusationDTO> accusationDTOBasePageResults = queryService.queryDTO(accusationDTO);
        return accusationDTOBasePageResults;
    }
}

package com.wjs.qb.service.message;

import com.wjs.common.base.base.BasePageResults;
import com.wjs.qb.api.dto.MessageDTO;

public interface MessageService {

    void delete(Long id);

    void deleteAll(Long memberId);

    BasePageResults<MessageDTO> query(MessageDTO messageDTO);
}

package com.wjs.qb.service.message;


import com.wjs.common.base.base.BasePageResults;
import com.wjs.common.base.query.QueryService;
import com.wjs.qb.api.dto.MessageDTO;
import com.wjs.qb.domain.message.Message;
import com.wjs.qb.domain.message.MessageRpt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRpt messageRpt;
    @Autowired
    private QueryService queryService;

    @Override
    public void delete(Long id) {
        Message message = messageRpt.getById(id);
        message.delete();
    }

    @Override
    public void deleteAll(Long memberId) {
        List<Message> messages = messageRpt.queryByMemberId(memberId);
        messages.stream().forEach(message -> {
            message.delete();
        });
    }

    @Override
    public BasePageResults<MessageDTO> query(MessageDTO messageDTO) {
        BasePageResults<MessageDTO> messageDTOBasePageResults = queryService.queryDTO(messageDTO);
        return messageDTOBasePageResults;
    }
}

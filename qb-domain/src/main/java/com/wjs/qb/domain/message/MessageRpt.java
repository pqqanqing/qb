package com.wjs.qb.domain.message;

import com.wjs.common.base.rpt.BaseRpt;

import java.io.Serializable;
import java.util.List;

public interface MessageRpt extends BaseRpt<Message, Serializable> {
    List<Message> queryByMemberId(Long memberId);
}

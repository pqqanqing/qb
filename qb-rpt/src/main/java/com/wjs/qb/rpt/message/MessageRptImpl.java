package com.wjs.qb.rpt.message;

import com.wjs.common.base.rpt.BaseRptImpl;
import com.wjs.qb.domain.message.Message;
import com.wjs.qb.domain.message.MessageRpt;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public class MessageRptImpl extends BaseRptImpl<Message, Serializable> implements MessageRpt {
    @Override
    public List<Message> queryByMemberId(Long memberId) {
        String hql = "from Message m where m.member.id = :memberId";
        Query query = getSession().createQuery(hql);
        query.setParameter("memberId", memberId);
        return query.list();
    }
}

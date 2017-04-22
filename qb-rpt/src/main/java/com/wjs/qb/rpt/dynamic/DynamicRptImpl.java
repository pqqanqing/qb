package com.wjs.qb.rpt.dynamic;

import com.wjs.common.base.rpt.BaseRptImpl;
import com.wjs.qb.domain.dynamic.Dynamic;
import com.wjs.qb.domain.dynamic.Dynamic4Topic;
import com.wjs.qb.domain.dynamic.DynamicRpt;
import com.wjs.qb.domain.group.Group;
import com.wjs.qb.domain.member.Member;
import com.wjs.qb.domain.topic.Topic;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;

@Repository
public class DynamicRptImpl extends BaseRptImpl<Dynamic, Serializable> implements DynamicRpt {
    @Override
    public List<Dynamic> queryByGroup(Group group) {
        Query query = getSession().createQuery("from Dynamic d where d.group = :group");
        query.setParameter("group", group);
        return query.list();
    }

    @Override
    public List<Dynamic> queryByTopic(Topic topic) {
        Query query = getSession().createQuery("from Dynamic d where d.topic = :topic");
        query.setParameter("topic", topic);
        return query.list();
    }

    @Override
    public List<Dynamic> queryByMember(Member member) {
        Query query = getSession().createQuery("from Dynamic d left join fetch d.dynamicAccusations da where da is not null and d.member = :member");
        query.setParameter("member", member);
        return query.list();
    }

    @Override
    public Dynamic queryByStick(int type) {
        Query query = getSession().createQuery("from Dynamic d where d.type = :type and d.stick = :stick");
        query.setParameter("type", type);
        query.setParameter("stick", true);
        return (Dynamic) query.uniqueResult();
    }

    @Override
    public Dynamic queryByStick(Group group) {
        Query query = getSession().createQuery("from Dynamic d where d.group = :group and d.stick = :stick");
        query.setParameter("group", group);
        query.setParameter("stick", true);
        return (Dynamic) query.uniqueResult();
    }

    @Override
    public Integer queryApprovalNum(Topic topic) {
        Query query = getSession().createQuery("from Dynamic d where d.type = 4 and d.vote = 1 and d.topic = :topic");
        query.setParameter("topic", topic);
        List<Dynamic> dynamics = query.list();
        if(CollectionUtils.isEmpty(dynamics)) {
            return 0;
        }
        return dynamics.size();
    }

    @Override
    public Integer queryOpposedNum(Topic topic) {
        Query query = getSession().createQuery("from Dynamic d where d.type = 4 and d.vote = 0 and d.topic = :topic");
        query.setParameter("topic", topic);
        List<Dynamic> dynamics = query.list();
        if(CollectionUtils.isEmpty(dynamics)) {
            return 0;
        }
        return dynamics.size();
    }

    @Override
    public List<Dynamic> queryByMember4TopicVote(Long memberId, Long topicId) {
        Query query = getSession().createQuery("FROM Dynamic d where d.member.id = :memberId AND d.voteTopic.id = :topicId AND d.type = 4");
        query.setParameter("memberId", memberId);
        query.setParameter("topicId", topicId);
        return query.list();
    }
}

package com.wjs.qb.rpt.topic;

import com.wjs.common.base.rpt.BaseRptImpl;
import com.wjs.qb.domain.topic.Topic;
import com.wjs.qb.domain.topic.TopicRpt;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public class TopicRptImpl extends BaseRptImpl<Topic, Serializable> implements TopicRpt {
    @Override
    public List<Topic> query4ThisWeek(String date) {
        Query query = getSession().createQuery("from Topic t where str(t.createTime) >= :date");
        query.setParameter("date", date);
        return query.list();
    }

    @Override
    public Topic queryLatestTopic() {
        Query query = getSession().createQuery("from Topic t order by t.createTime desc");
        query.setFirstResult(0);
        query.setMaxResults(1);
        return (Topic) query.uniqueResult();
    }
}

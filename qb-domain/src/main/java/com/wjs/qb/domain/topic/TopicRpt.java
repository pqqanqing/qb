package com.wjs.qb.domain.topic;

import com.wjs.common.base.rpt.BaseRpt;
import java.io.Serializable;
import java.util.List;

public interface TopicRpt extends BaseRpt<Topic, Serializable> {
    /**
     * 获取本周创建的话题
     * @return
     */
    List<Topic> query4ThisWeek(String date);

    /**
     * 获取最新的一条话题
     * @return
     */
    Topic queryLatestTopic();
}

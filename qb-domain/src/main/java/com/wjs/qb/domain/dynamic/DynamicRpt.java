package com.wjs.qb.domain.dynamic;

import com.wjs.common.base.rpt.BaseRpt;
import com.wjs.qb.domain.group.Group;
import com.wjs.qb.domain.member.Member;
import com.wjs.qb.domain.topic.Topic;

import java.io.Serializable;
import java.util.List;

public interface DynamicRpt extends BaseRpt<Dynamic, Serializable> {
    List<Dynamic> queryByGroup(Group group);

    List<Dynamic> queryByTopic(Topic topic);

    List<Dynamic> queryByMember(Member member);

    Dynamic queryByStick(int type);

    Dynamic queryByStick(Group group);

    List<Dynamic> queryByMember4TopicVote(Long memberId, Long topicId);

    /**
     * 查询某话题的赞成总票数
     * @param topic
     * @return
     */
    Integer queryApprovalNum(Topic topic);

    /**
     * 查询某话题的反对总票数
     * @param topic
     * @return
     */
    Integer queryOpposedNum(Topic topic);
}

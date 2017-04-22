package com.wjs.qb.service.topic;

import com.wjs.common.base.base.BasePageResults;
import com.wjs.common.base.execption.BusinessExecption;
import com.wjs.common.base.query.QueryService;
import com.wjs.qb.api.dto.TopicDTO;
import com.wjs.qb.common.util.DateUtil;
import com.wjs.qb.domain.dynamic.Dynamic;
import com.wjs.qb.domain.dynamic.DynamicRpt;
import com.wjs.qb.domain.member.Member;
import com.wjs.qb.domain.member.MemberRpt;
import com.wjs.qb.domain.topic.Topic;
import com.wjs.qb.domain.topic.TopicRpt;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static java.util.Objects.isNull;

@Service
@Transactional
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicRpt topicRpt;

    @Autowired
    private MemberRpt memberRpt;

    @Autowired
    private DynamicRpt dynamicRpt;

    @Autowired
    private QueryService queryService;

    @Override
    public TopicDTO create(String memberId, TopicDTO topicDTO) {
        Member member = memberRpt.queryByMemberId(memberId);
        if (isNull(member)) throw new BusinessExecption("qb13");
        // 如果本周有新增话题，则提示不许增加
        List<Topic> topics = topicRpt.query4ThisWeek(DateUtil.getMonday());
        if (!CollectionUtils.isEmpty(topics)) {
            throw new BusinessExecption("qb20");
        }
        Topic topic = member.createTopic(topicDTO);
        topicRpt.save(topic);
        return topic.makeDTO();
    }

    @Override
    public TopicDTO soldOut(Long topicId) {
        // 下架话题
        Topic topic = topicRpt.getById(topicId);
        List<Dynamic> dynamicList = dynamicRpt.queryByTopic(topic);
        dynamicList.stream().forEach(dynamic -> {
            dynamic.setLogicDelete(true);
        });
        topic.soldOut();
        return topic.makeDTO();
    }

    @Override
    public BasePageResults<TopicDTO> query(TopicDTO topicDTO) {
        BasePageResults<TopicDTO> topicDTOBasePageResults = queryService.queryDTO(topicDTO);
        return topicDTOBasePageResults;
    }

    @Override
    public TopicDTO queryLatestTopic() {
        Topic topic = topicRpt.queryLatestTopic();
        if (topic != null) {
            TopicDTO topicDTO = topic.makeDTO();
            Integer approvalNum = queryApprovalNum(topic);
            Integer opposedNum = queryOpposedNum(topic);
            topicDTO.setApprovalNum(approvalNum);
            topicDTO.setOpposedNum(opposedNum);
            return topicDTO;
        }
        return null;
    }

    /**
     * 统计该话题的赞成票数
     * @param topic
     */
    private Integer queryApprovalNum(Topic topic) {
        //查询对该话题投赞成票的动态的条数
        return dynamicRpt.queryApprovalNum(topic);
    }

    /**
     * 统计该话题的反对票数
     * @param topic
     */
    private Integer queryOpposedNum(Topic topic) {
        //查询对该话题投反对票的动态的条数
        return dynamicRpt.queryOpposedNum(topic);
    }
}

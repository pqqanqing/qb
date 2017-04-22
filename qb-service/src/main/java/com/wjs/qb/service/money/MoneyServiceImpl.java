package com.wjs.qb.service.money;

import com.wjs.common.base.base.BasePageResults;
import com.wjs.common.base.query.QueryService;
import com.wjs.qb.api.dto.DynamicDTO;
import com.wjs.qb.api.dto.GroupDTO;
import com.wjs.qb.api.dto.MoneyDTO;
import com.wjs.qb.api.dto.TopicDTO;
import com.wjs.qb.service.dynamic.DynamicService;
import com.wjs.qb.service.group.GroupService;
import com.wjs.qb.service.topic.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.wjs.qb.api.dto.TopicDTO.STATUS_PUTAWAY;

@Service
@Transactional
public class MoneyServiceImpl implements MoneyService {

    @Autowired
    private QueryService queryService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private DynamicService dynamicService;

    @Override
    public MoneyDTO queryMoney() {
        MoneyDTO moneyDTO = new MoneyDTO();

        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setOrderByCreateTimeDesc(true);
        BasePageResults basePageResults = new BasePageResults();
        basePageResults.setPageNo(1);
        basePageResults.setPageSize(4);
        groupDTO.setBasePageResults(basePageResults);
        List<GroupDTO> groupList = groupService.query(groupDTO).getResults();
        moneyDTO.setGroupList(groupList);

        TopicDTO topicDTO = new TopicDTO();
        topicDTO.setStatusVal(STATUS_PUTAWAY);
        topicDTO.setOrderByCreateTimeDesc(true);
        BasePageResults basePageResults2 = new BasePageResults();
        basePageResults2.setPageNo(1);
        basePageResults2.setPageSize(3);
        topicDTO.setBasePageResults(basePageResults2);
        List<TopicDTO> topicList = topicService.query(topicDTO).getResults();
        moneyDTO.setTopicList(topicList);

        DynamicDTO dynamicDTO = new DynamicDTO();
        dynamicDTO.setOrderByCreateTimeDesc(true);
        BasePageResults basePageResults3 = new BasePageResults();
        basePageResults3.setPageNo(1);
        basePageResults3.setPageSize(5);
        dynamicDTO.setBasePageResults(basePageResults3);
        dynamicDTO.setStatusVal(DynamicDTO.STATUS_SUCC);
        List<DynamicDTO> dynamicList = dynamicService.query(dynamicDTO).getResults();
        moneyDTO.setDynamicList(dynamicList);
        return moneyDTO;
    }
}

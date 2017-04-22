package com.wjs.qb.service.test;

import com.wjs.common.base.base.BasePageResults;
import com.wjs.common.base.query.QueryService;
import com.wjs.qb.api.dto.DynamicDTO;
import com.wjs.qb.service.dynamic.DynamicService;
import org.hibernate.Query;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class DynamicServiceImplTest {

    @Autowired
    private DynamicService dynamicService;

    @Autowired
    private QueryService queryService;

    @Test
    public void testStick() {
        DynamicDTO dynamicDTO = dynamicService.stick(4L);
        System.out.println(dynamicDTO);
    }

    @Test
    public void testEssence() {
        DynamicDTO dynamicDTO = dynamicService.essence(2L);
        System.out.println(dynamicDTO);
    }

    @Test
    public void testQueryDynamic4Vote() {
        DynamicDTO dynamicDTO = new DynamicDTO();
        dynamicDTO.setId(1l);
        dynamicDTO.setType(1);
        dynamicDTO.setStatusVal("1");
        dynamicDTO.setOrderByStickEssence(true);
        BasePageResults basePageResults = new BasePageResults();
        basePageResults.setPageSize(5);
        basePageResults.setPageNo(1);
        dynamicDTO.setBasePageResults(basePageResults);
        BasePageResults<DynamicDTO> dynamicDTOBasePageResults =  queryService.queryDTO(dynamicDTO);
        System.out.println(dynamicDTOBasePageResults);
    }

}

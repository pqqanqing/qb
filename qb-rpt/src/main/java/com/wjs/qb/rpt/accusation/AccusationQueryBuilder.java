package com.wjs.qb.rpt.accusation;


import com.wjs.common.base.base.BaseQueryBuilder;
import com.wjs.qb.domain.accusation.Accusation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("accusationQueryBuilder")
@Scope("prototype")
public class AccusationQueryBuilder extends BaseQueryBuilder<Accusation> {

}

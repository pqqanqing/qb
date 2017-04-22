package com.wjs.qb.rpt.accusation;

import com.wjs.common.base.rpt.BaseRptImpl;
import com.wjs.qb.domain.accusation.Accusation;
import com.wjs.qb.domain.accusation.AccusationRpt;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class AccusationRptImpl extends BaseRptImpl<Accusation, Serializable> implements AccusationRpt {

}

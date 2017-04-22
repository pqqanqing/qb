package com.wjs.qb.rpt.group;

import com.wjs.common.base.rpt.BaseRptImpl;
import com.wjs.qb.domain.group.Group;
import com.wjs.qb.domain.group.GroupRpt;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class GroupRptImpl extends BaseRptImpl<Group, Serializable> implements GroupRpt {
}

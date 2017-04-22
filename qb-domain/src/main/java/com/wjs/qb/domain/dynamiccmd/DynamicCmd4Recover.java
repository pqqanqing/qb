package com.wjs.qb.domain.dynamiccmd;

import com.wjs.common.base.execption.BusinessExecption;
import com.wjs.qb.domain.dynamic.Dynamic;
import com.wjs.qb.domain.member.MemberRpt;
import lombok.Getter;
import lombok.Setter;

import static com.wjs.common.base.spring.SpringBeanContext.getBean;
import static java.util.Objects.isNull;

@Setter
@Getter
public class DynamicCmd4Recover extends DynamicCmd {

    private String memberId;

    public DynamicCmd4Recover() {
    }

    public DynamicCmd4Recover(String memberId, Dynamic dynamic) {
        this.memberId = memberId;
        this.member = getBean(MemberRpt.class).queryByMemberId(memberId);
        if (isNull(member)) throw new BusinessExecption("qb13");
        this.dynamic = dynamic;
    }

    @Override
    protected void doExec() {
        dynamic.recover();
    }
}

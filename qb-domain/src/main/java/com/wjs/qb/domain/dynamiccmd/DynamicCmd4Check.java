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
public class DynamicCmd4Check extends DynamicCmd {

    private String memberId;
    private boolean flag;

    public DynamicCmd4Check() {
    }

    public DynamicCmd4Check(String memberId, Dynamic dynamic, boolean flag) {
        this.memberId = memberId;
        this.member = getBean(MemberRpt.class).queryByMemberId(memberId);
        if (isNull(member)) throw new BusinessExecption("qb13");
        this.dynamic = dynamic;
        this.flag = flag;
    }

    @Override
    protected void doExec() {
        dynamic.check(flag);
    }
}

package com.wjs.qb.domain.daccusation;

import com.wjs.common.base.base.BaseEntity;
import com.wjs.qb.domain.accusation.Accusation;
import com.wjs.qb.domain.dynamic.Dynamic;
import com.wjs.qb.domain.member.Member;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DynamicAccusation extends BaseEntity {
    private Dynamic dynamic;
    private Accusation accusation;
    private Member member;

    public DynamicAccusation() {
    }

    public DynamicAccusation(Dynamic dynamic, Member member, Accusation accusation) {
        this.dynamic = dynamic;
        this.member = member;
        this.accusation = accusation;
    }
}

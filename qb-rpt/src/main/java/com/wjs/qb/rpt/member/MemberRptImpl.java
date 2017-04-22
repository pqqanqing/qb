package com.wjs.qb.rpt.member;

import com.wjs.common.base.rpt.BaseRptImpl;
import com.wjs.qb.domain.member.Member;
import com.wjs.qb.domain.member.MemberRpt;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class MemberRptImpl extends BaseRptImpl<Member, Serializable> implements MemberRpt {

    @Override
    public Member queryByOpenId(String openId) {
        String hql = "from Member m where  m.openId = :openId";
        Query query = getSession().createQuery(hql);
        query.setParameter("openId", openId);
        return (Member) query.uniqueResult();
    }

	@Override
	public Member queryByMemberId(String memberId) {
		String hql = "from Member m where m.memberId = :memberId";
		Query query = getSession().createQuery(hql);
		query.setParameter("memberId", memberId);
		return (Member) query.uniqueResult();
	}
}

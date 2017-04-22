package com.wjs.qb.domain.group;

import com.wjs.common.base.base.BaseEntity;
import com.wjs.qb.api.dto.AttachmentDTO;
import com.wjs.qb.api.dto.GroupDTO;
import com.wjs.qb.domain.attachment.Attachment;
import com.wjs.qb.domain.dynamic.Dynamic;
import com.wjs.qb.domain.member.Member;
import com.wjs.qb.domain.member.MemberRpt;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static com.wjs.common.base.spring.SpringBeanContext.getBean;
import static com.wjs.common.base.util.BeanPropertiesUtil.copyProperties;
import static java.util.Collections.unmodifiableList;
import static java.util.Objects.isNull;

@Setter
@Getter
public class Group extends BaseEntity {
    private Member member;
    private Attachment attachment;
    private Member manager;
    private String brief;
    private List<Dynamic> dynamics = new ArrayList<>();

    public Group() {
    }

    public Group(Member member, GroupDTO groupDTO) {
        this.member = member;
        setProperties(groupDTO);
    }

    public void setProperties(GroupDTO groupDTO) {
        this.name = groupDTO.getName();
        this.brief = groupDTO.getBrief();
        this.manager = getBean(MemberRpt.class).getById(groupDTO.getManager().getId());
        parseAttachment(groupDTO.getAttachment());
    }

    private void parseAttachment(AttachmentDTO attachment) {
        if (isNull(attachment)) return;
        this.attachment = new Attachment(attachment);
    }

    public void addDynamic(Dynamic dynamic) {
        dynamics.add(dynamic);
    }

    public List<Dynamic> getDynamics() {
        return unmodifiableList(dynamics);
    }

    public void delete() {
        setLogicDelete(true);
    }

    public String getLogo() {
        if (isNull(attachment)) return null;
        return attachment.getPath();
    }

    public String getMemberName() {
        if (isNull(manager)) return null;
        return manager.getName();
    }

    public String getMemberHead() {
        if (isNull(manager) || isNull(manager.getAttachment())) return null;
        return manager.getAttachment().getPath();
    }

    public Long getManagerId(){
        if (isNull(manager)) return null;
        return manager.getId();
    }

    public int getDynamicCount() {
        return dynamics.size();
    }

    public GroupDTO makeDTO() {
        return copyProperties(this, GroupDTO.class);
    }

}

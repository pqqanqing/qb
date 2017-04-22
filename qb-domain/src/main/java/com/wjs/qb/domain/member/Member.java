package com.wjs.qb.domain.member;

import com.wjs.common.base.base.BaseEntity;
import com.wjs.qb.api.dto.GroupDTO;
import com.wjs.qb.api.dto.MemberDTO;
import com.wjs.qb.api.dto.TopicDTO;
import com.wjs.qb.domain.attachment.Attachment;
import com.wjs.qb.domain.group.Group;
import com.wjs.qb.domain.group.GroupRpt;
import com.wjs.qb.domain.member.status.MemberStatus;
import com.wjs.qb.domain.member.status.MemberStatus4Normal;
import com.wjs.qb.domain.member.status.MemberStatus4ShutUp;
import com.wjs.qb.domain.message.Message;
import com.wjs.qb.domain.topic.Topic;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static com.wjs.common.base.spring.SpringBeanContext.getBean;
import static com.wjs.common.base.util.BeanPropertiesUtil.copyProperties;
import static java.util.Collections.unmodifiableList;

@Setter
@Getter
public class Member extends BaseEntity<MemberStatus> {

    private String openId;
    private String memberId;
    private String nickname;
    private Attachment attachment;
    private List<Topic> topics = new ArrayList<>();
    private List<Group> groups = new ArrayList<>();
    private List<Message> messages = new ArrayList<>();
    private int reportDynamicCount;
    private int reportCommentCount;

    public Member() {
        this.status = MemberStatus4Normal.getInstance();
    }

    public Member(MemberDTO memberDTO) {
        this();
        this.openId = memberDTO.getOpenId();
        this.nickname = memberDTO.getNickname();
        this.memberId = memberDTO.getMemberId();
        this.attachment = new Attachment(memberDTO.getAttachment());
    }

    public Member(String openId, String nickname, String headimgurl) {
        this();
        this.openId = openId;
        this.nickname = nickname;
        this.attachment = new Attachment(headimgurl);
    }

    public Topic createTopic(TopicDTO topicDTO) {
        Topic topic = new Topic(this, topicDTO);
        addTopic(topic);
        return topic;
    }

    public Group createGroup(GroupDTO groupDTO) {
        Group group = new Group(this, groupDTO);
        addGroup(group);
        return group;
    }

    private void addTopic(Topic topic) {
        topics.add(topic);
    }

    private void addGroup(Group group) {
        groups.add(group);
    }

    public void deleteGroup(Group group) {
        group.delete();
    }

    public Group modifyGroup(GroupDTO groupDTO) {
        Group group = getBean(GroupRpt.class).getById(groupDTO.getId());
        group.setProperties(groupDTO);
        return group;
    }

    public List<Topic> getTopics() {
        return unmodifiableList(topics);
    }

    public List<Group> getGroups() {
        return unmodifiableList(groups);
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public void shutup() {
        this.status = MemberStatus4ShutUp.getInstance();
    }

    public void cancelShutup() {
        this.status = MemberStatus4Normal.getInstance();
    }

    public void addReportDynamicCount() {
        this.reportDynamicCount++;
    }

    public void reduceReportDynamicCount(int size) {
        this.reportDynamicCount = this.reportDynamicCount - size;
    }

    public void addReportCommentCount() {
        this.reportCommentCount++;
    }

    public void reduceReportCommentCount(int size) {
        this.reportCommentCount = this.reportCommentCount - size;
    }

    public MemberDTO makeDTO() {
        return copyProperties(this, MemberDTO.class);
    }
}

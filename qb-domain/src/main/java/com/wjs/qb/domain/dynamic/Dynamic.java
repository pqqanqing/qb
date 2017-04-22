package com.wjs.qb.domain.dynamic;

import com.wjs.common.base.base.BaseEntity;
import com.wjs.common.base.execption.BusinessExecption;
import com.wjs.qb.api.dto.AttachmentDTO;
import com.wjs.qb.api.dto.CommentDTO;
import com.wjs.qb.api.dto.DynamicDTO;
import com.wjs.qb.common.sensitive.SensitiveWordFilter;
import com.wjs.qb.domain.accusation.Accusation;
import com.wjs.qb.domain.attachment.Attachment;
import com.wjs.qb.domain.comment.Comment;
import com.wjs.qb.domain.daccusation.DynamicAccusation;
import com.wjs.qb.domain.dynamic.status.DynamicStatus;
import com.wjs.qb.domain.dynamic.status.DynamicStatus4WaitCheck;
import com.wjs.qb.domain.dynamiccmd.DynamicCmd;
import com.wjs.qb.domain.member.Member;
import com.wjs.qb.domain.message.Message;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.wjs.common.base.util.BeanPropertiesUtil.copyProperties;
import static com.wjs.qb.api.dto.AttachmentDTO.OWNER_TYPE_DYNAMIC;
import static java.util.Collections.unmodifiableList;
import static java.util.Collections.unmodifiableSet;
import static java.util.Objects.isNull;
import static org.springframework.util.ObjectUtils.isEmpty;

@Setter
@Getter
public abstract class Dynamic extends BaseEntity<DynamicStatus> {

    protected Member member;
    protected List<Attachment> attachments = new ArrayList<>();
    protected List<Comment> comments = new ArrayList<>();
    protected List<DynamicCmd> dynamicCmds = new ArrayList<>();
    protected Set<DynamicAccusation> dynamicAccusations = new HashSet<>();
    protected String title;
    protected String content;
    protected Integer readCount;
    protected Boolean stick = false;
    protected Boolean essence = false;

    public Dynamic() {
    }

    public Dynamic(DynamicDTO dynamicDTO) {
        setProperties(dynamicDTO);
        this.status = DynamicStatus4WaitCheck.getInstance();
    }

    protected void setProperties(DynamicDTO dynamicDTO) {
        this.title = dynamicDTO.getTitle();
        this.content = dynamicDTO.getContent();
        parseAttachment(dynamicDTO.getAttachments());
    }

    protected void parseAttachment(AttachmentDTO[] attachments) {
        if (isEmpty(attachments)) return;
        for (AttachmentDTO attachmentDTO : attachments) {
            attachmentDTO.setOwnerType(OWNER_TYPE_DYNAMIC);
            addAttachment(new Attachment(attachmentDTO));
        }
    }

    public void check(boolean flag) {
        if (!status.isCheck()) throw new BusinessExecption("qb2");
        status = flag ? status.succ() : status.refuse();
    }

    protected void addAttachment(Attachment attachment) {
        attachments.add(attachment);
    }

    public void addDynamicCmd(DynamicCmd dynamicCmd) {
        dynamicCmds.add(dynamicCmd);
    }

    public Comment createComment(CommentDTO commentDTO) {
        if (!status.canComment()) throw new BusinessExecption("qb7");
        Comment comment = new Comment(this, commentDTO);
        comments.add(comment);
        return comment;
    }

    public void delete() {
        if (!status.canDelete()) throw new BusinessExecption("qb10");
        status = status.delete();
    }

    public void recover() {
        if (!status.canRecover()) throw new BusinessExecption("qb11");
        status = status.succ();
    }

    public void addReadCount() {
        this.readCount++;
    }

    public void accusation(Member member, Accusation accusation) {
        this.member.addReportDynamicCount();
        addDynamicAccusation(new DynamicAccusation(this, member, accusation));
    }

    protected void addDynamicAccusation(DynamicAccusation dynamicAccusation) {
        dynamicAccusations.add(dynamicAccusation);
    }

    public void cancelAccusation() {
        int size = dynamicAccusations.size();
        this.member.reduceReportDynamicCount(size);
        dynamicAccusations.stream().forEach(dynamicAccusation -> {
            dynamicAccusation.setLogicDelete(true);
        });
    }

    public void doStick() {
        if (stick) return;
        stick();
        this.stick = true;
    }

    public abstract void stick();

    public void unStick() {
        this.stick = false;
    }

    public void essence() {
        this.essence = true;
    }

    public void unessence() {
        this.essence = true;
    }

    public Integer getCommentCount() {
        return comments.size();
    }

    public String getNickname() {
        if (isNull(member)) return null;
        return member.getNickname();
    }

    public String getHead() {
        if (isNull(member) || isNull(member.getAttachment())) return null;
        return member.getAttachment().getPath();
    }

    public List<Attachment> getAttachments() {
        return unmodifiableList(attachments);
    }

    public List<Comment> getComments() {
        return unmodifiableList(comments);
    }

    public List<DynamicCmd> getDynamicCmds() {
        return unmodifiableList(dynamicCmds);
    }

    public Set<DynamicAccusation> getDynamicAccusations() {
        return unmodifiableSet(dynamicAccusations);
    }

    public List<String> getAttachmentPaths() {
        if (CollectionUtils.isEmpty(attachments)) return null;
        List<String> list = new ArrayList<>();
        attachments.stream().forEach(attachment -> {
            list.add(attachment.getPath());
        });
        return list;
    }

    public Message createMessage(Comment comment) {
        Message message = new Message(this, comment);
        member.addMessage(message);
        return message;
    }

    public String getTitle() {
        return SensitiveWordFilter.replaceSensitive(title);
    }

    public String getContent() {
        return SensitiveWordFilter.replaceSensitive(content);
    }

    public DynamicDTO makeDTO() {
        return copyProperties(this, DynamicDTO.class);
    }

}

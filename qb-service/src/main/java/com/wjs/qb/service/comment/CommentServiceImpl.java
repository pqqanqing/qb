package com.wjs.qb.service.comment;

import com.wjs.common.base.base.BasePageResults;
import com.wjs.common.base.execption.BusinessExecption;
import com.wjs.common.base.query.QueryService;
import com.wjs.qb.api.dto.CommentDTO;
import com.wjs.qb.domain.accusation.Accusation;
import com.wjs.qb.domain.accusation.AccusationRpt;
import com.wjs.qb.domain.comment.Comment;
import com.wjs.qb.domain.comment.CommentRpt;
import com.wjs.qb.domain.dynamic.Dynamic;
import com.wjs.qb.domain.dynamic.DynamicRpt;
import com.wjs.qb.domain.member.Member;
import com.wjs.qb.domain.member.MemberRpt;
import com.wjs.qb.domain.member.status.MemberStatus;
import com.wjs.qb.domain.message.Message;
import com.wjs.qb.domain.message.MessageRpt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Objects.isNull;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private DynamicRpt dynamicRpt;
    @Autowired
    private CommentRpt commentRpt;
    @Autowired
    private MemberRpt memberRpt;
    @Autowired
    private AccusationRpt accusationRpt;
    @Autowired
    private MessageRpt messageRpt;
    @Autowired
    private QueryService queryService;

    @Override
    public CommentDTO publishComment(Long dynamicId, CommentDTO commentDTO) {
        Dynamic dynamic = dynamicRpt.getById(dynamicId);
        if (isNull(dynamic)) throw new BusinessExecption("qb6");
        if (isNull(commentDTO.getCommentator())) throw new BusinessExecption("评论人不能为空!");
        Member member = memberRpt.queryByOpenId(commentDTO.getCommentator().getOpenId());
        MemberStatus status = member.getStatus();
        if (!status.canPublishComment()) throw new BusinessExecption("评论人被禁言!");
        Comment comment = dynamic.createComment(commentDTO);
        commentRpt.save(comment);

        Message message = dynamic.createMessage(comment);
        messageRpt.save(message);
        return comment.makeDTO();
    }

    @Override
    public CommentDTO publishChildComment(Long id, CommentDTO commentDTO) {
        Comment comment = commentRpt.getById(id);
        Comment child = comment.publishChild(comment, commentDTO);
        commentRpt.save(child);
        Comment parent = comment.getRootParent();
        child.setParent(parent);
        parent.addComment(child);

        Message message = comment.createMessage(child);
        messageRpt.save(message);
        return child.makeDTO();
    }

    @Override
    public void deleteComment(Long id) {
        commentRpt.deleteById(id);
    }

    @Override
    public CommentDTO accusation(Long commentId, String openId, Long accusationId) {
        Comment comment = commentRpt.getById(commentId);
        Member member = memberRpt.queryByOpenId(openId);
        if (isNull(member)) throw new BusinessExecption("qb13");
        Accusation accusation = accusationRpt.getById(accusationId);
        if (isNull(accusation)) throw new BusinessExecption("qb15");
        comment.accusation(member, accusation);
        return comment.makeDTO();
    }

    @Override
    public CommentDTO cancelAccusation(Long commentID) {
        Comment comment = commentRpt.getById(commentID);
        comment.cancelAccusation();
        return comment.makeDTO();
    }

    @Override
    public BasePageResults<CommentDTO> query(CommentDTO commentDTO) {
        BasePageResults<CommentDTO> commentDTOBasePageResults = queryService.queryDTO(commentDTO);
        return commentDTOBasePageResults;
    }

}

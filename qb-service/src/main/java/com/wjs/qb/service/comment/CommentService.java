package com.wjs.qb.service.comment;

import com.wjs.common.base.base.BasePageResults;
import com.wjs.qb.api.dto.CommentDTO;

public interface CommentService {

    CommentDTO publishComment(Long dynamicId, CommentDTO commentDTO);

    CommentDTO publishChildComment(Long id, CommentDTO commentDTO);

    void deleteComment(Long id);

    CommentDTO accusation(Long commentId, String openId, Long accusationId);

    CommentDTO cancelAccusation(Long commentId);

    BasePageResults<CommentDTO> query(CommentDTO commentDTO);
}

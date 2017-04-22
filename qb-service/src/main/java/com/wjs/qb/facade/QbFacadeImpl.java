package com.wjs.qb.facade;

import com.wjs.common.base.rpc.RpcResponse;
import com.wjs.qb.api.dto.CommentDTO;
import com.wjs.qb.api.dto.DynamicDTO;
import com.wjs.qb.api.dto.GroupDTO;
import com.wjs.qb.api.dto.TopicDTO;
import com.wjs.qb.api.facade.QbFacade;
import com.wjs.qb.service.comment.CommentService;
import com.wjs.qb.service.dynamic.DynamicService;
import com.wjs.qb.service.group.GroupService;
import com.wjs.qb.service.topic.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("wechatFacade")
public class QbFacadeImpl implements QbFacade {

    @Autowired
    private GroupService groupService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private DynamicService dynamicService;

    @Autowired
    private CommentService commentService;

    @Override
    public RpcResponse<GroupDTO> createOrModifyGroup(String memberId, GroupDTO groupDTO) {
        return new RpcResponse<>(groupService.createOrModify(memberId, groupDTO));
    }

    @Override
    public RpcResponse deleteGroup(String memberId, Long id) {
        groupService.delete(memberId, id);
        return new RpcResponse();
    }

    @Override
    public RpcResponse<TopicDTO> createTopic(String openId, TopicDTO topicDTO) {
        return new RpcResponse<>(topicService.create(openId, topicDTO));
    }

    @Override
    public RpcResponse<TopicDTO> soldOutTopic(Long id) {
        return new RpcResponse<>(topicService.soldOut(id));
    }

    @Override
    public RpcResponse<DynamicDTO> sendDynamic(String openId, DynamicDTO dynamicDTO) {
        return new RpcResponse<>(dynamicService.sendDynamic(openId, dynamicDTO));
    }

    @Override
    public RpcResponse<DynamicDTO> checkDynamic(Long id, String memberId, boolean flag) {
        return new RpcResponse<>(dynamicService.checkDynamic(id, memberId, flag));
    }

    @Override
    public RpcResponse<DynamicDTO> deleteDynamic(Long id, String memberId) {
        return new RpcResponse<>(dynamicService.deleteDynamic(id, memberId));
    }

    @Override
    public RpcResponse<DynamicDTO> recoverDynamic(Long id, String memberId) {
        return new RpcResponse<>(dynamicService.recoverDynamic(id, memberId));
    }

    @Override
    public RpcResponse<DynamicDTO> addReadCount(Long id) {
        return new RpcResponse<>(dynamicService.addReadCount(id));
    }

    @Override
    public RpcResponse<DynamicDTO> accusationDynamic(Long id, String openId, Long accusationId) {
        return new RpcResponse<>(dynamicService.accusation(id, openId, accusationId));
    }

    @Override
    public RpcResponse<DynamicDTO> cancelAccusationDynamic(Long id) {
        return new RpcResponse<>(dynamicService.cancelAccusation(id));
    }

	@Override
	public RpcResponse<CommentDTO> publishComment(Long dynamicId,CommentDTO commentDTO) {
		return new RpcResponse<>(commentService.publishComment(dynamicId,commentDTO));
	}

	@Override
	public RpcResponse deleteComment(Long commentID) {
		commentService.deleteComment(commentID);
		return new RpcResponse();
	}

	@Override
	public RpcResponse<CommentDTO> accusationComment(Long commentID, String openId, Long accusationId) {
		return new RpcResponse<>(commentService.accusation(commentID, openId, accusationId));
	}

	@Override
	public RpcResponse<CommentDTO> cancelAccusationComment(Long commentID) {
		return new RpcResponse<>(commentService.cancelAccusation(commentID));
	}

}

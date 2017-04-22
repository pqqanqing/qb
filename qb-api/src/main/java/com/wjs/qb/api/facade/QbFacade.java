package com.wjs.qb.api.facade;

import com.wjs.common.base.rpc.RpcResponse;
import com.wjs.qb.api.dto.CommentDTO;
import com.wjs.qb.api.dto.DynamicDTO;
import com.wjs.qb.api.dto.GroupDTO;
import com.wjs.qb.api.dto.TopicDTO;

public interface QbFacade {

    /**
     * 创建小组
     *
     * @param memberId 用户memberId
     * @param groupDTO 组信息dto
     * @return
     */
    RpcResponse<GroupDTO> createOrModifyGroup(String memberId, GroupDTO groupDTO);

    /**
     * 删除小组
     *
     * @param memberId 用户id
     * @param id       小组id
     * @return
     */
    RpcResponse deleteGroup(String memberId, Long id);

    /**
     * 创建话题
     *
     * @param openId   用户id
     * @param topicDTO 话题dto
     * @return
     */
    RpcResponse<TopicDTO> createTopic(String openId, TopicDTO topicDTO);

    /**
     * 下架话题
     *
     * @param id 话题id
     * @return
     */
    RpcResponse<TopicDTO> soldOutTopic(Long id);

    /**
     * 发表动态
     *
     * @param openId     用户id
     * @param dynamicDTO 动态dto
     * @return
     */
    RpcResponse<DynamicDTO> sendDynamic(String openId, DynamicDTO dynamicDTO);

    /**
     * 审核动态
     *
     * @param id       动态id
     * @param memberId 审核人id
     * @param flag     true审核通过,false审核不通过
     * @return
     */
    RpcResponse<DynamicDTO> checkDynamic(Long id, String memberId, boolean flag);

    /**
     * 删除动态
     *
     * @param id       动态id
     * @param memberId 会员id
     * @return
     */
    RpcResponse<DynamicDTO> deleteDynamic(Long id, String memberId);

    /**
     * 恢复动态
     *
     * @param id       动态id
     * @param memberId 会员id
     * @return
     */
    RpcResponse<DynamicDTO> recoverDynamic(Long id, String memberId);

    /**
     * 增加阅读数
     *
     * @param id 动态id
     * @return
     */
    RpcResponse<DynamicDTO> addReadCount(Long id);

    /**
     * 举报动态
     *
     * @param id           动态id
     * @param openId       用户id
     * @param accusationId 举报id
     * @return
     */
    RpcResponse<DynamicDTO> accusationDynamic(Long id, String openId, Long accusationId);

    /**
     * 取消举报
     *
     * @param id 动态id
     * @return
     */
    RpcResponse<DynamicDTO> cancelAccusationDynamic(Long id);

	/**
	 * 发表评论
	 * @param commentDTO
	 * @param dynamicId	动态Id
	 * @return
	 */
	RpcResponse<CommentDTO> publishComment(Long dynamicId, CommentDTO commentDTO);

	/**
	 * 删除评论
	 * @param commentId	评论Id
	 * @return
	 */
	RpcResponse deleteComment(Long commentId);

	/**
	 * 举报评论
	 * @param commentId
	 * @param openId
	 * @param accusationId
	 * @return
	 */
	RpcResponse<CommentDTO> accusationComment(Long commentId, String openId, Long accusationId);

	/**
	 * 取消举报
	 * @param commentId
	 * @return
	 */
	RpcResponse<CommentDTO> cancelAccusationComment(Long commentId);
}

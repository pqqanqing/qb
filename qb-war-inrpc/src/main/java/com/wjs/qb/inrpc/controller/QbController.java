package com.wjs.qb.inrpc.controller;

import com.wjs.common.base.base.BasePageResults;
import com.wjs.common.base.rpc.RpcResponse;
import com.wjs.qb.api.dto.*;
import com.wjs.qb.service.accusation.AccusationService;
import com.wjs.qb.service.comment.CommentService;
import com.wjs.qb.service.dynamic.DynamicService;
import com.wjs.qb.service.group.GroupService;
import com.wjs.qb.service.member.MemberService;
import com.wjs.qb.service.message.MessageService;
import com.wjs.qb.service.money.MoneyService;
import com.wjs.qb.service.topic.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@CrossOrigin
@Controller
@RequestMapping("/wechat")
public class QbController {

    @Autowired
    private GroupService groupService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private DynamicService dynamicService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private MoneyService moneyService;
    @Autowired
    private AccusationService accusationService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private MemberService memberService;

    /**
     * 创建或者修改小组
     *
     * @param memberId 会员id
     * @param groupDTO 小组dto
     * @return
     */
    @RequestMapping(value = "/group/{memberId}", method = POST)
    @ResponseBody
    public RpcResponse<GroupDTO> createOrModifyGroup(@PathVariable String memberId, @RequestBody GroupDTO groupDTO) {
        return new RpcResponse<>(groupService.createOrModify(memberId, groupDTO));
    }

    /**
     * 删除小组
     *
     * @param memberId 会员id
     * @param id       小组id
     * @return
     */
    @RequestMapping(value = "/group/{memberId}", method = DELETE)
    @ResponseBody
    public RpcResponse deleteGroup(@PathVariable String memberId, @RequestParam Long id) {
        groupService.delete(memberId, id);
        return new RpcResponse();
    }

    /**
     * 创建话题
     *
     * @param memberId 会员id
     * @param topicDTO 话题dto
     * @return
     */
    @RequestMapping(value = "/topic/{memberId}", method = POST)
    @ResponseBody
    public RpcResponse<TopicDTO> create(@PathVariable String memberId, @RequestBody TopicDTO topicDTO) {
        return new RpcResponse<>(topicService.create(memberId, topicDTO));
    }

    /**
     * 下架话题
     *
     * @param topicId 话题id
     * @return
     */
    @RequestMapping(value = "/topic/soldOut/{topicId}", method = POST)
    @ResponseBody
    public RpcResponse<TopicDTO> soldOut(@PathVariable Long topicId) {
        return new RpcResponse<>(topicService.soldOut(topicId));
    }

    /**
     * 发表动态:type(1:普通,2:话题,3:小组,4:话题投票)
     *
     * @param openId
     * @param dynamicDTO
     * @return
     */
    @RequestMapping(value = "/dynamic/{openId}", method = POST)
    @ResponseBody
    public RpcResponse<DynamicDTO> sendDynamic(@PathVariable String openId, @RequestBody DynamicDTO dynamicDTO) {
        return new RpcResponse<>(dynamicService.sendDynamic(openId, dynamicDTO));
    }

    /**
     * 后台管理人员发表动态
     * @param openId
     * @param dynamicDTO
     * @return
     */
    @RequestMapping(value = "/manager/dynamic/{openId}")
    @ResponseBody
    public RpcResponse<DynamicDTO> sendDynamic4Manager(@PathVariable String openId, @RequestBody DynamicDTO dynamicDTO) {
        return new RpcResponse<>(dynamicService.sendDynamic4Manager(openId, dynamicDTO));
    }

    /**
     * 审核动态flag(true:审核通过,false:审核失败)
     *
     * @param id       动态id
     * @param memberId 会员id
     * @param flag
     * @return
     */
    @RequestMapping(value = "/dynamic/check/{id}", method = POST)
    @ResponseBody
    public RpcResponse<DynamicDTO> checkDynamic(@PathVariable Long id, @RequestParam String memberId, @RequestParam boolean flag) {
        return new RpcResponse<>(dynamicService.checkDynamic(id, memberId, flag));
    }

    /**
     * 删帖动态
     *
     * @param id       动态id
     * @param memberId 会员id
     * @return
     */
    @RequestMapping(value = "/dynamic/delete/{id}", method = POST)
    @ResponseBody
    public RpcResponse<DynamicDTO> deleteDynamic(@PathVariable Long id, @RequestParam String memberId) {
        return new RpcResponse<>(dynamicService.deleteDynamic(id, memberId));
    }

    /**
     * 恢复动态
     *
     * @param id       动态id
     * @param memberId 会员id
     * @return
     */
    @RequestMapping(value = "/dynamic/recover/{id}", method = POST)
    @ResponseBody
    public RpcResponse<DynamicDTO> recoverDynamic(@PathVariable Long id, @RequestParam String memberId) {
        return new RpcResponse<>(dynamicService.recoverDynamic(id, memberId));
    }

    /**
     * 删除动态
     *
     * @param id 动态id
     * @return
     */
    @RequestMapping(value = "/dynamic/{id}", method = DELETE)
    @ResponseBody
    public RpcResponse<DynamicDTO> delete(@PathVariable Long id) {
        dynamicService.delete(id);
        return new RpcResponse<>();
    }

    /**
     * 动态置顶功能
     *
     * @param id 动态主键
     * @return
     */
    @RequestMapping(value = "/dynamic/stick/{id}", method = GET)
    @ResponseBody
    public RpcResponse<DynamicDTO> stick(@PathVariable Long id) {
        return new RpcResponse<>(dynamicService.stick(id));
    }

    /**
     * 动态精华功能
     *
     * @param id 动态主键
     * @return
     */
    @RequestMapping(value = "/dynamic/essence/{id}", method = GET)
    @ResponseBody
    public RpcResponse<DynamicDTO> essence(@PathVariable Long id) {
        return new RpcResponse<>(dynamicService.essence(id));
    }

    /**
     * 动态取消精华功能
     *
     * @param id 动态主键
     * @return
     */
    @RequestMapping(value = "/dynamic/unessence/{id}", method = GET)
    @ResponseBody
    public RpcResponse<DynamicDTO> unessence(@PathVariable Long id) {
        return new RpcResponse<>(dynamicService.unessence(id));
    }

    /**
     * 动态计数
     *
     * @param id 动态id
     * @return
     */
    @RequestMapping(value = "/dynamic/readCount/{id}", method = GET)
    @ResponseBody
    public RpcResponse<DynamicDTO> addReadCount(@PathVariable Long id) {
        return new RpcResponse<>(dynamicService.addReadCount(id));
    }

    /**
     * 举报动态
     *
     * @param id           动态id
     * @param openId       会员openId
     * @param accusationId 举报id
     * @return
     */
    @RequestMapping(value = "/dynamic/accusation/{id}", method = POST)
    @ResponseBody
    public RpcResponse<DynamicDTO> accusationDynamic(@PathVariable Long id, @RequestParam String openId, @RequestParam Long accusationId) {
        return new RpcResponse<>(dynamicService.accusation(id, openId, accusationId));
    }

    /**
     * 取消举报
     *
     * @param id 举报id
     * @return
     */
    @RequestMapping(value = "/dynamic/accusation/cancel/{id}", method = POST)
    @ResponseBody
    public RpcResponse<DynamicDTO> cancelAccusationDynamic(@PathVariable Long id) {
        return new RpcResponse<>(dynamicService.cancelAccusation(id));
    }

    /**
     * 发表评论
     *
     * @param dynamicId  动态id
     * @param commentDTO 评论dto
     * @return
     */
    @RequestMapping(value = "/comment/publish/{dynamicId}", method = POST)
    @ResponseBody
    public RpcResponse<CommentDTO> publishComment(@PathVariable Long dynamicId, @RequestBody CommentDTO commentDTO) {
        return new RpcResponse<>(commentService.publishComment(dynamicId, commentDTO));
    }

    /**
     * 发表子评论
     *
     * @param id         评论id
     * @param commentDTO 评论dto
     * @return
     */
    @RequestMapping(value = "/comment/publish/child/{id}", method = POST)
    @ResponseBody
    public RpcResponse<CommentDTO> publishChildComment(@PathVariable Long id, @RequestBody CommentDTO commentDTO) {
        return new RpcResponse<>(commentService.publishChildComment(id, commentDTO));
    }

    /**
     * 删除评论
     *
     * @param id 评论id
     * @return
     */
    @RequestMapping(value = "/comment/{id}", method = DELETE)
    @ResponseBody
    public RpcResponse<CommentDTO> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return new RpcResponse<>();
    }

    /**
     * 举报评论
     *
     * @param id           评论id
     * @param openId       会员openId
     * @param accusationId 举报id
     * @return
     */
    @RequestMapping(value = "/comment/accusation/{id}", method = POST)
    @ResponseBody
    public RpcResponse<CommentDTO> accusationComment(@PathVariable Long id, @RequestParam String openId, @RequestParam Long accusationId) {
        return new RpcResponse<>(commentService.accusation(id, openId, accusationId));
    }

    /**
     * 取消举报
     *
     * @param id 评论id
     * @return
     */
    @RequestMapping(value = "/comment/accusation/cancel/{id}", method = DELETE)
    @ResponseBody
    public RpcResponse<CommentDTO> cancelAccusationComment(@PathVariable Long id) {
        return new RpcResponse<>(commentService.cancelAccusation(id));
    }

    /**
     * 删除消息
     *
     * @param id 消息id
     * @return
     */
    @RequestMapping(value = "/message/delete/{id}", method = DELETE)
    @ResponseBody
    public RpcResponse deleteMessage(@PathVariable Long id) {
        messageService.delete(id);
        return new RpcResponse<>();
    }

    /**
     * 清空所有消息
     *
     * @param memberId 会员id
     * @return
     */
    @RequestMapping(value = "/message/deleteAll/{memberId}", method = DELETE)
    @ResponseBody
    public RpcResponse deleteAllMessage(@PathVariable Long memberId) {
        messageService.deleteAll(memberId);
        return new RpcResponse<>();
    }

    /**
     * 创建会员
     *
     * @param memberDTO
     * @return
     */
    @RequestMapping(value = "/member/create", method = POST)
    @ResponseBody
    public RpcResponse<MemberDTO> createMember(@RequestBody MemberDTO memberDTO) {
        return new RpcResponse<>(memberService.create(memberDTO));
    }

    /**
     * 查询钱吧
     *
     * @return
     */
    @RequestMapping(value = "/moneies", method = GET)
    @ResponseBody
    public RpcResponse<MoneyDTO> queryMoneies(HttpServletRequest request) {
        return new RpcResponse<>(moneyService.queryMoney());
    }


    /**
     * 小组查询
     *
     * @param groupDTO 小组dto
     * @return
     */
    @RequestMapping(value = "/groups", method = POST)
    @ResponseBody
    public RpcResponse<BasePageResults<GroupDTO>> queryGroups(@RequestBody GroupDTO groupDTO) {
        return new RpcResponse<>(groupService.query(groupDTO));
    }

    /**
     * 话题查询
     *
     * @param topicDTO 话题dto
     * @return
     */
    @RequestMapping(value = "/topics", method = POST)
    @ResponseBody
    public RpcResponse<BasePageResults<TopicDTO>> queryTopics(@RequestBody TopicDTO topicDTO) {
        return new RpcResponse<>(topicService.query(topicDTO));
    }

    /**
     *查询最新的一条话题
     * @return
     */
    @RequestMapping(value = "/topic", method = GET)
    @ResponseBody
    public RpcResponse<TopicDTO> queryTopic() {
        return new RpcResponse<>(topicService.queryLatestTopic());
    }

    /**
     * 动态查询
     *
     * @param dynamicDTO 动态dto
     * @return
     */
    @RequestMapping(value = "/dynamics", method = POST)
    @ResponseBody
    public RpcResponse<BasePageResults<DynamicDTO>> queryDynamics(@RequestBody DynamicDTO dynamicDTO) {
        return new RpcResponse<>(dynamicService.query(dynamicDTO));
    }

    /**
     * 举报查询
     *
     * @return
     */
    @RequestMapping(value = "/accusations", method = GET)
    @ResponseBody
    public RpcResponse<BasePageResults<AccusationDTO>> queryAccusations() {
        return new RpcResponse<>(accusationService.query());
    }

    /**
     * 评论查询
     *
     * @param commentDTO 评论dto
     * @return
     */
    @RequestMapping(value = "/comments", method = POST)
    @ResponseBody
    public RpcResponse<BasePageResults<CommentDTO>> queryComments(@RequestBody CommentDTO commentDTO) {
        return new RpcResponse<>(commentService.query(commentDTO));
    }

    /**
     * 查询消息
     *
     * @param messageDTO 消息dto
     * @return
     */
    @RequestMapping(value = "/messages", method = POST)
    @ResponseBody
    public RpcResponse<BasePageResults<MessageDTO>> queryMessages(@RequestBody MessageDTO messageDTO) {
        return new RpcResponse<>(messageService.query(messageDTO));
    }

    /**
     * 查询会员
     *
     * @param memberDTO
     * @return
     */
    @RequestMapping(value = "/members", method = POST)
    @ResponseBody
    public RpcResponse<BasePageResults<MemberDTO>> queryMembers(@RequestBody MemberDTO memberDTO) {
        return new RpcResponse<>(memberService.query(memberDTO));
    }

    /**
     * 会员禁言
     *
     * @param openId
     * @return
     */
    @RequestMapping(value = "/members/shutup/{openId}", method = POST)
    @ResponseBody
    public RpcResponse<MemberDTO> shutup(@PathVariable String openId) {
        return new RpcResponse<>(memberService.shutup(openId));
    }

    /**
     * 解除会员禁言
     *
     * @param openId
     * @return
     */
    @RequestMapping(value = "/members/cancelShutup/{openId}", method = POST)
    @ResponseBody
    public RpcResponse<MemberDTO> cancelShutup(@PathVariable String openId) {
        return new RpcResponse<>(memberService.cancelShutup(openId));
    }

    /**
     * @param memberId
     * @return
     */
    @RequestMapping(value = "/members/{memberId}/accusations", method = POST)
    @ResponseBody
    public RpcResponse<List<DynamicDTO>> queryAccusations4Member(@PathVariable String memberId) {
        return new RpcResponse<>(dynamicService.queryByMemberId(memberId));
    }
}

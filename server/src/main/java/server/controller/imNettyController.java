package server.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.bean.ao.RecordResponseAO;
import server.bean.vo.MessageVO;
import server.bean.vo.UserSearchResponseVO;
import server.service.MessageService;
import server.utils.JsonResult;

import java.util.List;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2023/5/26
 */
@ApiModel("IM聊天")
@RestController
@Slf4j
public class imNettyController {

    @Autowired
    private MessageService messageService;

    @ApiOperation("获取聊天记录")
    @PostMapping("getMessageList")
    public JsonResult getMessageList(@RequestBody RecordResponseAO response) {
        log.info("imNettyController getMessageList --> response:{}", response);
        List<MessageVO>  messageVOList = messageService.getMessageList(response);
        return JsonResult.success(messageVOList);
    }

    @ApiOperation("获取有聊天记录的人的列表")
    @GetMapping("getChatUserList")
    public JsonResult getChatUserList(
                                     @RequestParam("keyword") String keyword, @RequestParam("token") String token,
                                     @RequestParam("page") int page, @RequestParam("size") int size) {
        if ( keyword.equals("-1") ) {
            List<MessageVO>  messageVOList = messageService.getChatUserList(token, page, size);
            return JsonResult.success(messageVOList);
        }
        List<UserSearchResponseVO> response = messageService.searchUserListByUsername(keyword) ;
        return JsonResult.success(response);
    }

    @ApiOperation("搜索用户")
    @GetMapping("searchUserList")
    public JsonResult searchUserList(@RequestParam("keyword") String keyword) {
        log.info("imNettyController searchUserList keyword:{}", keyword);
        List<UserSearchResponseVO> response = messageService.searchUserListByUsername(keyword) ;
        return JsonResult.success(response);
    }


}

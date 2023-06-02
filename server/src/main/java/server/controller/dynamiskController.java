package server.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.Mapper.ServerMapper;
import server.bean.ao.CommentAO;
import server.bean.ao.DynamiskAO;
import server.bean.dto.CommentDTO;
import server.bean.dto.DynamiskDTO;
import server.bean.vo.CommentVO;
import server.bean.vo.DynamicVO;
import server.dao.ElvenDao;
import server.service.CommentService;
import server.service.DynamicService;
import server.utils.JsonResult;

import java.util.List;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2023/5/17
 */
@ApiModel("动态")
@RestController
@Slf4j
public class dynamiskController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private DynamicService dynamicService;

    @Autowired
    private ServerMapper serverMapper;

    @ApiOperation("发布动态")
    @PostMapping("/addDynamic")
    public JsonResult addDynamic(@RequestBody DynamiskAO dynamiskAO){
        log.info("dynamiskController addDynamic dynamiskAO:{}", dynamiskAO);
        DynamiskDTO dynamiskDTO = serverMapper.dynamiskAO2DynamiskDTO(dynamiskAO);
        System.out.println( "--->" + dynamiskDTO );
        dynamicService.addDynamic(dynamiskDTO, dynamiskAO.getLinks());
        return JsonResult.success();
    }

    @ApiOperation("获取一条动态，查看动态详情")
    @PostMapping("/getOneDynamic")
    public JsonResult getOneDynamic() {

        return JsonResult.success();
    }

    @ApiOperation("发表评论或回复")
    @PostMapping("addComment")
    public JsonResult addComment(@RequestBody CommentAO commentAO) {
        log.info("dynamiskController addComment commentAO:{}", commentAO);
        CommentDTO commentDTO = serverMapper.commentAO2CommentDTO(commentAO);
        commentService.addComment(commentDTO);
        return JsonResult.success("发布成功");
    }

    /**
     * 获取动态列表
     */
    @GetMapping("getDynamicList")
    public JsonResult getDynamicList(@RequestParam("page") int page, @RequestParam("size") int  size) {
        log.info("dynamiskController getDynamicList page:{} size:{}", page, size);
        List<DynamicVO> dynamicVOList = dynamicService.getDynamicList(page, size);
        return JsonResult.success(dynamicVOList);
    }

    /**
     * 获取评论列表
     */
    @GetMapping("getCommentList")
    public JsonResult getCommentList(@RequestParam("dynamicId") String dynamicId, @RequestParam("page") int page, @RequestParam("size") int  size) {
        log.info("dynamiskController getCommentList page:{} size:{}", page, size);
        List<CommentVO> commentVOList = commentService.getCommentList(dynamicId, page, size);
        return JsonResult.success(commentVOList);
    }

    /**
     * 获取评论的回复列表
     */
    @GetMapping("getReplyList")
    public JsonResult getReplyList(@RequestParam("commentId") String commentId,
                                   @RequestParam("userId") int userId,
                                   @RequestParam("page") int page, @RequestParam("size") int  size) {
        log.info("dynamiskController getReplyList commentId:{} page:{} size:{}", commentId, page, size);
        List<CommentVO> commentVOList = commentService.getReplyList(commentId, userId, page, size);
        return JsonResult.success(commentVOList);
    }

}

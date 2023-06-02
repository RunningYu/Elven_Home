package server.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import server.bean.ao.ElvenAO;
import server.bean.ao.GetPageAO;
import server.bean.ao.LikeAO;
import server.bean.ao.UploadResponseAO;
import server.bean.vo.ElvesVO;
import server.service.ElvenService;
import server.utils.JsonResult;
import server.utils.MinioUtil;

import java.util.List;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2023/5/15
 */
@ApiModel("精灵")
@RestController
@Slf4j
public class elvenController {


    @Autowired
    private MinioUtil minioUtil;

    @Autowired
    private ElvenService elvenService;

    @ApiOperation("图片上传，返回图片链接")
    @PostMapping("/uploadPicture")
    public JsonResult minioUpload(MultipartFile file){
        JsonResult jsonResult = new JsonResult(200);
        UploadResponseAO response = null;
        try {
            response = minioUtil.uploadFile(file, "file");
            jsonResult.setData(response);
            jsonResult.setMsg("上传成功");
            System.out.println(jsonResult);
        } catch (Exception e) {
            jsonResult.setMsg("上传失败");
            jsonResult.setCode(400);
            return jsonResult;
        }
        return jsonResult;
    }

    @ApiOperation("添加精灵")
    @PostMapping("/addElven")
    public JsonResult addElven(@RequestBody ElvenAO elvenAO) {
        log.info("elvenController addElven elves : {}", elvenAO);
        elvenService.addElven(elvenAO);
        return JsonResult.success();
    }

    @ApiOperation("获取精灵列表")
    @GetMapping("/getElvenList")
    public JsonResult getElvenList(@RequestParam("token") String token, @RequestParam("kind") String kind,
                                   @RequestParam("page") int page, @RequestParam("size") int size) {
        log.info("elvenController getElvenList kind:{} page:{} size:{}", kind, page, size);
        List<ElvesVO> elvesVOList = elvenService.getElvenListByKind(token, kind, page, size);
        return JsonResult.success(elvesVOList);
    }

    @ApiOperation("获取精灵热点列表")
    @PostMapping("/getElvenHotList")
    public JsonResult getElvenHotList(@RequestBody GetPageAO getPageAO) {
        log.info("elvenController getElvenList getPageAO:{}", getPageAO);
        List<ElvesVO> elvesVOList = elvenService.getElvenHotList(getPageAO.getToken(), getPageAO.getPage(), getPageAO.getSize());
        return JsonResult.success(elvesVOList);
    }

    @ApiOperation("获取精灵热点列表")
    @GetMapping("/getElvenKindList")
    public JsonResult getElvenKindList() {
        log.info("elvenController getElvenKindList");
        List<String> kindList = elvenService.getElvenKindList();
        log.info("elvenController getElvenKindList kindList:{}", kindList);
        return JsonResult.success(kindList);
    }

    @ApiOperation("（取消）点赞")
    @PostMapping("/likeElven")
    public JsonResult likeElven(@RequestBody LikeAO likeAO) {
        log.info("elvenController likeElven likeAO:{}",likeAO);
        return elvenService.like(likeAO.getToken(), likeAO.getElvesId());
    }


}

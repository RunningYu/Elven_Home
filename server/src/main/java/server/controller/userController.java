package server.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import server.Mapper.ServerMapper;
import server.bean.ao.TokenAO;
import server.bean.ao.UserLoginAO;
import server.bean.dto.UserDTO;
import server.service.UserService;
import server.utils.JsonResult;


/**
 * @author : 其然乐衣Letitbe
 * @date : 2023/5/18
 */
@ApiModel("用户")
@RestController
@Slf4j
public class userController {

    @Autowired
    private UserService userService;

    @Autowired
    private ServerMapper serverMapper;

    @ApiOperation("用户登录接口（首次用改电话登录，就当是注册，创建改新用户）")
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/login")
    public JsonResult userLogin (@RequestBody UserLoginAO userLoginAO) {
        log.info("userController userLogin userLoginAO:{}", userLoginAO);
        UserDTO userDTO = serverMapper.userLoginAO2UserDTO(userLoginAO);
        JsonResult jsonResult = userService.userLogin(userDTO);
        return jsonResult;
    }

    @ApiOperation("通过token")
    @PostMapping("/getUserInfoByToken")
    public JsonResult getUserInfoByToken(@RequestBody TokenAO token) {
        log.info("userController getUserInfoByToken token:{}", token);
        JsonResult jsonResult = userService.getUserInfoByToken(token.getToken());
        return jsonResult;
    }

}

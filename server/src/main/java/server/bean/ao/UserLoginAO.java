package server.bean.ao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2023/5/18
 */
@ApiModel("用户登录信息")
@Data
public class UserLoginAO {

    @ApiModelProperty("学号")
    private String studentId;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

}

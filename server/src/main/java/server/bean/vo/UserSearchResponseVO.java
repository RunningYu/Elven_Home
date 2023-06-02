package server.bean.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2023/5/16
 */
@ApiModel("用户")
@Data
@TableName("tb_user")
public class UserSearchResponseVO {

    @ApiModelProperty("学号")
    private String targetId;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("电话")
    private String phone;

    @ApiModelProperty("头像链接")
    private String  headPicture;

    @ApiModelProperty("token")
    private String token;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

}

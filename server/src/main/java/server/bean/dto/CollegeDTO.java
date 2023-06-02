package server.bean.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2023/5/16
 */
@ApiModel("高校")
@Data
public class CollegeDTO {

    @ApiModelProperty("高校id")
    private String collegeId;

    @ApiModelProperty("高校名称")
    private String collegeName;

    @ApiModelProperty("点赞量")
    private int likeNumber;

    @ApiModelProperty("收藏量")
    private int collectName;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;

}

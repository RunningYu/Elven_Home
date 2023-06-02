package server.bean.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2023/5/16
 */
@ApiModel("浏览历史")
@Data
public class HistoryDTO {

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("被浏览的内容的id")
    private String lookedId;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;

}

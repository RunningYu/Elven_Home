package server.bean.ao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2023/5/22
 */
@ApiModel("查询聊天记录的请求体")
@Data
public class RecordResponseAO {

    @ApiModelProperty("用户token")
    private String token;

    @ApiModelProperty("对方的学号")
    private String targetId;

    private Integer page;

    private Integer size;
}

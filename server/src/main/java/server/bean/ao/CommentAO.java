package server.bean.ao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2023/5/16
 */
@ApiModel("评论")
@Data
public class CommentAO {

    @ApiModelProperty("回复id")
    private String commentId;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("动态id")
    private String dynamicId;

    @ApiModelProperty("发评论或回复的用户id")
    private Integer userId;

    @ApiModelProperty("被回复的用户id")
    private Integer repliedUserId;
}

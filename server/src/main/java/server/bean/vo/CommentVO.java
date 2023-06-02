package server.bean.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class CommentVO {

    @ApiModelProperty("发评论或回复的用户id")
    private String userId;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("用户头像链接")
    private String userHeadPicture;

    @ApiModelProperty("被回复的用户id")
    private String repliedUserId;

    @ApiModelProperty("被回复的用户名")
    private String repliedUserName;

    @ApiModelProperty("被回复的用户头像链接")
    private String repliedUserHeadPicture;

    @ApiModelProperty("平论id")
    private String commentId;

    @ApiModelProperty("回复id")
    private String replyId;

    @ApiModelProperty("动态id")
    private String dynamicId;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("回复量")
    private int replyNumber;

    @ApiModelProperty("点赞量")
    private int likeNumber;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}

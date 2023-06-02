package server.bean.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2023/5/17
 */
@Data
public class DynamicVO {


    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("用户头像链接")
    private String headPicture;

    @ApiModelProperty("动态id")
    private String dynamicId;

    @ApiModelProperty("高校")
    private String collegeName;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("收藏量")
    private int collectNumber;

    @ApiModelProperty("点赞量")
    private int likeNumber;

    @ApiModelProperty("评论量")
    private int commentNumber;

    @ApiModelProperty("是否删除了， 0-未删除 1-已删除")
    private int isDelete;

    @ApiModelProperty("1-动态 2-问答")
    private int type;

    @ApiModelProperty("照片数组")
    private List<String> links;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}

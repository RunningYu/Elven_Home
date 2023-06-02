package server.bean.dto;

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
@ApiModel("动态")
@Data
@TableName("tb_dynamisk")
public class DynamiskDTO {

    @ApiModelProperty("动态id")
    private String dynamicId;

    @ApiModelProperty("用户id")
    private Integer userId;

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

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

}

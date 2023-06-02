package server.bean.dto;

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
@ApiModel("精灵")
@Data
@TableName("tb_elves")
public class ElvesDTO {

    @ApiModelProperty("精灵id")
    private String elvesId;

    @ApiModelProperty("头像")
    private String headPicture;

    @ApiModelProperty("高校")
    private String collegeName;

    @ApiModelProperty("精灵名字")
    private String name;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("外貌")
    private String appearances;

    @ApiModelProperty("第一次目击的地方")
    private String firstFindPlace;

    @ApiModelProperty("粉丝数")
    private int fans;

    @ApiModelProperty("是否存活 1-活着 0-死亡")
    private int alive;

    @ApiModelProperty("常出没的地方")
    private String place;

    @ApiModelProperty("健康情况")
    private String health;

    @ApiModelProperty("种类")
    private String kind;

    @ApiModelProperty("性格")
    private String personality;

    @ApiModelProperty("更多")
    private String more;

    @ApiModelProperty("点赞量")
    private int likeNumber;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

}

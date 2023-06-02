package server.bean.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2023/5/16
 */
@ApiModel("图片")
@Data
public class PictureDTO {

    @ApiModelProperty("绑定的id，如精灵id或是动态id")
    private String ownerId;

    @ApiModelProperty("图片id")
    private String pictureId;

    @ApiModelProperty("图片链接")
    private String link;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;
}

package server.bean.ao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2023/5/16
 */
@ApiModel("动态")
@Data
public class DynamiskAO {

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("高校")
    private String collegeName;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("是否删除了， 0-未删除 1-已删除")
    private int isDelete;

    @ApiModelProperty("1-动态 2-问答")
    private int type;

    @ApiModelProperty("图片数组")
    private List<String> links;


}

package server.bean.ao;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2023/5/29
 */
@Data
public class LikeAO {

    @ApiModelProperty("用户id")
    private String token;


    @ApiModelProperty("精灵id")
    private String elvesId;
}

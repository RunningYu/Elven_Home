package server.bean.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2023/5/22
 */
@ApiModel("聊天消息")
@Data
public class MessageDTO {

    private Integer id;

    /** 发送人的学号 */
    private String studentId;

    /** 目标接收ID（用户学号 or 群聊id） */
    private String targetStudentId;

    private String username;

    private String headPicture;

    private String content;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}

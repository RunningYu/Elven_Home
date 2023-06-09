package server.bean.ao;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2022/12/1
 */
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "UploadResponse")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadResponseAO {

    private String minIoUrl;

    private String nginxUrl;
}

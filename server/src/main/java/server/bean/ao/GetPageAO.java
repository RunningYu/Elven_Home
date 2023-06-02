package server.bean.ao;

import lombok.Data;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2023/5/29
 */
@Data
public class GetPageAO {

    private String token;

    private Integer page;

    private Integer size;

}

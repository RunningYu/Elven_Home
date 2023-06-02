package server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import server.bean.dto.DynamiskDTO;
import server.bean.vo.DynamicVO;

import java.util.List;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2023/5/17
 */
public interface DynamicService extends IService<DynamiskDTO> {
    void addDynamic(DynamiskDTO dynamiskDTO, List<String> pictureList);

    List<DynamicVO> getDynamicList(int page, int size);
}

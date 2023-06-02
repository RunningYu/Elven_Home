package server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import server.bean.ao.ElvenAO;
import server.bean.dto.ElvesDTO;
import server.bean.vo.ElvesVO;
import server.utils.JsonResult;

import java.util.List;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2023/5/16
 */
public interface ElvenService extends IService<ElvesDTO> {

    void addElven(ElvenAO elvenAO);

    List<ElvesVO> getElvenListByKind(String token, String kind, int page, int size);

    JsonResult like(String token, String elvesId);

    List<ElvesVO> getElvenHotList(String token, int page, int size);

    List<String> getElvenKindList();
}

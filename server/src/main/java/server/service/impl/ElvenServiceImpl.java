package server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import server.Mapper.ServerMapper;
import server.bean.ao.ElvenAO;
import server.bean.ao.UploadResponseAO;
import server.bean.dto.ElvesDTO;
import server.bean.dto.UserDTO;
import server.bean.vo.ElvesVO;
import server.dao.ElvenDao;
import server.dao.UserDao;
import server.service.ElvenService;
import server.utils.JsonResult;
import server.utils.MinioUtil;

import java.util.*;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2023/5/16
 */
@Slf4j
@Service
public class ElvenServiceImpl extends ServiceImpl<ElvenDao, ElvesDTO> implements ElvenService {

    @Autowired
    private MinioUtil minioUtil;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ServerMapper serverMapper;

    @Autowired
    private ElvenDao elvenDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addElven(ElvenAO elvenAO) {
        String elvenId = UUID.randomUUID().toString().replace("-", "1");
        elvenAO.setElvesId(elvenId);
        ElvesDTO elvesDTO = serverMapper.elvenAO2ElvesDTO(elvenAO);
        String headPicture = null;
        if (elvenAO.getLinks() != null) {
            elvesDTO.setHeadPicture(elvenAO.getLinks().get(0));
            for (String link : elvenAO.getLinks()) {
                // 存储精灵照片组
                elvenDao.addLink(elvenAO.getElvesId(), UUID.randomUUID().toString().replace("-", "1"), link);
            }
        }
        // 存储精灵进本信息体
        elvenDao.insert(elvesDTO);

    }

    @ApiOperation("图片上传，返回图片链接")
    public String minioUpload(MultipartFile file) {
        JsonResult jsonResult = new JsonResult(200);
        UploadResponseAO response = null;
        try {
            response = minioUtil.uploadFile(file, "file");
            jsonResult.setData(response);
            jsonResult.setMsg("上传成功");
            System.out.println(jsonResult);
            return response.getMinIoUrl();
        } catch (Exception e) {
            jsonResult.setMsg("上传失败");
            jsonResult.setCode(400);
        }
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<ElvesVO> getElvenListByKind(String token, String kind, int page, int size) {
        UserDTO userDTO = userDao.getUserFromToken(token);
        int userId = 0;
        if (!ObjectUtils.isEmpty(userDTO)) {
            userId = userDTO.getUserId();
        }
        int startIndex = (page - 1) * size;
        List<ElvesDTO> elvesDTOList = new ArrayList<>();
        if (kind.equals("-1")) {
            elvesDTOList = elvenDao.getElvenList(startIndex, size);
        } else {
            elvesDTOList = elvenDao.getElvenListByKind(kind, startIndex, size);
        }
        List<ElvesVO> elvesVOList = new ArrayList<>();
        for (ElvesDTO elvesDTO : elvesDTOList) {
            ElvesVO elvesVO = serverMapper.elvesDTO2ElvesVO(elvesDTO);
            List<String> picLinks = elvenDao.getPictureList(elvesDTO.getElvesId());
            elvesVO.setLinks(picLinks);
            if (!ObjectUtils.isEmpty(userId)) {
                int like = elvenDao.haveLike(userId, elvesDTO.getElvesId());
                elvesVO.setHaveLike(like > 0 ? 1 : 0);
            } else {
                elvesVO.setHaveLike(0);
            }
            elvesVOList.add(elvesVO);
        }
        return elvesVOList;
    }


    @Override
    public JsonResult like(String token, String targetId) {
        Map<String, Integer> map = new HashMap<>();
        UserDTO userDTO = userDao.getUserFromToken(token);
        int userId = 0;
        if (!ObjectUtils.isEmpty(userDTO)) {
            userId = userDTO.getUserId();
        }
        int haveLike = elvenDao.haveLike(userId, targetId);
        if (haveLike > 0) {
            elvenDao.disLike(userId, targetId);
            elvenDao.likeNumberSubtract(targetId);
            map.put("haveLike", 0);
            ElvesDTO elvesDTO = elvenDao.getElvenById(targetId);
            if (!ObjectUtils.isEmpty(elvesDTO)) {
                map.put("likeNumber", elvesDTO.getLikeNumber());
            }
            return JsonResult.success("successfully dislike", map);
        }
        elvenDao.like(userId, targetId);
        elvenDao.likeNumberAdd(targetId);
        map.put("haveLike", 1);
        ElvesDTO elvesDTO = elvenDao.getElvenById(targetId);
        if (!ObjectUtils.isEmpty(elvesDTO)) {
            map.put("likeNumber", elvesDTO.getLikeNumber());
        }
        return JsonResult.success("successfully like", map);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<ElvesVO> getElvenHotList(String token, int page, int size) {
        UserDTO userDTO = userDao.getUserFromToken(token);
        int userId = 0;
        if (!ObjectUtils.isEmpty(userDTO)) {
            userId = userDTO.getUserId();
        }
        int startIndex = (page - 1) * size;
        List<ElvesDTO> elvesDTOList = elvenDao.getElvenHotList(startIndex, size);
        List<ElvesVO> elvesVOList = new ArrayList<>();
        for (ElvesDTO elvesDTO : elvesDTOList) {
            System.out.println("--->" + elvesDTO);
            ElvesVO elvesVO = serverMapper.elvesDTO2ElvesVO(elvesDTO);
            List<String> picLinks = elvenDao.getPictureList(elvesDTO.getElvesId());
            elvesVO.setLinks(picLinks);
            if (!ObjectUtils.isEmpty(userId)) {
                int like = elvenDao.haveLike(userId, elvesDTO.getElvesId());
                elvesVO.setHaveLike(like > 0 ? 1 : 0);
            } else {
                elvesVO.setHaveLike(0);
            }
            elvesVOList.add(elvesVO);
        }
        return elvesVOList;
    }

    @Override
    public List<String> getElvenKindList() {
        List<String> kindList = elvenDao.getElvenKindList();
        return kindList;
    }
}

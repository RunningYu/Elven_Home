package server.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.Mapper.ServerMapper;
import server.bean.dto.DynamiskDTO;
import server.bean.dto.UserDTO;
import server.bean.vo.DynamicVO;
import server.dao.DynamicDao;
import server.dao.ElvenDao;
import server.dao.UserDao;
import server.service.DynamicService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2023/5/17
 */
@Service
@Slf4j
public class DynamicServiceImpl extends ServiceImpl<DynamicDao, DynamiskDTO> implements DynamicService {

    @Autowired
    private ElvenDao elvenDao;

    @Autowired
    private ServerMapper serverMapper;

    @Autowired
    private UserDao userDao;

    @Autowired
    private DynamicDao dynamicDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addDynamic(DynamiskDTO dynamiskDTO, List<String> pictureList) {
        dynamiskDTO.setDynamicId(UUID.randomUUID().toString().replace("-", "1"));
        dynamicDao.insert(dynamiskDTO);
        if (!ObjectUtil.isEmpty( pictureList )) {
            for(String link : pictureList) {
                // 存储图片
                elvenDao.addLink(dynamiskDTO.getDynamicId(), UUID.randomUUID().toString().replace("-", "1"), link);
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<DynamicVO> getDynamicList(int page, int size) {
        int startIndex = (page - 1) * size;
        List<DynamiskDTO> dynamiskList = dynamicDao.getDynamicList(startIndex, size);
        List<DynamicVO> dynamicVOList = new ArrayList<>();
        for ( DynamiskDTO dynamiskDTO : dynamiskList ) {
            UserDTO user = userDao.getUserByUserId(dynamiskDTO.getUserId());
            DynamicVO dynamicVO = serverMapper.dynamiskDTO2DynamicVO(dynamiskDTO);
            if ( !ObjectUtil.isEmpty(user) ) {
                dynamicVO.setUserName(user.getUsername());
                dynamicVO.setHeadPicture(user.getHeadPicture());
            }
            List<String> pictureList = elvenDao.getPictureList(dynamiskDTO.getDynamicId());
            dynamicVO.setLinks(pictureList);
            dynamicVOList.add(dynamicVO);
            log.info("dynamicVO:{}", dynamicVO);
        }

        return dynamicVOList;
    }

}

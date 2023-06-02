package server.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.Mapper.ServerMapper;
import server.bean.dto.UserDTO;
import server.bean.vo.UserVO;
import server.dao.UserDao;
import server.service.UserService;
import server.utils.JsonResult;
import server.utils.JwtTokenManager;
import server.utils.MD5;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2023/5/17
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserDao, UserDTO> implements UserService {


    @Autowired
    private ServerMapper serverMapper;

    @Autowired
    private JwtTokenManager jwtTokenManager;

    @Autowired
    private MD5 md5;

    @Autowired
    private UserDao userDao;

    @Override
    public JsonResult userLogin(UserDTO userDTO) {
        System.out.println( userDTO );
        // 先检查是否存在该学号
        if ( !haveThisStudentId( userDTO.getStudentId() ) ) {
            return JsonResult.error("There is no this studentId! Not CQUT student, can't login! ");
        }
        String mdCode = md5.encrypt(userDTO.getPassword());
        UserDTO user = userDao.getUserByStudentId(userDTO.getStudentId());
        if( ObjectUtil.isEmpty(user) ) {
            userDTO.setPassword(mdCode);
            userDao.insert(userDTO);
        } else {
            System.out.println("原密码：" + userDTO.getPassword());
            System.out.println( "加密后的密码 ：" + mdCode );
            // 判断密码是否正确
            if ( !user.getPassword().equals(mdCode) ) {
                log.info("UserServiceImpl userLogin -> result : (password is not right, can't login!)");
                return JsonResult.success("password is not right, can't login! ");
            }
        }
        UserDTO userDTO1 = userDao.getUserByStudentId(userDTO.getStudentId());
        UserVO userVO = serverMapper.userDTO2UserVO(userDTO1);
        // 登录成功后，生成token，生成 权限token
        String token = jwtTokenManager.createToken(userDTO1);
        userVO.setToken( token );
        userDao.updateUserToken( userDTO.getStudentId(), token );
        return JsonResult.success(userVO);
    }

    private boolean havaThisUser(String studentId) {
        int have = userDao.havaThisUser(studentId);
        return have == 0 ? false : true;
    }

    private boolean haveThisStudentId(String studentId) {
        int have = userDao.haveThisStudentId(studentId);
        System.out.println( "---------------->" + have );
        return have == 0 ? false : true;
    }

    @Override
    public void insertStudentId(String studentId, String collegeName) {
        userDao.insertStudentId(studentId, collegeName);
    }

    @Override
    public JsonResult getUserInfoByToken(String token) {
        UserDTO userDTO = userDao.getUserFromToken(token);
        System.out.println( userDTO );
        return JsonResult.success(userDTO);
    }
}

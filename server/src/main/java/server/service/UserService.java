package server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import server.bean.dto.UserDTO;
import server.utils.JsonResult;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2023/5/17
 */
public interface UserService extends IService<UserDTO> {
    JsonResult userLogin(UserDTO userDTO);

    void insertStudentId(String studentId, String collegeName);

    JsonResult getUserInfoByToken(String token);
}

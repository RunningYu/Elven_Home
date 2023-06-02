package server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import server.bean.dto.UserDTO;

import java.util.List;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2023/5/17
 */
@Mapper
public interface UserDao extends BaseMapper<UserDTO> {
    @Select("select * from tb_user where user_id = #{userId}")
    UserDTO getUserByUserId(Integer userId);

    @Insert("insert into tb_student_id (student_id, college_name) values (#{studentId}, #{collegeName})")
    void insertStudentId(String studentId, String collegeName);

//    @Insert("insert into tb_student_id (student_id, username, password) values (#{studentId}, #{username}, #{password})")
//    void insertStudentId(String studentId, String username, String password);

    @Select("select count(*) from tb_student_id where student_id = #{studentId}")
    int haveThisStudentId(String studentId);

    @Select("select count(*) from tb_user where student_id = #{studentId}")
    int havaThisUser(String studentId);

    @Select("select * from tb_user where student_id = #{studentId}")
    UserDTO getUserByStudentId(String studentId);

    @Update("update tb_user set token = #{token} where student_id = #{studentId}")
    void updateUserToken(String studentId, String token);

    @Select("select * from tb_user where token = #{token}")
    UserDTO getUserFromToken(String token);

    @Select("select * from tb_user where username like concat('%',#{keyword},'%') or student_id like concat('%',#{keyword},'%')")
    List<UserDTO> searchUserListByUsername(String keyword);
}

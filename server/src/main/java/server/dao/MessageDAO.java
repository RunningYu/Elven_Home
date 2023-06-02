package server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import server.bean.dto.UserDTO;
import server.bean.po.ChatTempPO;
import server.bean.po.MessagePO;

import java.util.List;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2023/5/22
 */
@Mapper
public interface MessageDAO extends BaseMapper<MessagePO> {
    @Select("select * from tb_message where ( student_id = #{studentId} and target_id = #{targetId} ) or ( student_id = #{targetId} and target_id = #{studentId} )" +
            "ORDER by create_time DESC limit #{startIndex}, #{size}")
    List<MessagePO> getMessageList(String studentId, String targetId, int startIndex, int size);

    @Select("select * from tb_message where student_id = #{studentId} ORDER by create_time DESC limit #{startIndex}, #{size}")
    List<MessagePO> getChatUserList(String studentId, int startIndex, int size);

    @Select("select * from tb_chat_record where student_id = #{studentId} order by update_time DESC limit #{startIndex}, #{size}")
    List<ChatTempPO> getChatTempPOList(String studentId, int startIndex, int size);

    @Select("select count(*) from tb_chat_record where student_id = #{studentId} and target_id = #{targetId}")
    int haveChatRecord(String studentId, String targetId);

    @Insert("insert into tb_chat_record(student_id, target_id) values (#{studentId}, #{targetId})")
    void chatRecord(String studentId, String targetId);

    @Select("select * from tb_message where student_id = #{studentId} and target_id = #{targetId} order by create_time DESC limit 0, 1")
    MessagePO getMessagePOByTargetId(String studentId, String targetId);

    @Update("update tb_chat_record set update_time = now() where student_id = #{studentId} and target_id = #{targetId}")
    void updateChatRecord(String studentId, String targetId, String content);

}

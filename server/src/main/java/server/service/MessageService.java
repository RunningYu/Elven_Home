package server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import server.bean.ao.RecordResponseAO;
import server.bean.dto.MessageDTO;
import server.bean.po.MessagePO;
import server.bean.vo.MessageVO;
import server.bean.vo.UserSearchResponseVO;

import java.util.List;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2023/5/22
 */
public interface MessageService extends IService<MessagePO> {
    void insertMessage(MessageDTO messageDTO);

    List<MessageVO> getMessageList(RecordResponseAO response);

    List<MessageVO> getChatUserList(String token, int page, int size);

    int haveChatRecord(String studentId, String targetId);

    void chatRecord(String studentId, String targetId);

    void updateChatRecord(String studentId, String targetId, String content);

    List<UserSearchResponseVO> searchUserListByUsername(String keyword);
}

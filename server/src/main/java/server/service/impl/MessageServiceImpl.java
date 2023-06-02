package server.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import server.Mapper.ServerMapper;
import server.bean.ao.RecordResponseAO;
import server.bean.dto.MessageDTO;
import server.bean.dto.UserDTO;
import server.bean.po.ChatTempPO;
import server.bean.po.MessagePO;
import server.bean.vo.MessageVO;
import server.bean.vo.UserSearchResponseVO;
import server.dao.MessageDAO;
import server.dao.UserDao;
import server.service.MessageService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2023/5/22
 */
@Service
@Slf4j
public class MessageServiceImpl extends ServiceImpl<MessageDAO, MessagePO> implements MessageService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ServerMapper serverMapper;

    @Autowired
    private MessageDAO messageDAO;

    @Override
    public void insertMessage(MessageDTO messageDTO) {
        log.info("MessageServiceImpl insertMessage messageDTO:{}", messageDTO);
        MessagePO messagePO = serverMapper.messageDTO2messagePO(messageDTO);
        messagePO.setTargetId(messageDTO.getTargetStudentId());
        log.info("MessageServiceImpl insertMessage messagePO:{}", messagePO);
        messageDAO.insert(messagePO);
    }

    @Override
    public List<MessageVO> getMessageList(RecordResponseAO response) {
        log.info("MessageServiceImpl insertMessage response:{}", response);
        int startIndex = (response.getPage() - 1) * response.getSize();
        UserDTO userDTO = userDao.getUserFromToken(response.getToken());
        String studentId = "";
        if (!ObjectUtils.isEmpty(userDTO)) {
            studentId = userDTO.getStudentId();
        }
        log.info("studentId:{} targetId:{} startIndex:{} size:{}", studentId, response.getTargetId(), startIndex, response.getSize() );
        List<MessagePO> messagePOS = messageDAO.getMessageList(studentId, response.getTargetId(), startIndex, response.getSize());
        List<MessageVO> messageVOList = new ArrayList<>();
        for (MessagePO messagePO : messagePOS) {
            MessageVO messageVO = serverMapper.messagePO2MessageVO(messagePO);
            messageVOList.add(messageVO);
        }
        return messageVOList;
    }

    @Override
    public List<MessageVO> getChatUserList(String token, int page, int size) {
        log.info("MessageServiceImpl insertMessage studentId:{} page:{} size:{}", token, page, size);
        UserDTO userDTO = userDao.getUserFromToken(token);
        String studentId = "-1";
        if ( !ObjectUtils.isEmpty(userDTO) ) {
            studentId = userDTO.getStudentId();
        }
        int startIndex = (page - 1) * size;
        List<ChatTempPO> chatTempPOS = messageDAO.getChatTempPOList(studentId, startIndex, size);
        System.out.println(JSONObject.toJSONString( chatTempPOS ));
        List<MessageVO> messageVOList = new ArrayList<>();
        for (ChatTempPO chatTempPO : chatTempPOS) {
            MessagePO messagePO = messageDAO.getMessagePOByTargetId(studentId, chatTempPO.getTargetId());
            MessageVO messageVO = serverMapper.messagePO2MessageVO(messagePO);
            messageVOList.add(messageVO);
            System.out.println( messageVO );
        }
        return messageVOList;
    }

    @Override
    public int haveChatRecord(String studentId, String targetId) {
        return messageDAO.haveChatRecord(studentId, targetId);
    }

    @Override
    public void chatRecord(String studentId, String targetId) {
        messageDAO.chatRecord(studentId, targetId);
    }

    @Override
    public void updateChatRecord(String studentId, String targetId, String content) {
        messageDAO.updateChatRecord(studentId, targetId, content);
    }

    @Override
    public List<UserSearchResponseVO> searchUserListByUsername(String keyword) {
        log.info("MessageServiceImpl insertMessage keyword:{}", keyword);
        List<UserDTO> userList = userDao.searchUserListByUsername(keyword);
        List<UserSearchResponseVO> responseVOList = new ArrayList<>();
        for ( UserDTO userDTO : userList ) {
            UserSearchResponseVO responseVO = serverMapper.userDTO2UserSearchResponseVO(userDTO);
            responseVO.setTargetId(userDTO.getStudentId());
            responseVOList.add(responseVO);
        }
        log.info("MessageServiceImpl insertMessage responseVOList:{}", responseVOList);
        return responseVOList;
    }
}

package server.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import server.bean.ao.*;
import server.bean.dto.*;
import server.bean.po.MessagePO;
import server.bean.vo.*;
import server.nettyIM.command.ChatMessage;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2023/5/16
 */
@Mapper(componentModel = "spring")
public interface ServerMapper {

    @Mappings({
            @Mapping(source = "userId", target = "userId")
    })
    DynamiskDTO dynamiskAO2DynamiskDTO(DynamiskAO dynamiskAO);

    ElvesDTO elvenAO2ElvesDTO(ElvenAO elvenAO);

    CommentDTO commentAO2CommentDTO(CommentAO commentAO);

    DynamicVO dynamiskDTO2DynamicVO(DynamiskDTO dynamiskDTO);

    CommentVO commentDTO2CommentVO(CommentDTO commentDTO);

    UserDTO userAO2UserDTO(UserAO userAO);

    UserDTO userLoginAO2UserDTO(UserLoginAO userLoginAO);

    @Mappings({
            @Mapping(source = "elvesId", target = "elvesId")
    })
    ElvesVO elvesDTO2ElvesVO(ElvesDTO elvesDTO);

    UserVO userDTO2UserVO(UserDTO userDTO1);

    MessagePO messageDTO2messagePO(MessageDTO messageDTO);

    MessageDTO chatMessage2MessageDTO(ChatMessage chat);

    MessageVO messagePO2MessageVO(MessagePO messagePO);

    MessageResponse messageDTO2MessageResponse(MessageDTO messageDTO);

    UserSearchResponseVO userDTO2UserSearchResponseVO(UserDTO userDTO);
}

package server.nettyIM.handler;

import com.alibaba.fastjson2.JSON;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import server.Mapper.ServerMapper;
import server.bean.dto.MessageDTO;
import server.bean.dto.UserDTO;
import server.bean.vo.MessageResponse;
import server.dao.UserDao;
import server.nettyIM.Common.Result;
import server.nettyIM.command.ChatMessage;
import server.nettyIM.enums.MessageType;
import server.nettyIM.im.IMserver;
import server.service.MessageService;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2023/5/22
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class ChatHandler {

    @Autowired
    private UserDao userDao;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ServerMapper serverMapper;

    public void execute(ChannelHandlerContext ctx, TextWebSocketFrame frame) {
        log.info("ChatHandler execute --> fram:{}", frame.text());

        ChatMessage chat = JSON.parseObject(frame.text(), ChatMessage.class);
        log.info("ChatHandler execute --> chat:{}", chat);

        // 判断是否已经有同名的人（即同一个人）已经在线
        if (!IMserver.USERS.containsKey(chat.getToken())) {
            // 加到映射表里
            IMserver.USERS.put(chat.getToken(), ctx.channel());
        }

        UserDTO userDTO = userDao.getUserFromToken(chat.getToken());
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setContent( chat.getContent() );
        log.info("ChatHandler execute --> messageDTO:{}", messageDTO);
        log.info("ChatHandler execute --> userDTO:{}", userDTO);
        // 封装发送者的学号、用户名、头像
        if (!ObjectUtils.isEmpty(userDTO)) {
            messageDTO.setStudentId(userDTO.getStudentId());
            messageDTO.setUsername(userDTO.getUsername());
            messageDTO.setHeadPicture(userDTO.getHeadPicture());
        }
        UserDTO targetUserDTO = new UserDTO();
        // 封装接收者的学号
        if (!StringUtil.isNullOrEmpty(chat.getTargetToken())) {
//            targetUserDTO = userDao.getUserFromToken(chat.getTargetToken());
            String targetStudentId = chat.getTargetToken();
            targetUserDTO = userDao.getUserByStudentId(targetStudentId);
            messageDTO.setTargetStudentId( targetUserDTO.getStudentId() );
        }

        // 封装要发送的消息体
        MessageResponse msgResponse = buildMessageResponse(messageDTO, userDTO, targetUserDTO);
        log.info("ChatHandler execute --> msgResponse:{}", msgResponse);
        switch (MessageType.match(chat.getType())) {
            // 私聊消息
            case PRIVATE: {
                log.info("ChatHandler execute PRIVATE");
                if (StringUtil.isNullOrEmpty(chat.getTargetToken())) {
                    ctx.channel().writeAndFlush(Result.fail("消息发送失败，targetToken=null，发送消息前请指定接收对象targetToken"));
                    log.info("ChatHandler execute PRIVATE --> " + "消息发送失败，targetToken=null，发送消息前请指定接收对象targetToken");
                    return;
                }
                String targetToken = targetUserDTO.getToken();
                Channel targetChannel = IMserver.USERS.get(targetToken);
                // 如果接收方在线，就写消息
                if (null != targetChannel) {
                    targetChannel.writeAndFlush( new TextWebSocketFrame(JSON.toJSONString(msgResponse)) );
                    log.info("ChatHandler execute  --> PRIVATE 私聊消息: " + JSON.toJSONString(msgResponse));
                }
//                ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(msgResponse)));
                // 记录聊天信息
                doMessage(messageDTO);
                break;
            }

            // 群聊消息 ChannelGroup 发送消息，会给每一个注册进 ChannelGroup 中的channel发送消息
            case GROUP: {
                log.info("GROUP------------------------>群聊");
                for (Channel channel : IMserver.USERS.values()) {
                    if ( channel == ctx.channel() ) {
                        continue;
                    }
                    channel.writeAndFlush( new TextWebSocketFrame(JSON.toJSONString(msgResponse)) );
                }
                doMessage(messageDTO);
                break;
            }
            default: {
                ctx.channel().writeAndFlush(Result.fail("不支持消息类型"));
                log.info("ChatHandler execute 不支持消息类型 chat:{}", JSON.toJSONString(chat));
                break;
            }
        }
    }

    private MessageResponse buildMessageResponse(MessageDTO messageDTO, UserDTO userDTO, UserDTO targetUserDTO) {
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setStudentId( userDTO.getStudentId() );
        messageResponse.setUsername( userDTO.getUsername() );
        messageResponse.setHeadPicture( userDTO.getHeadPicture() );
        messageResponse.setTargetId( targetUserDTO.getStudentId() );
        messageResponse.setTargetUsername( targetUserDTO.getUsername() );
        messageResponse.setTargetHeadPicture( targetUserDTO.getHeadPicture() );
        messageResponse.setContent( messageDTO.getContent() );
        messageResponse.setToken( userDTO.getToken() );
        messageResponse.setTargetToken(targetUserDTO.getToken());
        return messageResponse;
    }

    private void doMessage(MessageDTO messageDTO) {
        messageService.insertMessage(messageDTO);
        log.info("----------------> have inserted" );
        int have = messageService.haveChatRecord(messageDTO.getStudentId(), messageDTO.getTargetStudentId());
        log.info("------------>have : " + have);
        if (have == 0) {
            messageService.chatRecord(messageDTO.getStudentId(), messageDTO.getTargetStudentId());
        } else {
            messageService.updateChatRecord(messageDTO.getStudentId(), messageDTO.getTargetStudentId(), messageDTO.getContent());
        }
    }
}

package server.nettyIM.handler;

import com.alibaba.fastjson2.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import server.nettyIM.Common.Result;
import server.nettyIM.command.ChatMessage;
import server.nettyIM.enums.CommandType;

import javax.annotation.PostConstruct;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2023/5/22
 */
@Slf4j
@Component
public class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Autowired
    private ChatHandler chatHandler;

    private ConnectionHandler connectionHandler = new ConnectionHandler();

    private static WebSocketHandler webSocketHandler;

    /** 因为是new出来的handler,没有托给spring容器,所以一定要先初始化,否则autowired失效 */
    @PostConstruct
    public void init() {
        webSocketHandler = this;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame frame) throws Exception {
        log.info("WebSocketHandler channelRead0 frame.text():{}", frame.text());
//        try {
            ChatMessage chatMessage = JSON.parseObject(frame.text(), ChatMessage.class);
            log.info("WebSocketHandler channelRead0 chatMessage:{}", JSON.toJSONString(chatMessage));
//            webSocketHandler.chatHandler.execute(ctx, frame);

            switch (chatMessage.getCode()) {
                case CommandType.CONNECTION : {
                    connectionHandler.execute(ctx, chatMessage);
                    break;
                }
                case CommandType.CHAT : {
                    webSocketHandler.chatHandler.execute(ctx, frame);
                    break;
                }
                case CommandType.JOIN_GROUP : {
                    JoinGroupHandler.execute(ctx);
                    break;
                }
                default : {
                    ctx.channel().writeAndFlush(Result.fail("不支持的CODE"));
                    break;
                }
            }
            log.info("WebSocketHandler channelRead0 --> end");
//        }
//        catch (Exception e) {
//            ctx.channel().writeAndFlush(Result.fail(e.getMessage()));
//        }

    }
}

package server.nettyIM.handler;

import io.netty.channel.ChannelHandlerContext;
import server.nettyIM.Common.Result;
import server.nettyIM.im.IMserver;

/**
 * @author : 其然乐衣Letitbe
 * @date : 2023/5/22
 */
public class JoinGroupHandler {
    public static void execute(ChannelHandlerContext ctx) {
        IMserver.GROUP.add(ctx.channel());
        ctx.channel().writeAndFlush(Result.success("加入系统默认群聊成功~"));
    }
}

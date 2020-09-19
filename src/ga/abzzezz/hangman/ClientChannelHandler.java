package ga.abzzezz.hangman;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientChannelHandler extends SimpleChannelInboundHandler<String> {

    @Override
    public void handlerAdded(final ChannelHandlerContext ctx) throws Exception {
        Logger.getAnonymousLogger().log(Level.INFO, "connected to source");
        super.handlerAdded(ctx);
    }

    @Override
    protected void channelRead0(final ChannelHandlerContext channelHandlerContext, final String readLine) throws Exception {
        Main.MAIN_CLIENT.getPacketManager().handlePacket(readLine);
    }
}

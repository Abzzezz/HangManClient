package ga.abzzezz.hangman;

import ga.abzzezz.hangman.packet.PacketManager;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client extends Thread {

    public static final int PORT = 1010;
    private PacketManager packetManager;

    @Override
    public void run() {
        final EventLoopGroup group = new NioEventLoopGroup();
        try {
            final Bootstrap bootstrap = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class).handler(new ClientInitializer());
            packetManager = new PacketManager(bootstrap.connect("localhost", PORT).sync().channel());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public PacketManager getPacketManager() {
        return packetManager;
    }
}

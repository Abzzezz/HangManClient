package ga.abzzezz.hangman.packet;

import ga.abzzezz.hangman.packet.packets.*;
import io.netty.channel.Channel;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PacketManager {

    private final List<Packet> packets = new ArrayList<>();
    private final Channel channel;

    public PacketManager(final Channel channel) {
        this.channel = channel;
        packets.add(new CreateRoomPacket());
        packets.add(new JoinRoomPacket());
        packets.add(new PlayerJoinPacket());
        packets.add(new SendWordPacket());
        packets.add(new WordRevealPacket());
        packets.add(new PlayerUpdatePacket());
    }

    /**
     * Handle Packet based on input string and if needed: respond
     *
     * @param readLine read line
     */
    public void handlePacket(final String readLine) {
        final JSONObject receivedPacketJson = new JSONObject(readLine);
        this.getPacket(receivedPacketJson.getString(PacketFormatter.PACKET_KEY)).ifPresent(foundPacket -> {
            final String message = receivedPacketJson.getString(PacketFormatter.MESSAGE_KEY);

            final boolean extraAvailable = receivedPacketJson.getBoolean(PacketFormatter.EXTRA_DATA_AVAILABLE_KEY);
            if (extraAvailable)
                foundPacket.setAddData(receivedPacketJson.getJSONObject(PacketFormatter.EXTRA_DATA_KEY));

            foundPacket.receive(message);

            foundPacket.respond(message).ifPresent(responseString -> channel.writeAndFlush(PacketFormatter.formatPacket(foundPacket, responseString)));
        });
    }

    /**
     * Send specific packet through print-stream
     *
     * @param packet Packet to send
     */
    public void sendPacket(final Packet packet) {
        packet.send().ifPresent(sendString -> {
            Logger.getAnonymousLogger().log(Level.INFO, "Sending packet: " + packet.getClass());
            channel.writeAndFlush(PacketFormatter.formatPacket(packet, sendString));
        });
    }


    private Optional<Packet> getPacket(final String packetId) {
        return packets.stream().filter(packet -> packet.getPacketId().equals(packetId)).findFirst();
    }

}

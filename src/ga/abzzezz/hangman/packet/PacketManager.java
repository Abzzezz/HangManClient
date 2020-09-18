package ga.abzzezz.hangman.packet;

import ga.abzzezz.hangman.packet.packets.*;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PacketManager {

    private final List<Packet> packets = new ArrayList<>();

    public PacketManager() {
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
     * @param readLine    read line
     * @param printWriter print stream to print response to
     */
    public void handlePacket(final String readLine, final PrintWriter printWriter) {
        final JSONObject receivedPacketJson = new JSONObject(readLine);
        this.getPacket(receivedPacketJson.getString(PacketFormatter.PACKET_KEY)).ifPresent(foundPacket -> {
            final String message = receivedPacketJson.getString(PacketFormatter.MESSAGE_KEY);

            final boolean extraAvailable = receivedPacketJson.getBoolean(PacketFormatter.EXTRA_DATA_AVAILABLE_KEY);
            if (extraAvailable)
                foundPacket.setAddData(receivedPacketJson.getJSONObject(PacketFormatter.EXTRA_DATA_KEY));

            foundPacket.receive(message);

            foundPacket.respond(message).ifPresent(responseString -> {
                printWriter.println(PacketFormatter.formatPacket(foundPacket, responseString));
                printWriter.flush();
            });
        });
    }

    /**
     * Send specific packet through print-stream
     *
     * @param packet      Packet to send
     * @param printWriter print stream to write to
     */
    public void sendPacket(final Packet packet, final PrintWriter printWriter) {
        packet.send().ifPresent(sendString -> {
            Logger.getAnonymousLogger().log(Level.INFO, "Sending packet: " + packet.getClass());
            printWriter.println(PacketFormatter.formatPacket(packet, sendString));
            printWriter.flush();
        });
    }


    private Optional<Packet> getPacket(final String packetId) {
        return packets.stream().filter(packet -> packet.getPacketId().equals(packetId)).findFirst();
    }

}

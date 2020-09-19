package ga.abzzezz.hangman.packet.packets;

import ga.abzzezz.hangman.packet.Packet;
import ga.abzzezz.hangman.util.Holder;

import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JoinRoomPacket extends Packet {

    private String playerName;

    /**
     * Packet id constructor with given packet identifier
     */
    public JoinRoomPacket() {
        super("ROOM_JOIN");
    }

    public JoinRoomPacket(final String playerName) {
        super("ROOM_JOIN");
        this.playerName = playerName;
    }

    @Override
    public Optional<String> respond(String input) {
        return Optional.empty();
    }

    @Override
    public Optional<String> send() {
        Holder.thisPlayer = UUID.randomUUID().toString();

        getMoreData().put("player_name", playerName).put("player_identification", Holder.thisPlayer);
        return Optional.of(Holder.roomId);
    }

    @Override
    public void receive(final String input) {
        if (input.equals("200")) {
            Logger.getAnonymousLogger().log(Level.INFO, input);
        } else {
            Logger.getAnonymousLogger().log(Level.WARNING, "Couldn't join room");
        }
    }
}

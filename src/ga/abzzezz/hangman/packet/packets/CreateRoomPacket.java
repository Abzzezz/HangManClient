package ga.abzzezz.hangman.packet.packets;

import ga.abzzezz.hangman.packet.Packet;
import ga.abzzezz.hangman.util.Holder;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateRoomPacket extends Packet {

    public CreateRoomPacket() {
        super("ROOM_CREATE");
    }

    @Override
    public Optional<String> respond(String input) {
        return Optional.empty();
    }

    @Override
    public Optional<String> send() {
        return Optional.of("NULL");
    }

    @Override
    public void receive(String input) {
        Logger.getAnonymousLogger().log(Level.INFO, "Received room id: " + input);
        Holder.roomId = input;
    }

}

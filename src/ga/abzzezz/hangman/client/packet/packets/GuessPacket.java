package ga.abzzezz.hangman.client.packet.packets;

import ga.abzzezz.hangman.client.packet.Packet;
import ga.abzzezz.hangman.util.Holder;

import java.util.Optional;

public class GuessPacket extends Packet {

    private final char aChar;

    public GuessPacket(final char aChar) {
        super("GUESS_LETTER");
        this.aChar = aChar;
    }

    @Override
    public Optional<String> respond(String input) {
        return Optional.empty();
    }

    @Override
    public Optional<String> send() {
        if(Holder.word.isEmpty()) return Optional.empty();

        getMoreData().put("letter", aChar);
        getMoreData().put("player_identification", Holder.thisPlayer);
        return Optional.of(Holder.roomId);
    }

    @Override
    public void receive(final String input) {
    }
}

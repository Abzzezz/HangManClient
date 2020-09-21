package ga.abzzezz.hangman.client.packet.packets;


import ga.abzzezz.hangman.client.packet.Packet;
import ga.abzzezz.hangman.util.Holder;

import java.util.Optional;

public class WordRevealPacket extends Packet {

    public WordRevealPacket() {
        super("WORD_REVEAL");
    }

    @Override
    public Optional<String> respond(final String input) {
        return Optional.empty();
    }

    @Override
    public Optional<String> send() {
        return Optional.empty();
    }

    @Override
    public void receive(final String input) {
        Holder.tries = getAddData().getInt("tries");
        Holder.word = input;
    }
}

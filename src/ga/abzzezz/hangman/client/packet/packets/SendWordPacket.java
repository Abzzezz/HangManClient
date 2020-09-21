package ga.abzzezz.hangman.client.packet.packets;

import ga.abzzezz.hangman.client.packet.Packet;
import ga.abzzezz.hangman.util.Holder;

import javax.swing.*;
import java.util.Optional;

public class SendWordPacket extends Packet {

    public SendWordPacket() {
        super("WORD_SELECT");
    }

    @Override
    public Optional<String> respond(final String input) {
        getMoreData().put("chosen_word", JOptionPane.showInputDialog("Word"));
        return Optional.of(Holder.roomId);
    }

    @Override
    public Optional<String> send() {
        return Optional.empty();
    }

    @Override
    public void receive(String input) {

    }
}

package ga.abzzezz.hangman.client.packet.packets;


import ga.abzzezz.hangman.client.packet.Packet;
import ga.abzzezz.hangman.util.Holder;
import ga.abzzezz.hangman.util.Player;
import org.json.JSONObject;

import java.util.Optional;

public class PlayerJoinPacket extends Packet {

    public PlayerJoinPacket() {
        super("PLAYER_JOIN");
    }

    @Override
    public Optional<String> respond(String input) {
        return Optional.empty();
    }

    @Override
    public Optional<String> send() {
        return Optional.empty();
    }

    @Override
    public void receive(final String input) {
        final JSONObject playerData = getAddData().getJSONObject("player_joined");
        Holder.PLAYERS.add(new Player(playerData.getString("player_name"), playerData.getInt("player_score")));
    }
}

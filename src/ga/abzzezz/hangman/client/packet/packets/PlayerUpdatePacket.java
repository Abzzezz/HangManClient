package ga.abzzezz.hangman.client.packet.packets;


import ga.abzzezz.hangman.client.packet.Packet;
import ga.abzzezz.hangman.util.Holder;
import org.json.JSONObject;

import java.util.Optional;

public class PlayerUpdatePacket extends Packet {

    public PlayerUpdatePacket() {
        super("PLAYER_UPDATE");
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
        final JSONObject playerObject = getAddData().getJSONObject("player");
        Holder.PLAYERS.stream().filter(player -> player.getPlayerName().equals(playerObject.getString("player_name"))).findAny().ifPresent(player -> player.setPlayerScore(playerObject.getInt("player_score")));
    }
}

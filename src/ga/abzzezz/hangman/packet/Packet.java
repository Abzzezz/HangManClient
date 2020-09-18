package ga.abzzezz.hangman.packet;

import org.json.JSONObject;

import java.util.Optional;

public abstract class Packet {

    //The packets id
    private final String packetId;
    //Packets own json
    protected JSONObject moreData;
    //JSON object to source additional data from
    private JSONObject addData;

    /**
     * Packet id constructor with given packet identifier
     *
     * @param packetId packet id
     */
    public Packet(final String packetId) {
        this.packetId = packetId;
        this.moreData = new JSONObject();
    }

    /**
     * Respond to received input
     *
     * @param input received input
     * @return Optional JSON response
     */
    public abstract Optional<String> respond(final String input);

    public abstract Optional<String> send();

    public abstract void receive(final String input);

    public JSONObject getMoreData() {
        return moreData;
    }

    public void setMoreData(JSONObject moreData) {
        this.moreData = moreData;
    }

    public JSONObject getAddData() {
        return addData;
    }

    public void setAddData(JSONObject addData) {
        this.addData = addData;
    }

    public String getPacketId() {
        return packetId;
    }
}

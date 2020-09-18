package ga.abzzezz.hangman;

import ga.abzzezz.hangman.packet.packets.CreateRoomPacket;
import ga.abzzezz.hangman.packet.packets.JoinRoomPacket;
import ga.abzzezz.hangman.screens.RoomScreen;
import ga.abzzezz.hangman.util.Holder;
import net.bplaced.abzzezz.EngineCore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static final Client MAIN_CLIENT = new Client();

    public static void main(final String[] args) throws IOException {
        Main.MAIN_CLIENT.start();

        System.out.println("Please enter room-id. Write your player name behind the id separated by a colon.");

        String line;
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while ((line = bufferedReader.readLine()) != null) {
            if (line.equals("cr")) {
                Main.MAIN_CLIENT.getPacketManager().sendPacket(new CreateRoomPacket(), Main.MAIN_CLIENT.getWriter());
            } else {
                final String[] colonSplit = line.split(":");
                Holder.roomId = colonSplit[0];
                Main.MAIN_CLIENT.getPacketManager().sendPacket(new JoinRoomPacket(colonSplit[1]), Main.MAIN_CLIENT.getWriter());
                final EngineCore engineCore = new EngineCore(600, 600, new RoomScreen());
                engineCore.start();
            }
        }
        bufferedReader.close();
    }

}

package ga.abzzezz.hangman;

import ga.abzzezz.hangman.client.Client;
import ga.abzzezz.hangman.client.packet.packets.CreateRoomPacket;
import ga.abzzezz.hangman.client.packet.packets.JoinRoomPacket;
import ga.abzzezz.hangman.screens.RoomScreen;
import ga.abzzezz.hangman.util.Holder;
import net.bplaced.abzzezz.EngineCore;

import javax.swing.*;

public class Main {

    public static final Client MAIN_CLIENT = new Client();

    public static void main(final String[] args) throws Exception {
        Main.MAIN_CLIENT.start();

        boolean x = true;

        while (x) {
            final String s = JOptionPane.showInputDialog("Please enter room-id. Write your player name behind the id separated by a colon.");
            if (s.equals("cr")) {
                Main.MAIN_CLIENT.getPacketManager().sendPacket(new CreateRoomPacket());
            } else {
                final String[] colonSplit = s.split(":");
                Holder.roomId = colonSplit[0];
                Main.MAIN_CLIENT.getPacketManager().sendPacket(new JoinRoomPacket(colonSplit[1]));
                final EngineCore engineCore = new EngineCore(600, 600, new RoomScreen());
                engineCore.start();
                x = false;
            }
        }
    }

}

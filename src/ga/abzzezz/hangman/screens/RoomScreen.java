/*
 * Copyright (c) 2020. Roman P.
 * All code belongs to its owners!
 * Last modified: 29.04.20, 14:02
 * APIS used:
 * LWJGL (https://www.lwjgl.org/)
 * Slick (http://slick.ninjacave.com/slick-util/)
 * Abzzezz Util (https://github.com/Abzzezz/AbzzezzUtil)
 */

package ga.abzzezz.hangman.screens;

import ga.abzzezz.hangman.Main;
import ga.abzzezz.hangman.packet.packets.GuessPacket;
import ga.abzzezz.hangman.util.Holder;
import ga.abzzezz.hangman.util.Player;
import net.bplaced.abzzezz.ui.Screen;
import net.bplaced.abzzezz.ui.uicomponents.Button;
import net.bplaced.abzzezz.ui.uicomponents.TextField;
import net.bplaced.abzzezz.utils.FontUtil;

import java.awt.*;

public class RoomScreen extends Screen {

    public FontUtil fontUtil;
    private TextField letter;

    @Override
    public void init() {
        fontUtil = new FontUtil("Roboto-Light", 15);
        getUiComponents().add(new Button(0, "Submit", getWidth() / 2, getHeight() / 2, 100, 20));
        getUiComponents().add(letter = new TextField(getWidth() / 2 - 200, getHeight() / 2, 100, 20, "Letter"));
        super.init();
    }

    @Override
    public void buttonPressed(float buttonID) {
        if (buttonID == 0) {
            if (!letter.toString().isEmpty()) {
                Main.MAIN_CLIENT.getPacketManager().sendPacket(new GuessPacket(letter.toString().charAt(0)), Main.MAIN_CLIENT.getWriter());
                letter.deleteAllText();
            }
        }
        super.buttonPressed(buttonID);
    }

    @Override
    public void keyTyped(int keyCode, char keyTyped) {
        super.keyTyped(keyCode, keyTyped);
    }

    @Override
    public void drawScreen() {
        fontUtil.drawString("Room id: " + Holder.roomId, 0, 0, Color.BLACK);
        fontUtil.drawString("Word: " + Holder.word, getWidth() / 2 - fontUtil.getStringWidth("Word: " + Holder.word) / 2, getHeight() / 4, Color.BLACK);
        fontUtil.drawString("Players:", 0, 25, Color.BLACK);

        final String triesString = "Tries Left: " + Holder.tries;
        fontUtil.drawString(triesString, getWidth() - fontUtil.getStringWidth(triesString) - 5, 0, Color.BLACK);

        int yStack = 40;
        for (final Player player : Holder.PLAYERS) {
            final String s = player.getPlayerName() + ": " + player.getPlayerScore();
            fontUtil.drawString(s, 0, yStack, Color.BLACK);
            yStack += fontUtil.getHeight();
        }
        super.drawScreen();
    }

    @Override
    public void drawShader() {
        super.drawShader();
    }
}
package ga.abzzezz.hangman;

import ga.abzzezz.hangman.packet.PacketManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client extends Thread {


    private final PacketManager packetManager = new PacketManager();
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;

    /**
     * Connect to server on default port: 1010
     */
    public Client() {
        try {
            this.socket = new Socket("2A02:908:1080:6D20:F001:81C2:BE78:BFE6", 1010);
            this.writer = new PrintWriter(socket.getOutputStream());
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.run();
    }


    public void connect() throws Exception {
        Logger.getAnonymousLogger().log(Level.INFO, "Connecting to sever");
        while (true) {

            while (reader.ready()) {
                packetManager.handlePacket(reader.readLine(), writer);
            }
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public PrintWriter getWriter() {
        return writer;
    }

    public BufferedReader getReader() {
        return reader;
    }

    public PacketManager getPacketManager() {
        return packetManager;
    }
}

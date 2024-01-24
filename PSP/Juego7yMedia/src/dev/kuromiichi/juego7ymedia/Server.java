package dev.kuromiichi.juego7ymedia;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;

public class Server {
    private static final int MAX_SIZE = 1024;
    private static final int PORT = 6119;
    private static final int MAX_PLAYERS_DEFAULT = 2;
    private static final HashMap<String, Player> players = new HashMap<>();

    static class Player {
        String name;
        InetAddress address;
        int port;
        int score = 0;

        Player(String name, InetAddress address, int port) {
            this.name = name;
            this.address = address;
            this.port = port;
        }
    }

    public static void main(String[] args) {
        int maxPlayers = MAX_PLAYERS_DEFAULT;
        if (args.length == 1) {
            maxPlayers = Integer.parseInt(args[0]);
        }

        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            addPlayers(socket, maxPlayers);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void gameLoop(DatagramSocket socket) throws IOException {
        System.out.println("Iniciando el juego");

    }

    private static void addPlayers(DatagramSocket socket, int maxPlayers) throws IOException {
        System.out.println("Listening on port " + PORT);
        do {
            DatagramPacket receivedPacket = receivePacket(socket);

            String playerMessage = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
            if (!playerMessage.startsWith("player ")) continue;

            String playerName = playerMessage.replaceFirst("player ", "");
            InetAddress playerAddress = receivedPacket.getAddress();
            int playerPort = receivedPacket.getPort();

            if (players.containsKey(playerName)) {
                sendPacket(socket, playerAddress, playerPort,
                        ("El jugador '" + playerName + "' ya existe").getBytes());
                continue;
            }

            Player player = new Player(playerName, playerAddress, playerPort);
            players.put(playerName, player);
            System.out.println("Se ha unido a la partida: " + playerName);
            sendPacket(socket, playerAddress, playerPort,
                    "Te has unido a la partida. Esperando para iniciar...".getBytes());
        } while (players.size() < maxPlayers);
    }

    private static void sendPacket(
            DatagramSocket socket, InetAddress clientAddress, int clientPort, byte[] message
    ) throws IOException {
        DatagramPacket sentPacket = new DatagramPacket(message, message.length, clientAddress, clientPort);
        socket.send(sentPacket);
    }

    private static DatagramPacket receivePacket(DatagramSocket socket) throws IOException {
        byte[] buffer = new byte[MAX_SIZE];
        DatagramPacket receivedPacket = new DatagramPacket(buffer, buffer.length);
        socket.receive(receivedPacket);
        return receivedPacket;
    }
}

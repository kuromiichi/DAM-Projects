package dev.kuromiichi.juego7ymedio;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.DecimalFormat;
import java.util.*;

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

    static class Deck {
        private final ArrayList<Integer> cards;

        Deck() {
            cards = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                cards.addAll(List.of(10, 20, 30, 40, 50, 60, 70, 5, 5, 5));
            }
            Collections.shuffle(cards);
        }

        int drawCard() {
            return cards.removeFirst();
        }
    }

    public static void main(String[] args) {
        int maxPlayers = MAX_PLAYERS_DEFAULT;
        if (args.length == 1) {
            maxPlayers = Integer.parseInt(args[0]);
        }

        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            addPlayers(socket, maxPlayers);
            gameLoop(socket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void gameLoop(DatagramSocket socket) throws IOException {
        System.out.println("Iniciando el juego");

        DecimalFormat df = new DecimalFormat("#.#");
        Deck deck = new Deck();
        ArrayList<String> playersInTurn = new ArrayList<>(players.keySet());

        while (!playersInTurn.isEmpty()) {
            Iterator<String> iterator = playersInTurn.iterator();
            while (iterator.hasNext()) {
                String playerName = iterator.next();
                System.out.println("Turno de " + playerName);
                Player player = players.get(playerName);
                int card = deck.drawCard();
                player.score += card;
                String message = "Tu carta te da " + df.format(card / 10.0) + " punto" +
                        (card == 1 ? "" : "s") + ". " + "Tu puntuación actual es de " +
                        df.format(player.score / 10.0) + " punto" +
                        (player.score == 1 ? "" : "s") + ".";
                sendPacket(socket, player.address, player.port, message.getBytes());

                if (player.score > 75) {
                    sendPacket(socket, player.address, player.port,
                            "Te has pasado de 7 y medio. Has perdido la ronda.".getBytes());
                    iterator.remove();
                    sendPacket(socket, player.address, player.port,
                            "Esperando a que acabe el turno...".getBytes());
                } else {
                    sendPacket(socket, player.address, player.port,
                            "¿Quieres pedir otra carta? (s/n)".getBytes());
                    sendPacket(socket, player.address, player.port, "continue".getBytes());
                }
            }

            int remainingRequests = playersInTurn.size();
            while (remainingRequests > 0) {
                DatagramPacket receivedPacket = receivePacket(socket);
                String response = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
                if (response.startsWith("n ")) {
                    String playerName = response.substring(2);
                    playersInTurn.remove(playerName);
                    sendPacket(socket, receivedPacket.getAddress(), receivedPacket.getPort(),
                            "Esperando a que acabe el turno...".getBytes());
                    remainingRequests--;
                } else if (response.startsWith("s ")) {
                    sendPacket(socket, receivedPacket.getAddress(), receivedPacket.getPort(),
                            "Esperando a que acabe el turno...".getBytes());
                    remainingRequests--;
                }
            }
        }


        String endMessage = "El juego ha terminado.\nLos resultados son:";
        System.out.println(endMessage);
        for (Player p : players.values()) {
            if (p.score <= 75) {
                System.out.println(p.name + ": " + df.format(p.score / 10.0) + " puntos");
            } else {
                System.out.println(p.name + " perdió la ronda");
            }
        }

        for (Player player : players.values()) {
            sendPacket(socket, player.address, player.port, endMessage.getBytes());
            for (Player p : players.values()) {
                if (p.score <= 75) {
                    sendPacket(socket, player.address, player.port,
                            (p.name + ": " + df.format(p.score / 10.0) + " puntos").getBytes());
                } else {
                    sendPacket(socket, player.address, player.port,
                            (p.name + " perdió la ronda").getBytes());
                }
            }
            sendPacket(socket, player.address, player.port, "endround".getBytes());
        }
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

package dev.kuromiichi.juego7ymedio;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.PortUnreachableException;
import java.util.Scanner;

public class Client {
    private static final int MAX_SIZE = 1024;
    private static final String HOST = "localhost";
    private static final int PORT = 6119;

    public static void main(String[] args) {
        String playerName = "Player";
        if (args.length == 1) {
            playerName = args[0];
        }

        try (DatagramSocket socket = new DatagramSocket()) {
            joinGame(socket, playerName);
            play(socket, playerName);
        } catch (PortUnreachableException e) {
            System.out.println("No se ha podido conectar con el servidor");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void play(DatagramSocket socket, String playerName) throws IOException {
        while (true) {
            DatagramPacket receivedPacket = receivePacket(socket);
            String message = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
            if (message.equals("continue")) {
                Scanner scanner = new Scanner(System.in);
                String answer;
                do {
                    answer = scanner.nextLine();
                    if (!answer.equals("s") && !answer.equals("n")) {
                        System.out.println("Respuesta inválida");
                    }
                } while (!answer.equals("s") && !answer.equals("n"));
                answer += " " + playerName;
                sendPacket(socket, InetAddress.getByName(HOST), PORT, answer.getBytes());
            } else if (message.equals("endround")) {
                break;
            } else {
                System.out.println(message);
            }
        }
    }

    private static void joinGame(DatagramSocket socket, String playerName) throws IOException {
        socket.connect(InetAddress.getByName(HOST), PORT);
        sendPacket(socket, InetAddress.getByName(HOST), PORT,
                ("player " + playerName).getBytes());

        DatagramPacket receivedPacket = receivePacket(socket);
        String response = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
        System.out.println(response);
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
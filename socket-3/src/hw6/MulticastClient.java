package hw6;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MulticastClient {
    public static void main(String[] argv) throws Exception {
        String multicastAddress = "224.0.0.1"; // マルチキャストアドレス
        int port = 5100;
        String message = "HELLO!!";

        DatagramSocket socket = new DatagramSocket();
        InetAddress group = InetAddress.getByName(multicastAddress);

        byte[] buffer = message.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, port);
        socket.send(packet);

        System.out.println("送信したメッセージ: " + message);
        socket.close();
    }
}

package hw6;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastServer {
    public static void main(String[] argv) throws Exception {
        String multicastAddress = "224.0.0.1"; // マルチキャストアドレス
        int port = 5100;

        MulticastSocket socket = new MulticastSocket(port);
        InetAddress group = InetAddress.getByName(multicastAddress);
        socket.joinGroup(group);

        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        System.out.println("Waiting for multicast message...");
        
        while (true) {
            socket.receive(packet);
            String received = new String(packet.getData(), 0, packet.getLength());
            System.out.println("受信したメッセージ: " + received);
        }
    }
}

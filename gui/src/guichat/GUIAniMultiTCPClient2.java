package guichat;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class GUIAniMultiTCPClient2 {

	private PrintWriter out;

	public static void main(String[] args) {
		GUIAniMultiTCPClient2 client = new GUIAniMultiTCPClient2();
		client.connectToServer();
		client.startConsoleInput();
	}

	public void connectToServer() {
		try {
			Socket socket = new Socket("localhost", 12345);
			out = new PrintWriter(socket.getOutputStream(), true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void startConsoleInput() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter emotion commands in the format '<faceIndex> <emotion>':");
		while (true) {
			String message = scanner.nextLine();
			sendMessage(message);
		}
	}

	public void sendMessage(String message) {
		if (out != null) {
			out.println(message);
		}
	}
}

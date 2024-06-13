package guichat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class GUIAniMultiTCPServer2 {

	private GUIAnimationMain animation;

	public GUIAniMultiTCPServer2(GUIAnimationMain animation) {
		this.animation = animation;
	}

	public static void main(String[] args) {
		GUIAnimationMain animation = new GUIAnimationMain();
		GUIAniMultiTCPServer2 server = new GUIAniMultiTCPServer2(animation);
		server.startServer();
	}

	public void startServer() {
		try (ServerSocket serverSocket = new ServerSocket(12345)) {
			System.out.println("Server started...");
			while (true) {
				Socket clientSocket = serverSocket.accept();
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					System.out.println("Received: " + inputLine);
					handleMessage(inputLine);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void handleMessage(String message) {
		String[] parts = message.split(" ");
		if (parts.length >= 2) {
			try {
				int faceIndex = Integer.parseInt(parts[0]);
				String emotion = parts[1];
				animation.setFaceEmotion(faceIndex, emotion);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
	}
}

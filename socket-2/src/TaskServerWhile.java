import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TaskServerWhile {

    public static void main(String[] args) {
        try {
            System.out.print("ポートを入力してください(5000など) → ");
            Scanner scanner = new Scanner(System.in);
            int port = scanner.nextInt();
            scanner.close();
            System.out.println("localhostの" + port + "番ポートで待機します");

            ServerSocket serverSocket = new ServerSocket(port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("クライアントと接続しました");

                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ITask task = (ITask) ois.readObject();

                task.exec();

                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(task);
                oos.flush();

                ois.close();
                oos.close();
                socket.close();
            }
        } catch (Exception e) {
            System.err.println("エラーが発生したのでプログラムを終了します");
            e.printStackTrace();
        }
    }
}

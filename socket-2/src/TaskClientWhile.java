import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TaskClientWhile {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("ポートを入力してください(5000など) → ");
        int port = scanner.nextInt();
        String host = "localhost"; // ホスト名をローカルホストに固定
        System.out.println("localhostの" + port + "番ポートに接続を要求します");

        try {
            while (true) {
                System.out.print("最大の素数を求めたい値を入力してください： ");
                int number = scanner.nextInt();

                if (number <= 1) {
                    System.out.println("終了します。");
                    break;
                }

                Socket socket = new Socket(host, port); // socket変数をループ内で定義
                TaskObject task = new TaskObject();
                task.setExecNumber(number);

                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(task);
                oos.flush();

                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                TaskObject resultTask = (TaskObject) ois.readObject();
                int result = resultTask.getResult();
                System.out.println("最大の素数: " + result );

                ois.close();
                oos.close();
                socket.close();
            }
        } catch (Exception e) {
            System.err.println("エラーが発生しました");
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}

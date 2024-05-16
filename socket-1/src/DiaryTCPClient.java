import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.Socket;
import java.util.Scanner;

public class DiaryTCPClient {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("ポートを入力してください(5000など) → ");
            int port = scanner.nextInt();
            scanner.nextLine(); // ポート入力後の改行を消費

            System.out.println("localhostの" + port + "番ポートに接続を要求します");
            Socket socket = new Socket("localhost", port);
            System.out.println("接続されました");

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            while (true) {
                System.out.println("英語の日記を付けましょう!（案内は日本語表記）");
                System.out.println("日付と天気を入力してください(例:2024.5.15.sunny ※ 天気はなるべくsunnyかrainかcloudで! 終了したい場合はquitかexit) ↓");
                String date = scanner.nextLine();

                if (date.equalsIgnoreCase("quit") || date.equalsIgnoreCase("exit")) {
                    System.out.println("プログラムを終了します。");
                    break;
                }

                System.out.println("あった出来事を入力してください(例:I was tired from class this morning.) ↓");
                String content = scanner.nextLine();

                Diary diary = new Diary();
                diary.setDate(date);
                diary.setContent(content);

                oos.writeObject(diary);
                oos.flush();

                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

                Diary okaeshiPresent = (Diary) ois.readObject();

                String replayMsg = okaeshiPresent.getDate();
                System.out.println("サーバからのメッセージは" + replayMsg);
                String replayContent = okaeshiPresent.getContent();
                System.out.println("Comments received:" + replayContent);
            }

            oos.close();
            socket.close();

        } catch (BindException be) {
            be.printStackTrace();
            System.err.println("ポート番号が不正か、サーバが起動していません");
            System.err.println("サーバが起動しているか確認してください");
            System.err.println("別のポート番号を指定してください(6000など)");
        } catch (Exception e) {
            System.err.println("エラーが発生したのでプログラムを終了します");
            throw new RuntimeException(e);
        }
    }
}

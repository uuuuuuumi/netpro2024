import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class DiaryServer {

    private static String serverProcess(String date) {//入力された天気によって出力するコメントを変える（お返し部分）
        
        StringBuilder sb = new StringBuilder();
        sb.append("📒");
        sb.append("📒");
        String result = sb.toString();
        
        if (date.contains("sunny")) {//晴れの場合
            result += "good weater!(^^)!";
        } else if (date.contains("rain")) {//雨の場合
            result += "bad weater(T_T)";
        }else if (date.contains("cloud")) {//曇りの場合
            result += "overcast weater('ω')";
        }else{//その他(晴れのち曇りとか...)
            result +="The weater was changeable.";
        }
        return result;
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("ポートを入力してください(5000など) → ");
            int port = scanner.nextInt();
            scanner.close();
            System.out.println("localhostの" + port + "番ポートで待機します");
            ServerSocket server = new ServerSocket(port);

            Socket socket = server.accept();
            System.out.println("接続しました。相手の入力を待っています......");

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            Diary diary = (Diary) ois.readObject();

            String msgPresent = diary.getDate();
            System.out.println("Date:" + msgPresent);//日付（メッセージ部分）
            String presentFromClient = diary.getContent();
            System.out.println("Event:" + presentFromClient);//英語日記の内容（プレゼント内容部分）

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            String weatherComment = serverProcess(msgPresent);
            String responseMessage = "今日も1日お疲れ様です！\n" + "Date:"+msgPresent + "\nEvent:" + presentFromClient ;

            Diary response = new Diary();
            response.setDate(responseMessage);
            response.setContent(weatherComment);

            oos.writeObject(response);
            oos.flush();

            ois.close();
            oos.close();
            socket.close();
            server.close();

        } catch (BindException be) {
            be.printStackTrace();
            System.out.println("ポート番号が不正、ポートが使用中です");
            System.err.println("別のポート番号を指定してください(6000など)");
        } catch (Exception e) {
            System.err.println("エラーが発生したのでプログラムを終了します");
            throw new RuntimeException(e);
        }
    }
}

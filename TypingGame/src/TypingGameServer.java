import java.io.IOException; // 入出力関連の例外を処理するためのインポート
import java.io.OutputStream; // 出力ストリームを扱うためのインポート
import java.io.OutputStreamWriter; // 出力ストリームに文字データを書き込むためのインポート
import java.io.Writer; // 文字データを書き込むためのインポート
import java.net.InetSocketAddress; // IPアドレスとポート番号を扱うためのインポート
import java.nio.charset.StandardCharsets; // 標準文字エンコーディングを扱うためのインポート
import java.util.Random; // ランダムな値を生成するためのインポート

import com.sun.net.httpserver.Headers; // HTTPヘッダーを扱うためのインポート
import com.sun.net.httpserver.HttpExchange; // HTTPリクエストとレスポンスを扱うためのインポート
import com.sun.net.httpserver.HttpHandler; // HTTPリクエストを処理するためのハンドラーのインポート
import com.sun.net.httpserver.HttpServer; // HTTPサーバーを扱うためのインポート

public class TypingGameServer {
    private static final int PORT = 8080; // サーバーがリッスンするポート番号
    private static final String[] NAMES = { // ランダムに選択される名前のリスト
        "石原さとみ", "山下智久", "新垣結衣", "二宮和也", "松本潤", "櫻井翔",
        "大野智", "相葉雅紀", "長澤まさみ", "広瀬すず", "有村架純", "佐藤健",
        "綾野剛", "山崎賢人", "橋本環奈", "菅田将暉", "小栗旬", "小関羽美",
        "中村倫也", "吉高由里子", "浜辺美波", "小川麗", "竹内涼真", "上田竜也",
        "坂口健太郎", "松坂桃李", "阿部亮平", "高畑充希", "田中圭", "上野樹里"
    };
    private static Random random = new Random(); // ランダムな名前を選択するためのランダムジェネレータ

    public static void main(String[] args) throws IOException {
        // HttpServerオブジェクトを作成し、指定されたポートでリッスン
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
        
        // "/word"のパスにリクエストが来た場合にWordHandlerを使用してリクエストを処理するように設定
        server.createContext("/word", new WordHandler());
        
        // デフォルトのエグゼキュータを設定（nullの場合はデフォルトが使用される）
        server.setExecutor(null);
        
        // サーバーを開始
        server.start();
        
        // サーバーが開始されたことをコンソールに出力
        System.out.println("Server started on port " + PORT);
    }

    // HTTPリクエストを処理するためのハンドラクラス
    static class WordHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // リクエストメソッドがGETの場合に処理を実行
            if ("GET".equals(exchange.getRequestMethod())) {
                // ランダムに名前を取得
                String response = getRandomName();
                
                // レスポンスヘッダーを設定
                Headers headers = exchange.getResponseHeaders();
                headers.add("Content-Type", "text/plain; charset=UTF-8");
                headers.add("Access-Control-Allow-Origin", "*");
                
                // レスポンスボディに名前を書き込み
                exchange.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);
                OutputStream os = exchange.getResponseBody();
                Writer writer = new OutputStreamWriter(os, StandardCharsets.UTF_8);
                writer.write(response);
                writer.close();
            } else {
                // GET以外のリクエストは405 Method Not Allowedを返す
                exchange.sendResponseHeaders(405, -1); // Method Not Allowed
            }
        }

        // ランダムに名前を選択するメソッド
        private String getRandomName() {
            return NAMES[random.nextInt(NAMES.length)];
        }
    }
}

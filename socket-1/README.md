## 日記サーバーと日記クライアント

このオリジナル通信プログラムは、Diary(やり取りされるオブジェクト)、DiaryServer(サーバークラス)とDiaryTCPCliant(クライアントクラス)で構成されています。
サーバーはクライアントから日付と天気、日記の内容を受け取り、日付と天気、日記の内容、天気の内容に応じて適したコメントを送信します。
何度もクライアントからキーボードで入力した内容をサーバに送信できるようになっており、quit,exitを入力すると終了します。


## 動作結果

- DiaryServer(サーバークラス)側

c:\Users\takoy\OneDrive\デスクトップ\netpro2024>
c:\Users\takoy\OneDrive\デスクトップ\netpro2024> c: && cd c:\Users\takoy\OneDrive\デスクトップ\netpro2024 && cmd /C "C:\Users\takoy\AppData\Roaming\Code\User\globalStorage\pleiades.java-extension-pack-jdk\java\21\bin\java.exe @C:\Users\takoy\AppData\Local\Temp\cp_44bjktn5rbipivvlvhnq0uifj.argfile DiaryServer "
ポートを入力してください(5000など) → 5000
localhostの5000番ポートで待機します

- DiaryTCPCliant(クライアントクラス)側

接続されました
英語の日記を付けましょう!（案内は日本語表記）
日付と天気を入力してください(例:2024.5.15.sunny ※ 天気はなるべくsunnyかrainかcloudで! 終了したい場合はquitかexit) ↓
2024.5.16.sunny 
あった出来事を入力してください(例:I was tired from class this morning.) ↓
I slept for 12 hours today.
サーバからのメッセージは今日も1日お疲れ様です！
Date:2024.5.16.sunny
Event:I slept for 12 hours today.
Comments received:??good weater!(^^)!
英語の日記を付けましょう!（案内は日本語表記）
日付と天気を入力してください(例:2024.5.15.sunny ※ 天気はなるべくsunnyかrainかcloudで! 終了したい場合はquitかexit) ↓

package hw6;

public class CountAZTenRunnable implements Runnable {
    private char myChar;

    public void setChar(char myChar) {
        this.myChar = myChar;
    }

    public static void main(String[] args) {
        // スレッドを格納する配列を用意します
        Thread[] threads = new Thread[26];

        // a-z のスレッドを作成して開始します
        for (int i = 0; i < 26; i++) {
            char c1 = 97;// 'a' のASCII値は97です
            CountAZTenRunnable runnable = new CountAZTenRunnable();
            runnable.setChar((char) (c1 + i)); 

            threads[i] = new Thread(runnable, "th-" + (i + 1));//th- はスレッド名
            threads[i].start();
        }

        // メインスレッドは終了を待つだけ
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // run メソッドは、新しいスレッドが実行するコードを含みます
    public void run() {
        // この try-catch ブロックは、0 から 9 までの値を 1000 ミリ秒間隔で出力するループを実行します
        try {
            for (int i = 1; i <= 10; i++) {
                System.out.println(myChar + "" + i);
                Thread.sleep(1000); // ミリ秒単位のスリープ時間
            }
        } catch (InterruptedException e) {
            // スレッドが中断された場合は、例外を出力します
            System.err.println(e);
        }
    }
}
package hw6;

import java.util.Random;

public class ExThreadsMainTest {

    private static final int NUM_THREADS = 4; // スレッドの数
    private static final int MAX_VALUE = 100; // 各スレッドが生成する乱数の最大値

    public static void main(String[] args) {
        Thread[] threads = new Thread[NUM_THREADS]; // スレッドを格納する配列

        // 各スレッドを作成して開始
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new Thread(new RandomNumberGeneratorTask(i + 1, MAX_VALUE));
            threads[i].start();
        }

        // メインスレッドはすべてのスレッドが終了するのを待つ
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("すべてのスレッドが終了しました。");
    }

    private static class RandomNumberGeneratorTask implements Runnable {

        private final int threadId;
        private final int maxValue;

        public RandomNumberGeneratorTask(int threadId, int maxValue) {
            this.threadId = threadId;
            this.maxValue = maxValue;
        }

        @Override
        public void run() {
            Random random = new Random();
            for (int i = 0; i < 10; i++) {
                int randomNumber = random.nextInt(maxValue) + 1;
                System.out.printf("スレッド%d: 生成した乱数: %d\n", threadId, randomNumber);
                try {
                    Thread.sleep(500); // 500ミリ秒スリープ
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

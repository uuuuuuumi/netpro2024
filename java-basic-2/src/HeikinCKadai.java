import java.util.Arrays;
import java.util.Random;

public class HeikinCKadai {
    public static void main(String[] args) {
        // 100人分の数学点数をランダムに生成
        int N = 100;
        Kamoku[] student = new Kamoku[N];
        Random r = new Random();
        int totalScore = 0;
        for (int i = 0; i < 100; i++) {
            int score = r.nextInt(N + 1);
            student[i] = new Kamoku(score);
            totalScore += score;
        }

        // Kamokuクラスの配列をint[]の配列にコピー
        int h = 0;
        int[] hairetsu = new int[student.length];
        for (Kamoku k : student) {
            hairetsu[h] = k.getScore();
            h++;
        }

        // 平均点の計算
        int averageScore = totalScore / N;
        System.out.println("*平均点は " + averageScore + "*");

        // 合格者一覧を出力
        System.out.println("\n*以下合格者の点数です。*");
        int okCount = 0;
        for (int i = 0; i < 100; i++) {
            if (student[i].getScore() >= 80) {
                System.out.println("学生番号" + (i + 1) + ": " + student[i].getScore() + "点");
                okCount++;
            }
        }

        // 合格者の点数を昇順でソートして出力する
        System.out.println("\n*合格者の点数(昇順)*");
        Arrays.sort(hairetsu, 0, okCount);
        for (int i = 0; i < okCount; i++) {
            System.out.println("学生番号" + (i + 1) + ": " + hairetsu[i] + "点");
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ggg {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try {
                System.out.print("何歳ですか? (qまたはeで終了) ");
                String input = reader.readLine();

                if (input.equals("q") || input.equals("e")) {
                    System.out.println("プログラムを終了します。");
                    break;
                }

                int currentAge = Integer.parseInt(input);

                if (currentAge < 0 || currentAge >= 120) {
                    System.out.println("年齢が不正です。再入力してください。");
                    continue;
                }

                int yearOfBirth = 2023 - currentAge;
                int ageIn2030 = currentAge + 7;

                String era = getEraName(yearOfBirth);
                int yearInEra = yearOfBirth - getEraStartYear(era) + 1;

                System.out.println("あなたは" + currentAge + "歳ですね。");
                System.out.println("2030年、あなたは" + ageIn2030 + "歳になります。");
                System.out.println("あなたは" + era + " " + yearInEra + "年生まれです。");

            } catch (IOException e) {
                System.out.println("入力エラーが発生しました: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("年齢の入力が正しくありません。再入力してください。");
            }
        }
    }

    private static String getEraName(int yearOfBirth) {
        if (yearOfBirth >= 2019) {
            return "令和";
        } else if (yearOfBirth >= 1989) {
            return "平成";
        } else if (yearOfBirth >= 1926) {
            return "昭和";
        } else if (yearOfBirth >= 1912) {
            return "大正";
        } else {
            return "明治";
        }
    }

    private static int getEraStartYear(String era) {
        switch (era) {
            case "令和":
                return 2019;
            case "平成":
                return 1989;
            case "昭和":
                return 1926;
            case "大正":
                return 1912;
            default:
                return 1868; // 明治以前は明治元年(1868年)を基準とする
        }
    }
}

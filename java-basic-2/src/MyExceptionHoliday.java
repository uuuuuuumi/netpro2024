import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyExceptionHoliday {

    public static void main(String[] args) {
        MyExceptionHoliday myE = new MyExceptionHoliday();
        myE.holiday();
    }

    void holiday() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try {
                System.out.println("何日ですか? (0を入力すると終了します)");
                String line = reader.readLine();
                int theday = Integer.parseInt(line);

                if (theday == 0) {
                    System.out.println("プログラムを終了します。");
                    break;
                }
                System.out.println("日付" + theday + "日ですね。");
                test(theday);
            } catch (IOException e) {
                System.out.println(e);
            } catch (NoHolidayException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("数値を入力してください。");
            }
        }
    }

    void test(int theday) throws NoHolidayException {
		//休日・土日の日付
        if (theday == 3 || theday == 4 || theday == 5 || theday == 6 || theday == 11 || theday == 12 || theday == 18 || theday == 19 || theday == 25 || theday == 26) {
            System.out.println("今日はデートの日だぴょん!!!");
        } else {
            throw new NoHolidayException(theday);
        }
    }
}

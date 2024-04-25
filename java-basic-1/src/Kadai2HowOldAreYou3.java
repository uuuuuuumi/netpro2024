// C言語では、#include に相当する
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Kadai2HowOldAreYou3 {

	public static void main(String[] args) { 

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			// BufferedReader というのは、データ読み込みのクラス(型)
			// クラスの変数を作るには、new を使う。

			// readLine() は、入出力エラーの可能性がある。エラー処理がないとコンパイルできない。
			//  Java では、 try{ XXXXXXXX }  catch(エラーの型 変数) { XXXXXXXXXXXXXXXXXX} と書く
			while(true){
		try {
			System.out.println("何歳ですか?");
			String line = reader.readLine();//値を取得する

			if(line.equals("q")||line.equals("e")){
				System.out.println("プログラミングを終了します。");
				break;
			}

			int age = Integer.parseInt(line);//今の年齢

			if(age < 0 || age >=120){//年齢が0才か120才以上なら再入力を促す
				System.out.println("再入力してください。");
				continue;
			}
            System.out.println("あなたは" + age + "歳ですね。");
			System.out.println("あなたは2030年、" + (age + 7) + "歳ですね。");

int birthYear = 2023 - age;//生まれた年

String era = getEra(birthYear);//生まれた年号
	int eraYear = birthYear - getEraStartYear(era) + 1;//生まれた年号の年の計算

	System.out.println("あなたは" + era + " " + eraYear + "年生まれですね。");
		}
		catch(IOException e) {
			System.out.println(e);
		}


	}
}
private static String getEra(int birthYear) {//年号の指定
	if (birthYear >= 2019) {
		return "令和";
	} else if (birthYear >= 1989) {
		return "平成";
	} else if (birthYear >= 1926) {
		return "昭和";
	} else if (birthYear >= 1912) {
		return "大正";
	} else {
		return "明治";
	}
}
private static int getEraStartYear(String era) {//年号が始まる年の取得
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



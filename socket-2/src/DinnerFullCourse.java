public class DinnerFullCourse {
    private Dish[] list = new Dish[5];// [0]-[4]の計5個

	public static void main(String[] args) {
        DinnerFullCourse fullcourse = new DinnerFullCourse();
		fullcourse.eatAll();
	}

    DinnerFullCourse() {

		list[0] = new Dish();
		list[0].setName("特選シーザサラダ");
		list[0].setValune(10);

		list[1] = new Dish();
		list[1].setName("銀しゃり");
		list[1].setValune(2);
		
		list[2] = new Dish();
		list[2].setName("梅干し");
		list[2].setValune(20);

        list[3] = new Dish();
		list[3].setName("茶碗蒸し");
		list[3].setValune(5);

        list[4] = new Dish();
		list[4].setName("カタラーナ");
		list[4].setValune(7);
	}// Dinnerコンストラクターエンド

    void eatAll(){
        String str = "";
        for(Dish dish : list){//拡張for文/for(繰り返し用変数の宣言:集合式){処理}
             str += dish.getName() + "=" + dish.getValune() + ",";
        }
        System.out.println("たかしへ、ママです! 今日の晩御飯コースは" + str + "よ!");
    }// eat end
     // DinnerFullCourse()
}    //DinnerFullCourse end

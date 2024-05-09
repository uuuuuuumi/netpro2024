public class ForTest {

    public static void main(String[] args) {


        for(int i =0;i<10;i++) {//0から始まるから10未満!
            System.out.print("i = " + i +"," );

        }
        System.out.println("| Line = " +"1 is end");

        for(int i =0;i<10;i++) {//iは上とは別物だわよ!
            System.out.print("i = " + i +"," );

        }
        System.out.println("| Line = " +"2 is end");


    }
}
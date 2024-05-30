package basic;

public class StringProcessing {
    public static void main(String args[]){
//s1 = new String("ねこ");

//String s2 = new String("ねこ");

String s1 = "ねこ";

String s2 = "ねこ";



if(s1==s2){// 中の文字列をみて判定してくれている。

    System.out.println("yes! s1==s2:"+s1+":"+s2);

}else{

    System.out.println("no! s1=s2"+s1+":"+s2); //new Stringしたときだけobjectとしてふるまい判定にも

    //オブジェクトハッシュIDで比較させる。なのでその場合はequals()で比較しよう。

}
    }
}

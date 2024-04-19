//IfTest3 xが7,8,9ながbignumber
//xが4,5,6ならmiddle number
//xが1,2,3ならsmall numberを表示するプログラムを書け
//xがそれ以外(10や-1なら)ならout of scopeと表示するプログラムをかけ

public class IfTest3 {
	public static void main(String[] args) { 
		int x=Integer.parseInt(args[0]);
        if(x>=10||x<=0){
			System.exit(1);
		}
        if(x>=7){
            System.out.println(x+" is Big Number");
        }else if(x>=4){
            System.out.println(x+" is Middle Number");
        }else{
            System.out.println(x+" is Small Number");
        }
		/*以下を改造する。**/
		//System.out.println(x+" is Big Number");
	
	

	}//main end
}//class end



    public class XmasTree {
        public static void main(String[] args) {
            // クリスマスツリーの描画
            drawXmasTree();
        }
    
        private static void drawXmasTree() {
            int N = 10;
            for (int j = 0; j < N; j++) {
                // 左側の@
                for (int i = 0; i <= N - j; i++) {
                    System.out.print("@");
                }
                // 中央の*
                for (int i = 0; i <= j * 2; i++) {
                    System.out.print("*");
                }
                // 右側の@
                for (int i = 0; i <= N - j; i++) {
                    System.out.print("@");
                }
                System.out.print("\n");
            }
    
            // 幹の描画
            drawMiki();
        }
    
        private static void drawMiki() {
            int N = 10;
            int mikiHeight = 8;
            for (int i = 0; i < mikiHeight; i++) {
                // 左側の空白
                for (int j = 0; j < N - 1; j++) {
                    System.out.print(" ");
                }
                // 幹の描画
                for (int j = 0; j < 3; j++) {
                    System.out.print("#");
                }
                System.out.println();
            }
        }
    
    
}



//FacesAWTMain.java
//FacesAWTMain 目標 インナークラスのFaceObjクラスを作ってみよう。描画処理を移譲してください。
//3x3　の顔を描画してください。色などもぬってオリジナルな楽しい顔にしてください。

package guibasic;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FacesAWTKosekiUmi {

    public static void main(String[] args){
        new FacesAWTMain();
    }

    FacesAWTKosekiUmi(){
        FaceFrame f = new FaceFrame();
        f.setSize(800,800);
        f.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
        f.setVisible(true);
    }

    // インナークラスを定義
    class FaceFrame extends Frame {

        private int w;
        private int h;
        private int xStart;
        private int yStart;
        private FaceObj fobj1;

        FaceFrame(){
            fobj1 = new FaceObj();
        }

        public void paint(Graphics g) {
        // この中には、g.drawLine というのは入ってこない
	    // Graphicsクラス(型のようなもの---今は--)のgという変数はメソッドに渡す
            w = 200;
            h = 200;

            Color[] colors = {Color.RED, Color.GRAY, Color.BLUE, Color.ORANGE, Color.GREEN, Color.CYAN, Color.YELLOW, Color.MAGENTA, Color.LIGHT_GRAY};
            int[][] expressions = {{1, -1}, {0, -1}, {-1, -1}, {1, 0}, {0, 0}, {-1, 0}, {1, 1}, {0, 1}, {-1, 1}};

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    xStart = 50 + i * (w + 50);
                    yStart = 50 + j * (h + 50);
                    fobj1.drawFace(g, xStart, yStart, w, h, colors[i + j * 3], expressions[i + j * 3]);
                }
            }
        }
    }

    // Face class
    private class FaceObj {

        public void drawFace(Graphics g, int xStart, int yStart, int w, int h, Color color, int[] expression) {
            drawRim(g, xStart, yStart, w, h, color);
            drawBrow(g, xStart, yStart, 30, expression[0]); 
            drawEye(g, xStart, yStart, 35);
            drawEye2(g, xStart, yStart, 10);
            drawNose(g, xStart, yStart, 20);
            drawMouth(g, xStart, yStart, 100, expression[1]);
        }

        private void drawRim(Graphics g, int xStart, int yStart, int w, int h, Color color) {
            g.setColor(color);
            g.fillRect(xStart, yStart, w, h);
        }

        private void drawBrow(Graphics g, int xStart, int yStart, int bx, int direction) {
            g.setColor(Color.BLACK);
            int yBrow = yStart + 50;
            if (direction == 1) {
                g.drawLine(xStart + 45, yBrow - 5, xStart + 45 + bx, yBrow);
                g.drawLine(xStart + 125, yBrow , xStart + 125 + bx, yBrow -5);
            } else if (direction == -1) {
                g.drawLine(xStart + 45, yBrow, xStart + 45 + bx, yBrow - 5);
                g.drawLine(xStart + 125, yBrow - 5, xStart + 125 + bx, yBrow);
            } else {
                g.drawLine(xStart + 45, yBrow, xStart + 45 + bx, yBrow);
                g.drawLine(xStart + 125, yBrow, xStart + 125 + bx, yBrow);
            }
        }

        private void drawEye(Graphics g, int xStart, int yStart, int r) {
            g.setColor(Color.BLACK);
            g.fillOval(xStart + 45, yStart + 65, r, r);
            g.fillOval(xStart + 120, yStart + 65, r, r);
        }

        private void drawEye2(Graphics g, int xStart, int yStart, int r) {
            g.setColor(Color.WHITE);
            g.fillOval(xStart + 57, yStart + 65, r, r);
            g.fillOval(xStart + 133, yStart + 65, r, r);
        }

        private void drawNose(Graphics g, int xStart, int yStart, int nx) {
            g.setColor(Color.BLACK);
            g.drawLine(xStart + 100, yStart + 100, xStart + 100, yStart + 100 + nx);
        }

        private void drawMouth(Graphics g, int xStart, int yStart, int mx, int direction) {
            int xMiddle = xStart + 100;
            int yMiddle = yStart + 180;
            g.setColor(Color.BLACK);
            if (direction == 1) {
                g.drawArc(xMiddle - mx/2, yMiddle - 60, mx, 50, 0, -180);
            } else if (direction == -1) {
                g.drawArc(xMiddle - mx/2, yMiddle - 30, mx, 50, 0, 180);
            } else {
                g.drawLine(xMiddle - mx/2, yMiddle - 20, xMiddle + mx/2, yMiddle - 20);
            }
        }
    }
}

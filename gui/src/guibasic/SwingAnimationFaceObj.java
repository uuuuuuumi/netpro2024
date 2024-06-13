package guibasic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SwingAnimationFaceObj extends JPanel implements ActionListener {

    private Ellipse2D.Float ellipse = new Ellipse2D.Float();
    final int framespeed = 50;
    final private double MAXCounter = 500;

    private boolean isResetProcess = true;
    private double counter;
    private Timer timer;

    private int ballnum = 2;
    private BallRim[] myBalls = new BallRim[ballnum];

    public static void main(String[] args) {
        SwingAnimationFaceObj animation = new SwingAnimationFaceObj();
        JFrame frame = new JFrame("SwingFaceObject");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(animation);
        frame.setSize(550, 550);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public SwingAnimationFaceObj() {
        timer = new Timer(framespeed, this);
        timer.setInitialDelay(500);
        timer.start();
    }

    public void paint(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2.setRenderingHints(rh);
        Dimension windowSize = getSize();

        if (isResetProcess) {
            initProcess(windowSize.width, windowSize.height);
            isResetProcess = false;
        }

        this.doNextStep(windowSize.width, windowSize.height);
        paintProcess(windowSize.width, windowSize.height, g2);
    }

    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public void setEllipseSize(double size, int w, int h) {
        counter = size;
        ellipse.setFrame(10, 10, size, size);
    }

    public void initProcess(int w, int h) {
        for (int i = 0; i < myBalls.length; i++) {
            myBalls[i] = new BallRim(w, h);
        }
        setEllipseSize(1, w, h);
    }

    public void doNextStep(int w, int h) {
        counter++;

        if (counter > MAXCounter) {
            initProcess(w, h);
        } else {
            for (int i = 0; i < myBalls.length; i++) {
                myBalls[i].move();
            }
            ellipse.setFrame(ellipse.getX(), ellipse.getY(), counter, counter);
        }
    }

    public void paintProcess(int w, int h, Graphics2D g2) {
        g2.setColor(Color.BLUE);
        g2.draw(ellipse);
        g2.drawString(counter + "Step経過", 10, 10);

        for (int i = 0; i < myBalls.length; i++) {
            myBalls[i].draw(g2);
        }
    }

    class BallRim {
        FaceObj fobj;
        String str = "";
        Random rdn;
        int w = 500;
        int h = 500;
        int x;
        int y;
        int radius;
        Color basicColor = Color.gray;
        double xDir = -1;
        double yDir = 1;
        private int strCounter;

        BallRim(int w, int h) {
            rdn = new Random();
            xDir = rdn.nextDouble() * 2 - 1;
            yDir = rdn.nextDouble() * 2 - 1;
            this.w = w;
            this.h = h;

            setPosition(rdn.nextInt(w), rdn.nextInt(h));
            setRadius(rdn.nextInt(30) + 30);

            Color bcolor = new Color(rdn.nextInt(255), rdn.nextInt(255), rdn.nextInt(255));
            setBasicColor(bcolor);

            fobj = new FaceObj();
        }

        void setBasicColor(Color bcolor) {
            this.basicColor = bcolor;
        }

        public void setCollisionText(String str, int strCounter) {
            this.str = str;
            this.strCounter = strCounter;
        }

        void move() {
            if ((xDir > 0) && (x + this.radius * 4 >= w)) {
                xDir = -xDir;
                setCollisionText("右が痛いわぁ", 3);
                fobj.setFaceColor(new Color(rdn.nextInt(255), rdn.nextInt(255), rdn.nextInt(255)));
            } else if ((xDir < 0) && (x <= 0)) {
                xDir = -xDir;
                setCollisionText("左の頬がめちゃ痛いわ！", 6);
                fobj.setFaceColor(new Color(rdn.nextInt(255), rdn.nextInt(255), rdn.nextInt(255)));
            } else if (xDir > 0) {
                x = x + 10;
            } else {
                x = x - 10;
            }

            if ((yDir > 0) && (y + this.radius * 3 >= h)) {
                yDir = -yDir;
                setCollisionText("顎が痛いわぁ", 3);
                fobj.setFaceColor(new Color(rdn.nextInt(255), rdn.nextInt(255), rdn.nextInt(255)));
            } else if ((yDir < 0) && (y <= 0)) {
                yDir = -yDir;
                setCollisionText("頭がめちゃ痛いわ！", 6);
                fobj.setFaceColor(new Color(rdn.nextInt(255), rdn.nextInt(255), rdn.nextInt(255)));
            }

            if (yDir > 0) {
                y = y + 10;
            } else {
                y = y - 10;
            }
        }

        void setPosition(int x, int y) {
            this.x = x;
            this.y = y;
        }

        void setRadius(int r) {
            this.radius = r;
        }

        void draw(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(basicColor);
            RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(x, y, 2 * radius, 2 * radius, 20, 20);
            g2.fill(roundedRectangle);

            if (strCounter > 0) {
                g.drawString(str, x, y);
                strCounter--;
            } else {
                str = "";
            }

            fobj.drawFace(g, x, y);
        }

    }

    // FaceObj class adapted from FacesAWTMain
    class FaceObj {
        private final int faceWidth = 200;
        private final int faceHeight = 200;
        private Color faceColor = Color.LIGHT_GRAY;
    
        public void setFaceColor(Color color) {
            this.faceColor = color;
        }
    
        public void drawFace(Graphics g, int xStart, int yStart) {
            drawRim(g, xStart, yStart, faceWidth, faceHeight);
            drawEye(g, xStart, yStart, 35);
            drawNose(g, xStart, yStart, 20);
            drawMouth(g, xStart, yStart, 100);
        }
    
        private void drawRim(Graphics g, int xStart, int yStart, int w, int h) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(faceColor);
            RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(xStart, yStart, w, h, 20, 20);
            g2.fill(roundedRectangle);
        }
    
        private void drawEye(Graphics g, int xStart, int yStart, int r) {
            g.setColor(Color.BLACK);
            g.fillOval(xStart + 45, yStart + 65, r, r);
            g.fillOval(xStart + 120, yStart + 65, r, r);
    
            g.setColor(Color.WHITE);
            g.fillOval(xStart + 57, yStart + 65, 10, 10);
            g.fillOval(xStart + 133, yStart + 65, 10, 10);
        }
    
        private void drawNose(Graphics g, int xStart, int yStart, int nx) {
            g.setColor(Color.BLACK);
            g.drawLine(xStart + 100, yStart + 100, xStart + 100, yStart + 100 + nx);
        }
    
        private void drawMouth(Graphics g, int xStart, int yStart, int mx) {
            int xMiddle = xStart + 100;
            int yMiddle = yStart + 180;
            g.setColor(Color.BLACK);
            g.drawLine(xMiddle - mx / 2, yMiddle - 20, xMiddle + mx / 2, yMiddle - 20);
        }
    }

}

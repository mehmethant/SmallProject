package Run;

import java.awt.*;

public class Ball extends Thread implements Drawable {

    private double x, y, vx, vy;
    private Color color;

    public Ball() {
        this.x = 320;
        this.y = 240;

        this.vx = (Math.random() * 10) - 5;
        this.vy = (Math.random() * 10) - 5;

        this.color = new Color(
                (int) (Math.random() * 255),
                (int) (Math.random() * 255),
                (int) (Math.random() * 255));

        start();
    }

    public void move() {
        this.x += vx;
        this.y += vy;
        // System.out.println(x+" "+y);
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            move();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval((int) x, (int) y, 20, 20);
    }
}

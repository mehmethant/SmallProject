package Run;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ReleaseMain extends Frame implements Runnable {

    public static void main(String[] args) {
        new ReleaseMain();
    }

    public ReleaseMain() {
        list = new ArrayList<>();
        for (int i = 0; i < 100; i++)
            list.add(new Ball());

        this.setSize(640, 480);
        this.setVisible(true);

        new Thread(this).start();
    }

    private List<Drawable> list;

    public void paint(Graphics g) {
        for (Drawable d : list)
            d.draw(g);
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            repaint();
            try {
                Thread.sleep(1000 / 30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

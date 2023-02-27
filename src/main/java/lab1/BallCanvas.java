package lab1;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class BallCanvas extends JPanel {
    private static ArrayList<Ball> balls = new ArrayList<>();
    private static ArrayList<Loosa> loosas = new ArrayList<>();

    public static ArrayList<Ball> getBalls() {
        return balls;
    }

    public static ArrayList<Loosa> getLoosas() {
        return loosas;
    }

    public BallCanvas(int W, int H) {
        Collections.addAll(this.loosas,
                new Loosa(0, 0),
                new Loosa(W / 2, 0),
                new Loosa(W, 0),
                new Loosa(0, H),
                new Loosa(W / 2, H),
                new Loosa(W, H));
    }

    public void add(Ball b) {
        this.balls.add(b);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (int i = 0; i < balls.size(); i++) {
            Ball b = balls.get(i);
            b.draw(g2);
        }
        for (Loosa l : loosas) {
            l.draw(g2);
        }
    }
}

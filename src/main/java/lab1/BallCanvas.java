package lab1;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class BallCanvas extends JPanel {
    private static ArrayList<Ball> balls = new ArrayList<>();

    public static ArrayList<Ball> getBalls() {
        return balls;
    }


    public void add(Ball b) {
        this.balls.add(b);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Ball b : balls) {
            b.draw(g2);
        }
    }
}

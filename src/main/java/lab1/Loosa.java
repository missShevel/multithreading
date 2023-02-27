package lab1;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Loosa {
    private static final int XSIZE = 25;
    private static final int YSIZE = 25;
    private final int x;
    private final int y;


    public Loosa(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.green);
        g2.fill(new Ellipse2D.Double(this.x, this.y, XSIZE, YSIZE));
    }

    public boolean isIntersected(int x_ball, int y_ball){
        return x_ball >= this.x &&
                x_ball < this.x + this.XSIZE &&
                y_ball >= this.y &&
                y_ball < this.y + this.YSIZE;
    }
}

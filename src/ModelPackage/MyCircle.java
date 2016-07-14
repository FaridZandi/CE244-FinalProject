package ModelPackage;

import ControlPackage.Control;
import ControlPackage.Drawable;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Created by Y50 on 7/8/2016.
 */
public class MyCircle implements Drawable
{
    private int radius;
    private int x;
    private int y;
    private Color color;

    public MyCircle(int radius, int x, int y, Color color) {
        this.radius = radius;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    @Override
    public void draw(Graphics2D g2d, Control control) {
        g2d.setColor(color);
        g2d.fillOval(x, y , radius , radius);
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}

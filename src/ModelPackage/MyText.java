package ModelPackage;

import ControlPackage.Control;
import ControlPackage.Drawable;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 * Created by Y50 on 7/8/2016.
 */
public class MyText implements Drawable
{
    private String text;
    private int x;
    private int y;
    private int fontSize;
    private Color color;

    public MyText(String text, int x, int y, int fontSize, Color color) {

        this.text = text;
        this.x = x;
        this.y = y;
        this.fontSize = fontSize;
        this.color = color;
    }

    @Override
    public void draw(Graphics2D g2d, Control control) {
        g2d.setFont(new Font("calibri" , Font.BOLD , 50));
        g2d.setColor(color);
        g2d.drawString(text , x , y);
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }
}

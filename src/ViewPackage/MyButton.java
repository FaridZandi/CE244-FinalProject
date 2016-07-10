package ViewPackage;

import ModelPackage.MyCircle;
import java.awt.Graphics;
import javax.swing.JButton;

/**
 * Created by Y50 on 7/9/2016.
 */
public class MyButton extends JButton
{

    public MyButton()
    {
        super();
    }

    public MyButton(String text) {
        super(text);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillRect(this.getX() , this.getY() , this.getWidth() ,this. getHeight());
    }


}

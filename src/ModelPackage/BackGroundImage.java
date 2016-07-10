package ModelPackage;

import ControlPackage.Control;
import ControlPackage.Drawable;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Created by Y50 on 7/9/2016.
 */
public class BackGroundImage implements Drawable
{
    private int x ;
    private int y ;
    private int width;
    private int height;


    private Image backgroundImage;


    public BackGroundImage(File file , int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        try {
            backgroundImage = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        backgroundImage = backgroundImage.getScaledInstance(width , height , Image.SCALE_DEFAULT);
    }

    @Override
    public void draw(Graphics2D g2d, Control control) {
        g2d.drawImage(backgroundImage , x , y , null);
    }
}

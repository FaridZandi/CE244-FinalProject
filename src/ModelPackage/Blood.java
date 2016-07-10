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
 * Created by Y50 on 7/8/2016.
 */
public class Blood implements Drawable
{

    public static final int NumberOfExplosionFrames = 9;

    private static BufferedImage bloodSpriteSheet;

    static {
        File img = new File("pc-computer---13-days-in-hell-.png");
        try {
            bloodSpriteSheet = ImageIO.read(img);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int step = 0;
    private int x;
    private int y;


    public Blood(int x , int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Graphics2D g2d, Control control) {

        int row = step / 3;
        int column = step % 3;

        Image subImg = Soldier.getSubImage(bloodSpriteSheet , row , column);
        g2d.drawImage(subImg , x , y, null);
    }

    public void setStep(int step) {
        this.step = step;
    }
}

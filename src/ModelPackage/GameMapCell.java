package ModelPackage;

import ViewPackage.GamePanel;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Created by Y50 on 6/29/2016.
 */
public abstract class GameMapCell
{

    private boolean isPassable;
    public static BufferedImage emptyMapCellImage;
    public static BufferedImage wallMapCellImage;
    public static BufferedImage battleMapCellImage;
    public static BufferedImage shopMapCellImage;


    static{
        File img1 = new File("Grass_texture_sketchup_warehouse_type086.jpg");
        File img2 = new File("GpvC5e.png");
        File img3 = new File("grass-min.jpg");
        File img4 = new File("grass-min.jpg");
        try {
            emptyMapCellImage = ImageIO.read(img1);
            wallMapCellImage = ImageIO.read(img2);
            battleMapCellImage = ImageIO.read(img3);
            shopMapCellImage = ImageIO.read(img4);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
    public static final int PerspectiveRate = 4;


    public abstract void draw(int cornerX, int cornerY, Graphics2D g2d);

    public void drawTile(int cornerX, int cornerY, Graphics2D g2d , BufferedImage bi, boolean isPassable) {

        this.isPassable = isPassable;
        int x0 = GamePanel.ScreenWidth / 3;
        int y0 = GamePanel.ScreenHeight / 2;
        Dimension origin = new Dimension(x0, y0);
        Dimension p1 = new Dimension(cornerX , cornerY);
        Dimension p2 = new Dimension(cornerX + GameMap.CellSize , cornerY);
        Dimension p3 = new Dimension(cornerX + GameMap.CellSize, cornerY + GameMap.CellSize);
        Dimension p4 = new Dimension(cornerX , cornerY + GameMap.CellSize);


        int[] polygonXs = {perspectiveAffectedWidth(p1 , origin).width ,perspectiveAffectedWidth(p2 , origin).width  ,perspectiveAffectedWidth(p3 , origin).width  ,perspectiveAffectedWidth(p4 , origin).width };
        int[] polygonYs = {perspectiveAffectedWidth(p1 , origin).height ,perspectiveAffectedWidth(p2 , origin).height  ,perspectiveAffectedWidth(p3 , origin).height  ,perspectiveAffectedWidth(p4 , origin).height };

        Shape polygon = new Polygon(polygonXs , polygonYs , 4);


//        if(polygon.intersects(0 , 0 , GamePanel.ScreenWidth / 1.5 , GamePanel.ScreenHeight))
        {
            g2d.setClip(polygon);
            g2d.drawImage(bi, Math.min(polygonXs[0], polygonXs[3]), polygonYs[0], null);
        }

//                g2d.setColor(new Color(50, 50, 50));
//        g2d.drawPolygon(polygonXs , polygonYs , 4);
//        g.setClip(shit);
//        g.fillPolygon(polygonXs , polygonYs , 4);
    }

    private Dimension perspectiveAffectedWidth(Dimension point , Dimension origin)
    {
        double a =(point.width - origin.width) / (double)PerspectiveRate;
        double b =(point.height - origin.height)/(double)(origin.height);
//        double c =(point.height - origin.height) / (double)origin.height  + 1;

//        int dimentionX = (int)(a * b) + point.width;
//        int dimentionY = (int)(c * PerspectiveRate * 15) + point.height;
        return new Dimension(point.width , point.height);
    }


    public boolean getPassable() {
        return isPassable;
    }
}

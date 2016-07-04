package ModelPackage;

import java.awt.Graphics2D;

/**
 * Created by Y50 on 6/29/2016.
 */
public class EmptyGameCell extends GameMapCell
{
    @Override
    public void draw(int cornerX, int cornerY, Graphics2D g2d) {
        super.drawTile(cornerX, cornerY, g2d , GameMapCell.emptyMapCellImage, true);
    }
}
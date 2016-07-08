package ModelPackage;

import ViewPackage.GamePanel;
import java.awt.Graphics2D;

/**
 * Created by Y50 on 6/29/2016.
 */
public class EmptyGameCell extends GameMapCell
{

    public EmptyGameCell(GamePanel gamePanel) {
        super(gamePanel);
    }

    @Override
    public void draw(int cornerX, int cornerY, Graphics2D g2d) {
        super.drawTile(cornerX, cornerY, g2d , GameMapCell.emptyMapCellImage, true);
    }

    @Override
    public void enter(Story story) {

    }

    @Override
    public void exit() {

    }


}

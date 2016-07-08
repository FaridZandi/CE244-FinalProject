package ModelPackage;

import ControlPackage.Control;
import ControlPackage.Drawable;
import ViewPackage.GamePanel;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * Created by Y50 on 6/29/2016.
 */
public class GameMap implements Drawable
{
    public static final int CellSize = 150;
    private final GamePanel gamePanel;
    private ArrayList<ArrayList<GameMapCell>> gameMapCells;

    public GameMap(GamePanel gamePanel, GameObjectsHolder gameObjectsHolder) {
        gameMapCells = new ArrayList<>();
        this.gamePanel = gamePanel;
        loadMap(gameObjectsHolder);
    }

    public ArrayList<ArrayList<GameMapCell>> getGameMapCells() {
        return gameMapCells;
    }

    private void loadMap(GameObjectsHolder gameObjectsHolder) {
        ArrayList<GameMapCell> temp1 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            temp1.add(new WallMapCell(gamePanel));
        }
        gameMapCells.add(temp1);
        ArrayList<GameMapCell> temp2 = new ArrayList<>();
        temp2 .add(new WallMapCell(gamePanel));
        for (int i = 0; i < 8; i++)
        {
            temp2.add(new EmptyGameCell(gamePanel));
        }
        temp2.add(new WallMapCell(gamePanel));
        gameMapCells.add(temp2);
        
        ArrayList<GameMapCell> temp3 = new ArrayList<>();
        temp3.add(new WallMapCell(gamePanel));
        for (int i = 0; i < 4; i++)
        {
            temp3.add(new EmptyGameCell(gamePanel));
            temp3.add(new WallMapCell(gamePanel));
        }
        temp3.add(new WallMapCell(gamePanel));
        gameMapCells.add(temp3);

        ArrayList<GameMapCell> temp4 = new ArrayList<>();
        temp4 .add(new WallMapCell(gamePanel));
        for (int i = 0; i < 8; i++)
        {
            temp4.add(new EmptyGameCell(gamePanel));
        }
        temp4.add(new WallMapCell(gamePanel));
        gameMapCells.add(temp4);

        ArrayList<GameMapCell> temp5 = new ArrayList<>();
        temp5 .add(new WallMapCell(gamePanel));
        for (int i = 0; i < 7; i++)
        {
            temp5.add(new EmptyGameCell(gamePanel));
        }
        temp5.add(new Shop(gamePanel , gameObjectsHolder , "all"));
        temp5.add(new WallMapCell(gamePanel));
        gameMapCells.add(temp5);

        ArrayList<GameMapCell> temp6 = new ArrayList<>();
        Battle battle1 = new Battle(gamePanel , "You’ve entered the castle, it takes a while for your eyes to get used to the darkness but the horrifying halo of your enemies is vaguely visible. Angel’s unsettling presence and the growling of thugs tell you that your first battle has BEGUN!" , "3 weak thugs - 1 weak angel" , 20 , 50);

        temp6 .add(new WallMapCell(gamePanel));
        temp6.add(battle1);
        for (int i = 0; i < 7; i++)
        {
            temp6.add(new EmptyGameCell(gamePanel));
        }
        temp6.add(new WallMapCell(gamePanel));
        gameMapCells.add(temp6);

        ArrayList<GameMapCell> temp7 = new ArrayList<>();
        temp7 .add(new WallMapCell(gamePanel));
        for (int i = 0; i < 8; i++)
        {
            temp7.add(new EmptyGameCell(gamePanel));
        }
        temp7.add(new WallMapCell(gamePanel));
        gameMapCells.add(temp7);

        ArrayList<GameMapCell> temp8 = new ArrayList<>();
        temp8 .add(new WallMapCell(gamePanel));
        for (int i = 0; i < 8; i++)
        {
            temp8.add(new EmptyGameCell(gamePanel));
        }
        temp8.add(new WallMapCell(gamePanel));
        gameMapCells.add(temp8);

        ArrayList<GameMapCell> temp9 = new ArrayList<>();

        temp9 .add(new WallMapCell(gamePanel));
        for (int i = 0; i < 8; i++)
        {
            temp9.add(new EmptyGameCell(gamePanel));
        }
        temp9.add(new WallMapCell(gamePanel));
        gameMapCells.add(temp9);

        ArrayList<GameMapCell> temp10 = new ArrayList<>();
        for (int i = 0; i < 10; i++)
        {
            temp10.add(new WallMapCell(gamePanel));
        }
        gameMapCells.add(temp10);
        gameMapCells.add(temp10);
        gameMapCells.add(temp10);
        gameMapCells.add(temp10);
        gameMapCells.add(temp10);
    }

    public void draw(Graphics2D g2d, Control control) {
        Player player = control.getModel().getStory().getGameObjectsHolder().getPlayer();

        double playerX = player.getLocationX();
        double playerY = player.getLocationY();

        int playerLocationIntX = (int)(playerX * CellSize);
        int playerLocationIntY = (int)(playerY * CellSize);

        int i = 0;
        int j;

        for (ArrayList<GameMapCell> gameMapCell : gameMapCells) {
            j = 0;
            for (GameMapCell mapCell : gameMapCell) {
                int a = (i * CellSize) - (playerLocationIntX) + (GamePanel.ScreenWidth/3);
                int b = (j * CellSize) - (playerLocationIntY) + (GamePanel.ScreenHeight/2);
                mapCell.draw(a , b , g2d);
                j++;
            }
            i++;
        }
    }
}

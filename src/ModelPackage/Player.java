package ModelPackage;

import ControlPackage.Control;
import ViewPackage.GamePanel;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 * Created by Y50 on 5/1/2016.
 */
public class Player {
    public static final int North = 1;
    public static final int West = 2;
    public static final int South = 3;
    public static final int East = 4;
    public static final int AnimationPlayFrameRate = 5;
    public static BufferedImage CharacterSpriteSheet;
    static {
        File img = new File("character.png");

        try {
            Player.CharacterSpriteSheet = ImageIO.read(img);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private ArrayList<Hero> heroes;
    private double locationX;
    private double locationY;
    private int direction;
    private int movementAnimationStep;
    private boolean isWalking;
    private boolean isSprinting;
    private int gold;
    private int xp;
    private int immortalityPotions;
    private Battle currentBattle;
    private boolean isGameOver;
    public Player()
    {
        gold = 40;
        xp = 15;
        immortalityPotions = 3;
        locationX = 4.0;
        locationY = 8.0;
        movementAnimationStep = -1;
        direction = North;

        isGameOver = false;
        isWalking = false;
        isSprinting = false;

        heroes = new ArrayList<>();
    }

    public void showNameAndTypes() {
    }

    public void showAbilitiesAndLevels() {

    }



    public void draw(Graphics2D g2d)
    {
        g2d.setClip(0 , 0 , GamePanel.ScreenWidth , GamePanel.ScreenHeight);

        final int WalkingImagesStartingRow = 7;
        final int SpriteSize = 64;

        int row = direction + WalkingImagesStartingRow;
        int step = 1 + (movementAnimationStep / AnimationPlayFrameRate);

        Image subImg = CharacterSpriteSheet.getSubimage(SpriteSize * step , SpriteSize * row , SpriteSize , SpriteSize);
        subImg = subImg.getScaledInstance(GameMap.CellSize, GameMap.CellSize, Image.SCALE_DEFAULT);
        AffineTransform tx = new AffineTransform();
        int txx = GamePanel.ScreenWidth / 6 -  GameMap.CellSize / 2 + (int)(37 * GameMap.CellSize / 150.0);
        int txy = (GamePanel.ScreenHeight / 4) - GameMap.CellSize / 2 + (int)(21 * GameMap.CellSize / 150.0);
        tx.translate(txx , txy);
        g2d.setTransform(tx);

        g2d.drawImage(subImg , tx , null);
    }



    public void walk(Control control) {
        ArrayList<Integer> pressedKeys = control.getPressedKeys();
        if(pressedKeys.size() != 0)
        {
            int dir = pressedKeys.get(0);
            if(!isWalking || dir != direction) {
                this.movementAnimationStep = 0;

                this.direction = dir;
                this.isWalking = true;
            }
        }
        else
        {
            this.movementAnimationStep = -AnimationPlayFrameRate;
            this.isWalking = false;
        }

        double movementSpeed = 0.05;
        if (isSprinting) {
            movementSpeed *=2;
        }
        final int NumberOfFrames = 8;

        double destinationX = locationX;
        double destinationY = locationY;
        if(isWalking) {
            switch (direction)
            {
                case North:
                    destinationY -= movementSpeed;
                    break;
                case South:
                    destinationY += movementSpeed;
                    break;
                case West:
                    destinationX -= movementSpeed;
                    break;
                case East:
                    destinationX += movementSpeed;
                    break;

            }

            double x1 = destinationX - 0.2;
            double x2 = destinationX + 0.2;
            double x3 = destinationX + 0.2;
            double x4 = destinationX - 0.2;
            double y1 = destinationY + 0.2;
            double y2 = destinationY + 0.2;
            double y3 = destinationY - 0;
            double y4 = destinationY - 0;

            ArrayList<ArrayList<GameMapCell>> temp = control.getModel().getStory().getGameMap().getGameMapCells();
            if(     !temp.get((int)x1).get((int)y1).getPassable() ||
                    !temp.get((int)x2).get((int)y2).getPassable() ||
                    !temp.get((int)x3).get((int)y3).getPassable() ||
                    !temp.get((int)x4).get((int)y4).getPassable())
            {
                movementAnimationStep = -8;
                isWalking = false;
                return;
            }
            else
            {
                locationX = destinationX;
                locationY = destinationY;
                int step = movementAnimationStep + 1;

                if (step >= NumberOfFrames * AnimationPlayFrameRate) {
                    step %= NumberOfFrames * AnimationPlayFrameRate;
                }
                movementAnimationStep = step;
            }
        }
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public double getLocationX() {
        return locationX;
    }

    public double getLocationY() {
        return locationY;
    }

    public int getGold() {
        return gold;
    }

    public int getXp() {
        return xp;
    }

    public ArrayList<Hero> getHeroes() {
        return heroes;
    }

    public Battle getCurrentBattle() {
        return currentBattle;
    }

    public void setSprinting(boolean isSprinting) {
        this.isSprinting = isSprinting;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public void setGold(int gold) {
    this.gold = gold;
}

    public void setXp(int xp) {
    this.xp = xp;
    }

    public void setCurrentBattle(Battle currentBattle) {
        this.currentBattle = currentBattle;
    }

    public void setHeroes(ArrayList<Hero> heroes) {
        this.heroes = heroes;
    }

    public void setImmortalityPotions(int immortalityPotions) {
        this.immortalityPotions = immortalityPotions;
    }

    public int getImmortalityPotions() {
        return immortalityPotions;
    }
}

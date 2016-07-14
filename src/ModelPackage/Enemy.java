package ModelPackage;

import ControlPackage.Control;
import ViewPackage.GamePanel;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.SwingWorker;

/**
 * Created by Y50 on 5/5/2016.
 */
public class Enemy extends Soldier {



    private EnemyArmy enemyArmy;

    public Enemy(String soldierType, String name, File img , EnemyArmy enemyArmy)
    {
        super(soldierType, name, new ArrayList<>(), img);
        this.enemyArmy = enemyArmy;
    }

    @Override
    public void getAttacked(int damage) {
        super.getAttacked(damage);
        if(this.getCurrentHealth() == 0)
        {



            Enemy ExplodingEnemy = this;
            SwingWorker ExplodeAnimation = new SwingWorker()
            {

                @Override
                protected Object doInBackground() throws Exception {
                    Blood blood = new Blood(ExplodingEnemy.getLocationX() , ExplodingEnemy.getLocationY());
                    getGamePanel().getDrawables().add(blood);

                    for (int explosionStep = 0; explosionStep < 3 * Blood.NumberOfExplosionFrames ; explosionStep++) {
                        blood.setStep(explosionStep / getAnimationPlayFrameRate());
                        Thread.sleep(1000/ Control.FPS);
                    }

                    getGamePanel().removeDrawable(blood);
                    enemyArmy.getEnemies().remove(ExplodingEnemy);
                    getGamePanel().removeDrawable(ExplodingEnemy);

                    if (!enemyArmy.getCurrentBattle().isAnyEnemyAlive()) {
                        enemyArmy.getCurrentBattle().setBattleFinished(true);
                    }

                    return null;
                }
            };
            ExplodeAnimation.execute();

//            View.show(this.getName() + " was slain!");
            //TODO : an enemy soldier has been killed here, perform necessary things.
        }
    }

    @Override
    public void init(GamePanel gamePanel ,Story story) {
        super.init(gamePanel , story);
//        this.enemyArmy = story.getCurrentBattle().getEnemyArmy();
    }

//    @Override
//    public void draw(Graphics2D g2d , Control control) {
//        super.draw(g2d , control);
//        if(isExploding)
//        {
//            int row = 0;
//            int column = explosionStep / getAnimationPlayFrameRate();
//            Image subImg = Soldier.getSubImage(bloodSpriteSheet , row , column);
//            g2d.drawImage(subImg , this.getLocationX() , this.getLocationY(), null);
//        }
//    }

    @Override
    public ArrayList<Soldier> getArmy() {
        return this.enemyArmy.getCurrentBattle().getTeam(this , true);
    }

    @Override
    public ArrayList<Soldier> getOpponentArmy() {
        return enemyArmy.getCurrentBattle().getTeam(this , false);

    }
}

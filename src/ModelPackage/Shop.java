package ModelPackage;

import ControlPackage.Drawable;
import ViewPackage.GamePanel;
import ViewPackage.View;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

/**
 * Created by Y50 on 5/1/2016.
 */
public class Shop extends GameMapCell {
    ArrayList<Item> buyableItems;
    private String category;
    ArrayList<Drawable> drawablesBackUp;
    JPanel shopButtons;
    Player player;

    public Shop(GamePanel gamePanel , GameObjectsHolder gameObjectsHolder, String category)
    {
        super(gamePanel);
        buyableItems = gameObjectsHolder.getItemsWithCategory(category);
    }

    public void purchase(String itemName, Hero buyer)
    {
        for (Item buyableItem : buyableItems)
        {
            if(buyableItem.getName().toLowerCase().equals(itemName.toLowerCase()))
            {
                buyableItem.purchasedBy(buyer);
                View.show("item " + buyableItem.getName() + " was purchased for "+  buyer.getName() + ". your current gold is : " + buyer.getPlayer().getGold());
                return;
            }
        }
    }

    public void sell(String itemName, Hero seller)
    {
        for (Item buyableItem : buyableItems)
        {
            if(buyableItem.getName().toLowerCase().equals(itemName.toLowerCase()))
            {
                seller.sell(itemName);
            }
        }
    }

    public void init(Story story)
    {
        this.player = story.getGameObjectsHolder().getPlayer();

        for (int i = 0; i < player.getHeroes().size(); i++) {
            Hero hero = player.getHeroes().get(i);
            hero.setLocationX((int)(GamePanel.ScreenWidth * (i / (5.0 * player.getHeroes().size()) + 0.43)));
            hero.setLocationY((int)(GamePanel.ScreenWidth * (i / (6.5 * player.getHeroes().size()) + 0.32)));
            hero.setDirection(Player.West);
        }

    }

    public void finish(Story story) {
        new SwingWorker()
        {
            @Override
            protected Object doInBackground() throws Exception {
                story.setInShop(false);
                shopButtons.setVisible(false);
                getGamePanel().getFrame().remove(shopButtons);
                getGamePanel().getDrawables().clear();
                getGamePanel().getDrawables().addAll(drawablesBackUp);
                MyCircle circle = new MyCircle(0 , GamePanel.ScreenWidth / 3 , GamePanel.ScreenHeight / 2, Color.RED);
                getGamePanel().getDrawables().add(circle);
                implodeBigCircle(circle);
                getGamePanel().removeDrawable(circle);
                getGamePanel().resetKeyboardListener();
                return null;
            }
        }.execute();


    }

    @Override
    public void draw(int cornerX, int cornerY, Graphics2D g2d) {
        super.drawTile(cornerX, cornerY, g2d, GameMapCell.shopMapCellImage, true);
    }

    @Override
    public void enter(Story story) {


        new SwingWorker()
        {
            @Override
            protected Object doInBackground() throws Exception {
                init(story);
                story.setInShop(true);
                drawablesBackUp = new ArrayList<>();
                drawablesBackUp.addAll(getGamePanel().getDrawables());
                MyCircle circle = new MyCircle(0 , GamePanel.ScreenWidth / 3 , GamePanel.ScreenHeight / 2, Color.RED);
                getGamePanel().getDrawables().add(circle);
                explodeBigCircle(circle);
                story.setInShop(true);

                getGamePanel().getDrawables().clear();
                //
                BackGroundImage backGroundImage = new BackGroundImage(new File("messageBox Background.png") , 0 , 0 , GamePanel.ScreenWidth * 2 /3 , GamePanel.ScreenHeight);
                getGamePanel().getDrawables().add(backGroundImage);
                getGamePanel().getDrawables().addAll(getGamePanel().getControl().getModel().getStory().getGameObjectsHolder().getPlayer().getHeroes());


                shopButtons = new JPanel();
                shopButtons.setPreferredSize(new Dimension(1500, 1000));
                shopButtons.setBounds(0 , 0 , 1500 ,1000 );

                shopButtons.setBackground(Color.GRAY);
                shopButtons.setLayout(null);

                JButton doneButton = new JButton("done");
                doneButton.setBounds(1100, 100 , 200 ,100);
                doneButton.addActionListener(e -> {
                    for (Component component : getGamePanel().getComponents()) {
                        getGamePanel().remove(component);
                    }
                    //TODO : exit!
                    finish(story);
                });
                shopButtons.add(doneButton);
                getGamePanel().getFrame().add(shopButtons);
                shopButtons.setVisible(true);
                new ProceedMenu(getGamePanel() , "You have entered a shop. you can sell your old items or buy new ones!" , () -> {});
                return null;
            }
        }.execute();
    }

    @Override
    public void exit() {

    }
}

package ControlPackage;
import ModelPackage.*;
import ViewPackage.GamePanel;
import ViewPackage.MyButton;
import ViewPackage.View;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.Scanner;
import java.util.Stack;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

/**
 * Created by Y50 on 5/11/2016.
 */
public class Control implements KeyListener,MouseInputListener
{
    private Model model;
    private InputHandler inputHandler;
    private View view;
    private GamePanel gamePanel;
    private Thread gameLoop;
    private boolean isRunning;
    private Stack<Integer> pressedKeys;
    public static final int FPS = 60;

//    public static void main(String[] args) {
//        CreateData.writeItems();
//        CreateData.writeHeroes();
//        CreateData.writeSoldierTypes();
//        CreateData.writeBattles();
//        Control control = new Control();
//        control.getModel().getStory().proceedToNextStage();
//
//        control.getContinuousInput();
//    }

    public Control(GamePanel gamePanel)
    {
        view = new View(this);
        model= new Model();
        model.init(gamePanel);
        this.gamePanel = gamePanel;
        pressedKeys = new Stack<>();
//        inputHandler = new InputHandler(this);

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
//        System.out.println(e.getKeyChar());
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_UP:
                pressedKeys.remove(new Integer(1));
                pressedKeys.push(1);
                break;
            case KeyEvent.VK_RIGHT:
                pressedKeys.remove(new Integer(4));
                pressedKeys.push(4);
                break;
            case KeyEvent.VK_DOWN:
                pressedKeys.remove(new Integer(3));
                pressedKeys.push(3);

                break;
            case KeyEvent.VK_LEFT:
                pressedKeys.remove(new Integer(2));
                pressedKeys.push(2);
                break;
            case KeyEvent.VK_SHIFT :
                model.getStory().getGameObjectsHolder().getPlayer().setSprinting(true);

                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_UP:
                pressedKeys.remove(new Integer(1));
                break;
            case KeyEvent.VK_RIGHT:
                pressedKeys.remove(new Integer(4));
                break;
            case KeyEvent.VK_DOWN:
                pressedKeys.remove(new Integer(3));

                break;
            case KeyEvent.VK_LEFT:
                pressedKeys.remove(new Integer(2));
                break;
            case KeyEvent.VK_SHIFT :
                model.getStory().getGameObjectsHolder().getPlayer().setSprinting(false);
        }
    }

    private boolean isWaitingForHeroSelection = true;
    private boolean isWaitingForActionSelection = false;
    private boolean isWaitingForTargetSelction = false;

    private Soldier selectedSoldier = null;

    private String whatToDo = "";
    private Ability toBeUsedAbility = null;
    private Item toBeUsedItem = null;


    @Override
    public void mouseClicked(MouseEvent e) {
        if(model.getStory().getInBattle())
        {
            for (Hero hero : model.getStory().getGameObjectsHolder().getPlayer().getHeroes()) {
                checkSoldier(e, hero);
            }


            for (Enemy enemy : model.getStory().getGameObjectsHolder().getPlayer().getCurrentBattle().getEnemyArmy().getEnemies()) {
                checkSoldier(e , enemy);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    private void checkSoldier(MouseEvent e, Soldier soldier) {
        int x = soldier.getLocationX()+ GameMap.CellSize / 2;
        int y = soldier.getLocationY()+ GameMap.CellSize / 2;
        int a = e.getX();
        int b = e.getY();

        if(Math.sqrt( (double)((x - a)*(x - a) + (y - b)*(y - b))) < (GameMap.CellSize / 3))
        {
            if(!model.getStory().getGameObjectsHolder().getPlayer().getCurrentBattle().isShowingOtherTeamAttackAnimation)
            {
                if (isWaitingForHeroSelection) {
                    selectedSoldier = soldier;
                    showSelectedSoldierData();
                } else if (isWaitingForActionSelection) {
                    selectedSoldier = soldier;
                    showSelectedSoldierData();
                } else if (isWaitingForTargetSelction) {
                    clearPage();
                    switch (whatToDo) {
                        case "attack":
                            selectedSoldier.attack(soldier, 0.0);
                            isWaitingForHeroSelection = true;
                            break;
                        case "cast":
                            selectedSoldier.cast(toBeUsedAbility.getName(), soldier.getName());
                            isWaitingForHeroSelection = true;
                            break;
                        case "use":

                            break;
                    }
                }
            }
        }
    }

    private void showSelectedSoldierData() {
        if(selectedSoldier instanceof Hero) {
            clearPage();
            MyButton attackButton = new MyButton();
            attackButton.setText("Attack!");
            int x = selectedSoldier.getLocationX()+ GameMap.CellSize / 2;
            int y = selectedSoldier.getLocationY()+ GameMap.CellSize / 2;

            attackButton.setBounds(x - GameMap.CellSize, y - GameMap.CellSize / 2, GameMap.CellSize / 2, GameMap.CellSize / 2);
            attackButton.setVisible(true);

            MyButton castButton = new MyButton();
            castButton.setText("Cast!");
            castButton.setBounds(x - GameMap.CellSize, y, GameMap.CellSize / 2, GameMap.CellSize / 2);
            castButton.setVisible(true);


            attackButton.addActionListener(e1 -> {
                whatToDo = "attack";
                isWaitingForHeroSelection = false;
                isWaitingForTargetSelction = true;
            });

            castButton.addActionListener(e2 -> {

                int i = 0;

                for (Ability ability : selectedSoldier.getAbilities()) {
                    if (ability.isCastable()) {

                        JButton abilityButton = new JButton(ability.getName());
                        abilityButton.setBounds(x - 2 * GameMap.CellSize, y - GameMap.CellSize / 2 + i * GameMap.CellSize / 2, GameMap.CellSize / 2, GameMap.CellSize / 2);
                        i++;
                        abilityButton.setVisible(true);
                        if(ability.getLevel() == 0)
                        {
                            abilityButton.setEnabled(false);
                        }
                        abilityButton.addActionListener(e3 ->{
                            isWaitingForHeroSelection = false;
                            CastableData castableData = ((CastableAbility)ability).getCastableData().get(ability.getLevel() - 1);
                            if(castableData.isGlobalFriendly() || castableData.isGlobalEnemy())
                            {
                                clearPage();
                                selectedSoldier.cast(ability.getName(), null);
                                isWaitingForHeroSelection = true;
                            }
                            else
                            {
                                whatToDo = "cast";
                                toBeUsedAbility = ability;
                                isWaitingForTargetSelction = true;
                            }
                        });
                        gamePanel.add(abilityButton);
                    }
                }
            });

            gamePanel.add(attackButton);
            gamePanel.add(castButton);
        }
    }

    private void clearPage() {
        for (Component component : gamePanel.getComponents()) {
            gamePanel.remove(component);
        }

    }

    public void getContinuousInput()
    {
        Scanner scanner = new Scanner(System.in);
        while(!model.getStory().isGameOver()) {
            view.getInput(scanner);
        }
    }


    public InputHandler getInputHandler() {
        return inputHandler;
    }

    public Model getModel() {
        return model;
    }

    public View getView() {
        return view;
    }

    public void start() {
        gameLoop = new Thread(() ->
        {
            isRunning = true;
            while(isRunning)
            {
                gameUpdate();
                gameRender();
                try {
                    Thread.sleep(1000/FPS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        gameLoop.start();
    }

    private void gameRender() {
        gamePanel.repaint();
    }

    private void gameUpdate() {
        model.update(1000 / (double)FPS);
    }

    public Stack<Integer> getPressedKeys() {
        return pressedKeys;
    }
}

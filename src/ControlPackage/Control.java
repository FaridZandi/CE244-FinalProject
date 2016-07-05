package ControlPackage;
import ModelPackage.*;
import ViewPackage.GamePanel;
import ViewPackage.View;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Y50 on 5/11/2016.
 */
public class Control implements KeyListener
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

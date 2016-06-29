package ControlPackage;
import ModelPackage.*;
import ViewPackage.GamePanel;
import ViewPackage.View;
import java.util.Scanner;

/**
 * Created by Y50 on 5/11/2016.
 */
public class Control
{
    private Model model;
    private InputHandler inputHandler;
    private View view;
    private GamePanel gamePanel;
    private Thread gameLoop;
    private boolean isRunning;

    public static final int FPS = 30;

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
        inputHandler = new InputHandler(this);
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
//        model.update(1000 / (double)FPS);
    }

}

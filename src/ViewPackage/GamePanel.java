package ViewPackage;

import ControlPackage.Control;
import ControlPackage.Drawable;
import ModelPackage.Ability;
import ModelPackage.Enemy;
import ModelPackage.GameMap;
import ModelPackage.Hero;
import ModelPackage.Soldier;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.event.MouseInputListener;

/**
 * Created by Y50 on 6/1/2016.
 */
public class GamePanel extends JPanel
{


    public static final int ScreenWidth = 1500;
    public static final int ScreenHeight = 1000;
    private Control control;
    private JFrame frame;
    public void init(Control control) {
        frame = new JFrame("Amazing Brick");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon("2leftarrow.png").getImage());

        frame.setSize(1500, 1000);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.GRAY);
        frame.getContentPane().add(this);

        this.control = control;
        this.setPreferredSize(new Dimension(1500 , 1000));
        this.setLocation(0 , 0);
        this.setBounds(0 , 0 , 1500 , 1000);
        this.setBackground(Color.CYAN);
        this.addMouseMotionListener(control);
        this.addMouseListener(control);
        frame.addKeyListener(control);
        frame.setLayout(null);
        frame.setVisible(true);

        drawables = new ArrayList<>();

        drawables.add(control.getModel().getStory().getGameMap());
        drawables.add(control.getModel().getStory().getGameObjectsHolder().getPlayer());
    }




    private ArrayList<Drawable> drawables;

    public synchronized void removeDrawable(Drawable drawable)
    {
        drawables.remove(drawable);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        super.paintComponent(g);

        for (int i = 0; i < drawables.size(); i++) {
            drawables.get(i).draw(g2d , control);
        }
//        GameMap gameMap = control.getModel().getStory().getGameMap();
//        Player player = control.getModel().getStory().getGameObjectsHolder().getPlayer();
//
//        if (!this.control.getModel().getStory().getInBattle()) {
//
//            gameMap.draw(g2d , control);
//            player.draw(g2d ,control);
//        }
//        else
//        {
//            player.drawHeroes(g2d , control);
//            player.getCurrentBattle().getEnemyArmy().drawUnits(g2d , control);
//        }
//        g2d.setColor(Color.BLACK);
//        g2d.fillRect(ScreenWidth * 2 / 3 , 0 , ScreenWidth /3 , ScreenHeight);

    }



    public JFrame getFrame() {
        return frame;
    }

    public Control getControl() {
        return control;
    }


    public ArrayList<Drawable> getDrawables() {
        return drawables;
    }

    public void resetKeyboardListener() {
        removeKeyListener(control);
        addKeyListener(control);

        grabFocus();
    }
}

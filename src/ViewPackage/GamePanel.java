package ViewPackage;

import ControlPackage.Control;
import ControlPackage.Drawable;
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
public class GamePanel extends JPanel implements MouseInputListener
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
        this.setBackground(Color.WHITE);
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        frame.addKeyListener(control);
        frame.setLayout(null);
        frame.setVisible(true);

        drawables = new ArrayList<>();

        drawables.add(control.getModel().getStory().getGameMap());
        drawables.add(control.getModel().getStory().getGameObjectsHolder().getPlayer());
    }

    private boolean isWaiting = true;
    private Hero doer = null;
    private boolean isAwaitingForInput = false;


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

    @Override
    public void mouseClicked(MouseEvent e) {
        if(this.control.getModel().getStory().getInBattle())
        {
            for (Hero hero : this.control.getModel().getStory().getGameObjectsHolder().getPlayer().getHeroes()) {
                checkSoldier(e, hero);
            }


            for (Enemy enemy : this.control.getModel().getStory().getGameObjectsHolder().getPlayer().getCurrentBattle().getEnemyArmy().getEnemies()) {
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
            if(isAwaitingForInput)
            {
                doer.attack(soldier , 0);
                isWaiting = true;
                isAwaitingForInput = false;
                doer = null;
            }
            for (Component component : getComponents()) {
                remove(component);
            }
            JButton jButton = new JButton();
            jButton.setText("Attack!");
            jButton.setBounds(x - GameMap.CellSize , y , GameMap.CellSize / 2 , GameMap.CellSize/2);
            jButton.setVisible(true);


            jButton.addActionListener(e1 -> {
                if(isWaiting)
                {
                    isAwaitingForInput = true;
                    doer = (Hero) soldier;
                    isWaiting = false;
                }
            });
            add(jButton);
        }
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
}

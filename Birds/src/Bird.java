import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Bird {

    public static final int max_top = 10;
    public static final int max_bottom = 392;

    float vy = 0;
    float acceleration = -0.7f;

    int speed = 5;
    int coord1 = 0;
    int coord2 = 1024;

    int x = 70;
    int y = 0;
    int dy = 0;

    int hp = 100;
    int level = 0;
    //double levelP = 200;
    int levelP = 0;
    double points = 0;

    int gBonus_count = 0;
    int bBonus_count = 0;
    int sBonus_count = 0;
    int MedBlocks_count = 0;
    int SuperMedBlocks_count = 0;
    int IntersectEnemy_count = 0;

    int spawn_rarity = 15000;

    boolean maxlevel = false;

    Image bird = new ImageIcon("bird.png").getImage();

    public Rectangle r() {
        return new Rectangle(x, y, 34, 24);
    }

    public void move() {

        y -= dy;

        if (y <= max_top)
            y = max_top;
        /*if (y >= max_bottom)
            y = max_bottom;*/
            //vy = max_bottom;

        if (coord2 - speed<= 0) {
            coord1 = 0;
            coord2 = 1024;
        } else {
            coord1 -= speed;
            coord2 -= speed;
        }
    }

    public void update() {

        vy += acceleration;
        y -= vy;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W ) {
            vy = 10;
        }
        /*if (key == KeyEvent.VK_CAPS_LOCK) {
            //spawn_rarity = 400;
            String cheat_code = JOptionPane.showInputDialog("ENTER CHEAT CODE: ");
             if (cheat_code == "GODMODE") {
                //sleep(10000);
                b.hp = (int) Double.POSITIVE_INFINITY;
                System.out.print(b.hp);
            }
            if (cheat_code == "MAXLEVEL") {
                spawn_rarity = 400;
                JOptionPane.showMessageDialog(null, "Вы выиграли!\n");
            }
        }*/
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
    }


}

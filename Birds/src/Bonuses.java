import javax.swing.*;
import java.awt.*;

public class Bonuses {

    int x;
    int y;
    int speed=-1;

    Image greenBonus = new ImageIcon("green_bonus.png").getImage();
    Image blueBonus = new ImageIcon("blue_bonus.png").getImage();
    Image superBonus = new ImageIcon("super_bonus_new.png").getImage();
    BackGround bn;

    public Rectangle r() {
        return new Rectangle(x, y, 40, 40);
    }

    public Bonuses(int x, int y, BackGround bn) {
        this.x = x;
        this.y = y;
        this.bn = bn;
    }

    public void move() {
        x = x - bn.b.speed + speed;
    }
}

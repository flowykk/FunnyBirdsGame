import javax.swing.*;
import java.awt.*;

public class Enemy {
    int x;
    int y;
    int en_speed=-1;

    Image enemy = new ImageIcon("enemy.png").getImage();
    BackGround bg;

    public Rectangle r() {
        return new Rectangle(x, y, 34, 24);
    }

    public Enemy(int x, int y, BackGround bg) {
        this.x = x;
        this.y = y;
        this.bg = bg;
    }

    public void move() {
        x = x - bg.b.speed + en_speed;
    }

}

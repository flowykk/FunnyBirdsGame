import javax.swing.*;
import java.awt.*;

public class MedBlocks {

    int x;
    int y;
    int speed=-1;

    Image medicine = new ImageIcon("medblock_new.png").getImage();
    Image smedicine = new ImageIcon("super_medblock.png").getImage();

    BackGround Med;

    public Rectangle m() {
        return new Rectangle(x, y, 50, 50);
    }

    public MedBlocks(int x, int y, BackGround Med) {
        this.x = x;
        this.y = y;
        // this.speed = speed;
        this.Med = Med;
    }
    /*public void SMedBlocks(int x, int y, BackGround Med) {
        this.x = x;
        this.y = y;
        // this.speed = speed;
        this.Med = Med;
    }*/

    public void move() {
        x = x - Med.b.speed + speed;
    }
}

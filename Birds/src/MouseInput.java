import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        /*public Rectangle playButton = new Rectangle(440,95,140,50);
        public Rectangle quitButton = new Rectangle(440,165,140,50);*/

        if (mx >= 440 && mx <= 580) {
            if (my >= 95 && my <= 145){
                BackGround.State = BackGround.STATE.GAME;
            }
        }

        /*if (mx >= 440 && mx <= 580) {
            if (my >= 190 && my <= 240){
                BackGround.State = BackGround.STATE.HELP;
            }
        }*/

        if (mx >= 440 && mx <= 580) {
            if (my >= 165 && my <= 215){
                System.exit(0);
            }
        }
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
}

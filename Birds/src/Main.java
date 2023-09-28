import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        JFrame f = new JFrame("JGames");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1024,410);
        f.add(new BackGround());
        f.setBackground(Color.BLACK);
        f.setVisible(true);


    }
}

class BackGround extends JPanel implements ActionListener, Runnable{

    Bird b = new Bird();


    Timer Timer = new Timer(20, this);

    Image img = new ImageIcon("bg.png").getImage();
    Image img_menu = new ImageIcon("bgnew.png").getImage();

    Thread EnemiesCreator = new Thread(this);
    java.util.List<Enemy> meteorites = new ArrayList<>();

    Thread MedblockSpawner = new Thread(this);
    java.util.List<MedBlocks> medicine = new ArrayList<>();

    Thread SMedblockSpawner = new Thread(this);
    java.util.List<MedBlocks> smedicine = new ArrayList<>();

    Thread GreenBonusSpawner = new Thread(this);
    java.util.List<Bonuses> greenBonus = new ArrayList<>();

    Thread BlueBonusSpawner = new Thread(this);
    java.util.List<Bonuses> blueBonus = new ArrayList<>();

    Thread SuperBonusSpawner = new Thread(this);
    java.util.List<Bonuses> superBonus = new ArrayList<>();

    public enum STATE {
        MENU,
        GAME
    }

    public static STATE State = STATE.MENU;

    public Rectangle playButton = new Rectangle(440,95,140,50);
    // public Rectangle helpButton = new Rectangle(440,190,140,50);
    public Rectangle quitButton = new Rectangle(440,165,140,50);

    public BackGround() {
        Timer.start();
        EnemiesCreator.start();
        MedblockSpawner.start();
        SMedblockSpawner.start();
        GreenBonusSpawner.start();
        BlueBonusSpawner.start();
        SuperBonusSpawner.start();
        addKeyListener(new MyKeyAdapter());
        addMouseListener(new MouseInput());
        setFocusable(true);
    }

    private class MyKeyAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            b.keyPressed(e);
            //check_CheatCode(e);
        }

        public void keyReleased(KeyEvent e) {
            b.keyReleased(e);
        }
    }

    public void paint(Graphics g) {

        if (State == STATE.GAME) {
            g.drawImage(img, b.coord1, 0, null);
            g.drawImage(img, b.coord2, 0, null);
            g.drawImage(b.bird, b.x, b.y, null);

            g.setFont(new Font("progresspixel-bold", Font.PLAIN, 15));
            g.setColor(Color.white);
            g.drawString("HP: " + b.hp, 870, 23);
            g.drawString("POINTS: " + String.format("%.0f", b.points), 870, 43);
            g.drawString("LEVEL: " + b.level, 870, 63);
            //g.drawString("levelP: " + b.levelP, 870, 83);
            //g.drawString("SPAWN: " + b.spawn_rarity, 900, 83);

            /*g.setFont(new Font("progresspixel-bold", Font.PLAIN, 12));
            g.setColor(Color.white);
            g.drawString("ДЛЯ ВВОДА ЧИТ-КОДА НАЖМИТЕ CAPS-LOCK", 10, 20);*/

        } else if(State == STATE.MENU) {

            Graphics2D g2d = (Graphics2D) g;

            g.drawImage(img_menu, b.coord1, 0, null);

            Font fnt = new Font("progresspixel-bold", Font.BOLD,45);
            g.setFont(fnt);
            g.setColor(Color.WHITE);
            g.drawString("GAME BIRDS", 360, 70);

            Font fnt1 = new Font("progresspixel-bold", Font.BOLD,33);
            g.setFont(fnt1);
            g.drawString("PLAY", playButton.x + 21, playButton.y+40);
            g2d.draw(playButton);
            /*g.drawString("HELP", helpButton.x + 26, helpButton.y+40);
            g2d.draw(helpButton);*/
            g.drawString("QUIT", quitButton.x + 26, quitButton.y+40);
            g2d.draw(quitButton);

            Font fnt2 = new Font("progresspixel-bold", Font.BOLD,15);
            g.setFont(fnt2);
            g.drawString("PRESS W-Key TO MAKE YOUR BIRD FLY", 340, 260);
            g.drawString("COLLECT STARS TO FARM POINTS", 360, 285);
            g.drawString("COLLECT MEDKITS TO HEAL", 388, 310);
            g.setColor(Color.RED);
            g.drawString("TRY NOT TO BUMP INTO ENEMY BIRDS", 347, 335);
        }

        //Menu menu = new Menu();

        Iterator<Enemy> i = meteorites.iterator();
        while(i.hasNext()) {
            Enemy m = i.next();
            if(m.x >= 1100 || m.x <= -100 || b.r().intersects(m.r())) {
                i.remove();
            }
            else {
                m.move();
                g.drawImage(m.enemy, m.x, m.y, null);
            }
        }

        Iterator<MedBlocks> k = medicine.iterator();
        while(k.hasNext()) {
            MedBlocks mb = k.next();
            if(mb.x >= 1600 || mb.x <= -100 || b.r().intersects(mb.m())) {
                //g.drawString("+50 ", b.x, b.y+50);
                k.remove();
            }
            else {
                mb.move();
                g.drawImage(mb.medicine, mb.x, mb.y, null);
            }
        }

        Iterator<MedBlocks> l = smedicine.iterator();
        while(l.hasNext()) {
            MedBlocks mb = l.next();
            if(mb.x >= 1600 || mb.x <= -100 || b.r().intersects(mb.m())) {
                //g.drawString("+50 ", b.x, b.y+50);
                l.remove();
            }
            else {
                mb.move();
                g.drawImage(mb.smedicine, mb.x, mb.y, null);
            }
        }

        Iterator<Bonuses> j = greenBonus.iterator();
        while(j.hasNext()) {
            Bonuses gb = j.next();
            if(gb.x >= 2000 || gb.x <= -100 || b.r().intersects(gb.r())) {
                j.remove();
            }
            else {
                gb.move();
                g.drawImage(gb.greenBonus, gb.x, gb.y, null);
            }
        }
        Iterator<Bonuses> f = blueBonus.iterator();
        while(f.hasNext()) {
            Bonuses gb = f.next();
            if(gb.x >= 2000 || gb.x <= -100 || b.r().intersects(gb.r())) {
                f.remove();
            }
            else {
                gb.move();
                g.drawImage(gb.blueBonus, gb.x, gb.y, null);
            }
        }
        Iterator<Bonuses> r = superBonus.iterator();
        while(r.hasNext()) {
            Bonuses gb = r.next();
            if(gb.x >= 2000 || gb.x <= -100 || b.r().intersects(gb.r())) {
                r.remove();
            }
            else {
                gb.move();
                g.drawImage(gb.superBonus, gb.x, gb.y, null);
            }
        }
    }

    private void Intersects() {
        for (MedBlocks mb : medicine) {
            if (b.r().intersects(mb.m())) {
                b.hp +=10;
                b.MedBlocks_count += 1;

                //System.out.print("hp: "+b.hp);
                //break;
            }
            //System.out.print("hp: "+b.hp);
        }
        for (MedBlocks mb : smedicine) {
            if (b.r().intersects(mb.m())) {
                b.hp +=60;
                b.SuperMedBlocks_count += 1;
                //System.out.print("hp: "+b.hp);
                //break;
            }
            //System.out.print("hp: "+b.hp);
        }
        for (Enemy en : meteorites) {
            if (b.r().intersects(en.r())) {
                b.hp -=20;
                b.points -= 50;
                b.IntersectEnemy_count += 1;
                //System.out.print("hp: "+b.hp);
                //break;
            }
            //System.out.print("hp: "+b.hp);
        }

        for (Bonuses gb : greenBonus) {
            if (b.r().intersects(gb.r())) {
                b.points += 50;
                b.gBonus_count += 1;
                //System.out.print("hp: "+b.hp);
                //break;
            }
            //System.out.print("hp: "+b.hp);
        }
        for (Bonuses gb : blueBonus) {
            if (b.r().intersects(gb.r())) {
                b.points += 80;
                b.bBonus_count += 1;
                //System.out.print("hp: "+b.hp);
                //break;
            }
            //System.out.print("hp: "+b.hp);
        }
        for (Bonuses gb : superBonus) {
            if (b.r().intersects(gb.r())) {
                b.points += 150;
                b.sBonus_count += 1;
                //System.out.print("hp: "+b.hp);
                //break;
            }
            //System.out.print("hp: "+b.hp);
        }
    }

    public void check_lose_or_win() {
        if (b.hp <= 0) {
            JOptionPane.showMessageDialog(null, "Вы проиграли! HP меньше 0!\n" +
                    "Набранные очки: " + String.format("%.0f", b.points) +
                    "\nКоличество пройденных уровней: " + b.level +
                    "\nКоличество собранных аптечек: " + b.MedBlocks_count +
                    "\nКоличество собранных cупер-аптечек: " + b.SuperMedBlocks_count +
                    "\nКоличество собранных зелёных бонусов: " + b.gBonus_count +
                    "\nКоличество собранных синих бонусов: " + b.bBonus_count +
                    "\nКоличество собранных супер-бонусов: " + b.sBonus_count +
                    "\nКоличество столкновений с вражескими птицами: " + b.IntersectEnemy_count);

            System.exit(1);
        } else if (b.hp > 100) {
            b.hp = 100;
        }

        if (b.y >= Bird.max_bottom) {
            JOptionPane.showMessageDialog(null, "Вы проиграли! Вы столкнулись с землёй!\n" +
                    "Набранные очки: " + String.format("%.0f", b.points) +
                    "\nКоличество пройденных уровней: " + b.level +
                    "\nКоличество собранных аптечек: " + b.MedBlocks_count +
                    "\nКоличество собранных cупер-аптечек: " + b.SuperMedBlocks_count +
                    "\nКоличество собранных зелёных бонусов: " + b.gBonus_count +
                    "\nКоличество собранных синих бонусов: " + b.bBonus_count +
                    "\nКоличество собранных супер-бонусов: " + b.sBonus_count +
                    "\nКоличество столкновений с вражескими птицами: " + b.IntersectEnemy_count);
            //State = BackGround.STATE.MENU;
            System.exit(1);
        }

        if (b.level >= 70) {
            JOptionPane.showMessageDialog(null, "Вы выиграли!\n" +
                    "Набранные очки: " + String.format("%.0f", b.points) +
                    "\nКоличество пройденных уровней: " + b.level +
                    "\nКоличество собранных аптечек: " + b.MedBlocks_count +
                    "\nКоличество собранных cупер-аптечек: " + b.SuperMedBlocks_count +
                    "\nКоличество собранных зелёных бонусов: " + b.gBonus_count +
                    "\nКоличество собранных синих бонусов: " + b.bBonus_count +
                    "\nКоличество собранных супер-бонусов: " + b.sBonus_count +
                    "\nКоличество столкновений с вражескими птицами: " + b.IntersectEnemy_count);

            System.exit(1);
        }
    }

    /*public void check_CheatCode(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_CAPS_LOCK) {
            String cheat_code = JOptionPane.showInputDialog("ENTER CHEAT CODE: ");
             /*if (cheat_code == "GODMODE") {
                //sleep(10000);
                b.hp = (int) Double.POSITIVE_INFINITY;
                System.out.print(b.hp);
            }
            if (cheat_code == "MAXLEVEL") {
                b.spawn_rarity = 400;
                JOptionPane.showMessageDialog(null, "Вы выиграли!\n");
            }
            if (cheat_code == "U6J7IYUN") {
                //sleep(10000);
            }
        }
    }*/

    public void point_check() {
        if (b.points <= 0) {
            b.points = 0;
        }

        /*if (b.points % 200 == 0 && b.points != 0) {
            b.level += 1;
            if (b.level % 5 == 0) {
                b.speed += 1;
            }
            b.spawn_rarity -=200;
        }*/
        /*if (b.levelP > 10) {
            b.level += 1;
        }*/
        if (b.points > b.levelP) {
            b.level +=1;
            if (b.level % 5 == 0) {
                b.speed += 1;
            }
            b.spawn_rarity -=200;
            b.levelP += 200;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (State == STATE.GAME) {
            b.move();
            b.update();
            Intersects();
            check_lose_or_win();
            b.points += 2;
            //b.levelP += 0.02;
            point_check();
            repaint();
        }
    }



    /*public static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch(Exception e) {}
    }*/

    @Override
    public void run() {
        while (true) {
            Random random = new Random();
            if (State == STATE.GAME) {
                try {
                    Thread.sleep(random.nextInt(b.spawn_rarity));
                    meteorites.add(new Enemy(1024, random.nextInt(600), this));

                    medicine.add(new MedBlocks(1024 + random.nextInt(500), random.nextInt(3000), this));

                    smedicine.add(new MedBlocks(1024 + random.nextInt(600), random.nextInt(10000), this));

                    greenBonus.add(new Bonuses(1024 + random.nextInt(700), random.nextInt(1000), this));

                    blueBonus.add(new Bonuses(1024 + random.nextInt(700), random.nextInt(4000), this));

                    superBonus.add(new Bonuses(1024 + random.nextInt(700), random.nextInt(10000), this));

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
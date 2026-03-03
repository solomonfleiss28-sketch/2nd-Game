//vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv DON'T CHANGE! vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
// Graphics Libraries
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

public class BasicGameApp implements Runnable, KeyListener, MouseListener {

    final int WIDTH = 1400;
    final int HEIGHT = 1200;

    Skier skier;
    Image skierImage;
    Image backgroundImage;

    Gate[] gates = new Gate[10];

    boolean gameOver = false;

    // CONSTRUCTOR
    public BasicGameApp() {

        setUpGraphics();

        skier = new Skier(650, 800);
        skierImage = getImage("skier.png");
        backgroundImage = getImage("background.jpg");

        int startY = -200;
        for (int i = 0; i < gates.length; i++) {
            int leftPole = 200 + (int)(Math.random() * 800);
            int rightPole = leftPole + 200;
            gates[i] = new Gate(leftPole, rightPole, startY);
            startY -= 180;
        }
    }

    public void moveThings() {

        if (gameOver) return;

        skier.move();

        for (int i = 0; i < gates.length; i++) {
            gates[i].scroll(5);
            if (gates[i].passed) {
                if (gates[i].y > skier.y + skier.height) {
                    if (skier.x > gates[i].leftX + 20 && skier.x + skier.width < gates[i].rightX) {

                        gates[i].passed = true;
                        System.out.println("Gate Passed!");

                    } else {
                        gameOver = true;
                        System.out.println("DSQ!");
                    }
                }
            }
        }
    }

    private void render() {

        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();

        g.clearRect(0, 0, WIDTH, HEIGHT);
        g.drawImage(backgroundImage, 0, 0, WIDTH, HEIGHT, null);

        // draw gates
        for (int i = 0; i < gates.length; i++) {
            gates[i].draw(g);
        }

        // draw skier
        g.drawImage(skierImage, skier.x, skier.y, skier.width, skier.height, null);

        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 80));
            g.drawString("DSQ'ed!", 400, 500);
        }

        g.dispose();
        bufferStrategy.show();
    }

    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    //vvvvvvvvvvvvvvvvvvvvvvvvvvvv DON'T CHANGE BELOW vvvvvvvvvvvvvvvvvvvv

    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;
    public BufferStrategy bufferStrategy;

    public static void main(String[] args) {
        BasicGameApp ex = new BasicGameApp();
        new Thread(ex).start();
    }

    public void run() {
        while (true) {
            moveThings();
            render();
            pause(10);
        }
    }

    public void pause(int time) {
        try { Thread.sleep(time); }
        catch (InterruptedException e) {}
    }

    private Image getImage(String filename){
        return Toolkit.getDefaultToolkit().getImage(filename);
    }

    private void setUpGraphics() {

        frame = new JFrame("Ski Game");

        panel = (JPanel) frame.getContentPane();
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        panel.setLayout(null);

        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);
        canvas.addKeyListener(this);

        panel.add(canvas);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == 37) Skier.left = true;
        if (key == 39) Skier.right = true;
        if (key == 38) Skier.up = true;
        if (key == 40) Skier.down = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == 37) Skier.left = false;
        if (key == 39) Skier.right = false;
        if (key == 38) Skier.up = false;
        if (key == 40) Skier.down = false;
    }

    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}

//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX



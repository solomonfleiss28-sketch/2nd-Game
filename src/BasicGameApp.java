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

   // defining the variables
    Skier skier;
    Image skierImage;
    Image backgroundImage;
    boolean gameStarted = false;
    boolean gameOver = false;
    boolean gameWon = false;

    int score = 0;
    int health = 100;

// array with ten gates provided
    Gate[] gates = new Gate[20];


    // CONSTRUCTOR
    public BasicGameApp() {

        setUpGraphics();
// building the skier then loading the background immage
        skier = new Skier(650, 800);
        skierImage = getImage("skier.png");
        backgroundImage = getImage("background.jpg");

      // builds the gates and there polls
        int startY = -200;
        for (int i = 0; i < gates.length; i++) {
            int leftPole = 300 + (int)(Math.random() * 800);
            int rightPole = leftPole + 300;
            gates[i] = new Gate(leftPole, rightPole, startY);
            startY -= 250; // controls the distance between the gates
        }
    }

    public void moveThings() {

        // doesn't do anything if the game hasn't started
        if (gameStarted == false) {
            return;
        }
//doesn't do anythign if the game is over
        if (gameOver == true || gameWon == true) {
            return;
        }

        // moves the skier
        skier.move();


        // makes the loop through every gate
        for (int i = 0; i < gates.length; i++) {

            // move gate down the hill
            gates[i].moveDown(2);

            if (gates[i].passed == false) {

                // check if the skeir is above the gate
                if (gates[i].y > skier.y + skier.height) {

                    // check if skier is between gate poles
                    if (skier.x > gates[i].leftX + 20) {

                        if (skier.x + skier.width < gates[i].rightX) {

                           //if skier sucseeds
                            gates[i].passed = true;
                            score = score + 10;

                        } else {
                            // if missed right then -25 health
                            gates[i].passed = true;
                            health = health - 25;
                        }

                    } else {
                        // if missed left then -25 health
                        gates[i].passed = true;
                        health = health - 25;
                    }

                    // after check total health
                    if (health <= 0) {
                        gameOver = true;
                    }
                }
            }
        }
        gameWon = true;
        for (int i = 0; i < gates.length; i++) {
            if (gates[i].passed == false) {//
                gameWon = false;
            }
        }
    }

    private void render() {

        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();

        // defines the click to start button.
        if (gameStarted== false) {
            g.setColor(Color.RED);
            g.fillRect(0, 0, WIDTH, HEIGHT);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 60));
            g.drawString("Ski Racing Game", 500, 400);
            g.drawString("Click to Start", 500, 500);
            g.dispose();
            bufferStrategy.show();
            return;
        }
        // loads background immage into the game
        g.clearRect(0, 0, WIDTH, HEIGHT);
        g.drawImage(backgroundImage, 0, 0, WIDTH, HEIGHT, null);

        // draw gates
        for (int i = 0; i < gates.length; i++) {
            gates[i].draw(g);
        }

        // draw skier
        g.drawImage(skierImage, skier.x, skier.y, skier.width, skier.height, null);

       // health bar below
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Score: " + score, 50, 50);
        g.drawRect(50, 80, 200, 30);
        g.setColor(Color.red);
        g.fillRect(50, 80, health * 2, 30);

        //signal message to user if the game is complete
        if (gameOver) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, WIDTH, HEIGHT);
            g.setColor(Color.MAGENTA);
            g.setFont(new Font("Arial", Font.BOLD, 60));
            g.drawString("GAME OVER", 500, 400);
            g.drawString("Score: " + score, 500, 500);
        }
        if (gameWon) {// If the player wins the game then the message below will print.
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, WIDTH, HEIGHT);
            g.setColor(Color.GREEN);
            g.setFont(new Font("MonoSpaced", Font.BOLD, 60));
            g.drawString("YOU WON!!!!", 500, 400);
            g.drawString("Score: " + score, 500, 500);
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

        frame = new JFrame("Coolest Ski Game Ever!!");

        panel = (JPanel) frame.getContentPane();//sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT)); //sizes the JPanel
        panel.setLayout(null); //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);
        canvas.addKeyListener(this);
        canvas.addMouseListener(this);


        panel.add(canvas);// adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");
    }

    @Override
    public void keyTyped(KeyEvent e) {}
// not being used
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
// allows the arrow keys to control the skier
        if (key == 37) Skier.left = false;
        if (key == 39) Skier.right = false;
        if (key == 38) Skier.up = false;
        if (key == 40) Skier.down = false;
    }

    @Override public void mouseClicked(MouseEvent e) {}
    // this will be unused
    @Override public void mousePressed(MouseEvent e) {
        gameStarted = true;// this action describes that to start the game the mouse must be clicked
    }


    // all mouse events  below will be unused
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}

//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX



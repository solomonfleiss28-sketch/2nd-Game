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

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;
    Skier skier;
    Image skierImage;
    Image backgroundImage;



    //Variable Definition Section
    //You can set their initial values too
    // Like Mario mario = new Mario(); //





    // Initialize your variables and construct your program objects here.
    public BasicGameApp() { // BasicGameApp constructor
        setUpGraphics();
        skier = new Skier(450, 500);
        skierImage = getImage("skier.png");
        backgroundImage = getImage("background.jpg");
        //variable and objects
        //create (construct) the objects needed for the game






    }
    // end BasicGameApp constructor

    public void moveThings() {
        //call the move() code for each object  -
        skier.move();

    }

    //Paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);
        g.drawImage(backgroundImage, 0, 0, WIDTH, HEIGHT, null);

        g.drawImage(skierImage, skier.x, skier.y, skier.width, skier.height, null);


        //draw the images
        // Signature: drawImage(Image img, int x, int y, int width, int height, ImageObserver observer)



        // Keep the code below at the end of render()
        g.dispose();
        bufferStrategy.show();
    }














    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
//vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv DON'T CHANGE! vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;
    public BufferStrategy bufferStrategy;

    // PSVM: This is the code that runs first and automatically
    public static void main(String[] args) {
        BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
        new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
    }

    // main thread
    // this is the code that plays the game after you set things up
    public void run() {
        //for the moment we will loop things forever.
        while (true) {
            moveThings();  //move all the game objects
            render();  // paint the graphics
            pause(10); // sleep for 10 ms
        }
    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time ) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }

    private Image getImage(String filename){
        return Toolkit.getDefaultToolkit().getImage(filename);
    }

    //Graphics setup method
    private void setUpGraphics() {
        frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);
        canvas.addKeyListener(this);

        panel.add(canvas);  // adds the canvas to the panel.

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
    public void keyTyped(KeyEvent e) {
        //we won't use this
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        int key = e.getKeyCode();

        if (key == 38) {
            Skier.up = true;
        } else if (key == 37) {
            Skier.left = true;
        } else if (key == 40) {
            Skier.down = true;
        } else if (key == 39) {
            Skier.right = true;
        }


    }




    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println(e.getKeyCode());
        int key = e.getKeyCode();
        if (key == 38) {
            Skier.up = false;
        } else if (key == 37) {
            Skier.left = false;
        } else if (key == 40) {
            Skier.down = false;
        } else if (key == 39) {
            Skier.right = false;
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Mouse Clicked");
        int mouseX = e.getX();
        int mouseY = e.getY();
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



//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
}


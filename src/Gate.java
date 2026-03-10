//Solomon M. Fleiss
import java.awt.*;

public class Gate {
//defines variables for the skier and starting values of the gates
    int leftX;
    int rightX;
    int y;
    int poleWidth = 20;
    int poleHeight = 120;

    boolean passed = false;// implements the passed variale used later to check if the skier sucseeded

    //allows the gate to be generated anywhere across the x axis
    public Gate(int leftX, int rightX, int y) {
        this.leftX = leftX;
        this.rightX = rightX;
        this.y = y;
    }
// speed at which the gate moves in this instance it will be defined in the main class
    public void moveDown(int speed) {
        y += speed;
    }
// draws both red left and blue right gate and draws each gate
    public void draw(Graphics2D g) {
        g.setColor(Color.RED);
        g.fillRect(leftX, y, poleWidth, poleHeight);
        g.setColor(Color.BLUE);
        g.fillRect(rightX, y, poleWidth, poleHeight);
        g.setColor(Color.BLACK);
        g.drawRect(leftX + poleWidth, y + 30, rightX - (leftX + poleWidth), 40);
    }
}
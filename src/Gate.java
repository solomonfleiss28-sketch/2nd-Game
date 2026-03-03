import java.awt.*;
public class Gate {

    int leftX;
    int rightX;
    int y;

    int poleWidth = 20;
    int poleHeight = 120;

    boolean passed = false;

    public Gate(int leftX, int rightX, int y) {
        this.leftX = leftX;
        this.rightX = rightX;
        this.y = y;
    }

    public void scroll(int speed) {
        y += speed;
    }

    public void draw(Graphics2D g) {

        g.setColor(Color.RED);
        g.fillRect(leftX, y, poleWidth, poleHeight);

        g.setColor(Color.BLUE);
        g.fillRect(rightX, y, poleWidth, poleHeight);

        g.setColor(Color.BLACK);
        g.drawRect(leftX + poleWidth, y + 30,
                rightX - (leftX + poleWidth),
                40);
    }
}
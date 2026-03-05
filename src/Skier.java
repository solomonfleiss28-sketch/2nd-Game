import java.awt.*;
public class Skier {

    public int x;
    public int y;
    public int width;
    public int height;

    public static boolean up;
    public static boolean down;
    public static boolean left;
    public static boolean right;
    // above gives the skiers variables

    public Skier(int startX, int startY){
        x = startX;
        y = startY;
        width = 80;
        height = 80;
        // starting location of skier
    }

    public void move(){

        if(up)   {y -= 5; }
        if(down) {y += 5;}
        if(left) {x -= 5;}
        if(right) {x += 5;}
// above details the rate of change the skier can progress at
    }
}
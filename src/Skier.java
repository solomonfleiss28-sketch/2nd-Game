//Solomon M. Fleiss
public class Skier {
    // above gives the skiers variables and allows it to move across the screen

    public int x;
    public int y;
    public int width;
    public int height;

    public static boolean up;
    public static boolean down;
    public static boolean left;
    public static boolean right;

// gives starting location and size of the skier
    public Skier(int startX, int startY){
        x = startX;
        y = startY;
        width = 80;
        height = 80;
    }

    // details the speed the skier can move when moving in each direction
    public void move(){

        if(up)   {y -= 5; }
        if(down) {y += 5;}
        if(left) {x -= 5;}
        if(right) {x += 5;}
    }
}
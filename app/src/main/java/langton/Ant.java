package langton;

enum Direction {
    NORTH(0, -1),
    SOUTH(0, 1),
    WEST(-1, 0),
    EAST(1, 0);

    public final int changeX, changeY;

    private Direction(int x, int y) {
        this.changeX = x;
        this.changeY = y;
    }
}

public class Ant {
    public int x, y;
    private byte r, g, b;
    private Direction dir;

    public Ant() {
        dir = Direction.NORTH;
    }    

    public Ant(int x, int y, Direction dir) {
        this.dir = dir;
    }

    public void setColot(byte r, byte g, byte b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Direction getDirection() {
        return dir;
    }

    public void move() {
        x += dir.changeX;
        y += dir.changeY;
    }

    public void rotate(boolean rotation) {
        // TODO fix this stupid shit 
        if(rotation) {
            switch(dir) {
                case NORTH: 
                    dir = Direction.EAST;
                    break;
                case EAST:
                    dir = Direction.SOUTH;
                    break;
                case SOUTH:
                    dir = Direction.WEST;
                    break;
                case WEST:
                    dir = Direction.NORTH;
                    break;
           }
       } else {
            switch(dir) {
                case NORTH: 
                    dir = Direction.WEST;
                    break;
                case EAST:
                    dir = Direction.NORTH;
                    break;
                case SOUTH:
                    dir = Direction.EAST;
                    break;
                case WEST:
                    dir = Direction.SOUTH;
                    break;
           }
       }
    }
}

package langton;

import simulation.BitMap;

public class LangtonBoard implements BitMap{

    private int width, height;
    private boolean[][] bitmap;
    private Ant ant;

    public LangtonBoard(int w, int h) {
        width = w;
        height = h;
        ant = new Ant();
    }
    @Override
    public void update() {
        boolean temp = bitmap[ant.x][ant.y];
        ant.rotate(temp); 
        bitmap[ant.x][ant.y] = !temp;
        ant.move();
    }

    @Override
    public void setEntity(int x, int y, boolean value) {
        bitmap[x][y] = value; 
    }

    @Override
    public boolean[][] getBitMap() {
        return bitmap;
    }

    @Override
    public void init() {
        ant.x = width / 2;
        ant.y = height / 2;
        bitmap = new boolean[width][height]; 
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

}
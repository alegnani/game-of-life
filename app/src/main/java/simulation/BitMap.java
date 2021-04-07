package simulation;

public interface BitMap {
    public void update();
    public void setEntity(int x, int y, boolean value);
    public boolean[][] getBitMap();
    public void init();
    public int getWidth();
    public int getHeight();
}

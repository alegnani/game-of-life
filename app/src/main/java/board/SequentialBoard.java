package board;

public class SequentialBoard extends Board{

    public SequentialBoard(int width, int height) {
        super(width, height);
    }

    @Override
    public void update() {
        for(int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                computeEntity(x, y);
            }
        }
        swapBuffers();
    }
}

package guiltspawn.engine;

public class Camera {

    private float x, y;
    private final GuiltSpawn game;

    public Camera(float x, float y, GuiltSpawn game) {
        this.x = x;
        this.y = y;
        this.game = game;
    }

    public void tick() {
        if (game.getHandler().getPlayer() != null) {
            x = -game.getHandler().getPlayer().getX() + game.getWidth() / 2;
            y = -game.getHandler().getPlayer().getY() + game.getHeight() / 2;
            fixBounds();
        }
    }

    public void fixBounds() {
        Handler h = game.getHandler();
        int xmax = -(h.getMinX());
        int xmin = -(h.getMaxX());

        if (x < xmin - 100 + 1920) {
            x = xmin - 100 + 1920;
        }

        if (x > xmax + 100) {
            x = xmax + 100;
        }

    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }    

}
